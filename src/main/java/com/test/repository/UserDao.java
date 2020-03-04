package com.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.test.dto.TeammateDto;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//login result
	public TeammateDto getUser(String email, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		return sqlSession.selectOne("teammate.getUser", params);
	}
}
