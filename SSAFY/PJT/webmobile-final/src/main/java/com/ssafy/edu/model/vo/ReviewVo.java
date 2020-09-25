package com.ssafy.edu.model.vo;

public class ReviewVo {
	private int num;
	private String title;
	private String content;
	private String time_Stamp;
	private String id;
	private String name;
	private int contentid;
	private int star;


	
	public ReviewVo(int num, String title, String content, String time_Stamp, String id, String name, int contentid,
			int star) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.time_Stamp = time_Stamp;
		this.id = id;
		this.name = name;
		this.contentid = contentid;
		this.star = star;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public ReviewVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime_Stamp() {
		return time_Stamp;
	}
	public void setTime_Stamp(String time_Stamp) {
		this.time_Stamp = time_Stamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	@Override
	public String toString() {
		return "ReviewVo [num=" + num + ", title=" + title + ", content=" + content + ", time_Stamp=" + time_Stamp
				+ ", id=" + id + ", name=" + name + ", contentid=" + contentid + ", star=" + star + "]";
	}

	
	
}
