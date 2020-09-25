package com.ssafy.edu.model.vo;

public class DetailLeports extends DetailVo{
	private String openPeriod;
	private String parkingFeeLeports;
	private String reservation;
	private String useFeeLeports;
	private String useTimeLeports;
	public DetailLeports() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailLeports(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate,String openPeriod, String parkingFeeLeports, String reservation, String useFeeLeports,
			String useTimeLeports) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		// TODO Auto-generated constructor stub
		this.openPeriod = openPeriod;
		this.parkingFeeLeports = parkingFeeLeports;
		this.reservation = reservation;
		this.useFeeLeports = useFeeLeports;
		this.useTimeLeports = useTimeLeports;
	}
	public DetailLeports(String openPeriod, String parkingFeeLeports, String reservation, String useFeeLeports,
			String useTimeLeports) {
		super();
		this.openPeriod = openPeriod;
		this.parkingFeeLeports = parkingFeeLeports;
		this.reservation = reservation;
		this.useFeeLeports = useFeeLeports;
		this.useTimeLeports = useTimeLeports;
	}
	public String getOpenPeriod() {
		return openPeriod;
	}
	public void setOpenPeriod(String openPeriod) {
		this.openPeriod = openPeriod;
	}
	public String getParkingFeeLeports() {
		return parkingFeeLeports;
	}
	public void setParkingFeeLeports(String parkingFeeLeports) {
		this.parkingFeeLeports = parkingFeeLeports;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getUseFeeLeports() {
		return useFeeLeports;
	}
	public void setUseFeeLeports(String useFeeLeports) {
		this.useFeeLeports = useFeeLeports;
	}
	public String getUseTimeLeports() {
		return useTimeLeports;
	}
	public void setUseTimeLeports(String useTimeLeports) {
		this.useTimeLeports = useTimeLeports;
	}
	@Override
	public String toString() {
		return "DetailLeports [openPeriod=" + openPeriod + ", parkingFeeLeports=" + parkingFeeLeports + ", reservation="
				+ reservation + ", useFeeLeports=" + useFeeLeports + ", useTimeLeports=" + useTimeLeports
				+ ", getContentId()=" + getContentId() + ", getAccomcount()=" + getAccomcount()
				+ ", getChkbabycarriage()=" + getChkbabycarriage() + ", getChkcreditcard()=" + getChkcreditcard()
				+ ", getChkpet()=" + getChkpet() + ", getParking()=" + getParking() + ", getInfocenter()="
				+ getInfocenter() + ", getRestdate()=" + getRestdate() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
 

}
