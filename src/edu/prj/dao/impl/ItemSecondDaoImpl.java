package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class ItemSecondDaoImpl implements ItemSecondDao {

	@Override
	public Long insert(ItemSecond bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert into ItemSecond (itemsName,itemId) ");
		sb.append(" values (?,?) ");

		paramsList.add(bean.getItemsName());
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
	public Long delete(Long itemsId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" and itemsId=? ");

		paramsList.add(itemsId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(ItemSecond bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete Item Set ");
		sb.append(" itemsName=?  ");
		sb.append(" ,itemId=?  ");
		sb.append(" where itemsId=? ");

		paramsList.add(bean.getItemsName());
		paramsList.add(bean.getItemId());
		paramsList.add(bean.getItemsId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<ItemSecond> list() {
		// TODO Auto-generated method stub
		List<ItemSecond> list = new ArrayList<ItemSecond>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" order by itemsId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			ItemSecond bean = null;
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
	
	public ItemSecond toBean(ResultSet rs) throws SQLException {
		ItemSecond bean;
		bean = new ItemSecond();
		bean.setItemsId(rs.getLong("itemsId"));
		bean.setItemsName(rs.getString("itemsName"));
		bean.setItemId(rs.getLong("itemId"));
		return bean;
	}

	@Override
	public ItemSecond load(Long itemsId) {
		// TODO Auto-generated method stub
		ItemSecond bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" and itemsId=? ");

		paramsList.add(itemsId);

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
		sb.append(" Select count(1) From ItemSecond ");
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
	public ItemSecond loadByName(String itemsName) {
		// TODO Auto-generated method stub
		ItemSecond bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" and itemsName=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(itemsName);
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
	public Long countByName(String itemsName) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" and itemsName=? ");

		paramsList.add(itemsName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<ItemSecond> listByName(String itemsName) {
		// TODO Auto-generated method stub
		List<ItemSecond> list = new ArrayList<ItemSecond>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From ItemSecond ");
		sb.append(" where 1=1  ");
		sb.append(" and itemsName like ? ");
		sb.append(" order by itemsId ");

		itemsName = "%" + itemsName + "%";
		paramsList.add(itemsName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			ItemSecond bean = null;
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
	public List<ItemSecond> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<ItemSecond> list = new ArrayList<ItemSecond>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ItemSecond");
		sb.append(" where 1=1 ");
		sb.append(" Order by itemsId ");
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
		ItemSecond bean = null;
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
	public List<ItemSecond> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<ItemSecond> list = new ArrayList<ItemSecond>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ItemSecond");
		sb.append(" where 1=1 ");
		sb.append(" and itemsName like ? ");
		sb.append(" Order by itemsId ");
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
			ItemSecond bean = null;
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
