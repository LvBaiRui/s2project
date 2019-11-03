package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class OrderItemServiceImpl implements OrderItemService{
	
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();

	@Override
	public Long insert(OrderItem bean) {
		// TODO Auto-generated method stub
		return orderItemDao.insert(bean);
	}

	@Override
	public Long delete(Long orderItemId) {
		// TODO Auto-generated method stub
		return orderItemDao.delete(orderItemId);
	}

	@Override
	public Long update(OrderItem bean) {
		// TODO Auto-generated method stub
		return orderItemDao.update(bean);
	}

	@Override
	public List<OrderItem> list() {
		// TODO Auto-generated method stub
		return orderItemDao.list();
	}

	@Override
	public OrderItem load(Long orderItemId) {
		// TODO Auto-generated method stub
		return orderItemDao.load(orderItemId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return orderItemDao.count();
	}

	@Override
	public OrderItem loadByName(Long productId) {
		// TODO Auto-generated method stub
		return orderItemDao.loadByName(productId);
	}

	@Override
	public Long countByName(Long productId) {
		// TODO Auto-generated method stub
		return orderItemDao.countByName(productId);
	}

	@Override
	public List<OrderItem> listByName(Long productId) {
		// TODO Auto-generated method stub
		return orderItemDao.listByName(productId);
	}

	@Override
	public List<OrderItem> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return orderItemDao.pager(pageNum, pageSize);
	}

	@Override
	public List<OrderItem> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return orderItemDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public OrderItem loadById(Long orderId) {
		// TODO Auto-generated method stub
		return orderItemDao.loadById(orderId);
	}

	@Override
	public Long deleteByOId(Long orderId) {
		// TODO Auto-generated method stub
		return orderItemDao.deleteByOId(orderId);
	}

}
