package com.birlasoft.libraryms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.birlasoft.admin.model.AdminVO;
import com.birlasoft.libraryms.util.DBOracleConnection;
import com.birlasoft.libraryms.util.Validation;
import com.birlasoft.member.model.MemberVO;

public class MemberDAOImpl implements Validation {
	static DBOracleConnection dbObject = new DBOracleConnection();
	private static final String SELECT_ALL_USER = "SELECT * FROM members";
	private static final String INSERT_USER = "INSERT INTO members VALUES (memberid.NEXTVAL, ?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_MEMBER = "SELECT * FROM members WHERE membername = ?";

	public void addMember(MemberVO member) {
		try {
			Connection connection = dbObject.getConnection();
			if (checkLoginID(member.getMemberName()) && (checkPassword(member.getPassword()))) {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
				preparedStatement.setString(1, member.getMemberName());
				preparedStatement.setString(2, member.getMemberDOB());
				preparedStatement.setString(3, member.getMemberNum());
				preparedStatement.setString(4, member.getMemberMail());
				preparedStatement.setString(5, member.getMemberState());
				preparedStatement.setString(6, member.getMemberCity());
				preparedStatement.setString(7, member.getMemberPincode());
				preparedStatement.setString(8, member.getMemberAddress());
				preparedStatement.setString(9, member.getPassword());
				preparedStatement.setString(10, member.getMemberStatus());
				ResultSet resultSet = preparedStatement.executeQuery();
				System.out.println("Your account is created successfully");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Your email and num should be unique.Try again");
		}
		catch (SQLException e) {
			System.out.println("Error in connecting to db" + e);
		}
	}
	
	public static MemberVO validateMember(String member) {
		try {
			Connection connection = dbObject.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEMBER);
			preparedStatement.setString(1, member);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String selectedUsername = resultSet.getString("membername");
				String selectedPassword = resultSet.getString("pass_word");
				String id = resultSet.getString("memberid");
				return new MemberVO(id, selectedUsername, selectedPassword);
			}

		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return null;
	}

	public static List<String> getAllMembers() {
		List<String> members = new ArrayList<>();
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
				String a = resultSet.getString(2);
				members.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return members;
	}

	@Override
	public boolean checkLoginID(String name) {
		if(name.contains(" ")) {
			System.out.println("Username should not contain any space. Enter in camelcase format.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkPassword(String password) {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if(matcher.matches())
			return true;
		else {
			System.out.println("Password should be of 8 characters and must contain one uppercase, one lowercase, one digit and one special charaacter.");
			return false;
		}
	}
}
