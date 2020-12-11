package server.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.DBinit;

public class InsertDBTest {

	@Test
	public void test() {
		int test = DBinit.insert();
		assertEquals(1, test);
	}

}
