<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.accounting.*" %>
<!DOCTYPE html><html><head><title>Payments</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Payments</h2>
<a href="${pageContext.request.contextPath}/PaymentServlet?action=new" class="btn btn-success">+ New Payment</a></div>
<table><tr><th>#</th><th>Invoice ID</th><th>Date</th><th>Amount</th><th>Method</th><th>Status</th><th>Actions</th></tr>
<% List<PaymentDTO> payments=(List<PaymentDTO>)request.getAttribute("payments");
   if(payments!=null) for(PaymentDTO p:payments){ %>
<tr><td><%=p.getPaymentId()%></td><td><%=p.getInvoiceId()%></td>
<td><%=p.getPaymentDate()!=null?p.getPaymentDate():""%></td>
<td><%=p.getAmount()%></td><td><%=p.getPaymentMethod()!=null?p.getPaymentMethod():""%></td>
<td><span class="badge badge-info"><%=p.getStatus()!=null?p.getStatus():""%></span></td>
<td><a href="${pageContext.request.contextPath}/PaymentServlet?action=delete&id=<%=p.getPaymentId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Del</a></td></tr>
<% } %></table></div></body></html>
