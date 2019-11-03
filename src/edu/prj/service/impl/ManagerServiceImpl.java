package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class ManagerServiceImpl implements ManagerService {
	
	private ManagerDao managerDao = new ManagerDaoImpl();

	@Override
	public Long insert(Manager bean) {
		// TODO Auto-generated method stub
		return managerDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return managerDao.delete(id);
	}

	@Override
	public Long update(Manager bean) {
		// TODO Auto-generated method stub
		return managerDao.update(bean);
	}

	@Override
	public List<Manager> list() {
		// TODO Auto-generated method stub
		return managerDao.list();
	}

	@Override
	public Manager load(Long id) {
		// TODO Auto-generated method stub
		return managerDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return managerDao.count();
	}

	@Override
	public Manager loadByName(String name) {
		// TODO Auto-generated method stub
		return managerDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return managerDao.countByName(name);
	}

	@Override
	public List<Manager> listByName(String userName) {
		// TODO Auto-generated method stub
		return managerDao.listByName(userName);
	}

	@Override
	public List<Manager> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return managerDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Manager> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return managerDao.pagerByName(name, pageNum, pageSize);
	}

}
