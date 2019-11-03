package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Long insert(Product bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert into Product (proName,proPrice,marketPrice,productInfo,image,isHot,dateTime,itemId) ");
		sb.append(" values (?,?,?,?,?,?,?,?) ");

		paramsList.add(bean.getProName());
		paramsList.add(bean.getProPrice());
		paramsList.add(bean.getMarketPrice());
		paramsList.add(bean.getProductInfo());
		paramsList.add(bean.getImage());
		paramsList.add(bean.getIsHot());
		paramsList.add(bean.getDateTime());
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
	public Long delete(Long productId) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Delete From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and productId=? ");

		paramsList.add(productId);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(Product bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Update Product Set ");
		sb.append(" proName=?  ");
		sb.append(" ,proPrice=?  ");
		sb.append(" ,marketPrice=?  ");
		sb.append(" ,productInfo=?  ");
		sb.append(" ,image=? ");
		sb.append(" ,isHot=?  ");
		sb.append(" ,dateTime=?  ");
		sb.append(" ,itemId=?  ");
		sb.append(" where productId=? ");

		paramsList.add(bean.getProName());
		paramsList.add(bean.getProPrice());
		paramsList.add(bean.getMarketPrice());
		paramsList.add(bean.getProductInfo());
		paramsList.add(bean.getImage());
		paramsList.add(bean.getIsHot());
		paramsList.add(bean.getDateTime());
		paramsList.add(bean.getItemId());
		paramsList.add(bean.getProductId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" order by productId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			Product bean = null;
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
	
	public Product toBean(ResultSet rs) throws SQLException {
		Product bean;
		bean = new Product();
		bean.setProductId(rs.getLong("productId"));
		bean.setProName(rs.getString("proName"));
		bean.setProPrice(rs.getDouble("proPrice"));
		bean.setMarketPrice(rs.getDouble("marketPrice"));
		bean.setProductInfo(rs.getString("productInfo"));
		bean.setImage(rs.getString("image"));
		bean.setIsHot(rs.getLong("isHot"));
		bean.setDateTime(rs.getDate("dateTime"));
		bean.setItemId(rs.getLong("itemId"));
		return bean;
	}

	@Override
	public Product load(Long productId) {
		// TODO Auto-generated method stub
		Product bean = null;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and productId=? ");

		paramsList.add(productId);

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
		sb.append(" Select count(1) From Product ");
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
	public Long countById(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and itemId=? ");

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
	public Product loadByName(String proName) {
		// TODO Auto-generated method stub
		Product bean = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and proName=? ");
		String sql = sb.toString();

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(proName);
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
	public Long countByName(String proName) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select count(1) From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and proName=? ");

		paramsList.add(proName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();

		num = DbPub.queryScalarLong(conn, sql,params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<Product> listByName(String proName) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and proName like ? ");
		sb.append(" order by productId ");

		proName = "%" + proName + "%";
		paramsList.add(proName);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();

		rs = DbPub.query(conn, sql,params);

		try {
			Product bean = null;
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
	public List<Product> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Product");
		sb.append(" where 1=1 ");
		sb.append(" Order by productId ");
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
		Product bean = null;
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
	public List<Product> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Product");
		sb.append(" where 1=1 ");
		sb.append(" and proName like ? ");
		sb.append(" Order by productId ");
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
			Product bean = null;
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
	public List<Product> pagerByNum(Long id, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Product");
		sb.append(" where 1=1 ");
		sb.append(" and itemId = ? ");
		sb.append(" Order by productId ");
		if(pageNum<1){
			pageNum=1L;
			}
			if(pageSize<1){
			pageSize=10L;
			}
			Long startIndex=(pageNum-1)*pageSize;
			sb.append("limit "+startIndex+","+pageSize);
			
        paramsList.add(id);
        Object[] params=paramsList.toArray();
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql,params);

		try {
			Product bean = null;
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
	public List<Product> pIsHot(Long isHot) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" and isHot = ?");
		sb.append(" order by dateTime desc limit 10 ");

		paramsList.add(isHot);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			Product bean = null;
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
	public List<Product> pIsNew() {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();

		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Product ");
		sb.append(" where 1=1  ");
		sb.append(" order by dateTime desc limit 10 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			Product bean = null;
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
