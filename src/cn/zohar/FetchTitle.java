package cn.zohar;

import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.regex.*;

public class FetchTitle {
	//标题抽取
	private static String printTitle(String orgTest) {
		Pattern pattern = Pattern.compile("<title>(.+?)</title>");
//		Pattern pattern = Pattern.compile("<div class=\"zhanhuimingcheng\">(.+?)</div>");
		Matcher m = pattern.matcher(orgTest);
		if(!m.find()) {
			return null;
		}
		return m.group(1);
	}
	
	//内容抽取
	private static String printMingCheng(String orgTest) {
		Pattern pattern = Pattern.compile("<div class=\"zhanhuimingcheng\">(.+?)</div>");
		Matcher m = pattern.matcher(orgTest);
		if(!m.find()) {
			return null;
		}
		return m.group(1);
	}
	
	
	public static void main(String[] args) throws Exception{
		String urls = "http://www.cnena.com/showroom/bencandy-htm-fid-11-id-24322.html";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(urls);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
//				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
					String html = EntityUtils.toString(entity1);
					String title = printTitle(html);
					String exhibitname = printMingCheng(html);
//					System.out.println(printBlog(html));
					System.out.println("-----解析后的title-----");
					System.out.println(title);
					System.out.println(exhibitname);
					
				
//				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
