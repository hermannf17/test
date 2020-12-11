package client.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import client.PingServer;

public class PingServerTest {

	@Test
	public void test() throws IOException{
		String ipAdress = "127.0.0.1";
		String test = PingServer.sendPingRequest(ipAdress);
		assertEquals("Host is rechable", test);
	}

}
