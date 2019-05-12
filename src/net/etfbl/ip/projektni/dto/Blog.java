package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Blog implements Serializable,Comparable<Blog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int userID;
	private String tema;
	private ArrayList<Komentar> komentari;
	private Date vrijemeKreiranja;

	public Blog() {
		this.komentari=new ArrayList<>();
	}

	public Blog(String id, int userID, String tema, ArrayList<Komentar> komentari, Date vrijemeKreiranja) {
		super();
		this.id = id;
		this.userID = userID;
		this.tema = tema;
		this.komentari = komentari;
		this.vrijemeKreiranja = vrijemeKreiranja;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(ArrayList<Komentar> komentari) {
		this.komentari = komentari;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getVrijemeKreiranja() {
		return vrijemeKreiranja;
	}

	public void setVrijemeKreiranja(Date vrijemeKreiranja) {
		this.vrijemeKreiranja = vrijemeKreiranja;
	}

	public String getStringVijemeKreiranja() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String vk=formatter.format(vrijemeKreiranja);
		return vk;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", userID=" + userID + ", tema=" + tema + ", komentari=" + komentari + "]";
	}
	
	@Override
	public int compareTo(Blog o) {
		// TODO Auto-generated method stub
		return o.vrijemeKreiranja.compareTo(this.vrijemeKreiranja);
	}

}
