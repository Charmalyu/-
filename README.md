# sm  人员管理系统案例
spring整合mybatis开发

功能：
     
     部门信息的增删改查、员工信息的增删改查、登陆、退出、个人信息、修改密码

三层架构：持久层--Mybatis、表现层--Servlet+Jsp、管理对象、切面处理--Spring

sm_service是实现持久层和业务层

sm_web是实现表现层

工具类有编码过滤器、核心控制器
