package net.etfbl.ip.projektni.dto;

import java.io.Serializable;

public class Komentar implements Serializable {
	
	@Override
	public String toString() {
		return "Komentar [userID=" + userID + ", komentar=" + komentar + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userID;
	private String komentar;
	
	
	public Komentar(int userID, String komentar) {
		super();
		this.userID = userID;
		this.komentar = komentar;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
	
	
	

}
