package cn.wekyjay.www.tools.xfmanager;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class Utils {

	public static BodyPublisher ofFormData(Map<Object, Object> data) {
		var first = true;
		var builder = new StringBuilder();
		for(Entry<Object, Object> entry : data.entrySet()) {
			if(first) first = false;
			else builder.append("&");
			builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
			builder.append("=");
			builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
		}
		return BodyPublishers.ofString(builder.toString());
	}
	public static String toQuery(Map<Object, Object> data) {
		var first = true;
		var builder = new StringBuilder();
		for(Entry<Object, Object> entry : data.entrySet()) {
			if(first) first = false;
			else builder.append("&");
			builder.append(entry.getKey().toString());
			builder.append("=");
			builder.append(entry.getValue().toString());
		}
		return builder.toString();
	}

	
	public static Image getImg(String url) throws IOException {
		URL b_url = new URL(url);
		URLConnection connection = b_url.openConnection();
		DataInputStream dis = new DataInputStream(connection.getInputStream());
		Image firstImg = ImageIO.read(dis);
		return firstImg;
	}
	
	public static String getResourcePath() {
		/** * 方法一：获取当前可执行jar包所在目录 */
		String filePath = System.getProperty("java.class.path");
		String pathSplit = System.getProperty("path.separator");//得到当前操作系统的分隔符，windows下是";",linux下是":"

		/** * 若没有其他依赖，则filePath的结果应当是该可运行jar包的绝对路径， * 此时我们只需要经过字符串解析，便可得到jar所在目录 */
		if(filePath.contains(pathSplit)){
		    filePath = filePath.substring(0,filePath.indexOf(pathSplit));
		}else if (filePath.endsWith(".jar")) {
		  
		  //截取路径中的jar包名,可执行jar包运行的结果里包含".jar"
		    filePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
		}

			return filePath;
	}
	
    /**
     * 将InputStream写入本地文件
     * @param destination 写入本地目录
     * @param input 输入流
     * @throws IOException IOException
     */
    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();

    }
}
