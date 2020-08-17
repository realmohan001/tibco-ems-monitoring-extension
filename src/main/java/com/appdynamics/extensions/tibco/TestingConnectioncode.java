package com.appdynamics.extensions.tibco;

import java.util.Hashtable;

import javax.naming.Context;

import com.tibco.tibjms.admin.ServerInfo;
import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;
import com.tibco.tibjms.naming.TibjmsContext;

public class TestingConnectioncode {
	
	public static void main(String args[])
	{
		String user="viewall";
		String plainPassword="";
		
		String emsURL="ssl://w11dcltemshq001.swacorp.com:21101,ssl://sdcdcltemshq001.swacorp.com:21101";
		String displayName = "21101";
		
		int myState = 0;
		
		  Hashtable sslParams = new Hashtable();
		  
		  sslParams.put(Context.SECURITY_PRINCIPAL, user);
          sslParams.put(Context.SECURITY_CREDENTIALS, (plainPassword==null || plainPassword.trim().length()==0) ? plainPassword : "");
          
          sslParams.put(TibjmsContext.SECURITY_PROTOCOL, "ssl");
          sslParams.put(TibjmsContext.SSL_ENABLE_VERIFY_HOST, new Boolean("false"));
          sslParams.put(TibjmsContext.SSL_ENABLE_VERIFY_HOST_NAME, new Boolean("false"));
          
          
          
          
          TibjmsAdmin tibjmsAdmin = null;
          try {
              System.out.println(String.format("Connecting to %s as %s", emsURL, user));
              tibjmsAdmin = new TibjmsAdmin(emsURL, user, plainPassword, sslParams);
              
              
              
              myState = tibjmsAdmin.getInfo().getState();
              
              
              if (myState != ServerInfo.SERVER_ACTIVE)
              {
            	  System.out.println("server not active");
              }
              else
              {
            	  System.out.println("server is active");
              }
              
              

          } catch (TibjmsAdminException e) {
        	  System.out.println("Error while connecting to Tibco EMS server [ " + displayName + " ] "+e);
          } catch (Exception e) {
        	  System.out.println("Unknown Error while connecting to Tibco EMS server [ " + displayName + " ] " + e);
          }
	}
}
