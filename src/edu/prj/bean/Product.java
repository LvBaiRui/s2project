package edu.prj.bean;

import java.util.Date;

public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4035968190784862760L;
	
	private Long productId;//商品id
	private String proName;//商品名称
	private Double proPrice;//商品价格
	private Double marketPrice;//市场价格
	private String productInfo;//商品描述
	private String image;//商品图片
	private Long isHot;//是否热销
	private Date dateTime;//日期
	private Long itemId;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(Double proPrice) {
		this.proPrice = proPrice;
	}
	public double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getIsHot() {
		return isHot;
	}
	public void setIsHot(Long isHot) {
		this.isHot = isHot;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	

}
