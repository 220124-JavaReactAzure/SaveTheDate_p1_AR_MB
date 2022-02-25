package com.revature.saveTheDate.test.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.services.ServiceServices;


public class ServiceServicesTestSuite {
	
	ServiceServices sut;
	ServiceDAO mockServiceDAO;
	
	Service service = new Service(1, "valid", 10);

	    @Before
		public void testPrep() {
			mockServiceDAO = mock(ServiceDAO.class);
			sut = new ServiceServices(mockServiceDAO);
	    }

	    @Test
		public void test_addService() {
			
	    boolean result = sut.addService(service);
	    
	    Assert.assertTrue(result);
	    }

	    @Test
		public void test_getAllServiceTypes() {
			
	    	
	        List<Service> testList = new ArrayList<Service>();
	        testList.add(service);

	        when(mockServiceDAO.getAllService()).thenReturn(testList);

	        List<Service> resultList = sut.getAllServices();

	        Assert.assertNotNull(resultList);
	    }

	    @Test
		public void test_getServiceTypeById() {
			
	        when(mockServiceDAO.getServiceById(1)).thenReturn(service);

	        Service result = sut.getServiceById(1);

	        Assert.assertNotNull(result);
	        
	    } 


}
	    
