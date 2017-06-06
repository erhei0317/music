package lha.music.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.struts2.ServletActionContext;

@ServerEndpoint(value = "/musicWebsocket",configurator=GetHttpSessionConfigurator.class)
public class MusicWebSocket {
	    private Session session;  
	    private HttpSession httpSession; 
	    private static Map<String,Session> sessions=new HashMap<String,Session>();
	    public static Map<String, Session> getSessions() {
			return sessions;
		}

		@OnOpen  
	    public void onOpen(Session session, EndpointConfig config) {  
	        this.session = session;  
	        httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	        String username=(String) httpSession.getAttribute("username");
	        if(username!=null){
	        	sessions.put(username, session);
	        }
	    }  
	  
	    @OnClose  
	    public void onClose(){  
	         
	    }  
	  
	    @OnMessage  
	    public void onMessage(String message, EndpointConfig config) throws IOException{  

	    	 
	    }  
	  
	    @OnError  
	    public void onError(Throwable t) throws Throwable {  
	      
	    }  
}
