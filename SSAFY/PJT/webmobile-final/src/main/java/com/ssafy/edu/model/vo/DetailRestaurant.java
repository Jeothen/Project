package com.ssafy.edu.model.vo;

public class DetailRestaurant extends DetailVo{
	private String firstMenu;
	private String kidsFacility;
	private String openTimeFood;
	private String packing;
	private String reservationFood;
	private String smoking;
	private String treatMenu;
	public DetailRestaurant(String firstMenu, String kidsFacility, String openTimeFood, String packing,
			String reservationFood, String smoking, String treatMenu) {
		super();
		this.firstMenu = firstMenu;
		this.kidsFacility = kidsFacility;
		this.openTimeFood = openTimeFood;
		this.packing = packing;
		this.reservationFood = reservationFood;
		this.smoking = smoking;
		this.treatMenu = treatMenu;
	}
	
	public DetailRestaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailRestaurant(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard,
			String chkpet, String parking, String infocenter, String restdate,String firstMenu, String kidsFacility, String openTimeFood, String packing,
			String reservationFood, String smoking, String treatMenu) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		// TODO Auto-generated constructor stub
		this.firstMenu = firstMenu;
		this.kidsFacility = kidsFacility;
		this.openTimeFood = openTimeFood;
		this.packing = packing;
		this.reservationFood = reservationFood;
		this.smoking = smoking;
		this.treatMenu = treatMenu;
	}
	public String getFirstMenu() {
		return firstMenu;
	}
	public void setFirstMenu(String firstMenu) {
		this.firstMenu = firstMenu;
	}
	public String getKidsFacility() {
		return kidsFacility;
	}
	public void setKidsFacility(String kidsFacility) {
		this.kidsFacility = kidsFacility;
	}
	public String getOpenTimeFood() {
		return openTimeFood;
	}
	public void setOpenTimeFood(String openTimeFood) {
		this.openTimeFood = openTimeFood;
	}
	public String getPacking() {
		return packing;
	}
	public void setPacking(String packing) {
		this.packing = packing;
	}
	public String getReservationFood() {
		return reservationFood;
	}
	public void setReservationFood(String reservationFood) {
		this.reservationFood = reservationFood;
	}
	public String getSmoking() {
		return smoking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public String getTreatMenu() {
		return treatMenu;
	}
	public void setTreatMenu(String treatMenu) {
		this.treatMenu = treatMenu;
	}
	@Override
	public String toString() {
		return "DetailRestaurant [firstMenu=" + firstMenu + ", kidsFacility=" + kidsFacility + ", openTimeFood="
				+ openTimeFood + ", packing=" + packing + ", reservationFood=" + reservationFood + ", smoking="
				+ smoking + ", treatMenu=" + treatMenu + ", getContentId()=" + getContentId() + ", getAccomcount()="
				+ getAccomcount() + ", getChkbabycarriage()=" + getChkbabycarriage() + ", getChkcreditcard()="
				+ getChkcreditcard() + ", getChkpet()=" + getChkpet() + ", getParking()=" + getParking()
				+ ", getInfocenter()=" + getInfocenter() + ", getRestdate()=" + getRestdate() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
