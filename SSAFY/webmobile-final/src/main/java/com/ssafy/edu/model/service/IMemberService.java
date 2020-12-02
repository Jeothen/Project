package com.ssafy.edu.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;

public interface IMemberService {
	
	public MemberVO selectMemberByID(String mem_id);
	
	public int getMemberListCnt();
	
	public List<MemberVO> selectAllMemberID(PagingVo pagination);
	
	public int insertMember(MemberVO member);

	boolean checkPassword(String mem_id, String password);
	
	public String checkMemberByID(String id);
	
	public int updateMemberType(HashMap<String,String> map);
	
	public int deleteMember(String mem_id);
	
	public String getMemberType(String mem_id);
	
	public List<MemberVO> searchMember(HashMap<String,Object> map);

	public int getSearchMemberCnt(HashMap<String, String> map);

	public int updateMember(MemberVO member);
	
	public int updatePassword(String id, String password);
	
	public String findUserID(String name, String mail);
}
