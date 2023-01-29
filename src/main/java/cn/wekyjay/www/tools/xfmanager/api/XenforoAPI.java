package cn.wekyjay.www.tools.xfmanager.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import cn.wekyjay.www.tools.xfmanager.Config;
import cn.wekyjay.www.tools.xfmanager.Utils;
import lombok.NonNull;

public abstract class XenforoAPI {
	/**
	 * 向服务器发起请求
	 * @param map
	 * @param apikey
	 * @param url
	 * @param mode
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpRequest request(Map<Object, Object> map, String apikey, String url, RequestMode mode) throws URISyntaxException {
		HttpRequest request;
		if(mode == RequestMode.POST) {
			if(apikey.equals(Config.getConfig().getSuperApikey())) map.put("api_bypass_permissions", 1);
			request = HttpRequest.newBuilder(new URI(url))
					.header("XF-API-Key", apikey)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(Utils.ofFormData(map))
					.build();
		}else{
			if(apikey.equals(Config.getConfig().getSuperApikey())) map.put("api_bypass_permissions", 1);
			if(map.size() > 0) url += "?" + Utils.toQuery(map); // 如果有参数
			request = HttpRequest.newBuilder(new URI(url))
					.header("XF-API-Key", apikey)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.GET()
					.build();
		}
		return request;
		
	}
	/**
	 * 获取服务器response（反馈）的信息
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}
	/**
	 * 异步获取服务器response（反馈）的信息
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static CompletableFuture<HttpResponse<String>> getAsyncResponse(HttpRequest request) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
		return futureResponse;
	}
	
	
}
