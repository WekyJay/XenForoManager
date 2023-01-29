package cn.wekyjay.www.tools.xfmanager.api.alerts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import cn.hutool.json.JSONUtil;
import cn.wekyjay.www.tools.xfmanager.Config;
import cn.wekyjay.www.tools.xfmanager.api.RequestMode;
import cn.wekyjay.www.tools.xfmanager.api.XenforoAPI;
import cn.wekyjay.www.tools.xfmanager.api.XfStatusCode;

public class AlertsAPI extends XenforoAPI{
	/**
	 * 获得API(密钥)用户的Alerts列表
	 * @param map
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void doGetAlerts(Map<Object, Object> map) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = request(map, Config.getConfig().getApikey(), AlertsURL.ALERTS_GET.toString(), RequestMode.GET);
		HttpResponse<String> response = getResponse(request);
	}
	/**
	 * 向XenForo提交 Alerts Post 请求.
	 * @param map
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Boolean doPostAlerts(Map<Object, Object> map) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = request(map, Config.getConfig().getSuperApikey(), AlertsURL.ALERTS_POST.toString(), RequestMode.POST);
		HttpResponse<String> response = getResponse(request);
		// 状态码检查
		XfStatusCode xfsc = new XfStatusCode(response);
		if(xfsc.isSuccess()) {
			return JSONUtil.parseObj(response.body()).getBool("success");
		}else {
			System.out.println(xfsc.getMessage());
			return false;
		}
		
	}
}
