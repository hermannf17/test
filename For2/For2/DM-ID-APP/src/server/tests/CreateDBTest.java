package server.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.DBinit;

public class CreateDBTest {

	@Test
	public void test() {
		int test = DBinit.createDB();
		assertEquals(1,test);
		
	}

}
