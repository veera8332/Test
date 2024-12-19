package com.ninza.hrm.api.pojoclass;

public class Project_POJO {
	public String createdBy;
	public String projectName;
	public String status;
	public int teamSize;
	private Project_POJO() {
		//during deserialization
	}
	public Project_POJO(String createdBy, String projectName, String status, int teamSize) {
		super();
		this.createdBy = createdBy;
		this.projectName = projectName;
		this.status = status;
		this.teamSize = teamSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getProjectName() {
		return projectName;
	}
	public String getStatus() {
		return status;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	
}
