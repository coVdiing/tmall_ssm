<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $("#addForm").submit(function(){
            if(!checkEmpty("name","商品名称"))
                return false;
            // if(!checkEmpty("subTitle","商品小标题"))
            //     return false;
            if(!checkEmpty("originalPrice","原价格"))
                return false;
            if(!checkEmpty("promotePrice","优惠价格"))
                return false;
            if(!checkEmpty("stock","库存"))
                return false;
        });
    });
</script>
<title>产品列表</title>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">全部分类</a></li>
        <li>${category.name}</li>
        <li class="active">产品列表</li>
    </ol>
    <!-- 展示数据 -->
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th>ID</th>
                <th>图片</th>
                <th>产品名称</th>
                <th>产品小标题</th>
                <th>原价格</th>
                <th>优惠价格</th>
                <th>库存数量</th>
                <th>图片管理</th>
                <th>设置属性</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td></td>
                    <td>${product.name}</td>
                    <td>${product.subTitle}</td>
                    <td>${product.originalPrice}</td>
                    <td>${product.promotePrice}</td>
                    <td>${product.stock}</td>
                    <td><a href="admin_productImage_list?${product.id}"><span class="glyphicon glyphicon-picture"></span></a></td>
                    <td><a href="admin_product_editPropertyValue"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="admin_product_edit?id=${product.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="admin_product_delete?id=${product.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- 添加数据 -->
    <div class="panel panel-warning addDiv" >
        <div class="panel-heading">新增产品</div>
        <div class="panel-body">
            <form action="admin_product_add" method="post" id="addForm">
               <table class="addTable">
                   <tr>
                       <td>产品名称</td>
                       <td><input id="name" name="name" type="text" class="form-control"></td>
                   </tr>
                   <tr>
                       <td>产品小标题</td>
                       <td><input id="subTitle" name="subTitle" type="text" class="form-control"></td>
                   </tr>
                   <tr>
                       <td>原价格</td>
                       <td><input type="text" id="originalPrice" value="99.8" name="originalPrice" class="form-control"> </td>
                   </tr>
                   <tr>
                       <td>优惠价格</td>
                       <td><input type="text" id="promotePrice" value="19.8" name="promotePrice" class="form-control"></td>
                   </tr>
                   <tr>
                       <td>库存</td>
                       <td><input type="text" id="stock" name="stock"  value="99" class="form-control"></td>
                   </tr>
                   <tr class="submitTR">
                       <td colspan="2" align="center">
                           <input type="hidden" name="cid" value="${category.id}">
                           <button type="submit" class="btn btn-success">提交</button>
                       </td>
                   </tr>
               </table>
            </form>
        </div>
    </div>
</div>
<div class="pageDiv">
    <%@ include file="../include/admin/adminPage.jsp" %>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>