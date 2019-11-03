package edu.prj.service;

import java.util.List;

import edu.prj.bean.Product;

public interface ProductService {
	
	Long insert(Product bean);

	Long delete(Long productId);

	Long update(Product bean);

	java.util.List<Product> list();

	Product load(Long productId);

	Long count();
	
	Long countById(Long id);

	Product loadByName(String proName);

	Long countByName(String proName);

	java.util.List<Product> listByName(String proName);
	
	List<Product> pager(Long pageNum, Long pageSize);

	List<Product> pagerByName(String name, Long pageNum, Long pageSize);

	List<Product> pagerByNum(Long id, Long pageNum, Long pageSize);
	
	List<Product> pIsHot(Long isHot);
	
	List<Product> pIsNew();
}
