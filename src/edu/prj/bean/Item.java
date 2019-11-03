package edu.prj.bean;

public class Item implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719590531261029840L;
	
	private Long itemId;//一级分类id
	private String itemName;//一级分类名称
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
