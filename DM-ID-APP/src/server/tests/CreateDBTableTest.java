package server.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.DBinit;

public class CreateDBTableTest {

	@Test
	public void test() {
		int test = DBinit.createTable();
		assertEquals(1,test);
	}

}
