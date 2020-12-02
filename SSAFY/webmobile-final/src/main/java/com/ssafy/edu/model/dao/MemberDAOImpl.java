package com.ssafy.edu.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.mapper.MemberMapper;
import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;

@Repository
public class MemberDAOImpl implements IMemberDAO {
	@Autowired
	private SqlSessionTemplate template;
		
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public MemberVO selectMemberByID(String mem_id) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.selectMemberByID(mem_id);
	}

	@Override
	public List<MemberVO> selectAllMemberID(PagingVo pagination) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.selectAllMemberID(pagination);
	}

	@Override
	public int insertMember(MemberVO member) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.insertMember(member);
	}

	@Override
	public int deleteMember(String mem_id) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.deleteMember(mem_id);
	}

	@Override
	public int updateMember(MemberVO member) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.updateMember(member);
	}

	@Override
	public String getMemberType(String mem_id) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.getMemberType(mem_id);
	}

	@Override
	public String getPassword(String mem_id) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.getPassword(mem_id);
	}

	@Override
	public String checkMemberByID(String mem_id) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.checkMemberByID(mem_id);
	}

	@Override
	public int updateMemberType(HashMap<String, String> map) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.updateMemberType(map);
	}

	@Override
	public List<MemberVO> searchMember(HashMap<String, Object> map) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.searchMember(map);
	}
	
	@Override
	public int getMemberListCnt() {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.getMemberListCnt();
	}

	@Override
	public int getSearchMemberCnt(HashMap<String, String> map) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.getSearchMemberCnt(map);
	}

	@Override
	public int updatePassword(String id, String password) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.updatePassword(id, password);
	}

	@Override
	public String findUserID(String name, String email) {
		MemberMapper mapper = template.getMapper(MemberMapper.class);
		return mapper.findUserID(name, email);
	}
}
