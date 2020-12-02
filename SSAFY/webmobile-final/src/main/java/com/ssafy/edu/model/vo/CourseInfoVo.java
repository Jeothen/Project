package com.ssafy.edu.model.vo;

public class CourseInfoVo {
	private int courseinfo_no;
	private int course_no;
	private String subdetailalt;
	private String subdetailimg;
	private String subdetailoverview;
	public CourseInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseInfoVo(int courseinfo_no, int course_no, String subdetailalt, String subdetailimg,
			String subdetailoverview) {
		super();
		this.courseinfo_no = courseinfo_no;
		this.course_no = course_no;
		this.subdetailalt = subdetailalt;
		this.subdetailimg = subdetailimg;
		this.subdetailoverview = subdetailoverview;
	}
	@Override
	public String toString() {
		return "CourseInfoVo [courseinfo_no=" + courseinfo_no + ", course_no=" + course_no + ", subdetailalt="
				+ subdetailalt + ", subdetailimg=" + subdetailimg + ", subdetailoverview=" + subdetailoverview + "]";
	}
	public int getCourseinfo_no() {
		return courseinfo_no;
	}
	public void setCourseinfo_no(int courseinfo_no) {
		this.courseinfo_no = courseinfo_no;
	}
	public int getCourse_no() {
		return course_no;
	}
	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}
	public String getSubdetailalt() {
		return subdetailalt;
	}
	public void setSubdetailalt(String subdetailalt) {
		this.subdetailalt = subdetailalt;
	}
	public String getSubdetailimg() {
		return subdetailimg;
	}
	public void setSubdetailimg(String subdetailimg) {
		this.subdetailimg = subdetailimg;
	}
	public String getSubdetailoverview() {
		return subdetailoverview;
	}
	public void setSubdetailoverview(String subdetailoverview) {
		this.subdetailoverview = subdetailoverview;
	}
}
