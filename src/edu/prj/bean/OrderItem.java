package edu.prj.bean;

public class OrderItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2162290450587787524L;

	private Long orderItemId;// 订单项表id
	private Long productId;// 商品id
	private Long orderId;// 订单id
	private Long count;// 数量
	private Double total;// 总计

	private Product product; // 表示包含那个商品

	private Order order; // 表示属于那个订单

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
}
