package edu.prj.bean;

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7648523064853748120L;
	
	private Long userId;//用户id
	private String userName;//用户名
	private String userPass;//用户密码
	private String name;//真实姓名
	private String email;//邮箱信息
	private String address;//地址
	private Long status;//用户状态
	private String code;//激活码
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
