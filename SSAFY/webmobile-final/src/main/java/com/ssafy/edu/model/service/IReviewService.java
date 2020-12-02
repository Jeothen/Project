package com.ssafy.edu.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.edu.model.vo.CommentVo;
import com.ssafy.edu.model.vo.ReviewImageVo;
import com.ssafy.edu.model.vo.ReviewVo;
import com.ssafy.edu.model.vo.TourInfoVo;



public interface IReviewService {
	//리뷰 정보 입력
	boolean insertReview(ReviewVo rv);
	//리뷰 정보 삭제
	boolean deleteReview(int num);
	//리뷰 정보 수정
	boolean updateReview(ReviewVo rv);
	//해당 관광지 리뷰 정보 반환
	List<ReviewVo> getReviewByTour(int contentId);
	//해당 사용자 리뷰 정보 반환
	List<ReviewVo> getReviewByUser(String id);
	//댓글 정보 입력
	boolean insertComment(CommentVo cm);
	//댓글 정보 삭제
	boolean deleteComment(int num);
	//해당 관광지 댓글 반환
	List<CommentVo> getCommentByTour(int contentId);
	//(해당 사용자 댓글 반환)
	//List<CommentVo> getCommentByUser(String id)

	boolean insertDib(int contentId,String id);
	boolean deleteDib(int contentId,String id);
	int selectDib(int contentId,String id);
	List<TourInfoVo> getDibByCId(String id);
	boolean insertImageByNum(HashMap<String, Object> parameters);
	List<HashMap<String, Object>> selectImageByNum(int num);
	int getRecentlyId();
	Double getStarByCid(int contentId);
}
