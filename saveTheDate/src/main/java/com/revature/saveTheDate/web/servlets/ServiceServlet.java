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
import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.services.ServiceServices;
import com.revature.saveTheDate.services.ServiceTypeServices;

public class ServiceServlet extends HttpServlet{
	
	private final ServiceServices serviceServices;
	private final ObjectMapper mapper;
	private final ServiceTypeServices serviceTypeServices;
	
	public ServiceServlet(ServiceServices serviceServices, ObjectMapper mapper,
			ServiceTypeServices serviceTypeServices) {
		super();
		this.serviceServices = serviceServices;
		this.mapper = mapper;
		this.serviceTypeServices = serviceTypeServices;
	}

	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /services
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("serviceId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?serviceId=# in your url");
					return;
				}
				
				int serviceId = Integer.valueOf(idParam);
				
			
				Service service = serviceServices.getServiceById(serviceId);
				if(service == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(service);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Service> services = serviceServices.getAllServices();
			String payload = mapper.writeValueAsString(services);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			String idParam = req.getParameter("serviceTypeId");
			if(idParam == null) {
				resp.setStatus(400);
				resp.getWriter().write("Please include the query ?serviceTypeId=# in your url");
				return;
			}
			ServiceType serviceType = serviceTypeServices.getServiceTypeById(Integer.valueOf(idParam));
			Service newService = mapper.readValue(req.getInputStream(), Service.class);
			newService.setServiceType(serviceType);
			boolean wasReg = serviceServices.addService(newService);
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
			Service updatedService = mapper.readValue(req.getInputStream(), Service.class);

			serviceServices.updateServiceWithSessionMethod(updatedService);
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
