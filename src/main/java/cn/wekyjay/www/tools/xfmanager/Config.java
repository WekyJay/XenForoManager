package cn.wekyjay.www.tools.xfmanager;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;

public class Config {
	private String apikey = null;
	private String superApikey = null;
	private String forumUrl = null;
	private static Config config = null;

	
	public static Config getConfig() {
		try {
			return config == null? config = new Config():config;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void reloadConfig() {
		try {
			config = new Config();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Config() throws IOException {
		var props = new Properties();
		
		try(InputStream in = this.getClass().getResourceAsStream("/setting.properties")){
			if(in == null) {
				System.out.println("null");
				return;
			}
			InputStreamReader isr = new InputStreamReader(in);
			props.load(isr);
			isr.close();
		}
		if(props.getProperty("ApiKey") != null)apikey = props.getProperty("ApiKey");
		if(props.getProperty("SuperApiKey") != null)superApikey = props.getProperty("SuperApiKey");
		if(props.getProperty("ForumUrl") != null)forumUrl = props.getProperty("ForumUrl");
	}
	
	public String getApikey() {
		return apikey;
	}
	public String getSuperApikey() {
		return superApikey;
	}
	
	public Image getImage(String str) {
		
		var file = Config.class.getResource(str).getFile();
		var img = new ImageIcon(file).getImage();
		
		
				
		return img;
	}
	
	public String getForumUrl() {
		return forumUrl;
	}
	
	public String getFilePath() {
		String url = System.getProperty("user.dir");
		return url;
		
	}
}


