package server.tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import server.DBconnection;

public class ConnectionDBTest {

	@Test
	public void test() {
		String test = DBconnection.connect(null).toString();
		assertNotNull(test);
	}

}
