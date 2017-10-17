package org.zrhx.com.entity;

import net.sf.json.JSONObject;


/**
 * 桢结构包装类
 * @author gs
 *
 */
public class DataFrame {
	/**
	 * x消息类型
	 */
	private String mesageType;
	/**
	 * 消息体
	 */
	private String conetent;
	/**
	 * 消息json
	 */
	private JSONObject jsonObject;


	public String getMesageType() {
		return mesageType;
	}


	public void setMesageType(String mesageType) {
		this.mesageType = mesageType;
	}


	public String getConetent() {
		return conetent;
	}


	public void setConetent(String conetent) {
		this.conetent = conetent;
	}


	public JSONObject getJsonObject() {
		return jsonObject;
	}


	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	

}
