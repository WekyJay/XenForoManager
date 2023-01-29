package cn.wekyjay.www.tools.xfmanager.api;

public enum RequestMode {
	POST("Post"),
	GET("Get");
	
	private String mode = null;
	private RequestMode(String mode) {
		this.mode = mode;
	}
	
	@Override
	public String toString() {
		return mode;
	}
}
