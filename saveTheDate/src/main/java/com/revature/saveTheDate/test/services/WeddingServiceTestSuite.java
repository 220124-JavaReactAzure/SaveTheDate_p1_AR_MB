package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.services.*;

public class WeddingServiceTestSuite {
    
    WeddingServices sut;
	WeddingDAO mockWeddingDAO;
	ServiceServices dut;
	ServiceDAO mockServiceDAO;

    @Before
	public void testPrep() {
		mockWeddingDAO = mock(WeddingDAO.class);
		sut = new WeddingServices(mockWeddingDAO);
		mockServiceDAO = mock(ServiceDAO.class);
		dut = new ServiceServices(mockServiceDAO);
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
	
	
	@Test
	public void test_addWedding_returnsTrue() {
		// Arrange
		Wedding validWedding = new Wedding();
		when(mockWeddingDAO.addWedding(validWedding)).thenReturn(true);
	
		// Act
		boolean actualResult = sut.addWedding(validWedding);
		
		// Assert
		Assert.assertTrue(actualResult);
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

		Wedding testWedding = new Wedding(10, 1, "08/23/1996", 500, 1, 1, 1, 1, 1);
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

	@Test 
	public void test_isWeddingUnderBudget(){

		Wedding testWedding = new Wedding(1, 1, "08/23/1996", 60, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(1, 1, "08/23/1996", 30, 1, 2, 3, 4, 5);
		when(mockServiceDAO.getServiceById(1).getCost()).thenReturn(10);
		when(mockServiceDAO.getServiceById(2).getCost()).thenReturn(10);
		when(mockServiceDAO.getServiceById(3).getCost()).thenReturn(10);
		when(mockServiceDAO.getServiceById(4).getCost()).thenReturn(10);
		when(mockServiceDAO.getServiceById(5).getCost()).thenReturn(10);

		boolean testResult = sut.isWeddingUnderBudget(testWedding);
		boolean testResult2 = sut.isWeddingUnderBudget(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingVenueAvailable(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding3 = new Wedding(12, 3, "08/24/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding4 = new Wedding(13, 4, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding5 = new Wedding(14, 5, "08/25/1996", 500, 1, 2, 3, 4, 5);

		List<Wedding> testList;
		testList.add(testWedding);
		testList.add(testWedding2);
		testList.add(testWedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding4);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding5);


		Assert.assertTrue(testResult2);
		Assert.assertFalse(testResult);
	}

	@Test 
	public void test_isWeddingCatererAvailable(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding3 = new Wedding(12, 3, "08/24/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding4 = new Wedding(13, 4, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding5 = new Wedding(14, 5, "08/25/1996", 500, 1, 2, 3, 4, 5);

		List<Wedding> testList;
		testList.add(testWedding);
		testList.add(testWedding2);
		testList.add(testWedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingCatererAvailable(testWedding4);
		boolean testResult2 = sut.isWeddingCatererAvailable(testWedding5);


		Assert.assertTrue(testResult2);
		Assert.assertFalse(testResult);
	}

	@Test 
	public void test_isWeddingFloristAvailable(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding3 = new Wedding(12, 3, "08/24/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding4 = new Wedding(13, 4, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding5 = new Wedding(14, 5, "08/25/1996", 500, 1, 2, 3, 4, 5);

		List<Wedding> testList;
		testList.add(testWedding);
		testList.add(testWedding2);
		testList.add(testWedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingFloristAvailable(testWedding4);
		boolean testResult2 = sut.isWeddingFloristAvailable(testWedding5);


		Assert.assertTrue(testResult2);
		Assert.assertFalse(testResult);
	}

	
	@Test 
	public void test_isWeddingPhotographerAvailable(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding3 = new Wedding(12, 3, "08/24/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding4 = new Wedding(13, 4, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding5 = new Wedding(14, 5, "08/25/1996", 500, 1, 2, 3, 4, 5);

		List<Wedding> testList;
		testList.add(testWedding);
		testList.add(testWedding2);
		testList.add(testWedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingPhotographerAvailable(testWedding4);
		boolean testResult2 = sut.isWeddingPhotographerAvailable(testWedding5);


		Assert.assertTrue(testResult2);
		Assert.assertFalse(testResult);
	}

	
	@Test 
	public void test_isWeddingMusicianAvailable(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding3 = new Wedding(12, 3, "08/24/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding4 = new Wedding(13, 4, "08/22/1996", 500, 1, 2, 3, 4, 5);
		Wedding testWedding5 = new Wedding(14, 5, "08/25/1996", 500, 1, 2, 3, 4, 5);

		List<Wedding> testList;
		testList.add(testWedding);
		testList.add(testWedding2);
		testList.add(testWedding3);

		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingMusicianAvailable(testWedding4);
		boolean testResult2 = sut.isWeddingMusicianAvailable(testWedding5);


		Assert.assertTrue(testResult2);
		Assert.assertFalse(testResult);
	}

	@Test 
	public void test_isWeddingVenueAVenue(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 0, 2, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 0, 2, 3, 4, 5);

		Service testService = new Service(1, "name", 10, 1);
		Service testService2 = new Service(2, "name", 10, 2);

		testWedding.setVenueId(1);
		testWedding2.setVenueId(2);

		boolean testResult = sut.isWeddingVenueAVenue(testWedding);
		boolean testResult2 = sut.isWeddingVenueAVenue(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingCatererACaterer(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 0, 3, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 0, 3, 4, 5);

		Service testService = new Service(1, "name", 10, 2);
		Service testService2 = new Service(2, "name", 10, 3);

		testWedding.setCatererId(1);
		testWedding2.setCatererId(2);

		boolean testResult = sut.isWeddingCatererACaterer(testWedding);
		boolean testResult2 = sut.isWeddingCatererACaterer(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingFloristAFlorist(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 0, 4, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 0, 4, 5);

		Service testService = new Service(1, "name", 10, 3);
		Service testService2 = new Service(2, "name", 10, 4);

		testWedding.setVenueId(1);
		testWedding2.setVenueId(2);

		boolean testResult = sut.isWeddingFloristAFlorist(testWedding);
		boolean testResult2 = sut.isWeddingFloristAFlorist(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingPhotographerAPhotographer(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 0, 5);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 0, 5);

		Service testService = new Service(1, "name", 10, 4);
		Service testService2 = new Service(2, "name", 10, 5);

		testWedding.setPhotographerId(1);
		testWedding2.setPhotographerId(2);

		boolean testResult = sut.isWeddingPhotographerAPhotographer(testWedding);
		boolean testResult2 = sut.isWeddingPhotographerAPhotographer(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingMucicianAMucician(){

		Wedding testWedding = new Wedding(10, 1, "08/22/1996", 500, 1, 2, 3, 4, 0);
		Wedding testWedding2 = new Wedding(11, 2, "08/23/1996", 500, 1, 2, 3, 4, 0);

		Service testService = new Service(1, "name", 10, 5);
		Service testService2 = new Service(2, "name", 10, 4);

		testWedding.setVenueId(1);
		testWedding2.setVenueId(2);

		boolean testResult = sut.isWeddingMucicianAMucician(testWedding);
		boolean testResult2 = sut.isWeddingMucicianAMucician(testWedding2);

		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}



	
}
