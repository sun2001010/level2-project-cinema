package cn.jasonone.listener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * 初始化Mybatis的SqlSessionFactory
 */
@WebListener
public class MyBatisListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String configPath = sc.getInitParameter("mybatis-config");
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream(configPath));
            sc.setAttribute("sqlSessionFactory", sqlSessionFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
