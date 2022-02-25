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
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.services.ServiceServices;
import com.revature.saveTheDate.services.UserServices;
import com.revature.saveTheDate.services.WeddingServices;


public class WeddingServlet extends HttpServlet{
	
	private final WeddingServices weddingServices;
	private final ObjectMapper mapper;
	private final ServiceServices serviceServices;
	private final UserServices userServices;
	
	


	public WeddingServlet(WeddingServices weddingServices, ObjectMapper mapper, ServiceServices serviceServices,
			UserServices userServices) {
		super();
		this.weddingServices = weddingServices;
		this.mapper = mapper;
		this.serviceServices = serviceServices;
		this.userServices = userServices;
	}

	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /weddings
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("weddingId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?weddingId=# in your url");
					return;
				}
				
				int weddingId = Integer.valueOf(idParam);
				
			
				Wedding wedding = weddingServices.getWeddingById(weddingId);
				if(wedding == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(wedding);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Wedding> weddings = weddingServices.getAllWeddings();
			String payload = mapper.writeValueAsString(weddings);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			Wedding newWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			String venueId = req.getParameter("venueId");
			if(venueId != null) {
				Service service = serviceServices.getServiceById(Integer.valueOf(venueId));
				newWedding.setVenue(service);
			}
			String musicianId = req.getParameter("musicianId");
			if(musicianId != null) {
				Service service = serviceServices.getServiceById(Integer.valueOf(musicianId));
				newWedding.setMusician(service);
			}
			String catererId = req.getParameter("catererId");
			if(catererId != null) {
				Service service = serviceServices.getServiceById(Integer.valueOf(catererId));
				newWedding.setCaterer(service);
			}
			String floristId = req.getParameter("floristId");
			if(floristId != null) {
				Service service = serviceServices.getServiceById(Integer.valueOf(floristId));
				newWedding.setFlorist(service);
			}
			String photographerId = req.getParameter("photographerId");
			if(photographerId != null) {
				Service service = serviceServices.getServiceById(Integer.valueOf(photographerId));
				newWedding.setPhotographer(service);
			}
			String userId = req.getParameter("userId");
			if(photographerId != null) {
				User user = userServices.getUserById(Integer.valueOf(userId));
				newWedding.setUser(user);
			}
			if(venueId == null || musicianId== null || catererId == null || floristId == null || photographerId == null) {
				resp.setStatus(400);
				resp.getWriter().write("Please include the query ?userId=#&venueId=#&musicianId=#&catererId=#&floristId=#&photographerId=# in your url");
				return;
			}
			boolean wasReg = weddingServices.addWedding(newWedding);
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
			Wedding updatedWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			//weddingServices.updateWeddingWithHQL(updatedWedding);
			weddingServices.updateWeddingWithSessionMethod(updatedWedding);
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
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
