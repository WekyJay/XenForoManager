package cn.wekyjay.www.tools.xfmanager.manager.alerts;

import java.util.HashMap;
import java.util.Map;

public class AlertsPrompt {
	private Integer to_user_id;
	private String alert;
	private Integer from_user_id;
	private String link_url;
	private String link_title;
	
	public AlertsPrompt() {
		this.to_user_id = 1;
		this.alert = "你发送了一条提醒。";
	}
	
	/**
	 * [必须]设置发送对象ID
	 * @param id
	 * @return
	 */
	public AlertsPrompt setToUserID(Integer id) {
		if(id != null) this.to_user_id = id;
		return this;
	}
	/**
	 * [必须]设置提醒内容
	 * @param alert
	 * @return
	 */
	public AlertsPrompt setAlert(String alert) {
		if(alert != null) this.alert = alert;
		return this;
	}
	/**
	 * 设置发送者的ID，如果不填则为密钥用户。
	 * @param id
	 * @return
	 */
	public AlertsPrompt setFromUserID(Integer id) {
		if(id != null) this.from_user_id = id;
		return this;
	}
	
	/**
	 * 设置提醒指向的链接。
	 * @param url
	 * @return
	 */
	public AlertsPrompt setLinkUrl(String url) {
		this.link_url = url;
		return this;
	}
	
	/**
	 * 设置提醒的标题，如果不填标题则被提醒内容替代。
	 * @param title
	 * @return
	 */
	public AlertsPrompt setLinkTitle(String title) {
		this.link_title = title;
		return this;
	}
	
	public Map<Object, Object> build(){
		var map = new HashMap<Object, Object>();
		map.put("to_user_id", to_user_id);
		map.put("alert", alert);
		if(this.from_user_id != null) map.put("from_user_id", from_user_id);
		if(this.link_url != null) map.put("link_url", link_url);
		if(this.link_title != null) map.put("link_title", link_title);
		return map;
	}
	
	
	
}
