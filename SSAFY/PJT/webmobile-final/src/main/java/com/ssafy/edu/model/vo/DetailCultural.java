package com.ssafy.edu.model.vo;

public class DetailCultural extends DetailVo{
	private String parkingFee;
	private String useFee;
	private String useTimeCulture;//이용 시간
	public DetailCultural(String parkingFee, String useFee, String useTimeCulture) {
		super();
		this.parkingFee = parkingFee;
		this.useFee = useFee;
		this.useTimeCulture = useTimeCulture;
	}
	public DetailCultural() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailCultural(int contentId, String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet,
			String parking, String infocenter, String restdate,String parkingFee, String useFee, String useTimeCulture) {
		super(contentId, accomcount, chkbabycarriage, chkcreditcard, chkpet, parking, infocenter, restdate);
		// TODO Auto-generated constructor stub
		this.parkingFee = parkingFee;
		this.useFee = useFee;
		this.useTimeCulture = useTimeCulture;
	}
	public String getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}
	public String getUseFee() {
		return useFee;
	}
	public void setUseFee(String useFee) {
		this.useFee = useFee;
	}
	public String getUseTimeCulture() {
		return useTimeCulture;
	}
	public void setUseTimeCulture(String useTimeCulture) {
		this.useTimeCulture = useTimeCulture;
	}
	
}
