package client;
import java.io.*;
import java.net.*;

public class PingServer {
	
	public static String sendPingRequest(String ipAdress) throws IOException{
		
		try {
			InetAddress test = InetAddress.getByName(ipAdress);
			if(test.isReachable(5000)) {
				return "Host is rechable";
			}else {
				return "Can't reach to this host";
			}
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		
		
	}
	

}
