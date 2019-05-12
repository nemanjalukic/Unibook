package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private String email;
	private String studijskiProgram;
	private int godinaStudija;
	private String slika;
	private String interesovaanje;
	private Fakultet fakultet;
	private int status;


	
	
	
	public User() {
		super();
	}
	
	
	public User(int id, String username, String password, String ime, String prezime, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
	}


	public User(int id, String username, String password, String ime, String prezime, String email,
			String studijskiProgram, int godinaStudija, String slika, String interesovaanje, Fakultet fakultet,
			int status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.studijskiProgram = studijskiProgram;
		this.godinaStudija = godinaStudija;
		this.slika = slika;
		this.interesovaanje = interesovaanje;
		this.fakultet = fakultet;
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStudijskiProgram() {
		return studijskiProgram;
	}
	public void setStudijskiProgram(String studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}
	public int getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getInteresovaanje() {
		return interesovaanje;
	}
	public void setInteresovaanje(String interesovaanje) {
		this.interesovaanje = interesovaanje;
	}
	public Fakultet getFakultet() {
		return fakultet;
	}
	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	
	

}
