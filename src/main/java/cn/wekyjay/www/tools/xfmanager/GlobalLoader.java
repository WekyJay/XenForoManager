package cn.wekyjay.www.tools.xfmanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import cn.wekyjay.www.tools.xfmanager.api.user.UsersAPI;
import cn.wekyjay.www.tools.xfmanager.manager.user.UserAvatar;
/**
 * 全局数据加载器
 * @author WekyJay
 *
 */
public class GlobalLoader {
	public static CountDownLatch globalLatch = new CountDownLatch(1);
	public GlobalLoader(){
		// 获取用户数据
		try {
			UsersAPI.doGetUsers();
		} catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 线程等待
		try {
			globalLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 头像数据加载
		UserAvatar.loadAvatar();
//		Main.latch.countDown();
		
	}
	
}
