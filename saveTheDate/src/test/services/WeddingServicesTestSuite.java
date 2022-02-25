package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.services.*;

public class WeddingServiceTestSuite {
	
	User user = new User(11, "valid", "valid", "valid", "valid", "valid", "valid", "valid");
	ServiceType venueType = new ServiceType(1, "venue");
	ServiceType catererType = new ServiceType(2, "venue");
	ServiceType floristType = new ServiceType(3, "venue");
	ServiceType photoType = new ServiceType(4, "venue");
	ServiceType musicType = new ServiceType(5, "venue");
	Service venue = new Service(1, "valid", 10);
	Service venue2 = new Service(2, "valid", 10);
	Service venue3 = new Service(11, "valid", 10, venueType);
	Service caterer = new Service(3, "valid", 10);
	Service caterer2 = new Service(4, "valid", 10);
	Service caterer3 = new Service(12, "valid", 10, catererType);
	Service florist = new Service(5, "valid", 10);
	Service florist2 = new Service(6, "valid", 10);
	Service florist3 = new Service(13, "valid", 10, floristType);
	Service photo = new Service(7, "valid", 10);
	Service photo2 = new Service(8, "valid", 10);
	Service photo3 = new Service(14, "valid", 10, photoType);
	Service music = new Service(9, "valid", 10);
	Service music2 = new Service(10, "valid", 10);
	Service music3 = new Service(15, "valid", 10, musicType);
	
    
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
	public void test_isWeddingValid() {
		
		// Arrange
		Wedding validWedding = new Wedding(1, user, "08/23/1996", 1000, venue, caterer, florist, photo, music);
		Wedding validWedding2 = new Wedding(1, user, "080/23/1996", 1000, venue, caterer, florist, photo, music);
		Wedding validWedding3 = new Wedding(1, user, "08/23/1996", 0, venue, caterer, florist, photo, music);
		
		// Act
		boolean actualResult = sut.isWeddingValid(validWedding);
		boolean actualResult2 = sut.isWeddingValid(validWedding2);
		boolean actualResult3 = sut.isWeddingValid(validWedding3);
		
		// Assert
		Assert.assertTrue(actualResult);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
		
	}
	
	@Test
	public void test_addWedding() {
		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		
		Assert.assertTrue(sut.addWedding(wedding));
		
	}

	@Test
	public void test_getAllWeddings(){

		Wedding wedding1 = new Wedding();
		Wedding wedding2 = new Wedding();
		Wedding wedding3 = new Wedding();

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(wedding1);
		testList.add(wedding2);
		testList.add(wedding3);

		List<Wedding> resultList = sut.getAllWeddings();

		Assert.assertNotNull(resultList);
	}

	@Test 
	public void test_getWeddingById(){

		Wedding validWedding = new Wedding(1, user, "08/23/1996", 1000, venue, caterer, florist, photo, music);
		
		when(mockWeddingDAO.getWeddingById(1)).thenReturn(validWedding);

		Wedding resultWedding = sut.getWeddingById(1);

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

		Wedding validWedding = new Wedding(1, user, "08/23/1996", 1000, venue, caterer, florist, photo, music);

		boolean testResult = sut.isWeddingUnderBudget(validWedding);
		

		Assert.assertTrue(testResult);
	
	}

	@Test 
	public void test_isWeddingVenueAvailable(){

		Wedding testWedding = new Wedding(1, user, "08/23/1996", 60, venue, caterer, florist, photo, music);
		Wedding testWedding2 = new Wedding(2, user, "08/23/1996", 60, venue2, caterer, florist, photo, music);
		Wedding testWedding3 = new Wedding(3, user, "08/23/1996", 60, venue, caterer, florist, photo, music);

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(testWedding);
		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding2);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding3);


		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);

	}

	@Test 
	public void test_isWeddingCatererAvailable(){

		Wedding testWedding = new Wedding(1, user, "08/23/1996", 60, venue, caterer, florist, photo, music);
		Wedding testWedding2 = new Wedding(2, user, "08/23/1996", 60, venue2, caterer2, florist, photo, music);
		Wedding testWedding3 = new Wedding(3, user, "08/23/1996", 60, venue, caterer, florist, photo, music);

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(testWedding);
		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding2);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding3);


		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingFloristAvailable(){

		Wedding testWedding = new Wedding(1, user, "08/23/1996", 60, venue, caterer, florist, photo, music);
		Wedding testWedding2 = new Wedding(2, user, "08/23/1996", 60, venue2, caterer, florist2, photo, music);
		Wedding testWedding3 = new Wedding(3, user, "08/23/1996", 60, venue, caterer, florist, photo, music);

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(testWedding);
		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding2);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding3);


		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	
	@Test 
	public void test_isWeddingPhotographerAvailable(){

		Wedding testWedding = new Wedding(1, user, "08/23/1996", 60, venue, caterer, florist, photo, music);
		Wedding testWedding2 = new Wedding(2, user, "08/23/1996", 60, venue2, caterer, florist, photo2, music);
		Wedding testWedding3 = new Wedding(3, user, "08/23/1996", 60, venue, caterer, florist, photo, music);

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(testWedding);
		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding2);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding3);


		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	
	@Test 
	public void test_isWeddingMusicianAvailable(){

		Wedding testWedding = new Wedding(1, user, "08/23/1996", 60, venue, caterer, florist, photo, music);
		Wedding testWedding2 = new Wedding(2, user, "08/23/1996", 60, venue2, caterer, florist, photo, music2);
		Wedding testWedding3 = new Wedding(3, user, "08/23/1996", 60, venue, caterer, florist, photo, music);

		List<Wedding> testList = new ArrayList<Wedding>();
		testList.add(testWedding);
		when(mockWeddingDAO.getAllWeddings()).thenReturn(testList);

		boolean testResult = sut.isWeddingVenueAvailable(testWedding2);
		boolean testResult2 = sut.isWeddingVenueAvailable(testWedding3);


		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingVenueAVenue(){

		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		Role role1 = new Role(2, "valid");
		User user2 = new User(1, role1, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding2 = new Wedding(2, user2, "08/27/1996", 1000, caterer3, caterer3, florist3, photo3, music3);

		boolean testResult = sut.isWeddingVenueAVenue(wedding);
		boolean testResult2 = sut.isWeddingVenueAVenue(wedding2);
	
		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
		
	}

	@Test 
	public void test_isWeddingCatererACaterer(){

		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		Role role1 = new Role(2, "valid");
		User user2 = new User(1, role1, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding2 = new Wedding(2, user2, "08/27/1996", 1000, caterer3, venue3, florist3, photo3, music3);

		boolean testResult = sut.isWeddingCatererACaterer(wedding);
		boolean testResult2 = sut.isWeddingCatererACaterer(wedding2);
	
		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingFloristAFlorist(){

		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		Role role1 = new Role(2, "valid");
		User user2 = new User(1, role1, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding2 = new Wedding(2, user2, "08/27/1996", 1000, caterer3, caterer3, venue3, photo3, music3);

		boolean testResult = sut.isWeddingFloristAFlorist(wedding);
		boolean testResult2 = sut.isWeddingFloristAFlorist(wedding2);
	
		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingPhotographerAPhotographer(){

		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		Role role1 = new Role(2, "valid");
		User user2 = new User(1, role1, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding2 = new Wedding(2, user2, "08/27/1996", 1000, caterer3, caterer3, venue3, venue3, music3);

		boolean testResult = sut.isWeddingPhotographerAPhotographer(wedding);
		boolean testResult2 = sut.isWeddingPhotographerAPhotographer(wedding2);
	
		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}

	@Test 
	public void test_isWeddingMucicianAMucician(){

		Role role = new Role(2, "valid");
		User user = new User(1, role, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding = new Wedding(1, user, "08/27/1996", 1000, venue3, caterer3, florist3, photo3, music3);
		
		Role role1 = new Role(2, "valid");
		User user2 = new User(1, role1, "validUname", "validUname", "validEmail", "validEmail", "valid", "valid", "valid");
		Wedding wedding2 = new Wedding(2, user2, "08/27/1996", 1000, caterer3, caterer3, venue3, venue3, venue3);

		boolean testResult = sut.isWeddingMucicianAMucician(wedding);
		boolean testResult2 = sut.isWeddingMucicianAMucician(wedding2);
	
		Assert.assertTrue(testResult);
		Assert.assertFalse(testResult2);
	}
}


