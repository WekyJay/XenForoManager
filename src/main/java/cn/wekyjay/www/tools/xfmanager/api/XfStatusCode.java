package cn.wekyjay.www.tools.xfmanager.api;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
@Getter
public class XfStatusCode extends HttpStatus{
	private String status = null;
	private String code = null;
	private String message = null;
	private List<String> params = new ArrayList<>();
	
	public XfStatusCode(HttpResponse<String> response) {
		if(response.statusCode() == 200) {
			status = "success";
		}else {
			JSONObject errors = JSONUtil.parseObj(response.body()).getJSONArray("errors").getJSONObject(0);
			this.status = "errors";
			this.code = errors.getStr("code");
			this.message = errors.getStr("message");
			// 改选项有问题待修复.
     		// this.params = errors.getJSONArray("params").toList(String.class);
		}		
	}
	
	public boolean isSuccess(){
		return this.status.equals("success");
	}
}
