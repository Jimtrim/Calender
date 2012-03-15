package gruppe35.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MeetingRoom {

	private int roomNumber;
	private int capacity;
	private String description;
	private boolean roomOccupied;
	private PropertyChangeSupport pcs;
	
	public MeetingRoom(int roomNumber, int capacity, String description){
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.description = description;
		this.roomOccupied = false;
		pcs = new PropertyChangeSupport(this);
		
	}

	public boolean isRoomOccupied() {
		return roomOccupied;
	}

	public void setRoomOccupied(boolean roomOccupied) {
		this.roomOccupied = roomOccupied;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String oldDescription = this.description;
		this.description = description;
		pcs.firePropertyChange("description", oldDescription, description);
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		int oldRoomNumber = this.roomNumber;
		this.roomNumber = roomNumber;
		pcs.firePropertyChange("roomNumber", oldRoomNumber, roomNumber);
	}
	
	public int getCapacity() {
		return capacity;
	}	

	public void setCapacity(int capacity) {
		int oldCapacity = this.capacity;
		this.capacity = capacity;
		pcs.firePropertyChange("capacity", oldCapacity, capacity);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
