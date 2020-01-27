package net.etfbl.ip.projektni.beans;

import java.util.ArrayList;

import net.etfbl.ip.projektni.dao.FajlDAO;
import net.etfbl.ip.projektni.dto.Fajl;



public class FajlBean {
	private Fajl fajl = new Fajl();

	public Fajl getFajl() {
		return fajl;
	}

	public void setFajl(Fajl fajl) {
		this.fajl = fajl;
	}

	public boolean insert(Fajl file) {
		return FajlDAO.insert(file);
	}

	public ArrayList<Fajl> getAll() {
		return FajlDAO.selectAll();
	}
}
