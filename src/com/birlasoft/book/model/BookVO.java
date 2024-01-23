package com.birlasoft.book.model;

public class BookVO {
	int bookid;
	String bookname;
	String genre;
	String author;
	String publishdate;
	String language;
	String stock;
	int currentStock;

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public BookVO(String bookname, String genre, String author, String publishdate, String language, int stock) {
		// this.bookid = bookid;
		this.bookname = bookname;
		this.genre = genre;
		this.author = author;
		this.publishdate = publishdate;
		this.language = language;
		this.currentStock = stock;
		// this.currentStock = currentStock;
	}

	public BookVO(int bookid, String bookname, String genre, String author, String publishdate, String language,
			String stock, int current) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.genre = genre;
		this.author = author;
		this.publishdate = publishdate;
		this.language = language;
		this.stock = stock;
		this.currentStock = current;
	}

	@Override
	public String toString() {
		return "BookVO [bookid=" + bookid + ", bookname=" + bookname + ", genre=" + genre + ", author=" + author
				+ ", publishdate=" + publishdate + ", language=" + language + ", stock=" + stock + ", currentStock="
				+ currentStock + "]";
	}

}
