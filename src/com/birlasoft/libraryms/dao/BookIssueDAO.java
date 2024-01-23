package com.birlasoft.libraryms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.birlasoft.book.model.BookIssueVO;
import com.birlasoft.book.model.BookVO;
import com.birlasoft.libraryms.util.DBOracleConnection;

public class BookIssueDAO {
	static DBOracleConnection dbObject = new DBOracleConnection();
	private static final String SELECT_ALL_BOOKS_ISSUED = "SELECT * FROM bookissuetb";
	private static final String SELECT_USER_BOOK = "SELECT * FROM bookissuetb where membername = ?";
	private static final String SELECT_USER_PENDING_BOOK = "SELECT * FROM bookissuetb where membername = ? and \"has_returned\" = ?";
	private static final String INSERT_RECORD = "INSERT INTO bookissuetb VALUES (?, ?, ?, ?, sysdate,sysdate+10, ?, null)";
	private static final String UPDATE_RECORD = "UPDATE bookissuetb SET \"has_returned\" = ?, returndate = sysdate where bookname = ? and memberid = ?";

	public static List<BookIssueVO> getAllBooksIssued() {
		List<BookIssueVO> bookmembers = new ArrayList<>();
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_ISSUED);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			// System.out.print(resultSet);

			while (resultSet.next()) {
				bookmembers.add(new BookIssueVO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5).toString(), resultSet.getDate(6).toString(), resultSet.getString(7)));
				// System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return bookmembers;
	}

	public static void insertIssuedBookRecord(String memberId, String member, int bookid, String book) {
		String date = new Date(System.currentTimeMillis()).toString();
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD)) {
			preparedStatement.setString(1, memberId);
			preparedStatement.setString(2, member);
			preparedStatement.setInt(3, bookid);
			preparedStatement.setString(4, book);
			preparedStatement.setString(5, "no");
			preparedStatement.executeUpdate();
			System.out.println("Book Issued Successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getBookByUser(String name) {
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_USER_BOOK);
			preparedStatement.setString(1, name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println("Book Id - " + resultSet.getString(3));
				System.out.println("Book Name - " + resultSet.getString(4));
				System.out.println("Issue Date - " + resultSet.getDate(5).toString());
				System.out.println("Due Date - " + resultSet.getDate(6).toString());
				System.out.println("Return Status - " + resultSet.getString(7));
				if(resultSet.getString(7).equals("yes"))
					System.out.println("Return Date - " + resultSet.getDate(8).toString());
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
	}

	public static void updateBookIssuedRecord(String memberId, String book) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD)) {
			preparedStatement.setString(1, "yes");
			preparedStatement.setString(2, book);
			preparedStatement.setString(3, memberId);
			preparedStatement.executeUpdate();
			System.out.println("Book Returned Successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getPendingBookByUser(String memberName) {
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_USER_PENDING_BOOK);
			preparedStatement.setString(1, memberName);
			preparedStatement.setString(2, "no");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
					System.out.println("Book Id - " + resultSet.getString(3));
					System.out.println("Book Name - " + resultSet.getString(4));
					System.out.println("Issue Date - " + resultSet.getDate(5).toString());
					System.out.println("Due Date - " + resultSet.getDate(6).toString());
					System.out.println();
				}
			
		
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
	}
}
