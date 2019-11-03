package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;



public class ItemServiceImpl implements ItemService {
	
	private ItemDao itemDao = new ItemDaoImpl();

	@Override
	public Long insert(Item bean) {
		// TODO Auto-generated method stub
		return itemDao.insert(bean);
	}

	@Override
	public Long delete(Long itemId) {
		// TODO Auto-generated method stub
		return itemDao.delete(itemId);
	}

	@Override
	public Long update(Item bean) {
		// TODO Auto-generated method stub
		return itemDao.update(bean);
	}

	@Override
	public List<Item> list() {
		// TODO Auto-generated method stub
		return itemDao.list();
	}

	@Override
	public Item load(Long itemId) {
		// TODO Auto-generated method stub
		return itemDao.load(itemId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return itemDao.count();
	}

	@Override
	public Item loadByName(String itemName) {
		// TODO Auto-generated method stub
		return itemDao.loadByName(itemName);
	}

	@Override
	public Long countByName(String itemName) {
		// TODO Auto-generated method stub
		return itemDao.countByName(itemName);
	}

	@Override
	public List<Item> listByName(String itemName) {
		// TODO Auto-generated method stub
		return itemDao.listByName(itemName);
	}

	@Override
	public List<Item> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return itemDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Item> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return itemDao.pagerByName(name, pageNum, pageSize);
	}

}
