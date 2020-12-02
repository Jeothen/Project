package com.ssafy.edu.model.vo;

public class DetailFestival extends DetailVo{
	private String agelimit;
	private String bookingPlace;
	private String discountInfoFestival;
	private String eventEndDate;
	private String eventPlace;
	private String eventStartDate;
	private String placeInfo;
	private String playTime;
	private String program;
	private String sponsor1;
	private String sponsor2;
	private String sponsor1tel;
	private String sponsor2tel;
	private String subevent;
	private String usetimeFestival;//이용 요금
	private String spendTimeFestival;//관람 소요시간
	
	
	public DetailFestival() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetailFestival(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate,String agelimit, String bookingPlace, String discountInfoFestival, String eventEndDate,
			String eventPlace, String eventStartDate, String placeInfo, String playTime, String program,
			String sponsor1, String sponsor2, String sponsor1tel, String sponsor2tel, String subevent,
			String usetimeFestival, String spendTimeFestival) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		this.agelimit = agelimit;
		this.bookingPlace = bookingPlace;
		this.discountInfoFestival = discountInfoFestival;
		this.eventEndDate = eventEndDate;
		this.eventPlace = eventPlace;
		this.eventStartDate = eventStartDate;
		this.placeInfo = placeInfo;
		this.playTime = playTime;
		this.program = program;
		this.sponsor1 = sponsor1;
		this.sponsor2 = sponsor2;
		this.sponsor1tel = sponsor1tel;
		this.sponsor2tel = sponsor2tel;
		this.subevent = subevent;
		this.usetimeFestival = usetimeFestival;
		this.spendTimeFestival = spendTimeFestival;
		// TODO Auto-generated constructor stub
	}
	public DetailFestival(String agelimit, String bookingPlace, String discountInfoFestival, String eventEndDate,
			String eventPlace, String eventStartDate, String placeInfo, String playTime, String program,
			String sponsor1, String sponsor2, String sponsor1tel, String sponsor2tel, String subevent,
			String usetimeFestival, String spendTimeFestival) {
		super();
		this.agelimit = agelimit;
		this.bookingPlace = bookingPlace;
		this.discountInfoFestival = discountInfoFestival;
		this.eventEndDate = eventEndDate;
		this.eventPlace = eventPlace;
		this.eventStartDate = eventStartDate;
		this.placeInfo = placeInfo;
		this.playTime = playTime;
		this.program = program;
		this.sponsor1 = sponsor1;
		this.sponsor2 = sponsor2;
		this.sponsor1tel = sponsor1tel;
		this.sponsor2tel = sponsor2tel;
		this.subevent = subevent;
		this.usetimeFestival = usetimeFestival;
		this.spendTimeFestival = spendTimeFestival;
	}
	public String getAgelimit() {
		return agelimit;
	}
	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}
	public String getBookingPlace() {
		return bookingPlace;
	}
	public void setBookingPlace(String bookingPlace) {
		this.bookingPlace = bookingPlace;
	}
	public String getDiscountInfoFestival() {
		return discountInfoFestival;
	}
	public void setDiscountInfoFestival(String discountInfoFestival) {
		this.discountInfoFestival = discountInfoFestival;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getPlaceInfo() {
		return placeInfo;
	}
	public void setPlaceInfo(String placeInfo) {
		this.placeInfo = placeInfo;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSponsor1() {
		return sponsor1;
	}
	public void setSponsor1(String sponsor1) {
		this.sponsor1 = sponsor1;
	}
	public String getSponsor2() {
		return sponsor2;
	}
	public void setSponsor2(String sponsor2) {
		this.sponsor2 = sponsor2;
	}
	public String getSponsor1tel() {
		return sponsor1tel;
	}
	public void setSponsor1tel(String sponsor1tel) {
		this.sponsor1tel = sponsor1tel;
	}
	public String getSponsor2tel() {
		return sponsor2tel;
	}
	public void setSponsor2tel(String sponsor2tel) {
		this.sponsor2tel = sponsor2tel;
	}
	public String getSubevent() {
		return subevent;
	}
	public void setSubevent(String subevent) {
		this.subevent = subevent;
	}
	public String getUsetimeFestival() {
		return usetimeFestival;
	}
	public void setUsetimeFestival(String usetimeFestival) {
		this.usetimeFestival = usetimeFestival;
	}
	public String getSpendTimeFestival() {
		return spendTimeFestival;
	}
	public void setSpendTimeFestival(String spendTimeFestival) {
		this.spendTimeFestival = spendTimeFestival;
	}
	@Override
	public String toString() {
		return "DetailFestival [agelimit=" + agelimit + ", bookingPlace=" + bookingPlace + ", discountInfoFestival="
				+ discountInfoFestival + ", eventEndDate=" + eventEndDate + ", eventPlace=" + eventPlace
				+ ", eventStartDate=" + eventStartDate + ", placeInfo=" + placeInfo + ", playTime=" + playTime
				+ ", program=" + program + ", sponsor1=" + sponsor1 + ", sponsor2=" + sponsor2 + ", sponsor1tel="
				+ sponsor1tel + ", sponsor2tel=" + sponsor2tel + ", subevent=" + subevent + ", usetimeFestival="
				+ usetimeFestival + ", spendTimeFestival=" + spendTimeFestival + "]";
	}
	
	
}
