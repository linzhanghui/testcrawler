package cn.zohar;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class FetchIp138 {
	
	public static String getPublicIP(){
		String ip = "";
		
		org.jsoup.nodes.Document doc = null;
		Connection con = null;
//		System.setProperty("proxySet", "true");
//		System.setProperty("http.proxyHost", "42.121.105.155");
//		System.setProperty("http.proxyPort", "8888");
//		con = Jsoup.connect("http://1111.ip138.com/ic.asp")
		con = Jsoup.connect("http://www.163trade.com/")
				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:36.0) Gecko/20100101 Firefox/36.0)")
				.timeout(50000);
		try {
			doc = con.get();
			org.jsoup.select.Elements els = doc.body().select("title");
			for(org.jsoup.nodes.Element el : els){
				ip = el.text();
			}
//			ip = ip.replaceAll("[^0-9.]", "");
		} catch (IOException e) {
			e.printStackTrace();
			return ip;
		}
		return ip;
	}
	
	public static void main(String[] args) {
		
		String str = getPublicIP();
		System.out.println(str);
	}
}
