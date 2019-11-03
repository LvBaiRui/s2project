package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class UserDaoImpl implements UserDao {

	@Override
	public Long insert(User bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert into User (userName,userPass,name,email,address,status,code) ");
		sb.append(" values (?,?,?,?,?,?,?) ");

		paramsList.add(bean.getUserName());
		paramsList.add(bean.getUserPass());
		paramsList.add(bean.getName());
		paramsList.add(bean.getEmail());
		paramsList.add(bean.getAddress());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getCode());
		

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long userId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From User ");
		sb.append(" where 1=1  ");
		sb.append(" and userId=? ");

		paramsList.add(userId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(User bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update User Set ");
		sb.append(" userName=?  ");
		sb.append(" ,userPass=? ");
		sb.append(" ,name=? ");
		sb.append(" ,email=? ");
		sb.append(" ,address=? ");
		sb.append(" ,status=? ");
		sb.append(" ,code=? ");
		sb.append(" where userId=? ");

		paramsList.add(bean.getUserName());
		paramsList.add(bean.getUserPass());
		paramsList.add(bean.getName());
		paramsList.add(bean.getEmail());
		paramsList.add(bean.getAddress());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getCode());
		paramsList.add(bean.getUserId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	
	@Override
	public Long updateInfo(User bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update User Set ");
		sb.append(" ,name=? ");
		sb.append(" ,email=? ");
		sb.append(" ,address=? ");
		sb.append(" where userId=? ");

		paramsList.add(bean.getName());
		paramsList.add(bean.getEmail());
		paramsList.add(bean.getAddress());
		paramsList.add(bean.getUserId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	
	@Override
	public Long update3(User bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update User Set ");
		sb.append(" userPass=? ");
		sb.append(" where userId=? ");

		paramsList.add(bean.getUserPass());
		paramsList.add(bean.getUserId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}


	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From User ");
		sb.append(" where 1=1  ");
		sb.append(" order by UserId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			User bean = null;
			while (rs.next()) {
				bean = toBean(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);
		return list;
	}
	
	public User toBean(ResultSet rs) throws SQLException {
		User bean;
		bean = new User();
		bean.setUserId(rs.getLong("userId"));
		bean.setUserName(rs.getString("userName"));
		bean.setUserPass(rs.getString("userPass"));
		bean.setName(rs.getString("name"));
		bean.setEmail(rs.getString("email"));
		bean.setAddress(rs.getString("address"));
		bean.setStatus(rs.getLong("status"));
		bean.setCode(rs.getString("code"));
		return bean;
	}

	@Override
	public User load(Long userId) {
		// TODO Auto-generated method stub
		User bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * from User");
		sb.append(" where 1=1 ");
		sb.append(" and userId=? ");
		
		paramsList.add(userId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);

		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From User ");
		sb.append(" where 1=1  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public User loadByName(String name) {
		// TODO Auto-generated method stub
		User bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From User ");
		sb.append(" where 1=1  ");
		sb.append(" and userName=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(name);
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return bean;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From User ");
		sb.append(" where 1=1  ");
		sb.append(" and userName=? ");

		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<User> listByName(String userName) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From User ");
		sb.append(" where 1=1  ");
		sb.append(" and userName like ? ");
		sb.append(" order by id ");

		userName = "%" + userName + "%";
		paramsList.add(userName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			User bean = null;
			while (rs.next()) {
				bean = toBean(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}
	
	@Override
	public List<User> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from User");
		sb.append(" where 1=1 ");
		sb.append(" Order by userId ");
		if(pageNum<1){
			pageNum=1L;
			}
			if(pageSize<1){
			pageSize=10L;
			}
			Long startIndex=(pageNum-1)*pageSize;
			sb.append("limit "+startIndex+","+pageSize);
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);
		User bean = null;
		try {
			
			while (rs.next()) {
				
			    bean=toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	@Override
	public List<User> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from User");
		sb.append(" where 1=1 ");
		sb.append(" and userName like ? ");
		sb.append(" Order by userId ");
		if(pageNum<1){
			pageNum=1L;
			}
			if(pageSize<1){
			pageSize=10L;
			}
			Long startIndex=(pageNum-1)*pageSize;
			sb.append("limit "+startIndex+","+pageSize);
			name="%"+ name+"%";
        paramsList.add(name);
        Object[] params=paramsList.toArray();
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql,params);

		try {
			User bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

}
