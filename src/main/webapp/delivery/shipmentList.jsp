<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>Shipments</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Shipments</h2>
<a href="${pageContext.request.contextPath}/ShipmentServlet?action=new" class="btn btn-success">+ New Shipment</a></div>
<table><tr><th>#</th><th>Order ID</th><th>Ship Date</th><th>Status</th><th>Route</th><th>Actions</th></tr>
<% List<ShipmentDTO> shipments=(List<ShipmentDTO>)request.getAttribute("shipments");
   if(shipments!=null) for(ShipmentDTO s:shipments){ %>
<tr><td><%=s.getShipmentId()%></td><td><%=s.getOrderId()%></td>
<td><%=s.getShipDate()!=null?s.getShipDate():""%></td>
<td><span class="badge badge-info"><%=s.getDeliveryStatus()!=null?s.getDeliveryStatus():""%></span></td>
<td><%=s.getRoute()!=null?s.getRoute():""%></td>
<td><a href="${pageContext.request.contextPath}/ShipmentServlet?action=pod&id=<%=s.getShipmentId()%>" class="btn btn-primary btn-sm">POD</a>
<a href="${pageContext.request.contextPath}/ShipmentServlet?action=edit&id=<%=s.getShipmentId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/ShipmentServlet?action=delete&id=<%=s.getShipmentId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
