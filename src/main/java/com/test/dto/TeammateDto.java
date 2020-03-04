package com.test.dto;

public class TeammateDto {

	// 로그인 정보
	private String groupware_id;
	private String groupware_password;
	private String phone_number;
	// 팀 내 주요 역할
	private String main_role;
	// 현재 진행 중인 업무
	private String present_task;
	
	public String getGroupware_id() {
		return groupware_id;
	}
	public void setGroupware_id(String groupware_id) {
		this.groupware_id = groupware_id;
	}
	public String getGroupware_password() {
		return groupware_password;
	}
	public void setGroupware_password(String groupware_password) {
		this.groupware_password = groupware_password;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getMain_role() {
		return main_role;
	}
	public void setMain_role(String main_role) {
		this.main_role = main_role;
	}
	public String getPresent_task() {
		return present_task;
	}
	public void setPresent_task(String present_task) {
		this.present_task = present_task;
	}
	
	// for 디버깅
	@Override
	public String toString() {
		return "TeammateDto [groupware_id=" + groupware_id + ", groupware_password=" + groupware_password
				+ ", phone_number=" + phone_number + ", main_role=" + main_role + ", present_task=" + present_task
				+ "]";
	}
}
