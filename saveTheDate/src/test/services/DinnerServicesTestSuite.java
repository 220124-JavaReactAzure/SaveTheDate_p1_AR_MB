package com.revature.saveTheDate.test.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.DinnerDAO;
import com.revature.saveTheDate.models.Dinner;
import com.revature.saveTheDate.services.DinnerServices;

public class DinnerServicesTestSuite {
	
	DinnerServices sut;
	DinnerDAO mockDinnerDAO;
	
	Dinner dinner = new Dinner(1, "valid");

	    @Before
		public void testPrep() {
			mockDinnerDAO = mock(DinnerDAO.class);
			sut = new DinnerServices(mockDinnerDAO);
	    }

	    @Test
		public void test_addDinner() {
			
	    boolean result = sut.addDinner(dinner);
	    
	    Assert.assertTrue(result);
	    }

	    @Test
		public void test_getAllDinners() {
			
	    	
	        List<Dinner> testList = new ArrayList<Dinner>();
	        testList.add(dinner);

	        when(mockDinnerDAO.getAllDinners()).thenReturn(testList);

	        List<Dinner> resultList = sut.getAllDinners();

	        Assert.assertNotNull(resultList);
	    }

	    @Test
		public void test_getDinnerById() {
			
	        when(mockDinnerDAO.getDinnerById(1)).thenReturn(dinner);

	        Dinner dinner2 = sut.getDinnerById(1);

	        Assert.assertNotNull(dinner2);
	        
	    } 
	    
	    @Test
	    public void test_isDinnerValid() {
	    	Dinner dinner3 = null;
	    	
	    	boolean result = sut.isDinnerValid(dinner);
	    	boolean result2 = sut.isDinnerValid(dinner3);
	    	
	    	Assert.assertTrue(result);
	    	Assert.assertFalse(result2);
	    }
	    

}
