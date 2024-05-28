package com.icia.mbp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.mbp.dto.MemberDTO;
import com.icia.mbp.dto.PageDTO;
import com.icia.mbp.dto.SearchDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public String idCheck(String mId) {
		return sql.selectOne("Member.idCheck", mId);
	}

	public void mJoin(MemberDTO member) {
		sql.insert("Member.mJoin", member);
	}

	public MemberDTO mView(String mId) {
		return sql.selectOne("Member.mView", mId);
	}

	public int mCount() {
		return sql.selectOne("Member.mCount");
	}

	public List<MemberDTO> mList(PageDTO paging) {
		return sql.selectList("Member.mList", paging);
	}

	public List<MemberDTO> mSearch(SearchDTO search) {
		return sql.selectList("Member.mSearch", search);
	}

	public void mModify(MemberDTO member) {
		sql.update("Member.mModify", member);
	}

	public void mDelete(String mId) {
		sql.delete("Member.mDelete", mId);
	}
	
	
	
	
	

}
