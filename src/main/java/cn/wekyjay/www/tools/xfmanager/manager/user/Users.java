package cn.wekyjay.www.tools.xfmanager.manager.user;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private static List<User> users = new ArrayList<>();

	public static List<User> getUserList() {
		return users;
	}
	/**
	 * 获取所有用户的用户名列表
	 * @return
	 */
	public static String[] getUsersName(){
		String[] str = new String[users.size()];
		for(int i = 0; i < users.size(); i++) {
			str[i] = users.get(i).getUsername();
		}
		return str;
		
	}
	/**
	 * 通过用户名获取User
	 * @param name
	 * @return
	 */
	public static User getUserOfName(String name) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(name)) return users.get(i);
		}
		return null;
		
	}
	
}
