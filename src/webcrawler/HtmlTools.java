package webcrawler;

public class HtmlTools {
	public static String fixUrl(String inUrl, Domain domain) {
		String url = "";
		
		if(inUrl.endsWith("/")){
			
		}
		
		if((inUrl.startWith(domain.getDomainUrl())){
			if(inUrl.startsWith("/")){
				url = domain.getDomainUrl().concat(inUrl);
			}else {
				url = domain.getDomainUrl().concat("/"+inUrl);
			}
		}else {
			url = inUrl;
		}
		
		if(url.endswith("/")){
			url = url.substring(0, url.length()-1);
		}
		
	}
	return url;
}
}
