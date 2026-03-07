<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.accounting.*,DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>Invoice Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% InvoiceDTO inv=(InvoiceDTO)request.getAttribute("invoice"); boolean isEdit=inv!=null;
   List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders"); %>
<h2><%=isEdit?"Edit":"New"%> Invoice</h2>
<form method="post" action="${pageContext.request.contextPath}/InvoiceServlet">
<% if(isEdit){ %><input type="hidden" name="invoiceId" value="<%=inv.getInvoiceId()%>"><% } %>
<div class="form-group"><label>Order</label><select name="orderId">
<% if(orders!=null) for(DeliveryOrderDTO o:orders){ %><option value="<%=o.getOrderId()%>" <%=isEdit&&inv.getOrderId()==o.getOrderId()?"selected":""%>><%=o.getOrderCode()%></option><% } %>
</select></div>
<div class="form-group"><label>Total Amount</label><input type="number" step="0.01" name="totalAmount" value="<%=isEdit&&inv.getTotalAmount()!=null?inv.getTotalAmount():""%>" required/></div>
<div class="form-group"><label>Status</label><select name="status">
<% String[] ss={"UNPAID","PARTIAL","PAID","CANCELLED"}; for(String s:ss){ %><option value="<%=s%>" <%=isEdit&&s.equals(inv.getStatus())?"selected":""%>><%=s%></option><% } %>
</select></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/InvoiceServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
