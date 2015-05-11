package cn.zohar.jsoup;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.LoggerFactory;

public class BaiduResult extends AbstractBaiduSearcher{
	private static final Logger LOG = LoggerFactory.getLogger(BaiduResult.class);
	
	@Override
	public SearchResult search(String keyword) {
		return search(keyword,1);
	}
	@Override
	public SearchResult search(String keyword, int page) {
		int pageSize = 10;
		//百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
		//如获取第一页则pn=0,第二页则pn=10,第三页则pn=20,以此类推
		String url = "http://www.baidu.com/s?pn="+(page-1)*pageSize+"&wd="+keyword;
		
		SearchResult searchResult = new SearchResult();
		searchResult.setPage(page);
		List<Webpage> webpages = new ArrayList<>();
		try {
			Document document = Jsoup.connect(url).get();
			
			//获取搜索结果数目
			int total = getBaiduSearchResultCount(document);
			searchResult.setTotal(total);
			int len = 10;
			if(total < 1) {
				return null;
			}
			//如果搜索到的结果不足一页
			if (total < 10) {
				len = total;
			}
			for(int i = 0;i < len; i++) {
				String titleCssQuery = "";
				String summaryCssQuery = "";
				LOG.debug("titleCssQuery:" + titleCssQuery);
				LOG.debug("summaryCssQuery:" + summaryCssQuery);
				Element titleElement = document.select(titleCssQuery).first();
				String href = "";
				String titleText = "";
				if(titleElement != null) {
					titleText = titleElement.text();
					href = titleElement.attr("href");
				}else {
					//处理百度百科
					titleCssQuery = "";
					summaryCssQuery = "";
					LOG.debug("处理百度百科 titleCssQuery:" + titleCssQuery);
					LOG.debug("处理百度百科 summaryCssQuery: " + summaryCssQuery);
					titleElement = document.select(titleCssQuery).first();
					if(titleElement != null) {
						titleText = titleElement.text();
						href = titleElement.attr("href");
					}
				}
				LOG.debug(titleText);
				Element summaryElement = document.select(summaryCssQuery).first();
				//处理百度知道
				if(summaryElement == null) {
					summaryCssQuery = summaryCssQuery.replace("div.c-abstract", "font");
					LOG.debug("处理百度知道 summaryCssQuery:" + summaryCssQuery);
					summaryElement = document.select(summaryCssQuery).first();
				}
				String summaryText = "";
				if(summaryElement != null) {
					summaryText = summaryElement.text();
				}
				LOG.debug(summaryText);
				
				if(titleText != null && !"".equals(titleText.trim()) && summaryText != null && !"".equals(summaryText.trim())){
					Web
				}
			}
			
			
		}
		
	}
	
	//获取结果总数
	private int getBaiduSearchResultCount(Document document){
		String cssQuery = "html body div div div ";
		LOG.debug("total cssQuery: "+ cssQuery);
		Element totalElement = document.select(cssQuery).first();
		String totalText = totalElement.text();
		LOG.info("搜索结果文本:" +totalText);
		
		String regEx = "[^0-9]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(totalText);
		totalText = matcher.replaceAll("");
		int total = Integer.parseInt(totalText);
		LOG.info("搜索结果数:"+total);
		return total;
	}
	
	
	public static void main(String[] args) {
		Searcher searcher = new BaiduResult();
		SearchResult searchResult = searcher.search("蓝鲸",1);
		List<Webpage> webpages = searchResult.getWebpages();
		if (webpages != null) {
			int i = 1;
			LOG.info("搜索结果 当前第 " + searchResult.getPage() + " 页，页面大小为：" + searchResult.getPageSize() + " 共有结果数: "+ searchResult.getTotal());
			for(Webpage webpage : webpages) {
				LOG.info("搜索结果 " + (i++) + " : ");
				LOG.info("标题：" + webpage.getTitle());
				LOG.info("URL: " + webpage.getUrl());
				LOG.info("摘要:" + webpage.getSummary());
				LOG.info("正文" + webpage.getContent());
				LOG.info("");
			}
		}else {
			LOG.error("没有搜索到结果");
		}
	}
}
