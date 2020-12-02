
package com.ssafy.edu.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.edu.model.dao.ITourDAO;
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
import com.ssafy.edu.model.vo.PagingVo;
import com.ssafy.edu.model.vo.SigunguVo;
import com.ssafy.edu.model.vo.TourInfoVo;

@Component
public class TourServiceImpl implements ITourService{
	@Autowired
	private ITourDAO dao;

	public void setDao(ITourDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<TourInfoVo> getTourInfoList(PagingVo pagination) {
		return dao.getTourInfoList(pagination);
	}

	@Override
	public boolean insertDetailCourse(TourInfoVo ti, DetailCourse d) {
		return dao.insertDetailCourse(ti, d);
	}

	@Override
	public List<TourInfoVo> getTourInfoByHere(double mapx, double mapy, double distance) {
		return dao.getTourInfoByHere(mapx, mapy, distance);
	}

	@Override
	public List<TourInfoVo> getAvailableTourInfoByHere(int mapx, int mapy, int distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourInfoVo> getUnAvailableTourInfoByHere(int mapx, int mapy, int distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourInfoVo> getTourInfoByLocation(int areaCode, int sigunguCode) {
		return dao.getTourInfoByLocation(areaCode, sigunguCode);
	}


	@Override
	public DetailCourse getDetailCourseByCId(int contentId) {
		return dao.getDetailCourseByCId(contentId);
	}

	@Override
	public DetailCultural getDetailCulturalByCId(int contentId) {
		// TODO Auto-generated method stub
		return dao.getDetailCulturalByCId(contentId);
	}

	@Override
	public DetailFestival getDetailFestivalByCId(int contentId) {
		return dao.getDetailFestivalByCId(contentId);
	}

	@Override
	public DetailLeports getDetailLeportsByCId(int contentId) {
		return dao.getDetailLeportsByCId(contentId);
	}

	@Override
	public DetailLodgment getDetailLodgmentByCId(int contentId) {
		return dao.getDetailLodgmentByCId(contentId);
	}

	@Override
	public DetailRestaurant getDetailRestaurantByCId(int contentId) {
		return dao.getDetailRestaurantByCId(contentId);
	}

	@Override
	public DetailShopping getDetailShoppingByCId(int contentId) {
		return dao.getDetailShoppingByCId(contentId);
	}

	@Override
	public DetailTourPlace getDetailTourPlaceByCId(int contentId) {
		return dao.getDetailTourPlaceByCId(contentId);
	}

	@Override
	public boolean insertDetailCultural(TourInfoVo ti, DetailCultural d) {
		return dao.insertDetailCultural(ti, d);
	}

	@Override
	public boolean insertDetailFestival(TourInfoVo ti, DetailFestival d) {
		return dao.insertDetailFestival(ti, d);
	}

	@Override
	public boolean insertDetailLeports(TourInfoVo ti, DetailLeports d) {
		return dao.insertDetailLeports(ti, d);
	}

	@Override
	public boolean insertDetailLodgment(TourInfoVo ti, DetailLodgment d) {
		return dao.insertDetailLodgment(ti, d);
	}

	@Override
	public boolean insertDetailRestaurant(TourInfoVo ti, DetailRestaurant d) {
		return dao.insertDetailRestaurant(ti, d);
	}

	@Override
	public boolean insertDetailShopping(TourInfoVo ti, DetailShopping d) {
		return dao.insertDetailShopping(ti, d);
	}

	@Override
	public boolean insertDetailTourPlace(TourInfoVo ti, DetailTourPlace d) {
		return dao.insertDetailTourPlace(ti, d);
	}

	@Override
	public boolean updateDetailCourse(TourInfoVo ti, DetailCourse d) {
		return dao.updateDetailCourse(ti, d);
	}

	@Override
	public boolean updateDetailCultural(TourInfoVo ti, DetailCultural d) {
		return dao.updateDetailCultural(ti, d);
	}

	@Override
	public boolean updateDetailFestival(TourInfoVo ti, DetailFestival d) {
		return dao.updateDetailFestival(ti, d);
	}

	@Override
	public boolean updateDetailLeports(TourInfoVo ti, DetailLeports d) {
		return dao.updateDetailLeports(ti, d);
	}

	@Override
	public boolean updateDetailLodgment(TourInfoVo ti, DetailLodgment d) {
		return dao.updateDetailLodgment(ti, d);
	}

	@Override
	public boolean updateDetailRestaurant(TourInfoVo ti, DetailRestaurant d) {
		return dao.updateDetailRestaurant(ti, d);
	}

	@Override
	public boolean updateDetailShopping(TourInfoVo ti, DetailShopping d) {
		return dao.updateDetailShopping(ti, d);
	}

	@Override
	public boolean updateDetailTourPlace(TourInfoVo ti, DetailTourPlace d) {
		return dao.updateDetailTourPlace(ti, d);
	}

	@Override
	public boolean deleteTourInfo(int contentId) {
		return dao.deleteTourInfo(contentId);
	}

	@Override
	public List<AreaVo> getArea() {
		return dao.getArea();
	}

	@Override
	public List<SigunguVo> getSigungu(int areaCode) {
		return dao.getSigungu(areaCode);
	}

	@Override
	public List<ContentTypeVo> getContentType() {
		return dao.getContentType();
	}

	@Override
	public TourInfoVo getTourInfoByCId(int contentId) {
		return dao.getTourInfoByCId(contentId);
	}

	@Override
	public int getContentId() {
		return dao.getContentId();
	}
	@Override
	public int getTourListCnt() {
		return dao.getTourListCnt();
	}

	@Override
	public int getSearchTourCnt(HashMap<String, String> maps) {
		return dao.getSearchTourCnt(maps);
	}
	@Override
	public int findContentTypeByName(String keyword) {
		return dao.findContentTypeByName(keyword);
	}

	@Override
	public List<TourInfoVo> getSearchTour(HashMap<String, Object> map) {
		return dao.getSearchTour(map);
	}
}
