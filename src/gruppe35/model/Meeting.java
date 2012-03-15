package gruppe35.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;

import no.ntnu.fp.model.Person;

public class Meeting {
	private String message;
	private Date date;
	private String startTime;
	private String endTime;
	private ArrayList<Person> participants;
	private String room;
	private PropertyChangeSupport pcs;
	
	public Meeting(String message, Date date, String startTime, String endTime, ArrayList<Person> participants, String room) {
		this.message = message;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.participants = participants;
		this.room = room;
		this.pcs = new PropertyChangeSupport(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		String oldMessage = this.message;
		this.message = message;
		pcs.firePropertyChange("message", oldMessage, message);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		Date oldDate = this.date;
		this.date = date;
		pcs.firePropertyChange("date", oldDate, date);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		String oldStartTime =  this.startTime;
		this.startTime = startTime;
		pcs.firePropertyChange("startTime", oldStartTime, startTime);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		String oldEndTime = this.endTime;
		this.endTime = endTime;
		pcs.firePropertyChange("endTime", oldEndTime, endTime);
	}

	public ArrayList<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Person> participants) {
		ArrayList<Person> oldParticipants = participants;
		this.participants = participants;
		pcs.firePropertyChange("participants", oldParticipants, participants);
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		String oldRoom = room;
		this.room = room;
		pcs.firePropertyChange("room", oldRoom, room);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
