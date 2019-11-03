package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();

	@Override
	public Long insert(Order bean) {
		// TODO Auto-generated method stub
		return orderDao.insert(bean);
	}

	@Override
	public Long delete(Long orderId) {
		// TODO Auto-generated method stub
		return orderDao.delete(orderId);
	}

	@Override
	public Long update(Order bean) {
		// TODO Auto-generated method stub
		return orderDao.update(bean);
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		return orderDao.list();
	}

	@Override
	public Order load(Long orderId) {
		// TODO Auto-generated method stub
		return orderDao.load(orderId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return orderDao.count();
	}

	@Override
	public Order loadByName(String name) {
		// TODO Auto-generated method stub
		return orderDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return orderDao.countByName(name);
	}

	@Override
	public List<Order> listByName(String name) {
		// TODO Auto-generated method stub
		return orderDao.listByName(name);
	}

	@Override
	public List<Order> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return orderDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Order> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return orderDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public Long countById(Long id) {
		// TODO Auto-generated method stub
		return orderDao.countById(id);
	}
	
	@Override
	public Long countById1(Long id) {
		// TODO Auto-generated method stub
		return orderDao.countById1(id);
	}

	@Override
	public List<Order> pagerById(Long id) {
		// TODO Auto-generated method stub
		return orderDao.pagerById(id);
	}

	@Override
	public List<Order> pagerById2(Long id, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return orderDao.pagerById2(id, pageNum, pageSize);
	}

	@Override
	public Long update2(Order bean) {
		// TODO Auto-generated method stub
		return orderDao.update2(bean);
	}

}
