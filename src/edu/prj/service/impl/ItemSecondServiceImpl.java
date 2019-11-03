package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class ItemSecondServiceImpl implements ItemSecondService{
	
	private ItemSecondDao itemSecondDao = new ItemSecondDaoImpl();

	@Override
	public Long insert(ItemSecond bean) {
		// TODO Auto-generated method stub
		return itemSecondDao.insert(bean);
	}

	@Override
	public Long delete(Long itemsId) {
		// TODO Auto-generated method stub
		return itemSecondDao.delete(itemsId);
	}

	@Override
	public Long update(ItemSecond bean) {
		// TODO Auto-generated method stub
		return itemSecondDao.update(bean);
	}

	@Override
	public List<ItemSecond> list() {
		// TODO Auto-generated method stub
		return itemSecondDao.list();
	}

	@Override
	public ItemSecond load(Long itemsId) {
		// TODO Auto-generated method stub
		return itemSecondDao.load(itemsId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return itemSecondDao.count();
	}

	@Override
	public ItemSecond loadByName(String itemsName) {
		// TODO Auto-generated method stub
		return itemSecondDao.loadByName(itemsName);
	}

	@Override
	public Long countByName(String itemsName) {
		// TODO Auto-generated method stub
		return itemSecondDao.countByName(itemsName);
	}

	@Override
	public List<ItemSecond> listByName(String itemsName) {
		// TODO Auto-generated method stub
		return itemSecondDao.listByName(itemsName);
	}

	@Override
	public List<ItemSecond> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return itemSecondDao.pager(pageNum, pageSize);
	}

	@Override
	public List<ItemSecond> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return itemSecondDao.pagerByName(name, pageNum, pageSize);
	}

}
