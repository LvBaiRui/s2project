package edu.prj.service;

import java.util.List;

import edu.prj.bean.User;

public interface UserService {
	
	Long insert(User bean);

	Long delete(Long userId);

	Long update(User bean);

	java.util.List<User> list();

	User load(Long userId);

	Long count();

	User loadByName(String name);

	Long countByName(String name);

	java.util.List<User> listByName(String userName);
	
	List<User> pager(Long pageNum, Long pageSize);

	List<User> pagerByName(String name, Long pageNum, Long pageSize);
	
	Long update3(User bean);

	Long updateInfo(User bean);

}
