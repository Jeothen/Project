
package com.ssafy.edu.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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


public interface TourMapper {
	//전체 관광정보 반환(범위?)
	public List<TourInfoVo> getTourInfoList(PagingVo pagination);
	//관광정보 입력
	//전체 관광정보 반환(범위?)
	//전체 관광정보 반환(범위?)
		//현재위치, 반경에 따른 관광정보 반환+정렬
		List<TourInfoVo> getTourInfoByHere(@Param("mapx") double mapx,@Param("mapy") double mapy,@Param("distance") double distance);
//		//현재위치, 반경에 따른 관광정보 반환(이용가능)+정렬
//		List<TourInfoVo> getAvailableTourInfoByHere(int mapx,int mapy,int distance);
//		//현재위치, 반경에 따른 관광정보 반환(이용 불가능)+정렬
//		List<TourInfoVo> getUnAvailableTourInfoByHere(int mapx,int mapy,int distance);
		//지역에 따른 관광정보 반환
		TourInfoVo getTourInfoByCId(int contentId);
		List<TourInfoVo> getTourInfoByLocation(@Param("areaCode") int areaCode,@Param("sigunguCode")int sigunguCode);
		//Id로? 상세정보 반환
		DetailCourse getDetailCourseByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailCultural getDetailCulturalByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailFestival getDetailFestivalByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailLeports getDetailLeportsByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailLodgment getDetailLodgmentByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailRestaurant getDetailRestaurantByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailShopping getDetailShoppingByCId(@Param("contentId") int contentId);
		//Id로? 상세정보 반환
		DetailTourPlace getDetailTourPlaceByCId(@Param("contentId") int contentId);
		//관광정보 입력
		public boolean insertDetailCourse(HashMap<String,Object> parameters);
		boolean insertDetailCultural(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailFestival(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailLeports(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailLodgment(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailRestaurant(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailShopping(HashMap<String,Object> parameters);
		//관광정보 입력
		boolean insertDetailTourPlace(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailCourse(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailCultural(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailFestival(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailLeports(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailLodgment(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailRestaurant(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailShopping(HashMap<String,Object> parameters);
		//관광정보 수정
		boolean updateDetailTourPlace(HashMap<String,Object> parameters);
		
		//관광정보 삭제
		boolean deleteTourInfo(@Param("contentId") int contentId);
//		//상세정보 입력
//		boolean insertDetail(DetailVo dt);
//		//상세정보 수정
//		boolean updateDetail(DetailVo dt);
//		//상세정보 삭제
//		boolean deleteDetail(int contentId);
		//지역 정보 반환
		List<AreaVo> getArea();//시,도
		List<SigunguVo> getSigungu(int areaCode);//시,군,구
		//컨텐츠 타입 반환
		List<ContentTypeVo> getContentType();

		int getContentId();
		int getTourListCnt();
		public int getSearchTourCnt(HashMap<String, String> maps);
		int findContentTypeByName(String keyword);
		List<TourInfoVo> getSearchTour(HashMap<String, Object> map);
}
