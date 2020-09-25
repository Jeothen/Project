package com.ssafy.edu.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.edu.model.dao.IReviewDAO;
import com.ssafy.edu.model.vo.CommentVo;
import com.ssafy.edu.model.vo.ReviewImageVo;
import com.ssafy.edu.model.vo.ReviewVo;
import com.ssafy.edu.model.vo.TourInfoVo;

@Component
public class ReviewServiceImpl implements IReviewService{
	@Autowired
	private IReviewDAO dao;
	public void setDao(IReviewDAO dao) {
		this.dao = dao;
	}
	@Override
	public boolean insertReview(ReviewVo rv) {
		return dao.insertReview(rv);
	}

	@Override
	public boolean deleteReview(int num) {
		// TODO Auto-generated method stub
		return dao.deleteReview(num);
	}

	@Override
	public boolean updateReview(ReviewVo rv) {
		// TODO Auto-generated method stub
		return dao.updateReview(rv);
	}

	@Override
	public List<ReviewVo> getReviewByTour(int contentId) {
		// TODO Auto-generated method stub
		return dao.getReviewByTour(contentId);
	}

	@Override
	public List<ReviewVo> getReviewByUser(String id) {
		// TODO Auto-generated method stub
		return dao.getReviewByUser(id);
	}

	@Override
	public boolean insertComment(CommentVo cm) {
		// TODO Auto-generated method stub
		return dao.insertComment(cm);
	}

	@Override
	public boolean deleteComment(int num) {
		// TODO Auto-generated method stub
		return dao.deleteComment(num);
	}

	@Override
	public List<CommentVo> getCommentByTour(int contentId) {
		// TODO Auto-generated method stub
		return dao.getCommentByTour(contentId);
	}
	@Override
	public boolean insertDib(int contentId, String id) {
		// TODO Auto-generated method stub
		return dao.insertDib(contentId, id);
	}
	@Override
	public boolean deleteDib(int contentId, String id) {
		// TODO Auto-generated method stub
		return dao.deleteDib(contentId, id);
	}
	@Override
	public int selectDib(int contentId, String id) {
		// TODO Auto-generated method stub
		return dao.selectDib(contentId, id);
	}
	@Override
	public List<TourInfoVo> getDibByCId(String id) {
		return dao.getDibByCId(id);
	}
	@Override
	public boolean insertImageByNum(HashMap<String, Object> parameters) {
		return dao.insertImageByNum(parameters);
	}
	@Override
	public List<HashMap<String, Object>> selectImageByNum(int num) {
		return dao.selectImageByNum(num);
	}
	@Override
	public int getRecentlyId() {
		return dao.getRecentlyId();
	}
	@Override
	public Double getStarByCid(int contentId) {
		// TODO Auto-generated method stub
		return dao.getStarByCid(contentId);
	}

}
