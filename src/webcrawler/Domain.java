package webcrawler;

public class Domain {
	
	private String domainHash;
	private String domainUrl;
	
	public Domain(String domainHash, String domainUrl) {
		this.domainHash = domainHash;
		this.domainUrl = domainUrl;
	}

	public String getDomainHash() {
		return domainHash;
	}

	public String getDomainUrl() {
		return domainUrl;
	}
	
	
}
