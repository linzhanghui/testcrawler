package cn.zohar;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ProxyIp {
	private ProxyIp() {}
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyIp.class);
	private static final String ACCEPT = "";
	private static final String ENCODING = "gzip, deflate";
	private static final String LANGUAGE = "";
	private static final String CONNECTION = "keep-alive";
	private static final String USER_AGENT = "";
	private static volatile boolean isSwitching = false;
	private static volatile long lastSwitchTime = 0l;
	//?
	private static final WebClient WEB_CLIENT = new WebClient(BrowserVersion.INTERNET_EXPLOER_11);
	private static final Pattern IP_PATTERN = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|");
	//可用代理ip列表
	private static final List<String> IPS = new ArrayList<>();
	private static volatile int currentIpIndex = 0;
	private static volatile boolean detect = true;
	//五分钟
	private static volatile int detectInterval = 300000;
	private static final Path PROXY_IPS_FILE = Paths.get("src/main/resources/proxy_ips.txt");
	//自身ip地址
	private static String previousIp = getCurrentIp();
	//能隐藏自己ip的代理
	private static final Set<String> EXCELLENT_IPS = new HashSet<>();
	//不能隐藏自己ip的代理
	private static final Set<String> NORMAL_IPS = new HashSet<>();
	private static final Path EXCELLENT_PROXY_IPS_FILE = Paths.get("src/main/xxyz.txt");
	private static final Path NORMAL_PROXY_IPS_FILE = Paths.get("src/main/rxxx.txt");
	
	//?总是要做的动作一行行保存进文本文件
	static {
		Set<String> ipSet = new HashSet<>();
		//如果本地有则读取
		try {
			if(Files.notExists(PROXY_IPS_FILE.getParent())){
				PROXY_IPS_FILE.getParent().toFile().mkdirs();
			}
			if(Files.notExists(PROXY_IPS_FILE)){
				PROXY_IPS_FILE.toFile().createNewFile();
			}
			if(Files.notExists(EXCELLENT_PROXY_IPS_FILE)){
				EXCELLENT_PROXY_IPS_FILE.toFile().createNewFile();
			}
			if(Files.notExists(NORMAL_PROXY_IPS_FILE)){
				NORMAL_PROXY_IPS_FILE.toFile().createNewFile();
			}
			LOGGER.info("代理ip存放路径："+PROXY_IPS_FILE.toAbsolutePath().toString());
			ipSet.addAll(Files.readAllLines(PROXY_IPS_FILE));
			ipSet.addAll(Files.readAllLines(EXCELLENT_PROXY_IPS_FILE));
		}catch(Exception e) {
			LOGGER.error("读取本地代理ip失败",e);
		}
		if(ipSet.isEmpty()){
			//从已知的两个网站获取代理ip和端口
			ipSet.addAll(getProxyIps());
		}
		IPS.addAll(ipSet);
		LOGGER.info("所有IP列表("+IPS.size()+"):");
		AtomicInteger i = new AtomicInteger();
		IPS.forEach(ip->LOGGER.info(i.incrementAndGet()+"、"+ip));
		
		new Thread(()->{
			//检查次数
			int count = 0;
			while(detect) {
				try {
					save();
					if(count%10==9){
						//也要防止被更新ip站点封锁;
						toNewIp();
					}
					Thread.sleep(detectInterval);
					//检查网站是否有新的ip
					getProxyIps().forEach(ip->{
						if(!IPS.contains(ip)) {
							IPS.add(ip);
							LOGGER.info("发现新代理ip：" +ip);
						}
					});
					count++;
				} catch(Exception e) {
					LOGGER.error("更新代理ip出差",e);
				}
			}
		}).start();
	}
	
	public static void stopDetect(){
		detect = false;
	}
	
	public static void startDetect() {
		detect = true;
	}
	
	private static void save() {
		try {
			Set<String> ips = new HashSet<>();
			ips.addAll(Files.readAllLines(PROXY_IPS_FILE));
			ips.addAll(IPS);
			//移除不能隐藏自己的ip
			ips.removeAll(NORMAL_IPS);
		}
	}
	
	
	
	
	
	
	
	
	
	
}
