package com.test.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.test.dto.TeammateDto;

@Repository
public class TestDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<TeammateDto> getTeammateList() {
		String sql = "select * from service_infra_development";
		// RowMapper 인터페이스를 익명 클래스로 선언
		return jdbcTemplate.query(sql, new RowMapper<TeammateDto>() {
			@Override
			public TeammateDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				TeammateDto teammateDto = new TeammateDto();
				teammateDto.setGroupware_id(rs.getString("groupware_id"));
				teammateDto.setGroupware_password(rs.getString("groupware_password"));
				teammateDto.setPhone_number(rs.getString("phone_number"));
				teammateDto.setMain_role(rs.getString("main_role"));
				teammateDto.setPresent_task(rs.getString("present_task"));
				return teammateDto;
			}
		});
	}
	
	public List<TeammateDto> getTeammateListBySqlSession() {
		System.out.println("mybatis 설정 및 조회 테스트 성공");
		// Mapper XML 을 활용하여 조회 쿼리 실행
		return sqlSession.selectList("teammate.selectAll");
	}
}
