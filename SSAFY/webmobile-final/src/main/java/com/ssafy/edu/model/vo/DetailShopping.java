package com.ssafy.edu.model.vo;

public class DetailShopping extends DetailVo{
	private String openTime;
	private String saleItem;
	private String shopGuide;
	public DetailShopping(String openTime, String saleItem, String shopGuide) {
		super();
		this.openTime = openTime;
		this.saleItem = saleItem;
		this.shopGuide = shopGuide;
	}
	public DetailShopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailShopping(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate,String openTime, String saleItem, String shopGuide) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		// TODO Auto-generated constructor stub
		this.openTime = openTime;
		this.saleItem = saleItem;
		this.shopGuide = shopGuide;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getSaleItem() {
		return saleItem;
	}
	public void setSaleItem(String saleItem) {
		this.saleItem = saleItem;
	}
	public String getShopGuide() {
		return shopGuide;
	}
	public void setShopGuide(String shopGuide) {
		this.shopGuide = shopGuide;
	}
	@Override
	public String toString() {
		return "DetailShopping [openTime=" + openTime + ", saleItem=" + saleItem + ", shopGuide=" + shopGuide
				+ ", getContentId()=" + getContentId() + ", getAccomcount()=" + getAccomcount()
				+ ", getChkbabycarriage()=" + getChkbabycarriage() + ", getChkcreditcard()=" + getChkcreditcard()
				+ ", getChkpet()=" + getChkpet() + ", getParking()=" + getParking() + ", getInfocenter()="
				+ getInfocenter() + ", getRestdate()=" + getRestdate() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
