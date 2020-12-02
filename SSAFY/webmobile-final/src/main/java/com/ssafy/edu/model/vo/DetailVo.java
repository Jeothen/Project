package com.ssafy.edu.model.vo;

public class DetailVo {
	private int contentId;
	private String accomcount;
	private String chkbabycarriage;
	private String chkcreditcard;
	private String chkpet;
	private String parking;
	private String infocenter;
	private String restdate;
	public DetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailVo(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate) {
		super();
		this.contentId = contentId;
		this.accomcount = accomcount;
		this.chkbabycarriage = chkbabycarriage;
		this.chkcreditcard = chkcreditcard;
		this.chkpet = chkpet;
		this.parking = parking;
		this.infocenter = infocenter;
		this.restdate = restdate;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getAccomcount() {
		return accomcount;
	}
	public void setAccomcount(String accomcount) {
		this.accomcount = accomcount;
	}
	public String getChkbabycarriage() {
		return chkbabycarriage;
	}
	public void setChkbabycarriage(String chkbabycarriage) {
		this.chkbabycarriage = chkbabycarriage;
	}
	public String getChkcreditcard() {
		return chkcreditcard;
	}
	public void setChkcreditcard(String chkcreditcard) {
		this.chkcreditcard = chkcreditcard;
	}
	public String getChkpet() {
		return chkpet;
	}
	public void setChkpet(String chkpet) {
		this.chkpet = chkpet;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getInfocenter() {
		return infocenter;
	}
	public void setInfocenter(String infocenter) {
		this.infocenter = infocenter;
	}
	public String getRestdate() {
		return restdate;
	}
	public void setRestdate(String restdate) {
		this.restdate = restdate;
	}
	@Override
	public String toString() {
		return "DetailVo [contentId=" + contentId + ", accomcount=" + accomcount + ", chkbabycarriage="
				+ chkbabycarriage + ", chkcreditcard=" + chkcreditcard + ", chkpet=" + chkpet + ", parking=" + parking
				+ ", infocenter=" + infocenter + ", restdate=" + restdate + "]";
	}
	
}
