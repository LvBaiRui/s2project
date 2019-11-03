package edu.prj.service;

import java.util.List;

import edu.prj.bean.Order;
import edu.prj.bean.OrderItem;

public interface OrderItemService {
	
	Long insert(OrderItem bean);

	Long delete(Long orderItemId);

	Long update(OrderItem bean);

	java.util.List<OrderItem> list();

	OrderItem load(Long orderItemId);

	Long count();

	OrderItem loadByName(Long productId);

	Long countByName(Long productId);

	java.util.List<OrderItem> listByName(Long productId);
	
	List<OrderItem> pager(Long pageNum, Long pageSize);

	List<OrderItem> pagerByName(String name, Long pageNum, Long pageSize);
	
	OrderItem loadById(Long orderId);

	Long deleteByOId(Long orderId);
	

}
