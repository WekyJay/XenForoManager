package cn.wekyjay.www.tools.xfmanager.manager.alerts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import cn.wekyjay.www.tools.xfmanager.api.alerts.AlertsAPI;

public class Alerts {
	/**
	 * 根据AlertsPrompt发送提醒
	 * @param map
	 */
	public static boolean sendPrompt(Map<Object, Object> map) {
		var flag = false;
		try {
			flag = AlertsAPI.doPostAlerts(map);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
