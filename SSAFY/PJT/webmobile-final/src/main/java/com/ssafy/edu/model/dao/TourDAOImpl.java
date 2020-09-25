
package com.ssafy.edu.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.mapper.TourMapper;
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

@Repository
public class TourDAOImpl implements ITourDAO{
	@Autowired
	private SqlSessionTemplate template;
	
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public List<TourInfoVo> getTourInfoList(PagingVo pagination) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getTourInfoList(pagination);
	}

	@Override
	public boolean insertDetailCourse(TourInfoVo ti, DetailCourse d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		parameters.put("distance",d.getDistance());
		parameters.put("taketime",d.getTakeTime());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailCourse(parameters);
	}

	@Override
	public List<TourInfoVo> getTourInfoByHere(double mapx, double mapy, double distance) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getTourInfoByHere(mapx, mapy, distance);
	}

	@Override
	public List<TourInfoVo> getAvailableTourInfoByHere(int mapx, int mapy, int distance) {
		return null;
	}

	@Override
	public List<TourInfoVo> getUnAvailableTourInfoByHere(int mapx, int mapy, int distance) {
		return null;
	}

	@Override
	public List<TourInfoVo> getTourInfoByLocation(int areaCode, int sigunguCode) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getTourInfoByLocation(areaCode, sigunguCode);
	}

	@Override
	public DetailCourse getDetailCourseByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailCourseByCId(contentId);
	}

	@Override
	public DetailCultural getDetailCulturalByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailCulturalByCId(contentId);
	}

	@Override
	public DetailFestival getDetailFestivalByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailFestivalByCId(contentId);
	}

	@Override
	public DetailLeports getDetailLeportsByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailLeportsByCId(contentId);
	}

	@Override
	public DetailLodgment getDetailLodgmentByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailLodgmentByCId(contentId);
	}

	@Override
	public DetailRestaurant getDetailRestaurantByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailRestaurantByCId(contentId);
	}

	@Override
	public DetailShopping getDetailShoppingByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailShoppingByCId(contentId);
	}

	@Override
	public DetailTourPlace getDetailTourPlaceByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getDetailTourPlaceByCId(contentId);
	}

	@Override
	public boolean insertDetailCultural(TourInfoVo ti, DetailCultural d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		parameters.put("parkingFee",d.getParkingFee());
		parameters.put("useFee",d.getUseFee());
		parameters.put("useTimeCulture",d.getUseTimeCulture());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailCultural(parameters);
	}

	@Override
	public boolean insertDetailFestival(TourInfoVo ti, DetailFestival d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("agelimit",d.getAgelimit());
		parameters.put("bookingPlace",d.getBookingPlace());
		parameters.put("discountInfoFestival",d.getDiscountInfoFestival());
		parameters.put("eventEndDate",d.getEventEndDate());
		parameters.put("eventPlace",d.getEventPlace());
		parameters.put("eventStartDate",d.getEventStartDate());
		parameters.put("placeInfo",d.getPlaceInfo());
		parameters.put("playTime",d.getPlayTime());
		parameters.put("program",d.getProgram());
		parameters.put("sponsor1",d.getSponsor1());
		parameters.put("sponsor2",d.getSponsor2());
		parameters.put("sponsor1tel",d.getSponsor1tel());
		parameters.put("sponsor2tel",d.getSponsor2tel());
		parameters.put("subevent",d.getSubevent());
		parameters.put("usetimeFestival",d.getUsetimeFestival());
		parameters.put("spendTimeFestival",d.getSpendTimeFestival());

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailFestival(parameters);
	}

	@Override
	public boolean insertDetailLeports(TourInfoVo ti, DetailLeports d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("openPeriod",d.getOpenPeriod());
		parameters.put("parkingFeeLeports",d.getParkingFeeLeports());
		parameters.put("reservation",d.getReservation());
		parameters.put("useFeeLeports",d.getUseFeeLeports());
		parameters.put("useTimeLeports",d.getUseTimeLeports());
		

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailLeports(parameters);
	}

	@Override
	public boolean insertDetailLodgment(TourInfoVo ti, DetailLodgment d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("barbecue",d.getBarbecue());
		parameters.put("campfire",d.getCampfire());
		parameters.put("checkinTime",d.getCheckinTime());
		parameters.put("checkoutTime",d.getCheckoutTime());
		parameters.put("chkcooking",d.getChkcooking());
		parameters.put("foodPlace",d.getFoodPlace());
		parameters.put("pickUp",d.getPickUp());
		parameters.put("publicBath",d.getPublicBath());
		parameters.put("reservationLodgin",d.getReservationLodgin());
		parameters.put("reservationUrl",d.getReservationUrl());
		parameters.put("roomCount",d.getRoomCount());
		parameters.put("roomType",d.getRoomType());
		parameters.put("subfacility",d.getSubfacility());

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailLodgment(parameters);
	}

	@Override
	public boolean insertDetailRestaurant(TourInfoVo ti, DetailRestaurant d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("firstMenu",d.getFirstMenu());
		parameters.put("kidsFacility",d.getKidsFacility());
		parameters.put("openTimeFood",d.getOpenTimeFood());
		parameters.put("packing",d.getPacking());
		parameters.put("reservationFood",d.getReservationFood());
		parameters.put("smoking",d.getSmoking());
		parameters.put("treatMenu",d.getTreatMenu());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailRestaurant(parameters);
	}

	@Override
	public boolean insertDetailShopping(TourInfoVo ti, DetailShopping d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("openTime",d.getOpenTime());
		parameters.put("saleItem",d.getSaleItem());
		parameters.put("shopGuide",d.getShopGuide());
		
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailShopping(parameters);
	}

	@Override
	public boolean insertDetailTourPlace(TourInfoVo ti, DetailTourPlace d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("expAgeRange", d.getExpAgeRange());
		parameters.put("expGuide", d.getExpGuide());
		parameters.put("useTime", d.getUseTime());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode",ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.insertDetailTourPlace(parameters);
	}

	@Override
	public boolean updateDetailCourse(TourInfoVo ti, DetailCourse d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		parameters.put("distance",d.getDistance());
		parameters.put("taketime",d.getTakeTime());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailCourse(parameters);
	}

	@Override
	public boolean updateDetailCultural(TourInfoVo ti, DetailCultural d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		parameters.put("parkingFee",d.getParkingFee());
		parameters.put("useFee",d.getUseFee());
		parameters.put("useTimeCulture",d.getUseTimeCulture());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailCultural(parameters);
	}

	@Override
	public boolean updateDetailFestival(TourInfoVo ti, DetailFestival d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("agelimit",d.getAgelimit());
		parameters.put("bookingPlace",d.getBookingPlace());
		parameters.put("discountInfoFestival",d.getDiscountInfoFestival());
		parameters.put("eventEndDate",d.getEventEndDate());
		parameters.put("eventPlace",d.getEventPlace());
		parameters.put("eventStartDate",d.getEventStartDate());
		parameters.put("placeInfo",d.getPlaceInfo());
		parameters.put("playTime",d.getPlayTime());
		parameters.put("program",d.getProgram());
		parameters.put("sponsor1",d.getSponsor1());
		parameters.put("sponsor2",d.getSponsor2());
		parameters.put("sponsor1tel",d.getSponsor1tel());
		parameters.put("sponsor2tel",d.getSponsor2tel());
		parameters.put("subevent",d.getSubevent());
		parameters.put("usetimeFestival",d.getUsetimeFestival());
		parameters.put("spendTimeFestival",d.getSpendTimeFestival());

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailFestival(parameters);
	}

	@Override
	public boolean updateDetailLeports(TourInfoVo ti, DetailLeports d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("openPeriod",d.getOpenPeriod());
		parameters.put("parkingFeeLeports",d.getParkingFeeLeports());
		parameters.put("reservation",d.getReservation());
		parameters.put("useFeeLeports",d.getUseFeeLeports());
		parameters.put("useTimeLeports",d.getUseTimeLeports());
		

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailLeports(parameters);
	}

	@Override
	public boolean updateDetailLodgment(TourInfoVo ti, DetailLodgment d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("barbecue",d.getBarbecue());
		parameters.put("campfire",d.getCampfire());
		parameters.put("checkinTime",d.getCheckinTime());
		parameters.put("checkoutTime",d.getCheckoutTime());
		parameters.put("chkcooking",d.getChkcooking());
		parameters.put("foodPlace",d.getFoodPlace());
		parameters.put("pickUp",d.getPickUp());
		parameters.put("publicBath",d.getPublicBath());
		parameters.put("reservationLodgin",d.getReservationLodgin());
		parameters.put("reservationUrl",d.getReservationUrl());
		parameters.put("roomCount",d.getRoomCount());
		parameters.put("roomType",d.getRoomType());
		parameters.put("subfacility",d.getSubfacility());

		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailLodgment(parameters);
	}

	@Override
	public boolean updateDetailRestaurant(TourInfoVo ti, DetailRestaurant d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("firstMenu",d.getFirstMenu());
		parameters.put("kidsFacility",d.getKidsFacility());
		parameters.put("openTimeFood",d.getOpenTimeFood());
		parameters.put("packing",d.getPacking());
		parameters.put("reservationFood",d.getReservationFood());
		parameters.put("smoking",d.getSmoking());
		parameters.put("treatMenu",d.getTreatMenu());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailRestaurant(parameters);
	}

	@Override
	public boolean updateDetailShopping(TourInfoVo ti, DetailShopping d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("openTime",d.getOpenTime());
		parameters.put("saleItem",d.getSaleItem());
		parameters.put("shopGuide",d.getShopGuide());
		
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailShopping(parameters);
	}

	@Override
	public boolean updateDetailTourPlace(TourInfoVo ti, DetailTourPlace d) {
		HashMap<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("contentId",ti.getContentId());
		parameters.put("contentTypeId",ti.getContentTypeId());
		parameters.put("mapx",ti.getMapx());
		parameters.put("mapy",ti.getMapy());
		parameters.put("overview",ti.getOverview());
		parameters.put("tel",ti.getTel());
		parameters.put("address",ti.getAddress());
		parameters.put("title",ti.getTitle());
		parameters.put("zipcode",ti.getZipcode());
		parameters.put("image",ti.getImage());
		parameters.put("homepage",ti.getHomepage());
		
		parameters.put("accomcount",d.getAccomcount());
		parameters.put("chkbabycarriage",d.getChkbabycarriage());
		parameters.put("chkcreditcard",d.getChkcreditcard());
		parameters.put("chkpet",d.getChkpet());
		parameters.put("parking",d.getParking());
		parameters.put("infocenter",d.getInfocenter());
		parameters.put("restdate",d.getRestdate());
		
		parameters.put("expAgeRange", d.getExpAgeRange());
		parameters.put("expGuide", d.getExpGuide());
		parameters.put("useTime", d.getUseTime());
		
		parameters.put("areacode", ti.getAreaCode());
		parameters.put("sigungucode", ti.getSigunguCode());
		
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.updateDetailTourPlace(parameters);
	}

	@Override
	public boolean deleteTourInfo(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);

		return mapper.deleteTourInfo(contentId);
	}

	@Override
	public List<AreaVo> getArea() {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getArea();
	}

	@Override
	public List<SigunguVo> getSigungu(int areaCode) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getSigungu(areaCode);
	}

	@Override
	public List<ContentTypeVo> getContentType() {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getContentType();
	}

	@Override
	public TourInfoVo getTourInfoByCId(int contentId) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getTourInfoByCId(contentId);
	}

	@Override
	public int getContentId() {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getContentId();
	}
	@Override
	public int getTourListCnt() {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getTourListCnt();
	}

	@Override
	public int getSearchTourCnt(HashMap<String, String> maps) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getSearchTourCnt(maps);
	}
	@Override
	public int findContentTypeByName(String keyword) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.findContentTypeByName(keyword);
	}

	@Override
	public List<TourInfoVo> getSearchTour(HashMap<String, Object> map) {
		TourMapper mapper = template.getMapper(TourMapper.class);
		return mapper.getSearchTour(map);
	}
}
