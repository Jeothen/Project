package com.ssafy.edu.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;

public interface IMemberDAO {
	
	public MemberVO selectMemberByID(String mem_id);
	
	public List<MemberVO> selectAllMemberID(PagingVo pagination);
	
	public int getMemberListCnt();
	
	public int insertMember(MemberVO member);
	
	public int deleteMember(String mem_id);
	
	public int updateMember(MemberVO member);
	
	public String getMemberType(String mem_id);
	
	public String getPassword(String mem_id);
	
	public String checkMemberByID(String mem_id);
	
	public int updateMemberType(HashMap<String,String> map);
	
	public List<MemberVO> searchMember(HashMap<String,Object> map);

	public int getSearchMemberCnt(HashMap<String, String> map);

	public int updatePassword(String id, String password);
	
	public String findUserID(String name, String email);
	
}
