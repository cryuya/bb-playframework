package controllers;

public class UserData {
	private int id;
	private String name;
	private String password;

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
}