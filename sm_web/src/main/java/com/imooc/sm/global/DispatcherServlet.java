package com.imooc.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends GenericServlet {

    private ApplicationContext context;

    public void init() throws ServletException {  //初始化方法
        super.init();
        context = new ClassPathXmlApplicationContext("spring.xml"); //获取context上下文
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;   //service方法无法拿到请求路径，所以得强转成Http

        /*
         *    staff/add.do      login.do  :   path  路径url
         *    staffController  :  beanName
         *    public void add(HttpServletRequest request, HttpServletRequest request){}    : methodName
         */
        String path = request.getServletPath().substring(1); //substring(1) 表示截取了/staff/add.do 从1开始 变为staff/add.do
        String beanName = null;
        String methodName = null;
        int index = path.indexOf("/");  //  路径中带“/”
        if (index != -1){     //代表是staff/add.do这种格式
            beanName = path.substring(0, index)+ "Controller";
            methodName = path.substring(index+1, path.indexOf(".do"));
        }else{    //代表是login.do这种特殊模块
            beanName = "selfController";
            methodName = path.substring(0, path.indexOf(".do"));
        }
        Object obj = context.getBean(beanName); //从IOC容器中获取对象
        try {
            Method method = obj.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class); //获取方法
            try {
                method.invoke(obj, request, response);  //执行方法
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
