<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.accounting.*" %>
<!DOCTYPE html><html><head><title>Invoice Detail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<% InvoiceDTO inv=(InvoiceDTO)request.getAttribute("invoice"); %>
<h2>Invoice #<%=inv!=null?inv.getInvoiceId():""%></h2>
<% if(inv!=null){ %>
<div class="card"><p><strong>Order ID:</strong> <%=inv.getOrderId()%> &nbsp; <strong>Total:</strong> <%=inv.getTotalAmount()%> &nbsp; <strong>Status:</strong> <%=inv.getStatus()%></p></div>
<div class="toolbar"><h3>Payments</h3>
<a href="${pageContext.request.contextPath}/PaymentServlet?action=new" class="btn btn-success">+ Add Payment</a></div>
<table><tr><th>#</th><th>Date</th><th>Amount</th><th>Method</th><th>Status</th></tr>
<% List<PaymentDTO> payments=(List<PaymentDTO>)request.getAttribute("payments");
   if(payments!=null) for(PaymentDTO p:payments){ %>
<tr><td><%=p.getPaymentId()%></td><td><%=p.getPaymentDate()!=null?p.getPaymentDate():""%></td>
<td><%=p.getAmount()%></td><td><%=p.getPaymentMethod()!=null?p.getPaymentMethod():""%></td><td><%=p.getStatus()!=null?p.getStatus():""%></td></tr>
<% } %></table><% } %>
<br/><a href="${pageContext.request.contextPath}/InvoiceServlet" class="btn btn-warning">Back</a>
</div></body></html>
