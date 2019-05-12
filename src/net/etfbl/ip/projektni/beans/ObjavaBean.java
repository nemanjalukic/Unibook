package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Objava;
import net.etfbl.ip.projektni.dto.ObjavaKorisnik;
import net.etfbl.ip.projektni.dto.User;

public class ObjavaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Objava objava= new Objava();
	
	public Objava getObjava() {
		return objava;
	}
	public void setObjava(Objava objava) {
		this.objava = objava;
	}
	
	public ArrayList<Objava> getObjave(User korisnik){
		return ObjavaDAO.getAllObjave(korisnik);
		
	}
	public void like(int id,int userID) {
		ObjavaDAO.updateLike(id,userID);
	}
	
	public void dislike(int id,int userID) {
		ObjavaDAO.updateDislike(id,userID);
	}
	
	public void unlike(int id,int userID) {
		ObjavaDAO.updateUnLike(id,userID);
	}
	
	public void undislike(int id,int userID) {
		ObjavaDAO.updateUnDislike(id,userID);
	}
	
	public int getLajkovanoOpcija(int id, int userID) {
		return ObjavaDAO.selectOpcijaLajkovano(id, userID);	
	}
	
	public void insertObjavaKorisnik(ObjavaKorisnik objavaKorisnik) {
		int id=ObjavaDAO.insert(new Objava());
		objavaKorisnik.setId(id);
		ObjavaDAO.insertObajvaKorisnik(objavaKorisnik);
	}

}
