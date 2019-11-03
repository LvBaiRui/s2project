package edu.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Druid辅助类库
 * 
 * @author super
 */

public class C3P0Fun {

	 //加载名字为“test”的配置文件
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("test");
    /**
     * 获取Connection连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn =dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

	
}
