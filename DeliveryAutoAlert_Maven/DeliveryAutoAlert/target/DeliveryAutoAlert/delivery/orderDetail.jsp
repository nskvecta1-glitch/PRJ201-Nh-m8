<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>Order Detail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<% DeliveryOrderDTO o=(DeliveryOrderDTO)request.getAttribute("order"); %>
<h2>Order: <%=o!=null?o.getOrderCode():""%></h2>
<% if(o!=null){ %>
<div class="card">
<p><strong>Status:</strong> <%=o.getStatus()%> &nbsp; <strong>COD:</strong> <%=o.isHasCod()?"Yes":"No"%> &nbsp; <strong>Total:</strong> <%=o.getTotalAmount()%></p>
<p><strong>Customer ID:</strong> <%=o.getCustomerId()%> &nbsp; <strong>Warehouse ID:</strong> <%=o.getWarehouseId()%></p>
</div>
<h3>Items</h3>
<table><tr><th>#</th><th>Product ID</th><th>Qty</th><th>Unit Price</th></tr>
<% List<OrderItemDTO> items=(List<OrderItemDTO>)request.getAttribute("items");
   if(items!=null) for(OrderItemDTO i:items){ %>
<tr><td><%=i.getOrderItemId()%></td><td><%=i.getProductId()%></td><td><%=i.getQuantity()%></td><td><%=i.getUnitPrice()%></td></tr>
<% } %></table>
<% } %>
<br/><a href="${pageContext.request.contextPath}/OrderServlet" class="btn btn-warning">Back</a>
</div></body></html>
