<%--
  Created by IntelliJ IDEA.
  User: vi
  Date: 2019/9/15
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>Title</title>
<script>
    $(function () {
       $("#addForm").submit(function() {
          if(checkEmpty("name","属性名称"))
              return true;
          return false;
       });
    });
</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">属性管理</li>

    </ol>
    <div class="addDiv panel panel-warning">
        <div class="panel-heading">编辑属性</div>
        <form id="addForm" action="admin_property_update" method="post">
            <table class="addTable">
                <tr>
                    <td>属性名称</td>
                    <td><input type="text" id="name" name="name" value="${p.name}"></td>
                </tr>
                <tr class="submitTR">
                    <td colspan="2" align="center">
                        <!-- 在editProperty.jsp中隐式提供id和cid-->
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="hidden" name="cid" value="${p.category.id}">
                        <button type="submit" class="btn btn-success">提交</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

