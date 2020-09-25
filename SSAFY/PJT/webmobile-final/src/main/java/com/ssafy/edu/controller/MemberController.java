package com.ssafy.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.edu.model.service.IMemberService;
import com.ssafy.edu.model.service.IReviewService;
import com.ssafy.edu.model.service.ITourService;
import com.ssafy.edu.model.service.SendEmail;
import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;
import com.ssafy.edu.model.vo.ReviewVo;
import com.ssafy.edu.model.vo.TourInfoVo;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private IMemberService member_service;

	public void setMember_service(IMemberService member_service) {
		this.member_service = member_service;
	}
	
	@Autowired
	private ITourService tour_service;

	public void setMem_service(ITourService tour_service) {
		this.tour_service = tour_service;
	}
	
	@Autowired
	private IReviewService review_service;
	
	public void setReview_service(IReviewService review_service) {
		this.review_service = review_service;
	}

	// 메인 페이지
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		double mapx = 0;
		double mapy = 0;
		double distance = 0;
		
		if (request.getParameter("mapx") == null) {
			double sessionMapx = (Double) session.getAttribute("mapx");
			double sessionMapy = (Double) session.getAttribute("mapy");
			double sessionDistance = (Double) session.getAttribute("distance");
			
			mapx = sessionMapx;
			mapy = sessionMapy;
			distance=sessionDistance;
		}else if(request.getParameter("mapx") != null) {
			mapx = Double.parseDouble(request.getParameter("mapx"));
			mapy = Double.parseDouble(request.getParameter("mapy"));
			distance = Double.parseDouble(request.getParameter("distance"));
		}
		
		List<TourInfoVo> tourList =  tour_service.getTourInfoByHere(mapy,mapx, distance);
		mv.addObject("distance", distance);
		mv.addObject("mapy", mapy);
		mv.addObject("mapx", mapx);
		mv.addObject("tourList", tourList);
		mv.setViewName("place/main");
		return mv;
	}
	
	@RequestMapping(value = "selectMember.do")
	public ModelAndView selectMemberByID() {
		ModelAndView mv = new ModelAndView();
		
		String mem_id = "loucks";
		MemberVO member = member_service.selectMemberByID(mem_id);
		
		mv.addObject("member",member);
		mv.setViewName("member/member");
		
		return mv;
	}
	
	@RequestMapping(value = "signUpForm.do") 
	public ModelAndView signUpPage() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/signUpForm");
		return mv;
	}
	
	/**
	 * 아이디 중복검사
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "idCheck.do")
	@ResponseBody
	public  Map<Object, Object> idCheck(HttpServletRequest request){
		String id = request.getParameter("user_id");
		
		String memberId = member_service.checkMemberByID(id);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if (memberId == null) {
			map.put("chk", 0);
		}else {
			map.put("chk",1);
		}
		return map;
	}
	
	//회원 가입 ajax
		@RequestMapping(value = "signUp.do", method = RequestMethod.POST)
		@ResponseBody
		public Map<Object, Object> signUp(HttpServletRequest request) {
			Map<Object, Object> map = new HashMap<Object, Object>();

			String mem_id = request.getParameter("user_id");
			String mem_pass = request.getParameter("user_pw");
			String mem_name = request.getParameter("user_name");
			String mem_mail = request.getParameter("user_mail");
			String mem_phone = request.getParameter("user_phone");
			
			MemberVO vo = new MemberVO();
			vo.setId(mem_id);
			vo.setPassword(mem_pass);
			vo.setEmail(mem_mail);
			vo.setName(mem_name);
			vo.setPhonenum(mem_phone);
			
			int flag = member_service.insertMember(vo);
			
			if (flag == 1) {
				map.put("chk", 0);
			}else {
				map.put("chk", 1);
			}
			return map;
		}
	@RequestMapping(value = "loginForm.do")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
	}
	
	@RequestMapping(value = "login.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> login(HttpSession session, HttpServletRequest request) throws Exception{

		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String mem_id = (String)request.getParameter("user_id");
		String password = (String)request.getParameter("user_pw");
		
		boolean flag = member_service.checkPassword(mem_id, password);
		
		if(flag == true) {
			MemberVO m = member_service.selectMemberByID(mem_id);
			session.setAttribute("ID", mem_id);
			session.setAttribute("name", m.getName());
			session.setAttribute("memberType", m.getMembertype());
			map.put("chk", 1);
		}else {
			map.put("chk", 0);
		}
		return map;
	}

	@RequestMapping(value = "memberlist.do")
	public ModelAndView memberlist                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        (@RequestParam(required = false, defaultValue = "1")int page,@RequestParam(required = false, defaultValue="1")int range,
			@RequestParam(required = false, defaultValue = "")String searchOption,@RequestParam(required = false, defaultValue="")String keyword) {
		ModelAndView mv = new ModelAndView();
		List<MemberVO> members = new ArrayList<MemberVO>();
		int listCnt =0;
		if(searchOption.equals("") && keyword.equals(""))
			listCnt = member_service.getMemberListCnt();
		else {
			HashMap<String,String> maps = new HashMap<String, String>();
			maps.put("searchoption", searchOption);
			maps.put("keyword",keyword);
			listCnt = member_service.getSearchMemberCnt(maps);
		}
		PagingVo pagination = new PagingVo();
		pagination.pageInfo(page, range, listCnt);
	
		if(searchOption.equals(""))
			members = member_service.selectAllMemberID(pagination);
		else {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("searchoption", searchOption);
			map.put("keyword",keyword);
			map.put("startList",pagination.getStartList());
			map.put("listSize",pagination.getListSize());
			members = member_service.searchMember(map);
		}
		mv.addObject("pagination",pagination);
		mv.addObject("memberlist",members);
		mv.addObject("searchOption",searchOption);
		mv.addObject("keyword",keyword);
		mv.setViewName("member/memberlist");
		return mv;
	}
	@RequestMapping(value="deleteMember.do",method=RequestMethod.GET)
	public ModelAndView deleteMember(@RequestParam(value="id") String id) {
		member_service.deleteMember(id);
		return new ModelAndView("redirect:/memberlist.do");
	}
	@RequestMapping(value="updateMemberType.do",method=RequestMethod.GET)
	public ModelAndView updateMemberType(@RequestParam(value="id") String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(id);
		map.put("id",id);
		String membertype = member_service.getMemberType(id);
		System.out.println(membertype);
		if(membertype.equals("admin")) {
			map.put("membertype", "user");
		}else {
			map.put("membertype", "admin");
		}
		member_service.updateMemberType(map);
		return new ModelAndView("redirect:/memberlist.do");
	}
	
	@RequestMapping(value = "setLocation.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> mylocationSetSession(HttpServletRequest request, HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		double mapx = Double.parseDouble(request.getParameter("lat"));
		double mapy = Double.parseDouble(request.getParameter("lon"));
		double distance = 0.0;
		
		if (request.getParameter("distance") == null) {
			distance = 5.0;
		}else {
			distance = Double.parseDouble(request.getParameter("distance"));
		}
		session.setAttribute("mapx", mapx);
		session.setAttribute("mapy", mapy);
		session.setAttribute("distance", distance);
		
		if (session.getAttribute("mapx") == null) {
			map.put("chk", 1);
		}else {
			map.put("chk", 0);
		}
		return map;

	}
	@RequestMapping(value = "setAddress.do", method = RequestMethod.POST)
	@ResponseBody
	public String myAddressSetSession(HttpServletRequest request, HttpSession session){
		String address = request.getParameter("address")+"";
		
		System.out.println(address);
		
		session.setAttribute("address", address);
		return address;
	}
	@RequestMapping(value = "logout.do")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("ID");
		session.removeAttribute("name");
		session.removeAttribute("memberType");
		
		mv.setViewName("redirect:main.do");
		return mv;
	}
	
	@RequestMapping(value = "myinfo.do")
	public ModelAndView myinfo(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String mem_id = (String)session.getAttribute("ID");
		MemberVO member = member_service.selectMemberByID(mem_id);
		
		List<TourInfoVo> dib =  review_service.getDibByCId(mem_id);
		List<ReviewVo> review =  review_service.getReviewByUser(mem_id);
		//삭제 버튼
		//디테일 페이지 이동
		//content type id
		//content id
		
		mv.addObject("dib",dib);
		mv.addObject("reviews",review);
		mv.addObject("member",member);
		mv.setViewName("member/myinfo");
		return mv;
	}
	
	@RequestMapping(value = "gotoDetail.do", method = RequestMethod.GET)
	public ModelAndView gotoDetail(HttpServletRequest request,int contentid) {
		ModelAndView mv = new ModelAndView();
		TourInfoVo tv= tour_service.getTourInfoByCId(contentid);
		System.out.println(tv.getContentTypeId());
		//삭제 버튼
		//디테일 페이지 이동
		//content type id
		//content id
		mv.addObject("id",tv.getContentId());
		mv.addObject("type",tv.getContentTypeId());
		mv.setViewName("redirect:/placeDetail.do");
		return mv;
	}
	@RequestMapping(value = "deleteReview.do", method = RequestMethod.GET)
	public ModelAndView deleteReview(HttpServletRequest request,int num) {
		ModelAndView mv = new ModelAndView();
		review_service.deleteReview(num);
		mv.setViewName("redirect:/myinfo.do");
		return mv;
	}
	@RequestMapping(value = "deleteReviewDetail.do", method = RequestMethod.GET)
	public ModelAndView deleteReviewDetail(HttpServletRequest request,int num,int contentid) {
		ModelAndView mv = new ModelAndView();
		TourInfoVo tr=tour_service.getTourInfoByCId(contentid);
		review_service.deleteReview(num);
		mv.addObject("id", tr.getContentId());
		mv.addObject("type",tr.getContentTypeId());
		mv.setViewName("redirect:/placeDetail.do");
		return mv;
	}
	@RequestMapping(value = "infoPassCheck.do")
	public ModelAndView infoPassCheck(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		String mem_id = (String)session.getAttribute("ID");
		MemberVO member = member_service.selectMemberByID(mem_id);
		
		mv.addObject("member",member);
		mv.setViewName("member/infoPassCheck");
		
		return mv;
	}
	
	@RequestMapping(value = "passCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object > passCheck(HttpServletRequest request, HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		//session에서 ID를 가져와 Member 정보를 가져온다
		String mem_id = (String)session.getAttribute("ID");
		//사용자가 입력한 password
		String mem_pass = (String)request.getParameter("user_pw");
		boolean flag = member_service.checkPassword(mem_id, mem_pass);
		//1이면 성공 0이면 실패
		if (flag == true) {
			map.put("chk", 1);
		}else {
			map.put("chk", 0);
		}
		return map;
	}
	
	@RequestMapping(value = "infoUpdate.do")
	public ModelAndView myinfoUpdate(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String mem_id = (String)session.getAttribute("ID");
		MemberVO member = member_service.selectMemberByID(mem_id);
		
		mv.addObject("member",member);
		mv.setViewName("member/myinfoUpdate");
		return mv;
	}
	
	@RequestMapping(value = "updateMyInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object > updateMyInfo(HttpServletRequest request, HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		//session에서 ID를 가져와 Member 정보를 가져온다
		String mem_id = (String)session.getAttribute("ID");
		//사용자가 입력한 password
		String mem_mail = (String)request.getParameter("user_mail");
		String mem_phone = (String)request.getParameter("user_phone");
		MemberVO member = member_service.selectMemberByID(mem_id);
		
		if (mem_mail == null) {
			member.setEmail(member.getEmail());
		}else {
			member.setEmail(mem_mail);
		}
		
		if (mem_phone == null) {
			member.setPhonenum(member.getPhonenum());
		}else {
			member.setPhonenum(mem_phone);
		}
		
		int flag = member_service.updateMember(member);
		//1이면 성공 0이면 실패
		if (flag == 1) {
			map.put("chk", 1);
		}else {
			map.put("chk", 0);
		}
		return map;
	}
	
	@RequestMapping(value = "changePass.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> chacnPassword(HttpServletRequest request,HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String mem_id = (String)session.getAttribute("ID");
		String now_pass = request.getParameter("now_pw");
		String password = request.getParameter("user_pw");
		
		boolean flag = member_service.checkPassword(mem_id, now_pass);
		
		if (flag == true) {
			int passflag = member_service.updatePassword(mem_id, password);
			
			if (passflag == 1) {
				map.put("chk", 1);
			}else {
				map.put("chk", 0);
			}
		}else {
			//현재 비밀번호 오류
			map.put("chk", 2);
		}
		
		return map;
	}
	
	@RequestMapping(value = "findMyInfo.do")
	public ModelAndView findMyInfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/findMyInfo");
		return mv;
	}
	
	@RequestMapping(value = "findMyID.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> findMyID(HttpServletRequest request,HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String name = request.getParameter("user_name");
		String mail = request.getParameter("user_mail");
		
		String id = member_service.findUserID(name, mail);
		if (id == null) {
			map.put("chk", 0);
		}else {
			SendEmail mailAPI = new SendEmail();
			mailAPI.sendID(mail, id);
			map.put("chk", 1);
		}
		
		return map;
	}
	
	@RequestMapping(value = "findMyPass.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> findMyPass(HttpServletRequest request,HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String id = request.getParameter("user_id");
		String mail = request.getParameter("user_mail");
		
		String tmpPwd = UUID.randomUUID().toString().replaceAll("-", "");
		tmpPwd = tmpPwd.substring(0,6);
		
		int flag = member_service.updatePassword(id, tmpPwd);
		
		if (flag == 1) {
			SendEmail mailAPI = new SendEmail();
			mailAPI.sendPass(mail, tmpPwd);
			map.put("chk", 1);
		}else {
			map.put("chk", 0);
		}
		
		return map;
	}
	
	@RequestMapping(value = "deleteAccount.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> deleteAccount(HttpServletRequest request,HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		String id = request.getParameter("user_id");
		String pass = request.getParameter("user_pw");
		boolean flag = member_service.checkPassword(id, pass);
		
		if (flag == true) {
			int deleteFlag = member_service.deleteMember(id);
			if (deleteFlag == 1) {
				session.removeAttribute("ID");
				session.removeAttribute("name");
				session.removeAttribute("memberType");
				//1이면 성공
				map.put("chk", 1);
			}else {
				//2면 어디선가 delete실패
				map.put("chk", 2);
			}
		}else {
			//0이면 정보 불일치
			map.put("chk", 0);
		}
		
		return map;
	}
	
	
}
