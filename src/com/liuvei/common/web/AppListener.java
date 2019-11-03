package com.liuvei.common.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AppListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub

    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	//0）创建一个application对象
    	javax.servlet.ServletContext application=event.getServletContext();
    	//1）将应用上下文的路径存放到名称为appPath的上下文作用域中
    	application.setAttribute("AppPath", application.getContextPath());
    	//2)将应用上下文的物理目录存放到名称为AppDir的上下文作用域中
    	application.setAttribute("AppDir", application.getRealPath("/"));
    	
    }
	
}
