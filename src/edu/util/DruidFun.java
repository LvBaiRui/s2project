package edu.util;

import java.io.InputStream;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid辅助类库
 * 
 * @author super
 */

public class DruidFun {

	/**
	 * 获取conn对象
	 */

	public static java.sql.Connection getConnection() {
		java.sql.Connection conn = null;
		try {
			if (dsDruid == null) {
				build();
			}
			if (dsDruid != null) {
				return dsDruid.getConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static javax.sql.DataSource dsDruid = null;

	private static javax.sql.DataSource build(){
		System.out.println("Druid.");
		if(dsDruid!=null) {
			return dsDruid;
		}
		try {
			Properties props=new Properties();
			InputStream is=DruidFun.class.getClassLoader().getResourceAsStream("app.properties");
			props.load(is);
			dsDruid=DruidDataSourceFactory.createDataSource(props);
			
		}catch(Exception e) {
			System.out.println("读取app.properties文件异常");
			e.printStackTrace();
		}
		return dsDruid;
	}
	
}
