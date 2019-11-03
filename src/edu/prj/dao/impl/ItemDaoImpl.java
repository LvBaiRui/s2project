package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class ItemDaoImpl implements ItemDao {

	@Override
	public Long insert(Item bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert into Item (itemName) ");
		sb.append(" values (?) ");

		paramsList.add(bean.getItemName());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long itemId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From Item ");
		sb.append(" where 1=1  ");
		sb.append(" and itemId=? ");

		paramsList.add(itemId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(Item bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update Item Set ");
		sb.append(" itemName=?  ");
		sb.append(" where itemId=? ");

		paramsList.add(bean.getItemName());
		paramsList.add(bean.getItemId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Item> list() {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<Item>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Item ");
		sb.append(" where 1=1  ");
		sb.append(" order by itemId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			Item bean = null;
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
	
	public Item toBean(ResultSet rs) throws SQLException {
		Item bean;
		bean = new Item();
		bean.setItemId(rs.getLong("itemId"));
		bean.setItemName(rs.getString("itemName"));
		return bean;
	}

	@Override
	public Item load(Long itemId) {
		// TODO Auto-generated method stub
		Item bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * From Item ");
		sb.append(" where 1=1  ");
		sb.append(" and itemId=? ");

		paramsList.add(itemId);

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
		sb.append(" Select count(1) From Item ");
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
	public Item loadByName(String itemName) {
		// TODO Auto-generated method stub
		Item bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From Item ");
		sb.append(" where 1=1  ");
		sb.append(" and itemName=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(itemName);
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
	public Long countByName(String itemName) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From Item ");
		sb.append(" where 1=1  ");
		sb.append(" and itemName=? ");

		paramsList.add(itemName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<Item> listByName(String itemName) {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<Item>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Item ");
		sb.append(" where 1=1  ");
		sb.append(" and itemName like ? ");
		sb.append(" order by itemId ");

		itemName = "%" + itemName + "%";
		paramsList.add(itemName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			Item bean = null;
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
	public List<Item> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<Item>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Item");
		sb.append(" where 1=1 ");
		sb.append(" Order by itemId ");
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
		Item bean = null;
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
	public List<Item> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<Item>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Item");
		sb.append(" where 1=1 ");
		sb.append(" and itemName like ? ");
		sb.append(" Order by itemId ");
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
			Item bean = null;
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
