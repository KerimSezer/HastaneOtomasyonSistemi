package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bashekim extends User {
	java.sql.Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Object getdoctorList;

	public Bashekim(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public Bashekim() {
	}

	public ArrayList<User> getdoctorList() throws SQLException {

		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT *FROM user WHERE type='doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {

		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id = " + clinic_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"),
						rs.getString("u.name"), rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
		String query = "UPDATE user SET name=?,tcno=?,password=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}

	}

	public boolean addWorker(int userId, int clinicId) throws SQLException {
		String query = "INSERT INTO worker (clinic_id, user_id) VALUES (?, ?)";

		boolean key = false;
		int count = 0;

		try {
			PreparedStatement checkStatement = con
					.prepareStatement("SELECT COUNT(*) FROM worker WHERE 'clinic_id' = ? AND 'user_id' = ?");
			checkStatement.setInt(1, clinicId);
			checkStatement.setInt(2, userId);
			ResultSet countResult = checkStatement.executeQuery();
			if (countResult.next()) {
				count = countResult.getInt(1);
			}
			if (count == 0) {
				PreparedStatement insertStatement = con.prepareStatement(query);
				insertStatement.setInt(1, clinicId);
				insertStatement.setInt(2, userId);
				insertStatement.executeUpdate();
			}
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
}
