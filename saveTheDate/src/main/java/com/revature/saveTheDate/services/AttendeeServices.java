package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.AttendeeDAO;
import com.revature.saveTheDate.models.Attendee;

public class AttendeeServices {

	private final AttendeeDAO attendeeDAO;
	//private final Logger logger = LogManager.getLogger();

	public AttendeeServices(AttendeeDAO attendeeDAO) {
		this.attendeeDAO = attendeeDAO;
		//logger.info("AttendeeServices created");
	}

	public boolean addAttendee(Attendee attendee) {
		//logger.info("AttendeeServices.addAttendee called for Attendee: " + attendee);
		if (isAttendeeValid(attendee){
			return attendeeDAO.addAttendee(attendee);
		}
		 return false;
	}

	public List<Attendee> getAllAttendees() {
		//logger.info("AttendeeServices.getAllAttendees called");
		return attendeeDAO.getAllAttendee();
	}

	public Attendee getAttendeeById(int id) {
		//logger.info("AttendeeServices.getAttendeeById called for id: " + id);
		return attendeeDAO.getAttendeeById(id);
	}

	public void updateAttendeeWithSessionMethod(Attendee attendee) {
		//logger.info("AttendeeServices.updateAttendeeWithSessionMethod called for Attendee: " + attendee);
		attendeeDAO.updateAttendeeWithSessionMethod(attendee);
	}
	
	public void deleteAttendeeById (int id) {
		//logger.info("AttendeeServices.deleteAttendeeById called for id: " + id);
		attendeeDAO.deleteAttendeeById(id);
	}
	
	public boolean isAttendeeValid(Attendee attendee) {
		if(attendee.isAttending()) {
			if (attendee.getDinner().getDinnername() == null){
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
}
