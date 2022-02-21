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
import com.revature.saveTheDate.models.Dinner;
import com.revature.saveTheDate.services.DinnerServices;

public class DinnerServlet extends HttpServlet {
	
	private final DinnerServices dinnerServices;
	private final ObjectMapper mapper;
	
	public DinnerServlet(DinnerServices dinnerServices, ObjectMapper mapper) {
		this.dinnerServices = dinnerServices;
		this.mapper = mapper;
	}
	
	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /dinners
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("dinnerId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?dinnerId=# in your url");
					return;
				}
				
				int dinnerId = Integer.valueOf(idParam);
				
			
				Dinner dinner = dinnerServices.getDinnerById(dinnerId);
				if(dinner == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(dinner);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Dinner> dinners = dinnerServices.getAllDinners();
			String payload = mapper.writeValueAsString(dinners);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			Dinner newDinner = mapper.readValue(req.getInputStream(), Dinner.class);
			boolean wasReg = dinnerServices.addDinner(newDinner);
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
			Dinner updatedDinner = mapper.readValue(req.getInputStream(), Dinner.class);

			dinnerServices.updateDinnerWithSessionMethod(updatedDinner);
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
