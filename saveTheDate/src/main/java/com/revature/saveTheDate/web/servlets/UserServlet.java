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
import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.services.RoleServices;
import com.revature.saveTheDate.services.UserServices;

public class UserServlet extends HttpServlet{
	
	private final UserServices userServices;
	private final ObjectMapper mapper;
	private final RoleServices roleServices;
	
	public UserServlet(UserServices userServices, ObjectMapper mapper, RoleServices roleServices) {
		super();
		this.userServices = userServices;
		this.mapper = mapper;
		this.roleServices = roleServices;
	}

	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /users
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("userId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?userId=# in your url");
					return;
				}
				
				int userId = Integer.valueOf(idParam);
				
			
				User user = userServices.getUserById(userId);
				if(user == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(user);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<User> users = userServices.getAllUsers();
			String payload = mapper.writeValueAsString(users);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			String idParam = req.getParameter("roleId");
			if(idParam == null) {
				resp.setStatus(400);
				resp.getWriter().write("Please include the query ?roleId=# in your url");
				return;
			}
			Role role = roleServices.getRoleById(Integer.valueOf(idParam));
			User newUser = mapper.readValue(req.getInputStream(), User.class);
			newUser.setRole(role);
			boolean wasReg = userServices.addUser(newUser);
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
			User updatedUser = mapper.readValue(req.getInputStream(), User.class);

			userServices.updateUserWithSessionMethod(updatedUser);
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
