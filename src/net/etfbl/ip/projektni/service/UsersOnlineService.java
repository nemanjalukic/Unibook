package net.etfbl.ip.projektni.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.etfbl.ip.projektni.controller.SessionCounter;

@Path("/usersonline")
public class UsersOnlineService {
	
	public UsersOnlineService() {
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sviProizvodi() {
		return String.valueOf(SessionCounter.getActiveSessions());
	}
}
