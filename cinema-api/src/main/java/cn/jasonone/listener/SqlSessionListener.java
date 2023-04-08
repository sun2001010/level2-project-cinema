package cn.jasonone.listener;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 初始化并管理Mybatis的SqlSession
 *
 */
@WebListener
public class SqlSessionListener implements ServletRequestListener {
    private ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        if (threadLocal.get() != null) {
            threadLocal.get().close();
            threadLocal.remove();
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 销毁上一次的SqlSession,防止Session的重复使用
        this.requestDestroyed(sre);
        ServletContext sc = sre.getServletContext();
        // 从ServletContext中获取SqlSessionFactory(cn.jasonone.listener.MyBatisListener.contextInitialized方法中加入)
        SqlSessionFactory ssf = (SqlSessionFactory) sc.getAttribute("sqlSessionFactory");
        // 从SqlSessionFactory中获取SqlSession
        SqlSession ss = ssf.openSession();
        // 将SqlSession放入ThreadLocal中
        threadLocal.set(ss);
        ServletRequest request = sre.getServletRequest();
        // 将SqlSession放入request中
        request.setAttribute("sqlSession", ss);
    }
}
