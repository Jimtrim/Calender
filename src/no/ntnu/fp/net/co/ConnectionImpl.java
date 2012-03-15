/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebj�rn Birkeland and Stein Jakob Nordb�
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {
	
	public static final int triesleft = 10;

    /** Keeps track of the used ports for each server port. */
    private static Map<Integer, Boolean> usedPorts = Collections.synchronizedMap(new HashMap<Integer, Boolean>());

    /**
     * Initialise initial sequence number and setup state machine.
     * 
     * @param myPort
     *            - the local port to associate with this connection
     */
    public ConnectionImpl(int myPort) {
        super();//Kaller p� AbstractConnection sin constructor
        try{
        	/**
        	 *Henter adressen til maskinen ved � finne localhost connection 
        	 *Og setter portnummeret
        	 */
        	this.myAddress = InetAddress.getByName("localhost").getHostAddress();
        }catch (Exception e) {
			e.printStackTrace();//Printer ut feilmelding, hvis man ikke finner localhost
		}
        this.myPort = myPort;
    }

    private String getIPv4Address() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Establish a connection to a remote location.
     * 
     * @param remoteAddress
     *            - the remote IP-address to connect to
     * @param remotePort
     *            - the remote portnumber to connect to
     * @throws IOException
     *             If there's an I/O error.
     * @throws java.net.SocketTimeoutException
     *             If timeout expires before connection is completed.
     * @see Connection#connect(InetAddress, int)
     */
   public void connect(InetAddress remoteAddress, int remotePort) throws IOException,
            SocketTimeoutException {
    	//Sjekker om tilstanden er lukket
    	if (state != State.CLOSED)
    		throw new IllegalStateException("Må være i lukket tilstand.");
    	
    	//Setter adresse og port
    	this.remoteAddress = remoteAddress.getHostAddress();
        this.remotePort = remotePort;
        //Konstruerer pakke
        KtnDatagram syn = constructInternalPacket(Flag.SYN);
        try {
        	//Send pakke
			simplySendPacket(syn);
		} catch (ClException e) {
			// Not connected to internet, wait and try again
			try { Thread.sleep(5000); } catch (InterruptedException ie) {  }
			
			this.connect(remoteAddress, remotePort);
			return;
		}
		//Forandrer tilstand til sendt
		state = State.SYN_SENT;
		
		//Mottar ack
        KtnDatagram synack = receiveAck();
        if (!isValid(synack))
        	throw new IOException("Ikke gyldig synack mottat.");
        //Setter fjernport
        this.remotePort = synack.getSrc_port();
        
        lastValidPacketReceived = synack;
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {  }
        //Sender ack
        sendAck(synack, false);
        //Setter tilstand til tilkoblet
        state = State.ESTABLISHED;
    }

    /**
     * Listen for, and accept, incoming connections.
     * 
     * @return A new ConnectionImpl-object representing the new connection.
     * @see Connection#accept()
     */
    public Connection accept() throws IOException, SocketTimeoutException {
        //throw new NotImplementedException();
    	
    	//Check state
       if(state == state.CLOSED){
    	   throw new IllegalStateException("Have to be in closed state to get connection");
       }
       
       state = state.LISTEN;
       
       //Receive SYN
       KtnDatagram syn = null;
       
       while(!(syn.getFlag() == Flag.SYN)){
    	   try{
    	   
    	   syn = receivePacket(true);
    	   }
       
    	   catch (Exception ex) {
    	   
    	   }
       }
       
       //Create new connection
       ConnectionImpl connection = new ConnectionImpl(getPortnumber());
       connection.remoteAddress = syn.getSrc_addr();
       connection.remotePort = syn.getSrc_port();
       
       connection.state = state.SYN_RCVD;
       
       
       
       //Send SYN_ACK and recevive ACK
       KtnDatagram ack = null;
       int tl = triesleft;
       while(ack.getFlag() != Flag.ACK && tl >0){
    	   tl--;
    	   connection.sendAck(syn, true);
    	   ack = connection.receiveAck();
    	   
    	   
       }
       
       state = state.CLOSED;
       
      
       
       
       //Finalize connection
       if(!isValid(ack)){
    	   throw new IOException("Unable to connect");
       }
       connection.lastValidPacketReceived = ack;
       connection.state = state.ESTABLISHED;
       return connection;
       
       
       
         
    }

    public int getPortnumber(){
    	int portnr = -1;
    	while(portnr == -1 || usedPorts.containsKey(portnr)){
    		portnr = (int)(Math.random()*10000+1024);
    	}
    	usedPorts.put(portnr, true);
    	return portnr;
    }
    
    /**
     * Send a message from the application.
     * 
     * @param msg
     *            - the String to be sent.
     * @throws ConnectException
     *             If no connection exists.
     * @throws IOException
     *             If no ACK was received.
     * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
     * @see no.ntnu.fp.net.co.Connection#send(String)
     */
    public void send(String msg) throws ConnectException, IOException {
        if(this.state != State.ESTABLISHED)
        	throw new IllegalStateException("System is not connected");
        
        KtnDatagram packet = constructDataPacket(msg);
        KtnDatagram ack = null;
        int tl = triesleft;
        
        while((ack.getFlag() != Flag.ACK || !isValid(ack) || ack.getAck() < packet.getSeq_nr()) && tl > 0){
        	ack = sendDataPacketWithRetransmit(packet);
        	tl--;
        }

    	if (ack.getSeq_nr() > lastValidPacketReceived.getSeq_nr())
    		lastValidPacketReceived = ack;
    	lastDataPacketSent = packet;
    }

    /**
     * Wait for incoming data.
     * 
     * @return The received data's payload as a String.
     * @see Connection#receive()
     * @see AbstractConnection#receivePacket(boolean)
     * @see AbstractConnection#sendAck(KtnDatagram, boolean)
     */
    public String receive() throws ConnectException, IOException {
        throw new NotImplementedException();
    }

    /**
     * Close the connection.
     * 
     * @see Connection#close()
     */
    public void close() throws IOException {
        throw new NotImplementedException();
    }

    /**
     * Test a packet for transmission errors. This function should only called
     * with data or ACK packets in the ESTABLISHED state.
     * 
     * @param packet
     *            Packet to test.
     * @return true if packet is free of errors, false otherwise.
     */
    protected boolean isValid(KtnDatagram packet) {
        return (packet.getChecksum() == packet.calculateChecksum() && packet != null);
    }
}
