package com.group4.erp;

public class BusinessTripDTO {
	private int work_outside_seq;
	private int RNUM;
	private int emp_no;
	private String emp_name;
	private String dep_name;
	private String mgr_name;
	private String jikup;
	private String destination;
	private String work_outside_reason;
	private String outside_start_time;
	private String outside_end_time;
	
	public int getWork_outside_seq() {
		return work_outside_seq;
	}
	public void setWork_outside_seq(int work_outside_seq) {
		this.work_outside_seq = work_outside_seq;
	}
	public int getRNUM() {
		return RNUM;
	}
	public void setRNUM(int rNUM) {
		RNUM = rNUM;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public String getJikup() {
		return jikup;
	}
	public void setJikup(String jikup) {
		this.jikup = jikup;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getWork_outside_reason() {
		return work_outside_reason;
	}
	public void setWork_outside_reason(String work_outside_reason) {
		this.work_outside_reason = work_outside_reason;
	}
	public String getOutside_start_time() {
		return outside_start_time;
	}
	public void setOutside_start_time(String outside_start_time) {
		this.outside_start_time = outside_start_time;
	}
	public String getOutside_end_time() {
		return outside_end_time;
	}
	public void setOutside_end_time(String outside_end_time) {
		this.outside_end_time = outside_end_time;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	

}
