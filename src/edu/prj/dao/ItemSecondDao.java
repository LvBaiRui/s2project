package edu.prj.dao;
import java.util.List;

import edu.prj.bean.ItemSecond;

public interface ItemSecondDao {

	Long insert(ItemSecond bean);

	Long delete(Long itemsId);

	Long update(ItemSecond bean);

	java.util.List<ItemSecond> list();

	ItemSecond load(Long itemsId);

	Long count();

	ItemSecond loadByName(String itemsName);

	Long countByName(String itemsName);

	java.util.List<ItemSecond> listByName(String itemsName);
	
	List<ItemSecond> pager(Long pageNum, Long pageSize);

	List<ItemSecond> pagerByName(String name, Long pageNum, Long pageSize);
	
}
