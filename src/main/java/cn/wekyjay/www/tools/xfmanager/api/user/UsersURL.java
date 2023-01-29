package cn.wekyjay.www.tools.xfmanager.api.user;

import cn.wekyjay.www.tools.xfmanager.Config;

public enum UsersURL {
	
	
	USERS_GET(Config.getConfig().getForumUrl() + "api/users/"),
	USERS_POST(Config.getConfig().getForumUrl() + "api/users/"),
	/**
	 * 通过邮箱找到User<br/>
	 * Query参数1：邮箱(String) 	[必须]
	 */
	USERS_FINDMAIL(Config.getConfig().getForumUrl() + "api/users/find-email"),
	/**
	 * 通过用户名找到User<br/>
	 * Query参数1：用户名(String) 	[必须]
	 */
	USERS_FINDNAME(Config.getConfig().getForumUrl() + "api/users/find-name"),
	/**
	 * 通过ID找到User<br/>
	 * 注意：需要替换{id}<br/>
	 * Qurey参数1：with_posts(boolean)<br/>
	 * Qurey参数2：page(integer)
	 * 
	 */
	USERS_GETID(Config.getConfig().getForumUrl() + "api/users/{id}/");
	
	private String url = null;
	
	UsersURL(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return url;
	}
	
}
