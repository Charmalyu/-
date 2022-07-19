<%--
  Created by IntelliJ IDEA.
  User: Charmal
  Date: 2022/3/30
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>、
<%--标签户--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>部门列表</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
        });
    </script>
    <!--框架高度设置-->
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">部门列表</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>

                    <%--第一列显示的字符--%>
                    <th scope="col">名称</th>
                        <%--第二列--%>
                    <th scope="col">地址</th>
                        <%--第三列--%>
                    <th scope="col">操作</th>
                </tr>
                <%--迭代--%>
                <c:forEach items="${LIST}" var="dep">
                <tr>
                    <%--第一个单元格显示的--%>
                    <td>${dep.name}</td>
                        <%--第二个单元格显示的--%>
                    <td>${dep.address}</td>
                    <td>
                        <%--编辑的超链接--%>
                        <a href="toEdit.do?id=${dep.id}" class="btn">编辑</a>
                            <%--删除的超链接--%>
                        <a href="remove.do?id=${dep.id}" class="btn">删除</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <!--列表-->
            <!--右边底部-->
            <div class="r_foot">
                <div class="r_foot_m">
                    <%--添加的超链接--%>
                    <a href="toAdd.do" class="btn">添加</a>
                </div>
            </div>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>