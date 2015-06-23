package com.rama.bean;

public class ContactValue {
	private String name;
	private String photo;
	private String details;
	
	
	
	public ContactValue(String name, String details) {
		this.name = name;
		this.details = details;
	}
	public ContactValue(String name, String photo, String details) {
		this.name = name;
		this.photo = photo;
		this.details = details;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
}
