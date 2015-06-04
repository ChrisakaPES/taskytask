package edu.neumont.models;

public class User {

	private static int ID = 0;
	String userName,firstName,lastName,email;
	char[] password;
	long user_id;
	int tasksComplete, slatesComplete;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName,String password, String firstName,String lastName,String email) {
		this.user_id = ID;
		this.userName = userName;
		this.password = password.toCharArray();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
}
