package com.test.controller;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.dto.TeammateDto;
import com.test.service.TestService;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/first")
	public String getFirstPage(@RequestParam(value="inputData") String inputData, Model model) {
		model.addAttribute("inputData", inputData);
		return "test-first";
	}
	
	@RequestMapping(value="/second")
	public String getSecondPage(@RequestParam(value="inputData") String inputData, Model model) {
		model.addAttribute("inputData", inputData);
		return "test-second";
	}
	
	// MySQL 연결 테스트
	@RequestMapping(value="/db-connection-test")
	public String dbConnectionTest(Model model) {
		try(Connection conn = dataSource.getConnection()) {
			System.out.println(conn);
			model.addAttribute("flag", "T");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "db-connection-test";
	}
	
	// 팀원 목록 호출
	@RequestMapping(value="/teammate-list")
	public String getTeammateList(Model model) {
		List<TeammateDto> teammateList = testService.getTeammateList();
		model.addAttribute("teammateList", teammateList);
		return "teammate-list";
	}
	
}
