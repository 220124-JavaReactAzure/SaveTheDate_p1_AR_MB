Package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.AttendeeDAO;
import com.revature.saveTheDate.models.Attendee;

public class AtendeeServices {

	private final AttendeeDAO attendeeDAO;

	public AttendeeServices(AttendeeDAO attendeeDAO) {
		this.attendeeDAO = attendeeDAO;
	}

	public boolean addAttendee(Attendee attendee) {

		return attendeeDAO.addAttendee(attendee);
	}

	public List<Attendee> getAllAttendees() {
		return attendeeDAO.getAllAttendee();

	}

	public Attendee getAttendeeById(int id) {

		return attendeeDAO.getAttendeeById(id);
	}

	public void updateAttendeeWithSessionMethod(Attendee attendee) {

		attendeeDAO.updateAttendeeWithSessionMethod(attendee);
	}
	
	public void deleteAttendee (int id) {
		attendeeDAO.deleteAttendee(id);
	}

	
}
