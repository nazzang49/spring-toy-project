package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.dto.TeammateDto;
import com.test.repository.TestDao;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;
	
	public List<TeammateDto> getTeammateList() {
		List<TeammateDto> teammateList = testDao.getTeammateListBySqlSession();
		return teammateList;
	}
	
}
