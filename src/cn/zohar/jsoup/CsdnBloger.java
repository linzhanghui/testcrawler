package cn.zohar.jsoup;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class CsdnBloger {
	public static void main(String[] args) {
		Map<String, String> cookies = login("username","password");
		String title = "自动发布文章标题";
		String content = "自动发布文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容";
		String tags = "标签1，标签2，标签3";
		publishBlog(cookies, title, content, tags);
	}
	
	public static void publishBlog(Map<String, String> cookies, String title, String content, String tags) throws Exception{
		//发布url和登陆取cookie-url不一致
		String url = "http://write.blog.csdn.net/postedit?edit=1&isPub=1";
		Connection conn = Jsoup.connect(url);
						.header("Accept","*/*")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;0.3")
						.header("Connection", "keep-alive")
						.header("Connection-Type","application/x-www-form-urlencoded; charset=UTF-8")
						.header("Host", "write.blog.csdn.net")
						.header("Pragma","no-cache")
						.header("Referer","http://write.blog.csdn.net/postedit")
						.header("User-Agent","Mozilla/5.0 (Windows NT 6.1;WOW64; rv:26.0) Gecko/20100101 Firefox/26.0")
						.header("X-Requested-With","XMLHttpRequest")
						.data("tags",tags)
						.data("titl",title)
						.data("typ","1")
						.data("cont",content)
						.data("desc","")
						.data("finm","")
						.data("chnl","0")
						.data("comm","2")
						.data("level","0")
						.data("tag2","")
						.data("artid","0")
						.data("stat","publish")
						.ignoreContentType(true);
						for(String cookie : cookies.keySet()){
							conn.cookie(cookie, cookies.get(cookie));
						}
						String text = conn.post().text();
						System.out.println(text);
	}
	
	
	public static Map<String,String> login(String userName, String password) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("u",userName);
		map.put("p",password);
		map.put("t", "log");
		map.put("remember", "0");
		map.put("f", "http%3A%2F%2Fwww.csdn.net%2F");
		map.put("rand","0.4835323424356");
		Connection con = Jsoup.connect("https://passport.csdn.net/ajax/accounthand.shpx");
		conn.header("Accept","*/*");
		conn.header("Accept-Encoding","gzip, deflate");
		conn.header("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.header("Host","passport.csdn.net");
		conn.header("Referer","https://passport.csdn.net/account/login");
		conn.header("User-Agent","Mozilla/5.0 (Windows NT 6.1;WOW64; rv:26.0) Gecko/20100101 Firefox 26.0");
		conn.header("X-Requested-With", "XMLHttpRequest");
		Response response = conn.ignoreContentType(true).method(Method.POST).data(map).execute();
		System.out.println("用户登陆返回信息："+response.body());
		Map<String, String> cookies = response.cookie();
		System.out.println("***************");
		cookies.keySet().stream().forEach((cookie)-> {
			System.out.println(cookie + ":" + cookies.get(cookie));
		});
		System.out.println("*****************");
		return cookies;
	}
}
