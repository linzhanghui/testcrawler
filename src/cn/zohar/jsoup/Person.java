package cn.zohar.jsoup;

import java.util.List;
import java.util.Map;

public class Person {
	private String name;
	//基本信息
	private Map<String, String> basicInfos;
	//教育经历
	List<String> educations;
	//工作经历
	List<String> jobs;
	//重要事件
	List<String> importants;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getBasicInfos() {
		return basicInfos;
	}
	public void setBasicInfos(Map<String, String> basicInfos) {
		this.basicInfos = basicInfos;
	}
	public List<String> getEducations() {
		return educations;
	}
	public void setEducations(List<String> educations) {
		this.educations = educations;
	}
	public List<String> getJobs() {
		return jobs;
	}
	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	public List<String> getImportants() {
		return importants;
	}
	public void setImportants(List<String> importants) {
		this.importants = importants;
	}
	
}
