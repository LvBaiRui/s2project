package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class OrderItemDaoImpl implements OrderItemDao {

	@Override
	public Long insert(OrderItem bean) {
		// TODO Auto-generated method stub
		Long num = Long.valueOf(0L);

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into OrderItem(productId,orderId,count,total) ");
		sb.append(" values(?,?,?,?) ");
		paramsList.add(bean.getProductId());
		paramsList.add(bean.getOrder().getOrderId());
		paramsList.add(bean.getCount());
		paramsList.add(bean.getTotal());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num.longValue() > 0L) {
			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql, new Object[0]);
			if (result.longValue() > 0L) {
				bean.setOrderItemId(result);
				num = result;
			}
		}
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(Long orderItemId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and orderItemId=? ");

		paramsList.add(orderItemId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	
	@Override
	public Long deleteByOId(Long orderId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and orderId=? ");

		paramsList.add(orderId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	

	@Override
	public Long update(OrderItem bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete OrderItem Set ");
		sb.append(" productId=? ");
		sb.append(" ,orderId=?  ");
		sb.append(" ,count=?  ");
		sb.append(" ,total=?  ");
		sb.append(" where orderItemId=? ");

		paramsList.add(bean.getProductId());
		paramsList.add(bean.getOrderId());
		paramsList.add(bean.getCount());
		paramsList.add(bean.getTotal());
		paramsList.add(bean.getOrderItemId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<OrderItem> list() {
		// TODO Auto-generated method stub
		List<OrderItem> list = new ArrayList<OrderItem>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" order by orderItemId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			OrderItem bean = null;
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
	
	public OrderItem toBean(ResultSet rs) throws SQLException {
		OrderItem bean;
		bean = new OrderItem();
		bean.setOrderItemId(rs.getLong("orderItemId"));
		bean.setProductId(rs.getLong("productId"));
		bean.setOrderId(rs.getLong("orderId"));
		bean.setCount(rs.getLong("count"));
		bean.setTotal(rs.getDouble("total"));
		return bean;
	}

	@Override
	public OrderItem load(Long orderItemId) {
		// TODO Auto-generated method stub
		OrderItem bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and orderItemId=? ");

		paramsList.add(orderItemId);

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
		sb.append(" Select count(1) From OrderItem ");
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
	public OrderItem loadByName(Long productId) {
		// TODO Auto-generated method stub
		OrderItem bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and productId=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(productId);
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
	public OrderItem loadById(Long orderId) {
		// TODO Auto-generated method stub
		OrderItem bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and orderId=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(orderId);
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
	public Long countByName(Long productId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From OrderItem ");
		sb.append(" where 1=1  ");
		sb.append(" and productId=? ");

		paramsList.add(productId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<OrderItem> listByName(Long productId) {
		return null;
		// TODO Auto-generated method stub
//		List<OrderItem> list = new ArrayList<OrderItem>();
//
//		StringBuilder sb = new StringBuilder();
//		List<Object> paramsList = new ArrayList<Object>();
//		sb.append(" Select * From OrderItem ");
//		sb.append(" where 1=1  ");
//		sb.append(" and name like ? ");
//		sb.append(" order by orderId ");
//
//		name = "%" + name + "%";
//		paramsList.add(name);
//
//		String sql = sb.toString();
//		Object[] params = paramsList.toArray();
//
//		Connection conn = null;
//		ResultSet rs = null;
//		conn = DbPub.getConn();
//
//		rs = DbPub.query(conn, sql,params);
//
//		try {
//			OrderItem bean = null;
//			while (rs.next()) {
//				bean = toBean(rs);
//
//				list.add(bean);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		DbPub.close(conn);

//		return list;
	}
	
	@Override
	public List<OrderItem> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<OrderItem> list = new ArrayList<OrderItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from OrderItem");
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
		OrderItem bean = null;
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
	public List<OrderItem> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<OrderItem> list = new ArrayList<OrderItem>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from OrderItem");
		sb.append(" where 1=1 ");
		sb.append(" and productId like ? ");
		sb.append(" Order by orderItemId ");
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
			OrderItem bean = null;
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
