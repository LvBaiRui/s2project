package edu.util;

/**
 * Druid辅助类库
 * 
 * @author super
 */

public class DBCPFun {

	/**
	 * 获取conn对象
	 */

	public static java.sql.Connection getConnection() {
		java.sql.Connection conn = null;
		try {
			if (dbDBCP == null) {
				build();
			}
			if (dbDBCP != null) {
				return dbDBCP.getConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static javax.sql.DataSource dbDBCP = null;

	private static javax.sql.DataSource build(){
		System.out.println("dbDBCP.");
		if(dbDBCP!=null) {
			return dbDBCP;
		}
		try {
			java.io.InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			
			java.util.Properties props=new java.util.Properties();
			props.load(in);
			dbDBCP=org.apache.commons.dbcp2.BasicDataSourceFactory.createDataSource(props);
			
		}catch(Exception e) {
			System.out.println("读取app.properties文件异常");
			e.printStackTrace();
		}
		return dbDBCP;
	}
	
}
