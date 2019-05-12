package net.etfbl.ip.projektni.controller;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionAttributeListener {
	 
    private static int activeSessions = 0;
 
   /* @Override
	public void sessionCreated(HttpSessionEvent se) {
    
    	synchronized (this) {
    		activeSessions++;
    		  }
        System.out.println(activeSessions);
	}*/


	/*@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(activeSessions > 0)
			synchronized (this) {
				activeSessions--;
			}
		System.out.println("DIS"+activeSessions);
	}*/


	public static int getActiveSessions() {
        return activeSessions;
    }

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String attributeName = se.getName();
		if(attributeName.equals("userBean")){
			synchronized (this) {
	    		activeSessions++;
	    		  }
	        System.out.println("ADD"+activeSessions);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String attributeName = se.getName();
		if(attributeName.equals("userBean")){
			synchronized (this) {
	    		activeSessions--;
	    		  }
	        System.out.println(activeSessions);
		}
	}
	
}