package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao = new ProductDaoImpl(); 

	@Override
	public Long insert(Product bean) {
		// TODO Auto-generated method stub
		return productDao.insert(bean);
	}

	@Override
	public Long delete(Long productId) {
		// TODO Auto-generated method stub
		return productDao.delete(productId);
	}

	@Override
	public Long update(Product bean) {
		// TODO Auto-generated method stub
		return productDao.update(bean);
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		return productDao.list();
	}

	@Override
	public Product load(Long productId) {
		// TODO Auto-generated method stub
		return productDao.load(productId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return productDao.count();
	}

	@Override
	public Long countById(Long id) {
		// TODO Auto-generated method stub
		return productDao.countById(id);
	}
	
	@Override
	public Product loadByName(String proName) {
		// TODO Auto-generated method stub
		return productDao.loadByName(proName);
	}

	@Override
	public Long countByName(String proName) {
		// TODO Auto-generated method stub
		return productDao.countByName(proName);
	}

	@Override
	public List<Product> listByName(String proName) {
		// TODO Auto-generated method stub
		return productDao.listByName(proName);
	}

	@Override
	public List<Product> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return productDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Product> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return productDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public List<Product> pIsHot(Long isHot) {
		// TODO Auto-generated method stub
		return productDao.pIsHot(isHot);
	}

	@Override
	public List<Product> pIsNew() {
		// TODO Auto-generated method stub
		return productDao.pIsNew();
	}

	@Override
	public List<Product> pagerByNum(Long id, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return productDao.pagerByNum(id, pageNum, pageSize);
	}



}
