package com.revature.saveTheDate.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.saveTheDate.models.Attendee;
import com.revature.saveTheDate.services.AttendeeServices;

public class AttendeeServlet extends HttpServlet{
	
	private final AttendeeServices attendeeServices;
	private final ObjectMapper mapper;
	
	public AttendeeServlet(AttendeeServices attendeeServices, ObjectMapper mapper) {
		this.attendeeServices = attendeeServices;
		this.mapper = mapper;
	}
	
	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /attendees
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("attendeeId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?attendeeId=# in your url");
					return;
				}
				
				int attendeeId = Integer.valueOf(idParam);
				
			
				Attendee attendee = attendeeServices.getAttendeeById(attendeeId);
				if(attendee == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(attendee);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Attendee> attendees = attendeeServices.getAllAttendees();
			String payload = mapper.writeValueAsString(attendees);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			Attendee newAttendee = mapper.readValue(req.getInputStream(), Attendee.class);
			boolean wasReg = attendeeServices.addAttendee(newAttendee);
			if(wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other exception did not persist");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Attendee updatedAttendee = mapper.readValue(req.getInputStream(), Attendee.class);

			attendeeServices.updateAttendeeWithSessionMethod(updatedAttendee);
			resp.setStatus(204);	
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception did not persist");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
b
		super.doDelete(req, resp);
	}

}