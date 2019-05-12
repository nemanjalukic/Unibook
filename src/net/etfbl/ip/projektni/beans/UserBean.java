package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dao.UserDAO;
import net.etfbl.ip.projektni.dto.Fakultet;
import net.etfbl.ip.projektni.dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private User profileOfUser=new User();
	private boolean isLoggedIn = false;

	public boolean login(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			System.out.println(user.getIme()+" "+user.getPassword());
			profileOfUser=user;
			isLoggedIn = true;
			logUser(user.getId());
			return true;
		}
		else if((user = UserDAO.selectByUsernameAndPasswordSimple(username, password)) != null){
			System.out.println(user.getIme()+" "+user.getPassword());
			profileOfUser=user;
			isLoggedIn = true;
			logUser(user.getId());
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		user = new User();
		isLoggedIn = false;
	}

	public User getUser() {
		return user;
	}

	public boolean isUserNameAllowed(String username) {
		return UserDAO.isUserNameUsed(username);
	}
	
	public boolean isEmailAllowed(String email) {
		return UserDAO.isEmailUsed(email);
	}
	
	public boolean add(User user) {
		return UserDAO.insert(user);
	}
	
	public boolean logUser(int  idUser) {
		return UserDAO.logUser(idUser);
	}
	
	public boolean update(User user) {
		return UserDAO.updateUser(user);
	}
	
	public ArrayList<User> getFreinds(int id){
		return UserDAO.getFreinds(id);
	}
	public ArrayList<User> getUnconnectedUsers(int id){
		return UserDAO.getUnconnectedUsers(id);
	}
	
	public User getByID(int id) {
		
		return UserDAO.selectById(id);
	}
	
	public ArrayList<User> getRequests(){
		
		return UserDAO.getRequests(user.getId());
		
	}

	public User getProfileOfUser() {
		return profileOfUser;
	}

	public void setProfileOfUser(User profileOfUser) {
		this.profileOfUser = profileOfUser;
	}
	
	public boolean isFreind() {
		return getFreinds(user.getId()).contains(profileOfUser);
		
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	

}
