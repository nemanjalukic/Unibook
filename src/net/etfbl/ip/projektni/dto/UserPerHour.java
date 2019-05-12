package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.util.Date;

public class UserPerHour implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Date time;
	private int id;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
