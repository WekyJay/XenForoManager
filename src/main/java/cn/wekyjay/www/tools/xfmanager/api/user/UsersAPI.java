package cn.wekyjay.www.tools.xfmanager.api.user;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.wekyjay.www.tools.xfmanager.Config;
import cn.wekyjay.www.tools.xfmanager.GlobalLoader;
import cn.wekyjay.www.tools.xfmanager.api.RequestMode;
import cn.wekyjay.www.tools.xfmanager.api.XenforoAPI;
import cn.wekyjay.www.tools.xfmanager.manager.user.User;
import cn.wekyjay.www.tools.xfmanager.manager.user.Users;

public class UsersAPI extends XenforoAPI{

	/**
	 * 获取或更新用户数据
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void doGetUsers() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		HttpRequest request = UsersAPI.request(new HashMap<Object,Object>(), Config.getConfig().getApikey(), UsersURL.USERS_GET.toString(),RequestMode.GET);
		HttpResponse<String> response = UsersAPI.getResponse(request);
		// 检查返回状态
		if(response.statusCode() == 200) Users.getUserList().clear();
		else return;
		// 获取页码
		int last_page = new JSONObject(JSONUtil.parseObj(response.body())).getJSONObject("pagination").getInt("last_page");
		// 异步遍历
		List<CompletableFuture<HttpResponse<String>>> list = new ArrayList<>();

		for(int i = 1; i <= last_page; i++) {
			Map<Object, Object> map =  new HashMap<Object,Object>();
			map.put("page", i);
			request = UsersAPI.request(map, Config.getConfig().getApikey(), UsersURL.USERS_GET.toString(),RequestMode.GET);
			list.add(UsersAPI.getAsyncResponse(request));
		}
		// 等待任务全部完成
		CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[last_page])); 
		// 任务全部完成，开始解析数据。
		if(list!=null && list.size()>0) {
			 for(int i = 0; i < last_page; i++) {
				response = list.get(i).get();
				JSONArray jsonArray = new JSONObject(JSONUtil.parseObj(response.body())).getJSONArray("users");
				jsonArray.forEach(user->{
					if(user instanceof JSONObject) {
						JSONUtil.toBean((JSONObject)user, User.class);
					}
				});
			 }
		}
		GlobalLoader.globalLatch.countDown();

	}

	
}
