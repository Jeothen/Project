package com.ssafy.edu.model.vo;

public class SigunguVo {
	private String name;
	private int areaCode;
	private int sigunguCode;
	public SigunguVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SigunguVo(String name, int areaCode, int sigunguCode) {
		super();
		this.name = name;
		this.areaCode = areaCode;
		this.sigunguCode = sigunguCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "SigunguVo [name=" + name + ", areaCode=" + areaCode + ", sigunguCode=" + sigunguCode + "]";
	}
	
}
