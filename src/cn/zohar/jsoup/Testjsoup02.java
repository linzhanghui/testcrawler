package cn.zohar.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Testjsoup02 {
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.163trade.com/").get();
//			String title = doc.title();
//			String 
//			Element content = doc.getElementById("body");
			Elements links = doc.getElementsByTag("a"); 
			for(Element link : links) {
				String linkHref = link.attr("href");
				String linkText = link.text();
				System.out.println(linkHref);
				System.out.println(linkText);
			}
//			System.out.println(link);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
