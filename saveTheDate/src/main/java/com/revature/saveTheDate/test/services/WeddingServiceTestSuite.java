package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.services.*;

public class WeddingServiceTestSuite {
    
    WeddingServices sut;
	WeddingDAO mockWeddingDAO;

    @Before
	public void testPrep() {
		mockWeddingDAO = mock(WeddingDAO.class);
		sut = new WeddingServices(mockWeddingDAO);
	}

	@Test
	public void test_isWeddingValid_returnsTrue_givenValidWedding() {
		
		// Arrange
		Wedding validWedding = new Wedding(1, 1, "08/23/1996", 500, 1, 1, 1, 1, 1);
		
		// Act
		boolean actualResult = sut.isWeddingValid(validWedding);
		
		// Assert
		Assert.assertTrue(actualResult);
		
	}
	
	@Test
	public void test_isWeddingValid_returnsFalse_givenInvalidWedding() {
		
		// Arrange
		Wedding invalidWedding1 = new Wedding(1, 1, "080/23/1996", 400, 1, 1, 1, 1, 1);
		Wedding invalidWedding2 = new Wedding(1, 1, "08/23/1996", 0, 1, 1, 1, 1, 1);
			
		//Act
		boolean actualResult1 = sut.isWeddingValid(invalidWedding1);
		boolean actualResult2 = sut.isWeddingValid(invalidWedding2);
	
		//Assert - everything you assert must pass the condition
		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);		
	}
	
	//TODO: Figure out implementation. CHARLES YOU DINGLEBERRY MOCK IT!!!!!!!
	@Test
	public void test_addWedding_returnsTrue() {
		// Arrange
		Wedding validWedding = new Wedding();
		when(mockWeddingDAO.addWedding(validWedding)).thenReturn(true);
	
		// Act
		boolean actualResult = sut.addWedding(validWedding);
		
		// Assert
		Assert.assertNotNull(actualResult);
	}

	@Test
	public void test_getAllWeddings(){

		Wedding wedding1 = new Wedding();
		Wedding wedding2 = new Wedding();
		Wedding wedding3 = new Wedding();

		List<Wedding> testList;
		testList.add(wedding1);
		testList.add(wedding2);
		testList.add(wedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		List<Wedding> resultList = sut.getAllWeddings();

		Assert.assertNotNull(resultList);
	}

	@Test 
	public void test_getWeddingById(){

		Wedding testWedding = new Wedding();
		when(mockWeddingDAO.getWeddingById(10)).thenReturn(testWedding);

		Wedding resultWedding = sut.getWeddingById(10);

		Assert.assertNotNull(resultWedding);
	}

	// @Test 
	// public void test_updateWeddingWithSessionMethod(){

	// 	Wedding testWedding = new Wedding();
	// 
	// }

	// @Test 
	// public void test_deleteWeddingById(){

	// 	Wedding testWedding = new Wedding();
	// }



	
}
