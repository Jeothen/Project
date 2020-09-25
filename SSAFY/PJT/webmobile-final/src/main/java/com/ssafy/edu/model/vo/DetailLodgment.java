package com.ssafy.edu.model.vo;

public class DetailLodgment extends DetailVo{
	private String barbecue;
	private String campfire;
	private String checkinTime;
	private String checkoutTime;
	private String chkcooking;
	private String foodPlace;
	private String pickUp;
	private String publicBath;
	private String reservationLodgin;//예약 안내
	private String reservationUrl;
	private String roomCount;
	private String roomType;
	private String subfacility;
	public DetailLodgment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailLodgment(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate,String barbecue, String campfire, String checkinTime, String checkoutTime, String chkcooking,
			String foodPlace, String pickUp, String publicBath, String reservationLodgin, String reservationUrl,
			String roomCount, String roomType, String subfacility) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		// TODO Auto-generated constructor stub
		this.barbecue = barbecue;
		this.campfire = campfire;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.chkcooking = chkcooking;
		this.foodPlace = foodPlace;
		this.pickUp = pickUp;
		this.publicBath = publicBath;
		this.reservationLodgin = reservationLodgin;
		this.reservationUrl = reservationUrl;
		this.roomCount = roomCount;
		this.roomType = roomType;
		this.subfacility = subfacility;
	}
	public DetailLodgment(String barbecue, String campfire, String checkinTime, String checkoutTime, String chkcooking,
			String foodPlace, String pickUp, String publicBath, String reservationLodgin, String reservationUrl,
			String roomCount, String roomType, String subfacility) {
		super();
		this.barbecue = barbecue;
		this.campfire = campfire;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.chkcooking = chkcooking;
		this.foodPlace = foodPlace;
		this.pickUp = pickUp;
		this.publicBath = publicBath;
		this.reservationLodgin = reservationLodgin;
		this.reservationUrl = reservationUrl;
		this.roomCount = roomCount;
		this.roomType = roomType;
		this.subfacility = subfacility;
	}
	public String getBarbecue() {
		return barbecue;
	}
	public void setBarbecue(String barbecue) {
		this.barbecue = barbecue;
	}
	public String getCampfire() {
		return campfire;
	}
	public void setCampfire(String campfire) {
		this.campfire = campfire;
	}
	public String getCheckinTime() {
		return checkinTime;
	}
	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}
	public String getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	public String getChkcooking() {
		return chkcooking;
	}
	public void setChkcooking(String chkcooking) {
		this.chkcooking = chkcooking;
	}
	public String getFoodPlace() {
		return foodPlace;
	}
	public void setFoodPlace(String foodPlace) {
		this.foodPlace = foodPlace;
	}
	public String getPickUp() {
		return pickUp;
	}
	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}
	public String getPublicBath() {
		return publicBath;
	}
	public void setPublicBath(String publicBath) {
		this.publicBath = publicBath;
	}
	public String getReservationLodgin() {
		return reservationLodgin;
	}
	public void setReservationLodgin(String reservationLodgin) {
		this.reservationLodgin = reservationLodgin;
	}
	public String getReservationUrl() {
		return reservationUrl;
	}
	public void setReservationUrl(String reservationUrl) {
		this.reservationUrl = reservationUrl;
	}
	public String getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getSubfacility() {
		return subfacility;
	}
	public void setSubfacility(String subfacility) {
		this.subfacility = subfacility;
	}
	@Override
	public String toString() {
		return "DetailLodgment [barbecue=" + barbecue + ", campfire=" + campfire + ", checkinTime=" + checkinTime
				+ ", checkoutTime=" + checkoutTime + ", chkcooking=" + chkcooking + ", foodPlace=" + foodPlace
				+ ", pickUp=" + pickUp + ", publicBath=" + publicBath + ", reservationLodgin=" + reservationLodgin
				+ ", reservationUrl=" + reservationUrl + ", roomCount=" + roomCount + ", roomType=" + roomType
				+ ", subfacility=" + subfacility + "]";
	}
	
}
