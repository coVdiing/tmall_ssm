<%--
  Created by IntelliJ IDEA.
  User: vi
  Date: 2019/9/21
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function(){
        $(".orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $(".orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });
</script>

<title>订单管理</title>

<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-condensed table-hover table-bordered">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>状态</th>
                    <th>金额</th>
                    <th width="100px">商品数量</th>
                    <th width="100px">买家名称</th>
                    <th>创建时间</th>
                    <th>支付时间</th>
                    <th>发货时间</th>
                    <th>确认收货时间</th>
                    <th width="120px">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${os}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.statusDesc}</td>
                        <td>￥<fmt:formatNumber  minFractionDigits="2" value="${order.total}" type="number"/></td>
                        <td align="center">${order.totalNumber}</td>
                        <td align="center">${order.user.name}</td>
                        <td><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <button oid="${order.id}" class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>
                            <c:if test="${order.status=='waitDelivery'}">
                                <a href="admin_order_delivery?id=${order.id}">
                                    <button class="btn btn-primary btn-xs">发货</button>
                                </a>
                            </c:if>
                        </td>
                    </tr>
                    <!-- display:none 此元素不会显示-->
                    <tr class="orderPageOrderItemTR" oid="${order.id}">
                        <td colspan="10" align="center">
                            <div class="orderPageOrderItem">
                                <table width="800px" align="center" class="orderPageOrderItemTable">
                                    <c:forEach items="${order.orderItems}" var="oi">
                                        <tr>
                                            <td align="left">
                                               <img width="40px" height="40px" src="img/productSingle/${oi.product.firstProductImage.id}.jpg" />
                                            </td>
                                            <td>
                                                <a href="#">
                                                    <span>${oi.product.name}</span>
                                                </a>
                                            </td>
                                            <td align="right">
                                                <span class="text-muted"> x ${oi.number}</span>
                                            </td>
                                            <td align="right">
                                                <span class="text-muted"> 单价: ￥${oi.product.promotePrice}</span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="pageDiv">
    <%@include file="../include/admin/adminPage.jsp" %>
</div>

<%@ include file="../include/admin/adminFooter.jsp" %>