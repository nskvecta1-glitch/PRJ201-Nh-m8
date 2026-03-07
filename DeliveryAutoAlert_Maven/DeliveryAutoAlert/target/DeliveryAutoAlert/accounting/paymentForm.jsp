<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.accounting.*" %>
<!DOCTYPE html><html><head><title>Payment Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% List<InvoiceDTO> invoices=(List<InvoiceDTO>)request.getAttribute("invoices"); %>
<h2>New Payment</h2>
<form method="post" action="${pageContext.request.contextPath}/PaymentServlet">
<div class="form-group"><label>Invoice</label><select name="invoiceId">
<% if(invoices!=null) for(InvoiceDTO i:invoices){ %><option value="<%=i.getInvoiceId()%>">#<%=i.getInvoiceId()%> - <%=i.getTotalAmount()%></option><% } %>
</select></div>
<div class="form-group"><label>Amount</label><input type="number" step="0.01" name="amount" required/></div>
<div class="form-group"><label>Method</label><select name="paymentMethod">
<% String[] methods={"CASH","COD","TRANSFER","CARD"}; for(String m:methods){ %><option value="<%=m%>"><%=m%></option><% } %>
</select></div>
<div class="form-group"><label>Status</label><select name="status">
<% String[] ss={"PENDING","COMPLETED","FAILED"}; for(String s:ss){ %><option value="<%=s%>"><%=s%></option><% } %>
</select></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/PaymentServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
