package com.birlasoft.book.model;

public class BookIssueVO {
	String memberId;
	String memberName;
	String bookId;
	String bookName;
	String issueDate;
	String dueDate;
	String returned;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getReturned() {
		return returned;
	}
	public void setReturned(String returned) {
		this.returned = returned;
	}
	public BookIssueVO(String memberId, String memberName, String bookId, String bookName, String issueDate,
			String dueDate, String returned) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returned = returned;
	}
	@Override
	public String toString() {
		return "BookIssueVO [memberId=" + memberId + ", memberName=" + memberName + ", bookId=" + bookId + ", bookName="
				+ bookName + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", returned=" + returned + "]";
	}
	
	
}
