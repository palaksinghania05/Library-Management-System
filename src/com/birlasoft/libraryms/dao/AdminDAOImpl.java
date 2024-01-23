package com.birlasoft.libraryms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.birlasoft.admin.model.AdminVO;
import com.birlasoft.libraryms.util.DBOracleConnection;

public class AdminDAOImpl {
	static DBOracleConnection dbObject = new DBOracleConnection();
	private static final String SELECT_USER = "SELECT * FROM admintb WHERE adminname = ?";
	private static final String SELECT_ALL_USER = "SELECT * FROM admintb";

	public static String validateAdmin(String admin) {
		try {
			Connection connection = dbObject.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
			preparedStatement.setString(1, admin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String selectedUsername = resultSet.getString("adminname");
				String selectedPassword = resultSet.getString("pass_word");
				new AdminVO(selectedUsername, selectedPassword);
				return selectedPassword;
			}

		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return null;
	}

	public static List<String> getAllAdmins() {
		List<String> admins = new ArrayList<>();
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			// System.out.print(resultSet);
			while (resultSet.next()) {
				String a = resultSet.getString(3);
				admins.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return admins;
	}

}
