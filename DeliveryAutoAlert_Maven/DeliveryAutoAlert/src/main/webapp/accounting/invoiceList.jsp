<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.accounting.*" %>
<!DOCTYPE html><html><head><title>Invoices</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Invoices</h2>
<a href="${pageContext.request.contextPath}/InvoiceServlet?action=new" class="btn btn-success">+ New Invoice</a></div>
<table><tr><th>#</th><th>Order ID</th><th>Date</th><th>Total</th><th>Status</th><th>Actions</th></tr>
<% List<InvoiceDTO> invoices=(List<InvoiceDTO>)request.getAttribute("invoices");
   if(invoices!=null) for(InvoiceDTO i:invoices){ %>
<tr><td><%=i.getInvoiceId()%></td><td><%=i.getOrderId()%></td>
<td><%=i.getInvoiceDate()!=null?i.getInvoiceDate():""%></td>
<td><%=i.getTotalAmount()!=null?i.getTotalAmount():""%></td>
<td><span class="badge badge-info"><%=i.getStatus()!=null?i.getStatus():""%></span></td>
<td><a href="${pageContext.request.contextPath}/InvoiceServlet?action=detail&id=<%=i.getInvoiceId()%>" class="btn btn-primary btn-sm">View</a>
<a href="${pageContext.request.contextPath}/InvoiceServlet?action=edit&id=<%=i.getInvoiceId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/InvoiceServlet?action=delete&id=<%=i.getInvoiceId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
