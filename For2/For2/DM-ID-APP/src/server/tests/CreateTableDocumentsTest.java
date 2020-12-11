package server.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.DBinit;

public class CreateTableDocumentsTest {

	@Test
	public void test() {
		int test = DBinit.createTableDocuments();
		assertEquals(1,test);
	}

}
