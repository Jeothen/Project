package com.ssafy.edu.model.vo;

public class ContentTypeVo {
	private String name;
	private int contentTypeId;
	public ContentTypeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContentTypeVo(String name, int contentTypeId) {
		super();
		this.name = name;
		this.contentTypeId = contentTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	@Override
	public String toString() {
		return "ContentTypeVo [name=" + name + ", contentTypeId=" + contentTypeId + "]";
	}
	
}
