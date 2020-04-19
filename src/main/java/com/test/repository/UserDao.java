package com.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.test.dto.TeammateDto;
import com.test.dto.UserDto;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// encrypt key value
	private static final String KEY_VALUE = "jyp";
	
	// login result
	public TeammateDto getUser(String email, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		return sqlSession.selectOne("teammate.getUser", params);
	}
	
	// return user info for authentication of security user
	public UserDto getSecurityUserByEmail(String username) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("keyValue", KEY_VALUE);
		return sqlSession.selectOne("user.getSecurityUser", params);
	}
	
	public UserDto getUserInfo(String email) {
		return sqlSession.selectOne("user.getUserInfo", email);
	}
	
	// join result
	public boolean insertJoin(UserDto userDto) {
		userDto.setKeyValue(KEY_VALUE);
		return sqlSession.insert("user.join", userDto) == 1;
	}
}
