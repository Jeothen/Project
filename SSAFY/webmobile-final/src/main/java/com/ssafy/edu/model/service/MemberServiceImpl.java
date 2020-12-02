package com.ssafy.edu.model.service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.edu.model.dao.IMemberDAO;
import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;

@Component
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDAO dao;

	public void setDao(IMemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public MemberVO selectMemberByID(String mem_id) {
		return dao.selectMemberByID(mem_id);
	}

	@Override
	public List<MemberVO> selectAllMemberID(PagingVo pagination) {
		return dao.selectAllMemberID(pagination);
	}
	@Override
	public boolean checkPassword(String mem_id,String password) {
		boolean check = false;
		if(LockPassword(password).equals(getPassword(mem_id))) { check = true; }
		
		return check;
	}
	
	@Override
	public int insertMember(MemberVO member) {
		member.setPassword(LockPassword(member.getPassword()));
		return dao.insertMember(member);
	}
	private String getPassword(String mem_id) {
		return dao.getPassword(mem_id);
	}
	
	@Override
	public String checkMemberByID(String mem_id) {
		return dao.checkMemberByID(mem_id);
	}
	@Override
	public int updateMemberType(HashMap<String, String> map) {
		return dao.updateMemberType(map);
	}
	@Override
	public int deleteMember(String mem_id) {
		return dao.deleteMember(mem_id);
	}
	private String LockPassword(String password) {
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			for(int i = 0;i < hash.length;i++) {
				String hex = Integer.toHexString(0xff&hash[i]);
				if(hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		}catch(Exception ex) {
			ex.getStackTrace();
		}
		return hexString.toString();
	}

	@Override
	public String getMemberType(String mem_id) {
		return dao.getMemberType(mem_id);
	}

	@Override
	public List<MemberVO> searchMember(HashMap<String, Object> map) {
		return dao.searchMember(map);
	}
	
	@Override
	public int getMemberListCnt() {
		return dao.getMemberListCnt();
	}

	@Override
	public int getSearchMemberCnt(HashMap<String, String> map) {
		return dao.getSearchMemberCnt(map);
	}

	@Override
	public int updateMember(MemberVO member) {
		return dao.updateMember(member);
	}

	@Override
	public int updatePassword(String id, String password) {
		password = LockPassword(password);
		return dao.updatePassword(id, password);
	}

	@Override
	public String findUserID(String name, String mail) {
		return dao.findUserID(name, mail);
	}

}
