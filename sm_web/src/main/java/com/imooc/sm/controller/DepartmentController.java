package com.imooc.sm.controller;

import com.imooc.sm.entity.Department;
import com.imooc.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    //    /department/list.do         /department_list.jsp
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.getAll(); //获取所有的部门信息
        request.setAttribute("LIST", list);   //将部门信息放置在request里
        request.getRequestDispatcher("../department_list.jsp").forward(request, response);   //转发到页面
    }

    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_add.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");   //从文本框取值

        Department department = new Department();
        department.setName(name);
        department.setAddress(address);   //  将文本框的值赋值给对象

        departmentService.add(department);  // 用业务处理功能将对象添加进去保存

        response.sendRedirect("list.do");  // 返回页面
    }

    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));  // 传递一个参数给控制器
        Department department = departmentService.get(id);  // 获取要修改的对象
        request.setAttribute("OBJ", department);
        request.getRequestDispatcher("../department_edit.jsp").forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));  // 提交这个参数
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setAddress(address);
        departmentService.edit(department);

        response.sendRedirect("list.do");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        departmentService.remove(id);

        response.sendRedirect("list.do");
    }
}
