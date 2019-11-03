package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class OrderDaoImpl implements OrderDao {

	@Override
	public Long insert(Order bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" INSERT INTO `order`(orderId,orderPrice,orderTime,orderStatus,name,orderPhone,orderAddress,userId) ");
		sb.append("  VALUES (?,?,?,?,?,?,?,?) ");
		
		paramsList.add(bean.getOrderId());
		paramsList.add(bean.getOrderPrice());
		paramsList.add(bean.getOrderTime());
		paramsList.add(bean.getOrderStatus());
		paramsList.add(bean.getName());
		paramsList.add(bean.getPhone());
		paramsList.add(bean.getOrderAddress());
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
	public Long delete(Long orderId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From `Order` ");
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
	public Long update(Order bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update `Order` Set ");
		sb.append(" name=? ");
		sb.append(" ,orderPhone=?  ");
		sb.append(" ,orderAddress=?  ");
		sb.append(" where orderId=? ");

		paramsList.add(bean.getName());
		paramsList.add(bean.getPhone());
		paramsList.add(bean.getOrderAddress());
		paramsList.add(bean.getOrderId());
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	
	
	@Override
	public Long update2(Order bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update `Order` Set ");
		sb.append(" OrderStatus=? ");
		sb.append(" where orderId=? ");
		
		paramsList.add(bean.getOrderStatus());
		paramsList.add(bean.getOrderId());
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}
	
	

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" order by orderId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			Order bean = null;
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
	
	public Order toBean(ResultSet rs) throws SQLException {
		Order bean;
		bean = new Order();
		bean.setOrderId(rs.getLong("orderId"));
		bean.setOrderTime(rs.getDate("orderTime"));
		bean.setOrderPrice(rs.getDouble("orderPrice"));
		bean.setOrderStatus(rs.getInt("orderStatus"));
		bean.setOrderAddress(rs.getString("orderAddress"));
		bean.setName(rs.getString("name"));
		bean.setPhone(rs.getLong("orderPhone"));
		bean.setUserId(rs.getLong("userId"));
		return bean;
	}

	@Override
	public Order load(Long orderId) {
		// TODO Auto-generated method stub
		Order bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" and orderId=? ");

		paramsList.add(orderId);

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
		sb.append(" Select count(1) From `Order` ");
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
	public Order loadByName(String name) {
		// TODO Auto-generated method stub
		Order bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" and name=? ");
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
		sb.append(" Select count(1) From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" and name=? ");

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
	public Long countById(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" and userId=? ");

		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}
	
	@Override
	public Long countById1(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select COUNT(1) from orderitem B ");
		sb.append(" left join `order` A  ");
		sb.append(" on A.orderId=B.orderId ");
		sb.append(" LEFT JOIN product C  ");
		sb.append(" on B.productId=C.productId ");
		sb.append(" where 1=1  ");
		sb.append(" and userId=? ");

		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}


	@Override
	public List<Order> listByName(String name) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From `Order` ");
		sb.append(" where 1=1  ");
		sb.append(" and name like ? ");
		sb.append(" order by orderId ");

		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			Order bean = null;
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
	public List<Order> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from `Order`");
		sb.append(" where 1=1 ");
		sb.append(" Order by orderId ");
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
		Order bean = null;
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
	public List<Order> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from `Order`");
		sb.append(" where 1=1 ");
		sb.append(" and name like ? ");
		sb.append(" Order by orderId ");
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
			Order bean = null;
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
	public List<Order> pagerById2(Long id, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select A.orderId,A.orderPrice,A.orderStatus from  `order` A"); 
		sb.append(" where 1=1 ");
		sb.append(" and A.userId = ? ");
		sb.append(" ORDER BY A.orderTime desc ");
		if(pageNum<1){
			pageNum=1L;
			}
			if(pageSize<1){
			pageSize=10L;
			}
			Long startIndex=(pageNum-1)*pageSize;
			sb.append("limit "+startIndex+","+pageSize);
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			Order bean = null;
			while (rs.next()) {
				bean = toBeanEx2(rs);

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
	public List<Order> pagerById(Long id) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select C.productId,C.image,C.proName,C.proPrice,B.count,B.total,A.orderId,A.orderStatus,A.orderPrice from orderitem B ");
		sb.append(" left join `order` A   ");
		sb.append("  on A.orderId=B.orderId ");
		sb.append(" LEFT JOIN product C   ");
		sb.append(" on B.productId=C.productId ");
		sb.append(" where 1=1  ");   
		sb.append(" and A.userId = ? ");
		sb.append(" ORDER BY A.orderTime desc ");
		
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			Order bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}
	
	
	public Order toBeanEx(ResultSet rs) throws SQLException {
		Order bean;
		bean = new Order();
		bean.setProductId(rs.getLong("productId"));
		bean.setImage(rs.getString("image"));
		bean.setProName(rs.getString("proName"));
		bean.setProPrice(rs.getDouble("proPrice"));
		bean.setCount(rs.getLong("count"));
		bean.setTotal(rs.getDouble("total"));
		bean.setOrderId(rs.getLong("orderId"));
		bean.setOrderStatus(rs.getInt("orderStatus"));
		bean.setOrderPrice(rs.getDouble("orderPrice"));
		return bean;
	}
	
	public Order toBeanEx2(ResultSet rs) throws SQLException {
		Order bean;
		bean = new Order();
		bean.setOrderPrice(rs.getDouble("orderPrice"));
		bean.setOrderId(rs.getLong("orderId"));
		bean.setOrderStatus(rs.getInt("orderStatus"));
		return bean;
	}
	
	

}
