package com.birlasoft.member.model;

public class MemberVO {
	String memberId;
	String memberName;
	String memberDOB;
	String memberNum;
	String memberMail;
	String memberState;
	String memberCity;
	String memberPincode;
	String memberAddress;
	String password;
	String memberStatus;
	
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
	public String getMemberDOB() {
		return memberDOB;
	}
	public void setMemberDOB(String memberDOB) {
		this.memberDOB = memberDOB;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public String getMemberState() {
		return memberState;
	}
	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}
	public String getMemberCity() {
		return memberCity;
	}
	public void setMemberCity(String memberCity) {
		this.memberCity = memberCity;
	}
	public String getMemberPincode() {
		return memberPincode;
	}
	public void setMemberPincode(String memberPincode) {
		this.memberPincode = memberPincode;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	public MemberVO(String memberId, String memberName, String memberDOB, String memberNum, String memberMail,
			String memberState, String memberCity, String memberPincode, String memberAddress, String password,
			String memberStatus) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberDOB = memberDOB;
		this.memberNum = memberNum;
		this.memberMail = memberMail;
		this.memberState = memberState;
		this.memberCity = memberCity;
		this.memberPincode = memberPincode;
		this.memberAddress = memberAddress;
		this.password = password;
		this.memberStatus = memberStatus;
	}
	
	public MemberVO() {
		
	}
	public MemberVO(String id, String selectedUsername, String selectedPassword) {
		this.memberId = id;
		this.memberName = selectedUsername;
		this.password = selectedPassword;
	}
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberName=" + memberName + ", memberDOB=" + memberDOB
				+ ", memberNum=" + memberNum + ", memberMail=" + memberMail + ", memberState=" + memberState
				+ ", memberCity=" + memberCity + ", memberPincode=" + memberPincode + ", memberAddress=" + memberAddress
				+ ", password=" + password + ", memberStatus=" + memberStatus + "]";
	}
}
