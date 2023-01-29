package cn.wekyjay.www.tools.xfmanager.manager.user;

import lombok.Data;

@Data
public class User{
	public User() { Users.getUserList().add(this); }
	/**
	 * 用户ID
	 */
	private int user_id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户头像链接
	 */
	private Object avatar_urls;
	/**
	 * 在线可见
	 */
	private boolean activity_visible;
	/**
	 * 是否是管理员
	 */
	private boolean is_admin;
}
