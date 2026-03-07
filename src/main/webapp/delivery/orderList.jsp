<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>Orders</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Delivery Orders</h2>
<div><a href="${pageContext.request.contextPath}/OrderServlet?action=search" class="btn btn-primary">Search</a>
&nbsp;<a href="${pageContext.request.contextPath}/OrderServlet?action=new" class="btn btn-success">+ New Order</a></div></div>
<table><tr><th>#</th><th>Code</th><th>Customer</th><th>Warehouse</th><th>Status</th><th>COD</th><th>Total</th><th>Actions</th></tr>
<% List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders");
   if(orders!=null) for(DeliveryOrderDTO o:orders){ %>
<tr><td><%=o.getOrderId()%></td><td><%=o.getOrderCode()!=null?o.getOrderCode():""%></td>
<td><%=o.getCustomerId()%></td><td><%=o.getWarehouseId()%></td>
<td><span class="badge badge-info"><%=o.getStatus()!=null?o.getStatus():""%></span></td>
<td><%=o.isHasCod()?"Yes":"No"%></td><td><%=o.getTotalAmount()!=null?o.getTotalAmount():""%></td>
<td><a href="${pageContext.request.contextPath}/OrderServlet?action=detail&id=<%=o.getOrderId()%>" class="btn btn-primary btn-sm">View</a>
<a href="${pageContext.request.contextPath}/OrderServlet?action=edit&id=<%=o.getOrderId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/OrderServlet?action=delete&id=<%=o.getOrderId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
