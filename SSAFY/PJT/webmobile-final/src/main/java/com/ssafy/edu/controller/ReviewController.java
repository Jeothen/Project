package com.ssafy.edu.controller;



import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.edu.model.service.IReviewService;
import com.ssafy.edu.model.service.ITourService;
import com.ssafy.edu.model.vo.CommentVo;
import com.ssafy.edu.model.vo.ReviewImageVo;
import com.ssafy.edu.model.vo.ReviewVo;



@RestController
public class ReviewController {
	@Autowired
	private IReviewService review_service;
	@Autowired
	private ITourService tour_service;
	
	@RequestMapping(value = "insertrv.do",method = RequestMethod.POST)
	public String insertReview(@ModelAttribute("reviewVo") ReviewVo reviewVo,RedirectAttributes rttr) throws Exception{
		review_service.insertReview(reviewVo);
		return null;
	}
	@RequestMapping(value = "deleterv.do",method = RequestMethod.GET)
	public String deleteReview(Model model,int num) {
		review_service.deleteReview(num);
		return null;
	}
	@RequestMapping(value = "updaterv.do",method = RequestMethod.POST)
	public String updateReview(@ModelAttribute("reviewVo")ReviewVo reviewVo) {
		review_service.updateReview(reviewVo);
		return null;
	}
	@RequestMapping(value = "getReviewByTour.do",method = RequestMethod.GET)
	public String getReviewByTour(Model model,int contentId) {
		review_service.getReviewByTour(contentId);
		return null;
	}
	@RequestMapping(value = "getReviewByUser.do",method = RequestMethod.GET)
	public String getReviewByUser(Model model,String id) {
		review_service.getReviewByUser(id);
		return null;
	}
	@RequestMapping(value = "insertComment.do",method = RequestMethod.POST)
	public String insertComment(Model model,CommentVo cm) {
		review_service.insertComment(cm);
		return null;
	}
	@RequestMapping(value = "deleteComment.do",method = RequestMethod.GET)
	public String deleteComment(Model model,int num) {
		review_service.deleteComment(num);
		return null;
	}
	@RequestMapping(value = "getCommentByTour.do",method = RequestMethod.GET)
	public String getCommentByTour(Model model,int contentId) {
		review_service.getCommentByTour(contentId);
		return null;
	}
	@RequestMapping(value = "changeDib.do",method = RequestMethod.GET)
	public boolean changeDib(HttpSession session,HttpServletRequest request) {
		//ModelAndView mv = new ModelAndView();
		int contentId = Integer.parseInt(request.getParameter("contentId"));
		String id=(String) session.getAttribute("ID");
		if(review_service.selectDib(contentId, id)>0) {
			review_service.deleteDib(contentId, id);
			//mv.addObject("check",false);
			
			return false;
		}else {
			review_service.insertDib(contentId, id);
			//mv.addObject("check",true);
			return true;
		}

		//mv.setViewName("place/placeDetail");
		
		//return mv;
	}
	@RequestMapping(value = "createReview.do",method = RequestMethod.GET)
	public ModelAndView reviewCreate(Model model,int id) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
		mv.addObject("tourInfo",tour_service.getTourInfoByCId(id));
		mv.setViewName("place/createReview");
		return mv;
	}
	@RequestMapping(value = "reviewRequest.do",method = RequestMethod.POST)
    public ModelAndView requestupload2(MultipartHttpServletRequest mtfRequest,HttpServletRequest request,HttpSession session) {
		ReviewVo rv=new ReviewVo();
		rv.setTitle("");
		rv.setContent(request.getParameter("content"));
		rv.setContentid(Integer.parseInt(request.getParameter("contentId")));
		rv.setStar(Integer.parseInt(request.getParameter("star")));
		rv.setId(session.getAttribute("ID")+"");
        Date d = new Date();         // 오늘 날짜 생성
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
        rv.setTime_Stamp(date.format(d));
        review_service.insertReview(rv);
        int num=review_service.getRecentlyId();
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		for (int i = 0; i < fileList.size(); i++) {
			HashMap<String, Object> param = new HashMap<String, Object>();
			//파일이름
			String fileName = fileList.get(i).getOriginalFilename();
			if(fileName.equals("")) {
				continue;
			}
			byte[] bytes;
			try {
				bytes = fileList.get(i).getBytes();
				
				try {
					Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
					param.put("num", num);
					param.put("file", blob);
					param.put("file_name", fileName);
					param.put("file_size", blob.length());
					review_service.insertImageByNum(param);
				} catch (SerialException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
//        for (int i = 0; i < fileList.size(); i++) {
//        	ReviewImageVo riv=new ReviewImageVo();
//        	riv.setReview_picture_url(fileList.get(i));
//        	riv.setNum(num);
//        	review_service.insertImageByNum(riv);
//		}
//        String src = mtfRequest.getParameter("src");
//        System.out.println("src value : " + src);
//
//        String path = "C:\\image\\";

//        for (MultipartFile mf : fileList) {
//            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
//            long fileSize = mf.getSize(); // 파일 사이즈
//            
//            System.out.println("originFileName : " + originFileName);
//            System.out.println("fileSize : " + fileSize);
//
//            String safeFile = path + System.currentTimeMillis() + originFileName;
//            try {
//                mf.transferTo(new File(safeFile));
//            } catch (IllegalStateException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
		System.out.println(request.getParameter("contentTypeId")+"SCSC");
		System.out.println(request.getParameter("contentId")+"SCSC");
		
		return new ModelAndView("redirect:/placeDetail.do?id="+request.getParameter("contentId")+"&type="+request.getParameter("contentTypeId"));
    }


}
