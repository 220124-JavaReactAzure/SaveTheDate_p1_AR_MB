package com.revature.saveTheDate.test.services;

package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.revature.saveTheDate.daos.ServiceTypeDAO;
import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.services.ServiceTypeServices;

import org.apache.logging.log4j.core.util.Assert;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Before;
import org.junit.Test;


public class ServiceTypeServicesTestSuite {

    ServiceTypeServices sut;
	ServiceTypeDAO mockServiceTypeDAO;

    @Before
	public void testPrep() {
		mockServiceTypeDAO = mock(ServiceTypeDAO.class);
		sut = new ServiceTypeServices(mockServiceTypeDAO);
    }

    @Test
	public void test_addServiceType() {
		
		ServiceType testServiceType = new ServiceType(1, "valid");
        ServiceType testServiceType2 = new ServiceType(6, "valid");
        ServiceType testServiceType3 = new ServiceType(0, "valid");
        ServiceType testServiceType4 = new ServiceType(1, null);

        Assert.assertTrue(sut.addServiceType(testServiceType));
        Assert.assertFalse(sut.addServiceType(testServiceType2));
        Assert.assertFalse(sut.addServiceType(testServiceType3));
        Assert.assertFalse(sut.addServiceType(testServiceType4));
    }

    @Test
	public void test_getAllServiceTypes() {
		
		ServiceType testServiceType = new ServiceType(1, "valid");
        
        List<ServiceType> testList;
        testList.add(testServiceType);

        when(mockServiceTypeDAO.getAllServiceTypes()).thenReturn(testList);

        List<ServiceType> resultList = sut.getAllServiceTypes();

        Assert.assertNotNull(resultList);
    }

    @Test
	public void test_getServiceTypeById() {
		
		ServiceType testServiceType = new ServiceType(1, "valid");

        when(mockServiceTypeDAO.getServiceTypeById(1)).thenReturn(testServiceType);

        ServiceType result = sut.getServiceTypeById(1);
        ServiceType result2 = sut.getServiceTypeById(2);

        Assert.assertNotNull(result);
        Assert.assertNull(result2);
    }

    @Test
	public void test_isServiceTypeValid() {
		
		ServiceType testServiceType = new ServiceType(1, "valid");
        ServiceType testServiceType2 = new ServiceType(6, "valid");
        ServiceType testServiceType3 = new ServiceType(0, "valid");
        ServiceType testServiceType4 = new ServiceType(1, null);

        boolean result = sut.isServiceTypeValid(testServiceType);
        boolean result2 = sut.isServiceTypeValid(testServiceType2);
        boolean result3 = sut.isServiceTypeValid(testServiceType3);
        boolean result4 = sut.isServiceTypeValid(testServiceType4);

        Assert.assertTrue(result);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertFalse(result4);
    }
    
}
