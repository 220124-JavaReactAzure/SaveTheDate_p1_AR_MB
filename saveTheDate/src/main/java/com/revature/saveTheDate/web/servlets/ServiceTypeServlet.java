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
import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.services.ServiceTypeServices;

public class ServiceTypeServlet extends HttpServlet{

	private final ServiceTypeServices serviceTypeServices;
	private final ObjectMapper mapper;
	
	public ServiceTypeServlet(ServiceTypeServices serviceTypeServices, ObjectMapper mapper) {
		this.serviceTypeServices = serviceTypeServices;
		this.mapper = mapper;
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
				String idParam = req.getParameter("serviceTypeId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?serviceTypeId=# in your url");
					return;
				}
				
				int serviceTypeId = Integer.valueOf(idParam);
				
			
				ServiceType serviceType = serviceTypeServices.getServiceTypeById(serviceTypeId);
				if(serviceType == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(serviceType);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<ServiceType> serviceTypes = serviceTypeServices.getAllServiceTypes();
			String payload = mapper.writeValueAsString(serviceTypes);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			ServiceType newServiceType = mapper.readValue(req.getInputStream(), ServiceType.class);
			boolean wasReg = serviceTypeServices.addServiceType(newServiceType);
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
			ServiceType updatedServiceType = mapper.readValue(req.getInputStream(), ServiceType.class);

			serviceTypeServices.updateServiceTypeWithSessionMethod(updatedServiceType);
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
