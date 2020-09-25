package com.ssafy.edu.model.vo;


public class ReviewImageVo {
	private String file;
	public ReviewImageVo() {
		super();
	}
	public ReviewImageVo(String file) {
		super();
		this.file = file;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "ReviewImageVo [file=" + file + "]";
	}
	

	
}
