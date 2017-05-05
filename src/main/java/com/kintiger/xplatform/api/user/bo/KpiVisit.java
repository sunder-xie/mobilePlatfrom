package com.kintiger.xplatform.api.user.bo;

import java.io.Serializable;



public class KpiVisit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String v_actual; //实际拜访
	private String v_target; //目标拜访
	private String v_rate; //拜访达成率
	
	public String getV_actual() {
		return v_actual;
	}
	public void setV_actual(String v_actual) {
		this.v_actual = v_actual;
	}
	public String getV_target() {
		return v_target;
	}
	public void setV_target(String v_target) {
		this.v_target = v_target;
	}
	public String getV_rate() {
		return v_rate;
	}
	public void setV_rate(String v_rate) {
		this.v_rate = v_rate;
	}
	
	
	
}
