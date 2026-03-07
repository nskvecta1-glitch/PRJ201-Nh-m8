<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*" %>
<!DOCTYPE html><html><head><title>Outbound Docs</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Outbound Documents</h2>
<div><a href="${pageContext.request.contextPath}/OutboundServlet?action=search" class="btn btn-primary">Search</a>
&nbsp;<a href="${pageContext.request.contextPath}/OutboundServlet?action=new" class="btn btn-success">+ New</a></div></div>
<table><tr><th>#</th><th>Order ID</th><th>Warehouse ID</th><th>Date</th><th>Status</th><th>Actions</th></tr>
<% List<OutboundDocDTO> docs=(List<OutboundDocDTO>)request.getAttribute("docs");
   if(docs!=null) for(OutboundDocDTO d:docs){ %>
<tr><td><%=d.getOutboundId()%></td><td><%=d.getRefOrderId()%></td><td><%=d.getWarehouseId()%></td>
<td><%=d.getOutboundDate()!=null?d.getOutboundDate():""%></td>
<td><span class="badge badge-info"><%=d.getStatus()!=null?d.getStatus():""%></span></td>
<td><a href="${pageContext.request.contextPath}/OutboundServlet?action=edit&id=<%=d.getOutboundId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/OutboundServlet?action=delete&id=<%=d.getOutboundId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
