<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*" %>
<!DOCTYPE html><html><head><title>Inbound Docs</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Inbound Documents</h2>
<a href="${pageContext.request.contextPath}/InboundServlet?action=new" class="btn btn-success">+ New</a></div>
<table><tr><th>#</th><th>Order ID</th><th>Warehouse ID</th><th>Date</th><th>Reason</th><th>Actions</th></tr>
<% List<InboundDocDTO> docs=(List<InboundDocDTO>)request.getAttribute("docs");
   if(docs!=null) for(InboundDocDTO d:docs){ %>
<tr><td><%=d.getInboundId()%></td><td><%=d.getRefOrderId()%></td><td><%=d.getWarehouseId()%></td>
<td><%=d.getInboundDate()!=null?d.getInboundDate():""%></td><td><%=d.getReason()!=null?d.getReason():""%></td>
<td><a href="${pageContext.request.contextPath}/InboundServlet?action=edit&id=<%=d.getInboundId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/InboundServlet?action=delete&id=<%=d.getInboundId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
