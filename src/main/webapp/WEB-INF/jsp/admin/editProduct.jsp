<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>产品编辑</title>
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
<ol class="breadcrumb">
    <li><a href="admin_product_list?cid=${category.id}">${category.name}</a></li>
    <li>${product.name}</li>
    <li class="active">产品编辑</li>
</ol>
<!-- 修改数据 -->
<div class="panel panel-warning editDiv" >
    <div class="panel-heading">产品编辑</div>
    <div class="panel-body">
        <form action="admin_product_update" method="post" id="addForm">
            <table class="editTable">
                <tr>
                    <td>产品名称</td>
                    <td><input id="name" name="name" type="text"  value="${product.name}"class="form-control"></td>
                </tr>
                <tr>
                    <td>产品小标题</td>
                    <td><input id="subTitle" name="subTitle" type="text" value="${product.subTitle}"class="form-control"></td>
                </tr>
                <tr>
                    <td>原价格</td>
                    <td><input type="text" id="originalPrice" name="originalPrice"  value="${product.originalPrice}" class="form-control"> </td>
                </tr>
                <tr>
                    <td>优惠价格</td>
                    <td><input type="text" id="promotePrice" name="promotePrice"  value="${product.promotePrice}"class="form-control"></td>
                </tr>
                <tr>
                    <td>库存</td>
                    <td><input type="text" id="stock" name="stock"  value="${product.stock}" class="form-control"></td>
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
