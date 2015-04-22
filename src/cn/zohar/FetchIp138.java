package cn.zohar;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class FetchIp138 {
	
	public static String getPublicIP(){
		String ip = "";
		
		org.jsoup.nodes.Document doc = null;
		Connection con = null;
//		System.setProperty("http.proxyHost", "42.121.105.155");
//		System.setProperty("http.proxyPort", "8888");
//		con = Jsoup.connect("http://1111.ip138.com/ic.asp")
		con = Jsoup.connect("http://www.shfair.org.cn/")
				.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C; .NET4.0E)")
				.timeout(50000);
		try {
			doc = con.get();
			org.jsoup.select.Elements els = doc.body().select("center");
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
