package com.icia.mbp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.mbp.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public String idCheck(String mId) {
		return sql.selectOne("Member.idCheck", mId);
	}


}
