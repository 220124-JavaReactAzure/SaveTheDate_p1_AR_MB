package com.revature.saveTheDate.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	
}
