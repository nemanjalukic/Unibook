package net.etfbl.ip.projektni.dto;

import java.io.Serializable;

public class Fajl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userID;
	private String opis;
	private String putanja;
	
	
	public Fajl() {
		super();
	}
	
	
	public Fajl(int userID, String opis, String putanja) {
		super();
		this.userID = userID;
		this.opis = opis;
		this.putanja = putanja;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public String getPutanja() {
		return putanja;
	}
	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}
	
	
	
	
	

}
