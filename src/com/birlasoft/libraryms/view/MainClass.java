package com.birlasoft.libraryms.view;

import java.util.List;
import java.util.Scanner;

import com.birlasoft.book.model.BookIssueVO;
import com.birlasoft.book.model.BookVO;
import com.birlasoft.libraryms.dao.AdminDAOImpl;
import com.birlasoft.libraryms.dao.BookDAOImpl;
import com.birlasoft.libraryms.dao.BookIssueDAO;
import com.birlasoft.libraryms.dao.MemberDAOImpl;
import com.birlasoft.libraryms.exceptions.WrongInputException;
import com.birlasoft.member.model.MemberVO;

public class MainClass {
	// data access objects
	static AdminDAOImpl adminDAO = new AdminDAOImpl();
	static BookDAOImpl bookDAO = new BookDAOImpl();
	static MemberDAOImpl memberDAO = new MemberDAOImpl();
	static BookIssueDAO bookissueDAO = new BookIssueDAO();
	// scanner object
	static Scanner sc = new Scanner(System.in);

	// member object
	static MemberVO memberObj = new MemberVO();

	public static void main(String[] args) {
		System.out.println("Are you a member or an admin?");
		String user = sc.next();
		System.out.println();

		try {
			if (user.toLowerCase().equals("member")) {
				System.out.println("Are you existing member? Enter yes or no");
				String input = sc.next();
				System.out.println();
				if (input.toLowerCase().equals("yes")) {
					System.out.println();
					System.out.println("You need to log in first!!");
					System.out.print("Enter username: ");
					String name = sc.next();
					System.out.print("Enter password: ");
					String password = sc.next();
					if (memberDAO.getAllMembers().contains(name.toLowerCase())) {
						memberObj = memberDAO.validateMember(name);
						if (!memberObj.getPassword().equals(password)) {
							System.out.println("Wrong Password");
						} else {
							System.out.println("Login Successful");
							System.out.println();
						}
					} else {
						System.out.println("You are not an existing member");
						signUp();
					}
					while (true) {
						int choice = memberMenu();
						switch (choice) {
						case 1: {
							System.out.println();
							issueBook();
							break;
						}
						case 2: {
							System.out.println();
							returnBook();
							break;
						}
						case 3: {
							System.out.println();
							viewBookHistory();
							break;
						}
						case 4: {
							System.out.println();
							viewPendingBooks();
							break;
						}
						case 5: {
							System.out.println();
							System.out.println("Good Bye!!");
							System.exit(0);
							break;
						}
						default: {
							throw new WrongInputException();
						}
						}
					}
				} else {
					signUp();
				}

			} else if (user.toLowerCase().equals("admin")) {
				System.out.println("You need to log in first!!");
				System.out.println("Enter username: ");
				String admin_name = sc.next();
				System.out.println("Enter password: ");
				String password = sc.next();
				if (adminDAO.getAllAdmins().contains(admin_name)) {
					if (!adminDAO.validateAdmin(admin_name).equals(password)) {
						System.out.println("Wrong Password");
					} else {
						System.out.println("Login Successful");
						while (true) {
							int choice = adminMenu();
							System.out.println();
							switch (choice) {
							case 1: {
								System.out.println();
								viewBooksIssued();
								break;
							}
							case 2: {
								System.out.println();
								addBook();
								break;
							}
							case 3: {
								System.out.println();
								deleteBook();
								break;
							}
							case 4: {
								System.out.println();
								viewBooks();
								break;
							}
							case 5: {
								System.out.println();
								System.out.println("Good Bye!!");
								System.exit(0);
								break;
							}
							default: {
								throw new WrongInputException();
							}
							}
						}
					}
				} else {
					System.out.println("No such user exist");
				}

			} else {
				// custom exception
				throw new WrongInputException();
			}
		} catch (WrongInputException e) {
			System.out.println("Invalid input");
		}
	}

	public static void signUp() {
		MemberVO member = new MemberVO();
		System.out.println("You need to sign up first!!");
		System.out.println("Enter username: ");
		member.setMemberName(sc.next());
		System.out.println("Enter dob: ");
		member.setMemberDOB(sc.next());
		System.out.println("Enter mobile num: ");
		member.setMemberNum(sc.next());
		System.out.println("Enter mail id: ");
		member.setMemberMail(sc.next());
		System.out.println("Enter state: ");
		member.setMemberState(sc.next());
		System.out.println("Enter city: ");
		member.setMemberCity(sc.next());
		System.out.println("Enter pincode: ");
		member.setMemberPincode(sc.next());
		System.out.println("Enter address ");
		member.setMemberAddress(sc.next());
		System.out.println("Enter password: ");
		member.setPassword(sc.next());
		member.setMemberStatus("Active");
		memberDAO.addMember(member);
	}

	public static int memberMenu() {
		System.out.println();
		System.out.println("Press the corresponding number to select one of the options: ");
		System.out.println("1. Issue a book");
		System.out.println("2. Return a book");
		System.out.println("3. View issue history");
		System.out.println("4. View pending books to return");
		System.out.println("5. Exit");
		System.out.print("Your choice: ");
		int choice = sc.nextInt();
		return choice;
	}

	public static int adminMenu() {
		System.out.println();
		System.out.println("Press the corresponding number to select one of the options: ");
		System.out.println("1. View Books Issued Details");
		System.out.println("2. Add a book");
		System.out.println("3. Remove a book");
		System.out.println("4. View all books");
		System.out.println("5. Exit");
		// TODO: Edit Profile
		// TODO: View all books
		// TODO: Delete a member

		System.out.print("Your choice: ");
		int choice = sc.nextInt();
		return choice;
	}

	public static void viewBooksIssued() {
		List<BookIssueVO> result = bookissueDAO.getAllBooksIssued();
		System.out.println("There are " + result.size() + " entries: ");
		System.out.println("*******************************************************************************************************");
		System.out.format("%-10s%-30s%-10s%-30s%-15s%-15s%-15s%n", "MemberID", "MemberName", "BookID", "BookName",
				"IssueDate", "DueDate", "ReturnStatus");
		System.out.println("*******************************************************************************************************");
		for (int i = 0; i < result.size(); i++) {
			String memberid = result.get(i).getMemberId();
			String membername = result.get(i).getMemberName();
			String bookid = result.get(i).getBookId();
			String bookname = result.get(i).getBookName();
			String issuedate = result.get(i).getIssueDate();
			String duedate = result.get(i).getDueDate();
			String returned = result.get(i).getReturned();
			System.out.format("%-10s%-30s%-10s%-30s%-15s%-15s%-15s%n", memberid, membername, bookid, bookname,
					issuedate, duedate, returned);
			// TODO: view member details
		}
	}

	public static void addBook() {
		System.out.println("Are you adding:");
		System.out.println("1. A new book?");
		System.out.println("2. An existing book?");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1: {
			System.out.print("Enter book name: ");
			String bookname = sc.nextLine();
			System.out.print("Enter genre of the book: ");
			String genre = sc.nextLine();
			System.out.print("Enter the name of the author of the book:");
			String author = sc.nextLine();
			System.out.print("Enter the publish date in dd-mm-yyyy format:");
			String publish = sc.nextLine();
			System.out.print("Enter the book language: ");
			String lang = sc.nextLine();
			System.out.print("Enter the stock of the book: ");
			int stock = sc.nextInt();
			bookDAO.insertNewBook(new BookVO(bookname, genre, author, publish, lang, stock));
			break;
		}
		case 2: {
			System.out.print("Enter book name: ");
			String bookname = sc.next();
			if (bookDAO.getAllBookNames().contains(bookname.toLowerCase())) {
				System.out.println("Enter the copies of the book you are adding: ");
				int stock = sc.nextInt();
				bookDAO.updateBookCopies(bookname, stock);
				// bookDAO.
			} else {
				System.out.println("No such book exist");
			}
			break;
		}
		}
	}

	public static void deleteBook() {
		System.out.print("Enter book name: ");
		String bookname = sc.next();
		if (bookDAO.getAllBookNames().contains(bookname.toLowerCase())) {
			bookDAO.deleteBook(bookname);
			// bookDAO.
		} else {
			System.out.println("No such book exist");
		}
	}

	public static void viewBooks() {
		List<BookVO> result = bookDAO.getAllBooks();
		System.out.println("There are " + result.size() + " entries: ");
		System.out.println("*******************************************************************************************************");
		System.out.format("%-10s%-25s%-20s%-25s%-15s%-15s%-15s%n", "BookID", "BookName", "Genre", "Author", "Language", "PublishDate", "CurrentStock");
		System.out.println("*******************************************************************************************************");
		for (int i = 0; i < result.size(); i++) {
			int bookid = result.get(i).getBookid();
			String bookname = result.get(i).getBookname();
			String genre = result.get(i).getGenre();
			String author = result.get(i).getAuthor();
			String publishdate = result.get(i).getPublishdate();
			String language = result.get(i).getLanguage();
			int current = result.get(i).getCurrentStock();
			System.out.format("%-10s%-25s%-20s%-25s%-15s%-15s%-15s%n", bookid, bookname,genre, author, language, publishdate, current);
		}
	}

	public static void issueBook() {
		System.out.println("Select filter: ");
		System.out.println("1. By Language: ");
		System.out.println("2. By Genre: ");
		System.out.println("3. By Author: ");
		int input = sc.nextInt();
		switch (input) {
		case 1: {
			System.out.println();
			System.out.println("Enter language: ");
			String lang = sc.next();
			bookDAO.booksByLang(lang);
			System.out.println();
			System.out.println("Enter the name of book you want to issue: ");
			String book = sc.next();
			if (bookDAO.getAllBookNames().contains(book)) {
				bookDAO.issueBook(memberObj, book);
			} else {
				System.out.println("We don't have any book by this name");
			}
			break;
		}
		case 2: {
			System.out.println();
			System.out.println("Enter genre: ");
			String gen = sc.next();
			bookDAO.booksByGenre(gen);
			System.out.println();
			System.out.println("Enter the name of book you want to issue: ");
			String book = sc.next();
			if (bookDAO.getAllBookNames().contains(book)) {
				bookDAO.issueBook(memberObj, book);
			} else {
				System.out.println("We don't have any book by this name");
			}
			break;
		}
		case 3: {
			System.out.println();
			System.out.println("Enter author name: ");
			String auth = sc.next();
			bookDAO.booksByAuthor(auth);
			System.out.println();
			System.out.println("Enter the name of book you want to issue: ");
			String book = sc.next();
			if (bookDAO.getAllBookNames().contains(book)) {
				bookDAO.issueBook(memberObj, book);
			} else {
				System.out.println("We don't have any book by this name");
			}
			break;
		}
		}
	}

	public static void returnBook() {
		System.out.println("Enter the name of book you want to return: ");
		String book = sc.next();
		bookDAO.returnBook(memberObj, book);
	}

	public static void viewBookHistory() {
		bookissueDAO.getBookByUser(memberObj.getMemberName());
	}

	public static void viewPendingBooks() {
		bookissueDAO.getPendingBookByUser(memberObj.getMemberName());
	}
}
