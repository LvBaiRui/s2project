package edu.prj.bean;

public class CartItem implements java.io.Serializable{

	/**
	 * 购物项
	 */
	private static final long serialVersionUID = 1L;

	//商品
		private Product product;
		
		//小计
		private Double subtotal;
		
		//数量
		private Integer count;

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Double getSubtotal() {
			return product.getProPrice()*count;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
		
		public CartItem(Product product, Integer count) {
			super();
			this.product = product;
			this.count = count;
		}

	
}
