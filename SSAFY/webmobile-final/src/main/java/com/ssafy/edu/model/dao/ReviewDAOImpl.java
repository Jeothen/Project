package com.ssafy.edu.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.mapper.ReviewMapper;
import com.ssafy.edu.model.vo.CommentVo;
import com.ssafy.edu.model.vo.ReviewImageVo;
import com.ssafy.edu.model.vo.ReviewVo;
import com.ssafy.edu.model.vo.TourInfoVo;


@Repository
public class ReviewDAOImpl implements IReviewDAO{
	@Autowired
	private SqlSessionTemplate template;
	
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public boolean insertReview(ReviewVo rv) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.insertReview(rv);
	}

	@Override
	public boolean deleteReview(int num) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.deleteReview(num);
	}

	@Override
	public boolean updateReview(ReviewVo rv) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.updateReview(rv);
	}

	@Override
	public List<ReviewVo> getReviewByTour(int contentId) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getReviewByTour(contentId);
	}

	@Override
	public List<ReviewVo> getReviewByUser(String id) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getReviewByUser(id);
	}

	@Override
	public boolean insertComment(CommentVo cm) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.insertComment(cm);
	}

	@Override
	public boolean deleteComment(int num) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.deleteComment(num);
	}

	@Override
	public List<CommentVo> getCommentByTour(int contentId) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getCommentByTour(contentId);
	}

	@Override
	public boolean insertDib(int contentId, String id) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		HashMap<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("contentId", contentId);
		parameters.put("id", id);
		
		return mapper.insertDib(parameters);
	}

	@Override
	public boolean deleteDib(int contentId, String id) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		HashMap<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("contentId", contentId);
		parameters.put("id", id);
		
		return mapper.deleteDib(parameters);
	}

	@Override
	public int selectDib(int contentId, String id) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		HashMap<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("contentId", contentId);
		parameters.put("id", id);
		
		return mapper.selectDib(parameters);
	}

	@Override
	public List<TourInfoVo> getDibByCId(String id) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getDibByCId(id);
	}

	@Override
	public boolean insertImageByNum(HashMap<String, Object> parameters) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.insertImageByNum(parameters);
	}

	@Override
	public List<HashMap<String, Object>> selectImageByNum(int num) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.selectImageByNum(num);
	}

	@Override
	public int getRecentlyId() {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getRecentlyId();
	}

	@Override
	public Double getStarByCid(int contentId) {
		ReviewMapper mapper = template.getMapper(ReviewMapper.class);
		return mapper.getStarByCid(contentId);
	}


}
