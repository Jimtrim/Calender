package gruppe35.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Employee {

	private String name;
	private String email;
	private String password;
	private String telefonnr;
	private String address;
	private PropertyChangeSupport pcs;

	public Employee(String name, String email, String password){
		this.name = name;
		this.email = email;
		this.password = password;
		this.telefonnr = "";
		this.address = "";
		this.pcs = new PropertyChangeSupport(this);	
	}
	
	public Employee(String name, String email, String password, String telefonnr, String address){
		this.name = name;
		this.email = email;
		this.password = password;
		this.telefonnr = telefonnr;
		this.address = address;
		this.pcs = new PropertyChangeSupport(this);	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		pcs.firePropertyChange("name", oldName, name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String oldEmail = this.email;
		this.email = email;
		pcs.firePropertyChange("email", oldEmail, email);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		String oldPassword = this.password;
		this.password = password;
		pcs.firePropertyChange("password", oldPassword, password);
	}

	public String getTelefonnr() {
		return telefonnr;
	}

	public void setTelefonnr(String telefonnr) {
		String oldTelefonnr = this.telefonnr;
		this.telefonnr = telefonnr;
		pcs.firePropertyChange("telefonnr", oldTelefonnr, telefonnr);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		String oldAddress = this.address;
		this.address = address;
		pcs.firePropertyChange("address", oldAddress, address);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
