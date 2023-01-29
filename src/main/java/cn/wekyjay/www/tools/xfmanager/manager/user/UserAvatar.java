package cn.wekyjay.www.tools.xfmanager.manager.user;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import cn.hutool.json.JSONObject;
import cn.wekyjay.www.tools.xfmanager.Config;

public class UserAvatar {
	private static Map<String,Image> gallery = new HashMap<>();
	private UserAvatar() {}
	
	/**
	 * 头像加入用户图库，如果存在则返回false
	 * @param name
	 * @param img
	 * @return
	 */
	public static boolean putGallery(String name, Image img) {
		if(img == null) img = Config.getConfig().getImage("/avatar.png");
		if(gallery.containsKey(name)) return false;
		else gallery.put(name, img);
		return true;
	}
	
	public static Image getAvatar(String name) {
		return gallery.get(name);
	}
	
	public static void loadAvatar() {
		Users.getUserList().forEach(user->{
			Image img = null;
			File file = new File(Config.getConfig().getFilePath() + "/avatars/" + user.getUsername() + ".png");


			// 如果文件不存在
			if(!file.exists()) {
				String avatar_url = new JSONObject(user.getAvatar_urls()).getStr("s");
				if(avatar_url == null) {
					 img = Config.getConfig().getImage("/images/avatar.png");
				}else {
					if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
					
					URLConnection con = null;
		
					try {
						con = new URL(avatar_url).openConnection();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					con.setConnectTimeout(20 * 1000);
					
			        try (InputStream in = con.getInputStream();
			             OutputStream out = new FileOutputStream(file.getPath())) {
			            //创建缓冲区
			            byte[] buff = new byte[1024];
			            int n;
			            // 开始读取
			            while ((n = in.read(buff)) >= 0) {
			                out.write(buff, 0, n);
			            }
			            out.flush();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
					img = new ImageIcon(file.getAbsolutePath()).getImage();
				}
			}else {
				img = new ImageIcon(file.getAbsolutePath()).getImage();
			}

			UserAvatar.putGallery(user.getUsername(), img);
		});
	}

}
