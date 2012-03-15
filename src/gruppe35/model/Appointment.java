package gruppe35.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

public class Appointment {
	private String message;
	private Date date;
	private String startTime;
	private String endTime;
	private String place;
	private PropertyChangeSupport pcs;
	
	public Appointment(String message, Date date, String startTime, String endTime, String place) {
		this.message = message;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.place = place;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		String oldPlace = place;
		this.place = place;
		pcs.firePropertyChange("place", oldPlace, place);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
