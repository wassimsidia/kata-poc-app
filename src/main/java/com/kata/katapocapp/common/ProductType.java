package com.kata.katapocapp.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wassim on 2018/03/18
 * Kata products Types
 */
public enum ProductType {
	
	HIGHT_TECH("Hight-Tech"), 
	PHONE("Phone");
	
	/**
	 * the event type
	 */
	String type;

	/**
	 * @param type
	 */
	private ProductType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 
	 * @param type the name to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public static ProductType fromString(String type){
		if(HIGHT_TECH.getType().equals(type)) return HIGHT_TECH ;
		if(PHONE.getType().equals(type)) return PHONE ;
		return null ;
	}
	
	
	public static List<String> asStringList(){
		List<String> values = new ArrayList<String>();
		values.add(HIGHT_TECH.getType());
		values.add(PHONE.getType());
		return values ;
	}

}
