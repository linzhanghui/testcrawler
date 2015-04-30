package cn.zohar.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Testjsoup01 {
	public static void main(String[] args){
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed html into a doc.</body></html>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println(body);
	}
}
