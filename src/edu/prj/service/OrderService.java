package edu.prj.service;

import java.util.List;

import edu.prj.bean.Order;

public interface OrderService {
	
	Long insert(Order bean);

	Long delete(Long orderId);

	Long update(Order bean);

	java.util.List<Order> list();

	Order load(Long orderId);

	Long count();

	Order loadByName(String name);

	Long countByName(String name);
	
	Long countById(Long id);
	Long countById1(Long id);

	java.util.List<Order> listByName(String name);
	
	List<Order> pager(Long pageNum, Long pageSize);

	List<Order> pagerByName(String name, Long pageNum, Long pageSize);

	List<Order> pagerById(Long id);
	
	List<Order> pagerById2(Long id ,Long pageNum, Long pageSize);
	
	Long update2(Order bean);
}
