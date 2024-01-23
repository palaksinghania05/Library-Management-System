package com.birlasoft.libraryms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.birlasoft.book.model.BookIssueVO;
import com.birlasoft.book.model.BookVO;
import com.birlasoft.libraryms.util.DBOracleConnection;
import com.birlasoft.member.model.MemberVO;

public class BookDAOImpl {
	static DBOracleConnection dbObject = new DBOracleConnection();
	private static final String SELECT_ALL_BOOKS = "SELECT * FROM booktb";
	private static final String SELECT_BOOK = "SELECT * FROM booktb where bookname = ?";
	private static final String UPDATE_BOOK = "UPDATE booktb SET currentstock = ? where bookname = ?";
	private static final String IN_STOCK_BOOK = "UPDATE booktb SET stock = ? where bookname = ?";
	private static final String OUT_STOCK_BOOK = "UPDATE booktb SET stock = ? where bookname = ?";
	private static final String INSERT_BOOK = "INSERT INTO booktb VALUES (bookid.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_BOOK = "DELETE FROM booktb where bookname = ?";
	private static final String ISSUE_BOOK_GENRE = "SELECT bookname, author, language from booktb where genre = ? and stock = ?";
	private static final String ISSUE_BOOK_LANG = "SELECT bookname, author, genre from booktb where language = ? and stock = ?";
	private static final String ISSUE_BOOK_AUTHOR = "SELECT bookname, genre, language from booktb where author = ? and stock = ?";
	private static final String ISSUE_BOOK = "UPDATE booktb SET currentstock = currentstock-1 where bookname = ?";
	private static final String RETURN_BOOK = "UPDATE booktb SET currentstock = currentstock+1 where bookname = ?";
	static BookIssueDAO bookissueDAO = new BookIssueDAO();

	public static void insertNewBook(BookVO book) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
			preparedStatement.setString(1, book.getBookname());
			preparedStatement.setString(2, book.getGenre());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPublishdate());
			preparedStatement.setString(5, book.getLanguage());
			preparedStatement.setString(6, "yes");
			preparedStatement.setInt(7, book.getCurrentStock());
			preparedStatement.executeUpdate();
			System.out.println("Book Added Successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteBook(String name) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			// connection.commit();
			System.out.println("Book Removed Successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<BookVO> getAllBooks() {
		List<BookVO> books = new ArrayList<>();
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				books.add(new BookVO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5).toString(), resultSet.getString(6), 
						resultSet.getString(7), resultSet.getInt(8)));
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return books;
	}

	public static List<String> getAllBookNames() {
		List<String> books = new ArrayList<>();
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				books.add(resultSet.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return books;
	}
	
	public static void updateBookCopies(String bookname, int stock) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
			preparedStatement.setInt(1, getBookByName(bookname).getCurrentStock() + stock);
			preparedStatement.setString(2, bookname);
			preparedStatement.executeUpdate();
			System.out.println("Book Copies Added Successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static BookVO getBookByName(String name) {
		Connection connection = dbObject.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_BOOK);
			preparedStatement.setString(1, name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				return new BookVO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8));
			}
		} catch (SQLException e) {
			System.out.println("Error in connecting to db");
		}
		return null;
	}
	
	public static void booksByLang(String lang) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ISSUE_BOOK_LANG)) {
			preparedStatement.setString(1, lang);
			preparedStatement.setString(2, "yes");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Book name - " + resultSet.getString(1));
				System.out.println("Author - " + resultSet.getString(2));
				System.out.println("Genre - " + resultSet.getString(3));
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void booksByGenre(String gen) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ISSUE_BOOK_GENRE)) {
			preparedStatement.setString(1, gen);
			preparedStatement.setString(2, "yes");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Book name - " + resultSet.getString(1));
				System.out.println("Author - " + resultSet.getString(2));
				System.out.println("Language - " + resultSet.getString(3));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void booksByAuthor(String auth) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ISSUE_BOOK_AUTHOR)) {
			preparedStatement.setString(1, auth);
			preparedStatement.setString(2, "yes");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Book name - " + resultSet.getString(1));
				System.out.println("Genre - " + resultSet.getString(2));
				System.out.println("Language - " + resultSet.getString(3));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void issueBook(MemberVO mem, String book) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ISSUE_BOOK)) {
			preparedStatement.setString(1, book);
			preparedStatement.executeUpdate();
			if(getBookByName(book).getCurrentStock() == 0) {
				getBookByName(book).setStock("no");
				PreparedStatement preparedStatement2 = connection.prepareStatement(OUT_STOCK_BOOK);
					preparedStatement2.setString(1, "no");
					preparedStatement2.setString(2, book);
					preparedStatement2.executeUpdate();
			}
			bookissueDAO.insertIssuedBookRecord(mem.getMemberId(), mem.getMemberName(), getBookByName(book).getBookid(), book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void returnBook(MemberVO mem, String book) {
		try (Connection connection = dbObject.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RETURN_BOOK)) {
			preparedStatement.setString(1, book);
			preparedStatement.executeUpdate();
			if(getBookByName(book).getCurrentStock() > 0) {
				PreparedStatement preparedStatement2 = connection.prepareStatement(IN_STOCK_BOOK);
					preparedStatement2.setString(1, "yes");
					preparedStatement2.setString(2, book);
					preparedStatement2.executeUpdate();
			}
			bookissueDAO.updateBookIssuedRecord(mem.getMemberId(), book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
