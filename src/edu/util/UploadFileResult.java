package edu.util;

public class UploadFileResult implements java.io.Serializable{
	 private static final long serialVersionUID = 456017761209505620L;
	 private Integer code;
	 private String desc;

    public UploadFileResult() {
    	setCode(-1);
    	setDesc("不成功。");
    }
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}
