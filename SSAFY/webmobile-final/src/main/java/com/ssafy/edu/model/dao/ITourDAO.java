
package com.ssafy.edu.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.edu.model.vo.AreaVo;
import com.ssafy.edu.model.vo.ContentTypeVo;
import com.ssafy.edu.model.vo.DetailCourse;
import com.ssafy.edu.model.vo.DetailCultural;
import com.ssafy.edu.model.vo.DetailFestival;
import com.ssafy.edu.model.vo.DetailLeports;
import com.ssafy.edu.model.vo.DetailLodgment;
import com.ssafy.edu.model.vo.DetailRestaurant;
import com.ssafy.edu.model.vo.DetailShopping;
import com.ssafy.edu.model.vo.DetailTourPlace;
import com.ssafy.edu.model.vo.DetailVo;
import com.ssafy.edu.model.vo.PagingVo;
import com.ssafy.edu.model.vo.SigunguVo;
import com.ssafy.edu.model.vo.TourInfoVo;


public interface ITourDAO {
	//전체 관광정보 반환(범위?)
	List<TourInfoVo> getTourInfoList(PagingVo pagination);
	//현재위치, 반경에 따른 관광정보 반환+정렬
	List<TourInfoVo> getTourInfoByHere(double mapx,double mapy,double distance);
	//현재위치, 반경에 따른 관광정보 반환(이용가능)+정렬
	List<TourInfoVo> getAvailableTourInfoByHere(int mapx,int mapy,int distance);
	//현재위치, 반경에 따른 관광정보 반환(이용 불가능)+정렬
	List<TourInfoVo> getUnAvailableTourInfoByHere(int mapx,int mapy,int distance);
	//아이디로 관광정보 반환
	TourInfoVo getTourInfoByCId(int contentId);
	//지역에 따른 관광정보 반환
	List<TourInfoVo> getTourInfoByLocation(int areaCode,int sigunguCode);
	//Id로? 상세정보 반환
	DetailCourse getDetailCourseByCId(int contentId);
	//Id로? 상세정보 반환
	DetailCultural getDetailCulturalByCId(int contentId);
	//Id로? 상세정보 반환
	DetailFestival getDetailFestivalByCId(int contentId);
	//Id로? 상세정보 반환
	DetailLeports getDetailLeportsByCId(int contentId);
	//Id로? 상세정보 반환
	DetailLodgment getDetailLodgmentByCId(int contentId);
	//Id로? 상세정보 반환
	DetailRestaurant getDetailRestaurantByCId(int contentId);
	//Id로? 상세정보 반환
	DetailShopping getDetailShoppingByCId(int contentId);
	//Id로? 상세정보 반환
	DetailTourPlace getDetailTourPlaceByCId(int contentId);
	//관광정보 입력
	boolean insertDetailCourse(TourInfoVo ti,DetailCourse d);
	//관광정보 입력
	boolean insertDetailCultural(TourInfoVo ti,DetailCultural d);
	//관광정보 입력
	boolean insertDetailFestival(TourInfoVo ti,DetailFestival d);
	//관광정보 입력
	boolean insertDetailLeports(TourInfoVo ti,DetailLeports d);
	//관광정보 입력
	boolean insertDetailLodgment(TourInfoVo ti,DetailLodgment d);
	//관광정보 입력
	boolean insertDetailRestaurant(TourInfoVo ti,DetailRestaurant d);
	//관광정보 입력
	boolean insertDetailShopping(TourInfoVo ti,DetailShopping d);
	//관광정보 입력
	boolean insertDetailTourPlace(TourInfoVo ti,DetailTourPlace d);
	//관광정보 수정
	boolean updateDetailCourse(TourInfoVo ti,DetailCourse d);
	//관광정보 수정
	boolean updateDetailCultural(TourInfoVo ti,DetailCultural d);
	//관광정보 수정
	boolean updateDetailFestival(TourInfoVo ti,DetailFestival d);
	//관광정보 수정
	boolean updateDetailLeports(TourInfoVo ti,DetailLeports d);
	//관광정보 수정
	boolean updateDetailLodgment(TourInfoVo ti,DetailLodgment d);
	//관광정보 수정
	boolean updateDetailRestaurant(TourInfoVo ti,DetailRestaurant d);
	//관광정보 수정
	boolean updateDetailShopping(TourInfoVo ti,DetailShopping d);
	//관광정보 수정
	boolean updateDetailTourPlace(TourInfoVo ti,DetailTourPlace d);
	
	//관광정보 삭제
	boolean deleteTourInfo(int contentId);
//	//상세정보 입력
//	boolean insertDetail(DetailVo dt);
//	//상세정보 수정
//	boolean updateDetail(DetailVo dt);
//	//상세정보 삭제
//	boolean deleteDetail(int contentId);
	//지역 정보 반환
	List<AreaVo> getArea();//시,도
	List<SigunguVo> getSigungu(int areaCode);//시,군,구
	//컨텐츠 타입 반환
	List<ContentTypeVo> getContentType();
	
	int getContentId();
	int getTourListCnt();
	int getSearchTourCnt(HashMap<String, String> maps);
	int findContentTypeByName(String keyword);
	List<TourInfoVo> getSearchTour(HashMap<String, Object> map);
}
