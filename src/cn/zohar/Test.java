package cn.zohar;

import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.regex.*;

public class Test {
	private static String printBlog(String orgTest) {
		Pattern pattern = Pattern.compile("<title>(.+?)</title>");
		Matcher m = pattern.matcher(orgTest);
		if(!m.find()) {
			return null;
		}
		return m.group(1);
	}
	
	public static void main(String[] args) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://www.163trade.com/");
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
//				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
					String html = EntityUtils.toString(entity1);
					String title = printBlog(html);
//					System.out.println(printBlog(html));
					System.out.println("-----解析后的title-----");
					System.out.println(title);
				
//				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
