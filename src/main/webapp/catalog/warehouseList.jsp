<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Warehouses</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Warehouses</h2>
<a href="${pageContext.request.contextPath}/WarehouseServlet?action=new" class="btn btn-success">+ New Warehouse</a></div>
<table><tr><th>#</th><th>Name</th><th>Location</th><th>Actions</th></tr>
<% List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses");
   if(warehouses!=null) for(WarehouseDTO w:warehouses){ %>
<tr><td><%=w.getWarehouseId()%></td><td><%=w.getWarehouseName()%></td>
<td><%=w.getLocation()!=null?w.getLocation():""%></td>
<td><a href="${pageContext.request.contextPath}/WarehouseServlet?action=edit&id=<%=w.getWarehouseId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/WarehouseServlet?action=delete&id=<%=w.getWarehouseId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Delete</a></td></tr>
<% } %></table></div></body></html>
