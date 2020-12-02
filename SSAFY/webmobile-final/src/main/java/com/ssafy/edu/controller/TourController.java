package com.ssafy.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.edu.model.service.IReviewService;
import com.ssafy.edu.model.service.ITourService;
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
import com.ssafy.edu.model.vo.MemberVO;
import com.ssafy.edu.model.vo.PagingVo;
import com.ssafy.edu.model.vo.SigunguVo;
import com.ssafy.edu.model.vo.ReviewVo;
import com.ssafy.edu.model.vo.TourInfoVo;

@RestController
public class TourController {
	private static final Logger logger = LoggerFactory.getLogger(TourController.class);

	@Autowired
	private ITourService tour_service;
	@Autowired
	private IReviewService review_service;

	public void setMem_service(ITourService tour_service) {
		this.tour_service = tour_service;
		this.review_service = review_service;
	}
	// 상세정보 불러올때 ContentTypeId값에 따라서 다르게 불러줘야함.
	// 12:TourPlace 14:Cultural 15:Festival 25:course 28:leports 32:lodgement
	// 38:shopping 39:restaurant

	void temp() {
		int contenttypeid = 0;
		int contentId = 0;
		DetailVo tmp;
		switch (contenttypeid) {
		case 12:
			tour_service.getDetailTourPlaceByCId(contentId);

			break;
		case 14:
			tour_service.getDetailCulturalByCId(contentId);

			break;
		case 15:
			tour_service.getDetailFestivalByCId(contentId);

			break;
		case 25:
			tour_service.getDetailCourseByCId(contentId);
			break;
		case 28:
			tour_service.getDetailLeportsByCId(contentId);
			break;
		case 32:
			tour_service.getDetailLodgmentByCId(contentId);
			break;
		case 38:
			tour_service.getDetailShoppingByCId(contentId);
			break;
		case 39:
			tour_service.getDetailRestaurantByCId(contentId);
			break;
		default:
			break;
		}
	}

	@RequestMapping(value = "insertplace.do")
	public ModelAndView insertPlace() {
		ModelAndView mv = new ModelAndView();
		List<AreaVo> arealist = tour_service.getArea();
		mv.addObject("arealist", arealist);
		mv.setViewName("place/insertplace");
		return mv;
	}

	@RequestMapping(value = "insertDetail.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> insertDetail(HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		int contentid = tour_service.getContentId() + 1;
		int contenttypeid = Integer.parseInt(request.getParameter("contenttypeid"));
		int area = Integer.parseInt(request.getParameter("area"));
		int sigo = Integer.parseInt(request.getParameter("sigo"));
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		String title = request.getParameter("title");
		String overview = request.getParameter("overview");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String image = request.getParameter("image");
		String homepage = request.getParameter("homepage");
		String accomcount = request.getParameter("accomcount");
		String chkbabycarriage = request.getParameter("chkbabycarriage");
		String chkcreditcard = request.getParameter("chkcreditcard");
		String chkpet = request.getParameter("chkpet");
		String infocenter = request.getParameter("infocenter");
		String parking = request.getParameter("parking");
		String restdate = request.getParameter("restdate");
		TourInfoVo tourvo = new TourInfoVo(contentid, contenttypeid, lng, lat, overview, tel, address, title, zipcode,
				image, homepage, 0, area, sigo);
		switch (contenttypeid) {
		case 12:
			String expagerange = request.getParameter("expagerange");
			String expguide = request.getParameter("expguide");
			String usetime = request.getParameter("usetime");
			DetailTourPlace tourplace = new DetailTourPlace(contentid, accomcount, chkbabycarriage, chkcreditcard,
					chkpet, parking, infocenter, restdate, expagerange, expguide, usetime);
			tour_service.insertDetailTourPlace(tourvo, tourplace);
			break;
		case 14:
			String parkingfee = request.getParameter("parkingfee");
			String usefee = request.getParameter("usefee");
			String usetimeculture = request.getParameter("usetimeculture");
			DetailCultural cultural = new DetailCultural(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, parkingfee, usefee, usetimeculture);
			tour_service.insertDetailCultural(tourvo, cultural);
			break;
		case 15:
			String agelimit = request.getParameter("agelimit");
			String bookingPlace = request.getParameter("bookingplace");
			String discountInfoFestival = request.getParameter("discountinfofestival");
			String eventStartDate = request.getParameter("eventstartdate");
			String eventEndDate = request.getParameter("eventenddate");
			String eventPlace = request.getParameter("eventplace");
			String placeInfo = request.getParameter("placeinfo");
			String playTime = request.getParameter("playtime");
			String sponsor1 = request.getParameter("sponsor1");
			String sponsor1tel = request.getParameter("sponsor1tel");
			String sponsor2 = request.getParameter("sponsor2");
			String sponsor2tel = request.getParameter("sponsor2tel");
			String program = request.getParameter("program");
			String subevent = request.getParameter("subevent");
			String usetimeFestival = request.getParameter("usetimefestival");
			String spendTimeFestival = request.getParameter("spendtimefestival");
			DetailFestival festival = new DetailFestival(agelimit, bookingPlace, discountInfoFestival, eventEndDate,
					eventPlace, eventStartDate, placeInfo, playTime, program, sponsor1, sponsor2, sponsor1tel,
					sponsor2tel, subevent, usetimeFestival, spendTimeFestival);
			tour_service.insertDetailFestival(tourvo, festival);
			break;
		case 25:
			String distance = request.getParameter("distance");
			String takeTime = request.getParameter("taketime");
			DetailCourse course = new DetailCourse(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, distance, takeTime);
			tour_service.insertDetailCourse(tourvo, course);
			break;
		case 28:
			String openperiod = request.getParameter("openperiod");
			String parkingfeeleports = request.getParameter("parkingfeeleports");
			String reservation = request.getParameter("reservation");
			String usefeeleports = request.getParameter("usefeeleports");
			String usetimeleports = request.getParameter("usetimeleports");
			DetailLeports leports = new DetailLeports(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, openperiod, parkingfeeleports, reservation, usefeeleports,
					usetimeleports);
			tour_service.insertDetailLeports(tourvo, leports);
			break;
		case 32:
			String barbecue = request.getParameter("barbecue");
			String campfire = request.getParameter("campfire");
			String checkintime = request.getParameter("checkintime");
			String checkouttime = request.getParameter("checkouttime");
			String chkcooking = request.getParameter("chkcooking");
			String foodplace = request.getParameter("foodplace");
			String pickup = request.getParameter("pickup");
			String publicbath = request.getParameter("publicbath");
			String reservationlodgin = request.getParameter("reservationlodgin");
			String reservationurl = request.getParameter("reservationurl");
			String roomcount = request.getParameter("roomcount");
			String roomtype = request.getParameter("roomtype");
			String subfacility = request.getParameter("subfacility");
			DetailLodgment lodgment = new DetailLodgment(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, barbecue, campfire, checkintime, checkouttime, chkcooking, foodplace,
					pickup, publicbath, reservationlodgin, reservationurl, roomcount, roomtype, subfacility);
			tour_service.insertDetailLodgment(tourvo, lodgment);
			break;
		case 38:
			String opentime = request.getParameter("opentime");
			String saleitem = request.getParameter("saleitem");
			String shopguide = request.getParameter("shopguide");
			DetailShopping shopping = new DetailShopping(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, opentime, saleitem, shopguide);
			tour_service.insertDetailShopping(tourvo, shopping);
			break;
		case 39:
			String firstmenu = request.getParameter("firstmenu");
			String kidsfacility = request.getParameter("kidsfacility");
			String opentimefood = request.getParameter("opentimefood");
			String packing = request.getParameter("packing");
			String reservationfood = request.getParameter("reservationfood");
			String smoking = request.getParameter("smoking");
			String treatmenu = request.getParameter("treatmenu");
			DetailRestaurant restaurant = new DetailRestaurant(contentid, accomcount, chkbabycarriage, chkcreditcard,
					chkpet, parking, infocenter, restdate, firstmenu, kidsfacility, opentimefood, packing,
					reservationfood, smoking, treatmenu);
			tour_service.insertDetailRestaurant(tourvo, restaurant);
			break;
		}
		map.put("contentid", contentid);
		map.put("contenttypeid", contenttypeid);
		return map;
	}

	@RequestMapping(value = "sigo.do", method = RequestMethod.GET)
	public @ResponseBody ModelAndView AjaxView(@RequestParam("area") int area) {
		ModelAndView mv = new ModelAndView();
		List<SigunguVo> sigungulist = tour_service.getSigungu(area);
		mv.setViewName("jsonView");
		mv.addObject("sigungu", sigungulist);
		return mv;
	}

	@RequestMapping(value = "placeDetail.do")
	public ModelAndView placeDetail(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int contentID = Integer.parseInt(request.getParameter("id"));
		int contentTypeId = Integer.parseInt(request.getParameter("type"));
		TourInfoVo tourInfo = tour_service.getTourInfoByCId(contentID);
		List<ReviewVo> reviews=review_service.getReviewByTour(contentID);
		mv.addObject("reviews",reviews);		
		//List<AllReview> allReviews=new ArrayList<AllReview>();
				for (int i = 0; i < reviews.size(); i++) {
					//AllReview allrv=new AllReview();
					ReviewVo rv=reviews.get(i);
					//allrv.setRv(rv);
					List<HashMap<String,Object>> resultList=review_service.selectImageByNum(rv.getNum());

					List<String> paramList = new ArrayList<String>();
					try {

					//자신의 데이터 베이스에서 리스트정보를 가져온다.


						mv.addObject("resultList", resultList);
						Iterator<HashMap<String, Object>> itr = resultList.iterator();
						while (itr.hasNext()) {

						    HashMap<String,Object> element = (HashMap<String, Object>) itr.next();
						    byte[] encoded=org.apache.commons.codec.binary.Base64.encodeBase64((byte[]) element.get("file"));
						    String encodedString = new String(encoded);
						    element.put("base64", encodedString);
						    paramList.add(encodedString);
						    //paramList.add(new ReviewImageVo(encodedString));
						    //System.out.println(encodedString);
						    logger.debug("FileInfo : " + encodedString);
						}
						mv.addObject("image"+rv.getNum(),paramList);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					for (int j = 0; j <images.size(); j++) {
//						HashMap<String,Object> image=images.get(j);
//						byte[] imageContent = (byte[]) image.get("review_picture_url");
//					    final HttpHeaders headers = new HttpHeaders();
//					       headers.setContentType(MediaType.IMAGE_PNG);
//					       piclist.add(new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK));
//					}
				}
		if(session.getAttribute("ID")!=null) {
			String id=(String) session.getAttribute("ID");
			if(review_service.selectDib(contentID,id)>0) {
				//mv.addObject("list",review_service.getDibByCId(id));
				mv.addObject("check_dib",true);
			}else {
				mv.addObject("check_dib",false);
			}

		} else {

		}
		mv.addObject("tourInfo", tourInfo);
		switch (contentTypeId) {
		case 12:
			DetailTourPlace tourPlace = tour_service.getDetailTourPlaceByCId(contentID);
			mv.addObject("detail", tourPlace);
			mv.addObject("typeId", 12);
			break;
		case 14:
			DetailCultural culture = tour_service.getDetailCulturalByCId(contentID);
			mv.addObject("detail", culture);
			mv.addObject("typeId", 14);
			break;
		case 15:
			DetailFestival festival = tour_service.getDetailFestivalByCId(contentID);
			mv.addObject("detail", festival);
			mv.addObject("typeId", 15);
			break;
		case 25:
			DetailCourse course = tour_service.getDetailCourseByCId(contentID);
			mv.addObject("detail", course);
			mv.addObject("typeId", 25);
			break;
		case 28:
			DetailLeports leports = tour_service.getDetailLeportsByCId(contentID);
			mv.addObject("detail", leports);
			mv.addObject("typeId", 28);
			break;
		case 32:
			DetailLodgment lodgment = tour_service.getDetailLodgmentByCId(contentID);

			if (lodgment.getReservationUrl().startsWith("a")) {
				lodgment.setReservationUrl("<" + lodgment.getReservationUrl() + ">");
			}

			mv.addObject("detail", lodgment);
			mv.addObject("typeId", 32);
			break;
		case 38:
			DetailShopping shopping = tour_service.getDetailShoppingByCId(contentID);
			mv.addObject("detail", shopping);
			mv.addObject("typeId", 38);
			break;
		case 39:
			DetailRestaurant restaurant = tour_service.getDetailRestaurantByCId(contentID);
			mv.addObject("detail", restaurant);
			mv.addObject("typeId", 39);
			break;

		default:
			break;
		}
		Double star=review_service.getStarByCid(contentID);
		if(star!=null) {
			mv.addObject("star", star);
		}else {
			mv.addObject("star", "점수 정보가 없습니다..");
		}
		mv.setViewName("place/placeDetail");
		return mv;
	}

	@RequestMapping(value = "bfupdateplace.do", method = RequestMethod.GET)
	public ModelAndView bfupdateplace(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int contentId = Integer.parseInt(request.getParameter("contentid"));
		int contenttypeid = Integer.parseInt(request.getParameter("contenttypeid"));
		TourInfoVo tourinfo = tour_service.getTourInfoByCId(contentId);
		mv.addObject("tourinfo", tourinfo);
		List<AreaVo> arealist = tour_service.getArea();
		mv.addObject("arealist", arealist);
		List<SigunguVo> sigungu = tour_service.getSigungu(tourinfo.getAreaCode());
		mv.addObject("sigungu",sigungu);
		switch (contenttypeid) {
		case 12:
			DetailTourPlace tour = tour_service.getDetailTourPlaceByCId(contentId);
			mv.addObject("map", tour);
			break;
		case 14:
			DetailCultural cultural = tour_service.getDetailCulturalByCId(contentId);
			mv.addObject("map", cultural);
			break;
		case 15:
			DetailFestival festival = tour_service.getDetailFestivalByCId(contentId);
			mv.addObject("map", festival);
			break;
		case 25:
			DetailCourse course = tour_service.getDetailCourseByCId(contentId);
			mv.addObject("map", course);
			break;
		case 28:
			DetailLeports leports = tour_service.getDetailLeportsByCId(contentId);
			mv.addObject("map", leports);
			break;
		case 32:
			DetailLodgment lodgment = tour_service.getDetailLodgmentByCId(contentId);
			mv.addObject("map", lodgment);
			break;
		case 38:
			DetailShopping shopping = tour_service.getDetailShoppingByCId(contentId);
			mv.addObject("map", shopping);
			break;
		case 39:
			DetailRestaurant restaurant = tour_service.getDetailRestaurantByCId(contentId);
			mv.addObject("map", restaurant);
			break;
		}
		mv.addObject("contenttypeid", contenttypeid);
		mv.setViewName("place/updateplace");
		return mv;
	}

	@RequestMapping(value = "updateDetail.do")
	@ResponseBody
	public Map<Object, Object> updateDetail(HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		int contentid = Integer.parseInt(request.getParameter("contentid"));
		int contenttypeid = Integer.parseInt(request.getParameter("contenttypeid"));
		int prevconid = Integer.parseInt(request.getParameter("prevconid"));
		int area = Integer.parseInt(request.getParameter("area"));
		int sigo = Integer.parseInt(request.getParameter("sigo"));
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		String title = request.getParameter("title");
		String overview = request.getParameter("overview");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String image = request.getParameter("image");
		String homepage = request.getParameter("homepage");
		String accomcount = request.getParameter("accomcount");
		String chkbabycarriage = request.getParameter("chkbabycarriage");
		String chkcreditcard = request.getParameter("chkcreditcard");
		String chkpet = request.getParameter("chkpet");
		String infocenter = request.getParameter("infocenter");
		String parking = request.getParameter("parking");
		String restdate = request.getParameter("restdate");
		TourInfoVo tourvo = new TourInfoVo(contentid, contenttypeid, lng, lat, overview, tel, address, title, zipcode,
				image, homepage, 0, area, sigo);
		map.put("id", contentid);
		map.put("type",contenttypeid);
		if (contenttypeid != prevconid) {
			tour_service.deleteTourInfo(contentid);
		}
		switch (contenttypeid) {
		case 12:
			String expagerange = request.getParameter("expagerange");
			String expguide = request.getParameter("expguide");
			String usetime = request.getParameter("usetime");
			DetailTourPlace tourplace = new DetailTourPlace(contentid, accomcount, chkbabycarriage, chkcreditcard,
					chkpet, parking, infocenter, restdate, expagerange, expguide, usetime);
			if (contenttypeid != prevconid)
				tour_service.insertDetailTourPlace(tourvo, tourplace);
			else
				tour_service.updateDetailTourPlace(tourvo, tourplace);
			break;
		case 14:
			String parkingfee = request.getParameter("parkingfee");
			String usefee = request.getParameter("usefee");
			String usetimeculture = request.getParameter("usetimeculture");
			DetailCultural cultural = new DetailCultural(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, parkingfee, usefee, usetimeculture);
			if (contenttypeid != prevconid)
				tour_service.insertDetailCultural(tourvo, cultural);
			else
				tour_service.updateDetailCultural(tourvo, cultural);
			break;
		case 15:
			String agelimit = request.getParameter("agelimit");
			String bookingPlace = request.getParameter("bookingplace");
			String discountInfoFestival = request.getParameter("discountinfofestival");
			String eventStartDate = request.getParameter("eventstartdate");
			String eventEndDate = request.getParameter("eventenddate");
			String eventPlace = request.getParameter("eventplace");
			String placeInfo = request.getParameter("placeinfo");
			String playTime = request.getParameter("playtime");
			String sponsor1 = request.getParameter("sponsor1");
			String sponsor1tel = request.getParameter("sponsor1tel");
			String sponsor2 = request.getParameter("sponsor2");
			String sponsor2tel = request.getParameter("sponsor2tel");
			String program = request.getParameter("program");
			String subevent = request.getParameter("subevent");
			String usetimeFestival = request.getParameter("usetimefestival");
			String spendTimeFestival = request.getParameter("spendtimefestival");
			DetailFestival festival = new DetailFestival(agelimit, bookingPlace, discountInfoFestival, eventEndDate,
					eventPlace, eventStartDate, placeInfo, playTime, program, sponsor1, sponsor2, sponsor1tel,
					sponsor2tel, subevent, usetimeFestival, spendTimeFestival);
			if (contenttypeid != prevconid)
				tour_service.insertDetailFestival(tourvo, festival);
			else
				tour_service.updateDetailFestival(tourvo, festival);
			break;
		case 25:
			String distance = request.getParameter("distance");
			String takeTime = request.getParameter("taketime");
			DetailCourse course = new DetailCourse(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, distance, takeTime);
			if (contenttypeid != prevconid)
				tour_service.insertDetailCourse(tourvo, course);
			else
				tour_service.updateDetailCourse(tourvo, course);
			break;
		case 28:
			String openperiod = request.getParameter("openperiod");
			String parkingfeeleports = request.getParameter("parkingfeeleports");
			String reservation = request.getParameter("reservation");
			String usefeeleports = request.getParameter("usefeeleports");
			String usetimeleports = request.getParameter("usetimeleports");
			DetailLeports leports = new DetailLeports(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, openperiod, parkingfeeleports, reservation, usefeeleports,
					usetimeleports);
			if (contenttypeid != prevconid)
				tour_service.insertDetailLeports(tourvo, leports);
			else
				tour_service.updateDetailLeports(tourvo, leports);
			break;
		case 32:
			String barbecue = request.getParameter("barbecue");
			String campfire = request.getParameter("campfire");
			String checkintime = request.getParameter("checkintime");
			String checkouttime = request.getParameter("checkouttime");
			String chkcooking = request.getParameter("chkcooking");
			String foodplace = request.getParameter("foodplace");
			String pickup = request.getParameter("pickup");
			String publicbath = request.getParameter("publicbath");
			String reservationlodgin = request.getParameter("reservationlodgin");
			String reservationurl = request.getParameter("reservationurl");
			String roomcount = request.getParameter("roomcount");
			String roomtype = request.getParameter("roomtype");
			String subfacility = request.getParameter("subfacility");
			DetailLodgment lodgment = new DetailLodgment(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, barbecue, campfire, checkintime, checkouttime, chkcooking, foodplace,
					pickup, publicbath, reservationlodgin, reservationurl, roomcount, roomtype, subfacility);
			if (contenttypeid != prevconid)
				tour_service.insertDetailLodgment(tourvo, lodgment);
			else
				tour_service.updateDetailLodgment(tourvo, lodgment);
			break;
		case 38:
			String opentime = request.getParameter("opentime");
			String saleitem = request.getParameter("saleitem");
			String shopguide = request.getParameter("shopguide");
			DetailShopping shopping = new DetailShopping(contentid, accomcount, chkbabycarriage, chkcreditcard, chkpet,
					parking, infocenter, restdate, opentime, saleitem, shopguide);
			if (contenttypeid != prevconid)
				tour_service.insertDetailShopping(tourvo, shopping);
			else
				tour_service.updateDetailShopping(tourvo, shopping);
			break;
		case 39:
			String firstmenu = request.getParameter("firstmenu");
			String kidsfacility = request.getParameter("kidsfacility");
			String opentimefood = request.getParameter("opentimefood");
			String packing = request.getParameter("packing");
			String reservationfood = request.getParameter("reservationfood");
			String smoking = request.getParameter("smoking");
			String treatmenu = request.getParameter("treatmenu");
			DetailRestaurant restaurant = new DetailRestaurant(contentid, accomcount, chkbabycarriage, chkcreditcard,
					chkpet, parking, infocenter, restdate, firstmenu, kidsfacility, opentimefood, packing,
					reservationfood, smoking, treatmenu);
			if (contenttypeid != prevconid)
				tour_service.insertDetailRestaurant(tourvo, restaurant);
			else
				tour_service.updateDetailRestaurant(tourvo, restaurant);
			break;
		}
		return map;
	}
	@RequestMapping(value = "tourlist.do")
	public ModelAndView memberlist                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        (@RequestParam(required = false, defaultValue = "1")int page,@RequestParam(required = false, defaultValue="1")int range,
			@RequestParam(required = false, defaultValue = "")String searchOption,@RequestParam(required = false, defaultValue="")String keyword) {
		ModelAndView mv = new ModelAndView();
		List<TourInfoVo> members = new ArrayList<TourInfoVo>();
		int listCnt =0;
		if(searchOption.equals("") && keyword.equals(""))
			listCnt = tour_service.getTourListCnt();
		else {
			HashMap<String,String> maps = new HashMap<String, String>();
			maps.put("searchoption", searchOption);
			if(searchOption.equals("CONTENTTYPE"))
				keyword = Integer.toString(tour_service.findContentTypeByName(keyword));
			maps.put("keyword",keyword);
			listCnt = tour_service.getSearchTourCnt(maps);
		}
		System.out.println(listCnt);
		PagingVo pagination = new PagingVo();
		pagination.pageInfo(page, range, listCnt);
	
		if(searchOption.equals(""))
			members = tour_service.getTourInfoList(pagination);
		else {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("searchoption", searchOption);
			map.put("keyword",keyword);
			map.put("startList",pagination.getStartList());
			map.put("listSize",pagination.getListSize());
			members = tour_service.getSearchTour(map);
		}
		mv.addObject("pagination",pagination);
		mv.addObject("tourlist",members);
		List<ContentTypeVo> contenttype = tour_service.getContentType();
		mv.addObject("contenttype",contenttype);
		mv.addObject("searchOption",searchOption);
		mv.addObject("keyword",keyword);
		mv.setViewName("place/tourlist");
		return mv;
	}
	@RequestMapping(value="deleteTour.do")
	public ModelAndView deleteTour(@RequestParam(value="id") int id) {
		tour_service.deleteTourInfo(id);
		return new ModelAndView("redirect:/tourlist.do");
	}
}