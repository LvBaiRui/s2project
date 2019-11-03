package edu.prj.dao;
import java.util.List;

import edu.prj.bean.Item;

public interface ItemDao {

	Long insert(Item bean);

	Long delete(Long itemId);

	Long update(Item bean);

	java.util.List<Item> list();

	Item load(Long itemId);

	Long count();

	Item loadByName(String itemName);

	Long countByName(String itemName);

	java.util.List<Item> listByName(String itemName);
	
	List<Item> pager(Long pageNum, Long pageSize);

	List<Item> pagerByName(String name, Long pageNum, Long pageSize);
	
}
