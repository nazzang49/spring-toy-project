package com.test.dto;

/**
 * 회원정보
 * @author 박진영
 *
 */
public class UserDto {

	private String user_email;
	private String user_password;
	private String user_phone;
	private String user_addr1;
	private String user_addr2;
	private String user_role;
	private String keyValue;
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_addr1() {
		return user_addr1;
	}
	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}
	public String getUser_addr2() {
		return user_addr2;
	}
	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	@Override
	public String toString() {
		return "UserDto [user_email=" + user_email + ", user_password=" + user_password + ", user_phone=" + user_phone
				+ ", user_addr1=" + user_addr1 + ", user_addr2=" + user_addr2 + ", user_role=" + user_role
				+ ", keyValue=" + keyValue + "]";
	}
}
