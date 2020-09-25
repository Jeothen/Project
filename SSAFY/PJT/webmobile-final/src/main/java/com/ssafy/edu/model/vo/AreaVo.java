package com.ssafy.edu.model.vo;

public class AreaVo {
	private String name;
	private int areanum;
	
	public AreaVo(String name, int areanum) {
		super();
		this.name = name;
		this.areanum = areanum;
	}
	
	public AreaVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAreaNum() {
		return areanum;
	}
	public void setAreaNum(int areanum) {
		this.areanum = areanum;
	}
	@Override
	public String toString() {
		return "AreaVo [name=" + name + ", areanum=" + areanum + "]";
	}
	
}
