package cn.wekyjay.www.tools.xfmanager.api.alerts;

import cn.wekyjay.www.tools.xfmanager.Config;

public enum AlertsURL {
	
	ALERTS_GET(Config.getConfig().getForumUrl() + "api/alerts/"),
	ALERTS_POST(Config.getConfig().getForumUrl() + "api/alerts/");
	
	
	private String url = null;
	
	AlertsURL(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return url;
	}
}
