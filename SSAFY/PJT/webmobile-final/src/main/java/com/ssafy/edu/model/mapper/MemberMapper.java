package com.ssafy.edu.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;

public interface MemberMapper {

	public MemberVO selectMemberByID(@Param("MEM_ID") String mem_id);
	
	public List<MemberVO> selectAllMemberID(PagingVo pagination);
	
	public String getPassword(@Param("id") String mem_id);

	public int insertMember(MemberVO member);
	
	public int deleteMember(String id);
	//회원정보 수정
	public int updateMember(MemberVO member);
	
	public String getMemberType(String id);
	
	public String checkMemberByID(String mem_id);
	
	public int updateMemberType(HashMap<String,String> map);
	
	public List<MemberVO> searchMember(HashMap<String,Object> map);
	
	public int getMemberListCnt();

	public int getSearchMemberCnt(HashMap<String, String> map);

	public int updatePassword(@Param("id")String id, @Param("password")String password);
	
	public String findUserID(@Param("name")String name, @Param("email")String mail);
	
}
