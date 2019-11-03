package edu.prj.bean;
public class ItemSecond implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7134220163841468860L;
	
	private Long itemsId;//二级分类id
	private String itemsName;//二级分类名称
	private Long itemId;//一级分类id 外键
	
	public Long getItemsId() {
		return itemsId;
	}
	public void setItemsId(Long itemsId) {
		this.itemsId = itemsId;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
}