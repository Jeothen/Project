package com.ssafy.edu.model.vo;

public class TourInfoVo {
	private int contentId;
	private int contentTypeId;
	private double mapx;
	private double mapy;
	private String overview;
	private String tel;
	private String address;
	private String title;
	private String zipcode;
	private String image;
	private String homepage;
	private double distance;
	private int areaCode;
	private int sigunguCode;
	
	public TourInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TourInfoVo(int contentId, int contentTypeId, double mapx, double mapy, String overview, String tel,
			String address, String title, String zipcode, String image, String homepage, double distance, int areaCode,
			int sigunguCode) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.mapx = mapx;
		this.mapy = mapy;
		this.overview = overview;
		this.tel = tel;
		this.address = address;
		this.title = title;
		this.zipcode = zipcode;
		this.image = image;
		this.homepage = homepage;
		this.distance = distance;
		this.areaCode = areaCode;
		this.sigunguCode = sigunguCode;
	}


	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public double getMapx() {
		return mapx;
	}
	public void setMapx(double mapx) {
		this.mapx = mapx;
	}
	public double getMapy() {
		return mapy;
	}
	public void setMapy(double mapy) {
		this.mapy = mapy;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public int getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}


	public int getSigunguCode() {
		return sigunguCode;
	}


	public void setSigunguCode(int sigunguCode) {
		this.sigunguCode = sigunguCode;
	}


	@Override
	public String toString() {
		return "TourInfoVo [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", mapx=" + mapx + ", mapy="
				+ mapy + ", overview=" + overview + ", tel=" + tel + ", address=" + address + ", title=" + title
				+ ", zipcode=" + zipcode + ", image=" + image + ", homepage=" + homepage + ", distance=" + distance
				+ ", areaCode=" + areaCode + ", sigunguCode=" + sigunguCode + "]";
	}



	
}
