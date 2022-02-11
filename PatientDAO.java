package com.revature.healthcarescheduling.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.healthcarescheduling.models.Patient;
import com.revature.healthcarescheduling.util.collections.LinkedList;
import com.revature.healthcarescheduling.util.collections.List;
import com.revature.healthcarescheduling.util.datasource.ConnectionFactory;

public class PatientDAO implements CrudDAO<Patient> {

	public Patient findByUsernameAndPassword(String username, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from patients where username = ? and password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Patient patient = new Patient();
				patient.setPatientId(rs.getString("patient_id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setEmail(rs.getString("email"));
				patient.setUsername(rs.getString("username"));
				patient.setPassword(rs.getString("password"));

				return patient;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// TODO: Implement FindByEmail
	public Patient findByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from patients where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Patient patient = new Patient();
				patient.setPatientId(rs.getString("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setEmail(rs.getString("email"));
				patient.setUsername(rs.getString("username"));
				patient.setPassword(rs.getString("password"));

				return patient;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	// TODO: Implement FindByUsername
	public Patient findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from patients where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Patient patient = new Patient();
				patient.setPatientId(rs.getString("id"));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setEmail(rs.getString("email"));
				patient.setUsername(rs.getString("username"));
				patient.setPassword(rs.getString("password"));

				return patient;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Patient create(Patient newPatient) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			newPatient.setPatientId(UUID.randomUUID().toString());

			String sql = "insert into patients (patients_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newPatient.getPatientId());
			ps.setString(2, newPatient.getFirstName());
			ps.setString(3, newPatient.getLastName());
			ps.setString(4, newPatient.getEmail());
			ps.setString(5, newPatient.getUsername());
			ps.setString(6, newPatient.getPassword());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newPatient;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

	public List<Patient> findAll() {

		List<Patient> patientsList = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from patients";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Patient patient = new Patient();
				patient.setPatientId(resultSet.getString("patients_id"));
				patient.setFirstName(resultSet.getString("first_name"));
				patient.setLastName(resultSet.getString("last_name"));
				patient.setEmail(resultSet.getString("email"));
				patient.setUsername(resultSet.getString("username"));
				patient.setPassword(resultSet.getString("password"));

				patientsList.add(patient);
			}

			return patientsList;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Patient findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Patient updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
