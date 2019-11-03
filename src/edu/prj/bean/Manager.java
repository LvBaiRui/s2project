package edu.prj.bean;

public class Manager implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3693326313213385999L;
	
	private Long managerId;//管理员id
	private String userName;//管理员名称
	private String userPass;//管理员密码
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
