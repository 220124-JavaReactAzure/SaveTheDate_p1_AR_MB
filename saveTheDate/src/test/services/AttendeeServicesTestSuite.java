package com.revature.saveTheDate.test.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.saveTheDate.daos.AttendeeDAO;
import com.revature.saveTheDate.daos.DinnerDAO;
import com.revature.saveTheDate.models.Attendee;
import com.revature.saveTheDate.models.Dinner;
import com.revature.saveTheDate.services.AttendeeServices;
import com.revature.saveTheDate.services.DinnerServices;

public class AttendeeServicesTestSuite {
	
	AttendeeServices sut;
	AttendeeDAO mockAttendeeDAO;
	
	Attendee attendee = new Attendee(1, null, null, false, null, false, null);

	    @Before
		public void testPrep() {
			mockAttendeeDAO = mock(AttendeeDAO.class);
			sut = new AttendeeServices(mockAttendeeDAO);
	    }

	    @Test
		public void test_addAttendee() {
			
	    boolean result = sut.addAttendee(attendee);
	    
	    Assert.assertTrue(result);
	    }

	    @Test
		public void test_getAllAttendees() {
			
	    	
	        List<Attendee> testList = new ArrayList<Attendee>();
	        testList.add(attendee);

	        when(mockAttendeeDAO.getAllAttendee()).thenReturn(testList);

	        List<Attendee> resultList = sut.getAllAttendees();

	        Assert.assertNotNull(resultList);
	    }

	    @Test
		public void test_getAttendeeById() {
			
	        when(mockAttendeeDAO.getAttendeeById(1)).thenReturn(attendee);

	        Attendee attendee2 = sut.getAttendeeById(1);

	        Assert.assertNotNull(attendee2);
	        
	    } 
	    

}
