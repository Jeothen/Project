package com.ssafy.edu.model.vo;

public class CommentVo {
	private int comment_no;
	private String text;
	private String id;
	private String num;
	private String time_Stamp;
	public CommentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVo(int comment_no,String text, String id, String num, String time_Stamp) {
		super();
		this.comment_no = comment_no;
		this.text = text;
		this.id = id;
		this.num = num;
		this.time_Stamp = time_Stamp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTime_Stamp() {
		return time_Stamp;
	}
	public void setTime_Stamp(String time_Stamp) {
		this.time_Stamp = time_Stamp;
	}
	@Override
	public String toString() {
		return "CommentVo [text=" + text + ", id=" + id + ", num=" + num + ", time_Stamp=" + time_Stamp + "]";
	}
	
}
