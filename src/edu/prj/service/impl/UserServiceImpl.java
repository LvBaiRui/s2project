package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl(); 

	@Override
	public Long insert(User bean) {
		// TODO Auto-generated method stub
		return userDao.insert(bean);
	}

	@Override
	public Long delete(Long userId) {
		// TODO Auto-generated method stub
		return userDao.delete(userId);
	}

	@Override
	public Long update(User bean) {
		// TODO Auto-generated method stub
		return userDao.update(bean);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	@Override
	public User load(Long userId) {
		// TODO Auto-generated method stub
		return userDao.load(userId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return userDao.count();
	}

	@Override
	public User loadByName(String name) {
		// TODO Auto-generated method stub
		return userDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return userDao.countByName(name);
	}

	@Override
	public List<User> listByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.listByName(userName);
	}

	@Override
	public List<User> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return userDao.pager(pageNum, pageSize);
	}

	@Override
	public List<User> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return userDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public Long update3(User bean) {
		// TODO Auto-generated method stub
		return userDao.update3(bean);
	}

	@Override
	public Long updateInfo(User bean) {
		// TODO Auto-generated method stub
		return userDao.updateInfo(bean);
	}

}
