<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Order Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:600px">
<% DeliveryOrderDTO o=(DeliveryOrderDTO)request.getAttribute("order"); boolean isEdit=o!=null;
   List<CustomerDTO> customers=(List<CustomerDTO>)request.getAttribute("customers");
   List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses"); %>
<h2><%=isEdit?"Edit":"New"%> Order</h2>
<form method="post" action="${pageContext.request.contextPath}/OrderServlet">
<% if(isEdit){ %><input type="hidden" name="orderId" value="<%=o.getOrderId()%>"><% } %>
<div class="form-group"><label>Order Code</label><input type="text" name="orderCode" value="<%=isEdit&&o.getOrderCode()!=null?o.getOrderCode():""%>" required/></div>
<div class="form-row">
<div class="form-group"><label>Customer</label><select name="customerId">
<% if(customers!=null) for(CustomerDTO c:customers){ %><option value="<%=c.getCustomerID()%>" <%=isEdit&&o.getCustomerId()==c.getCustomerID()?"selected":""%>><%=c.getCustomerName()%></option><% } %>
</select></div>
<div class="form-group"><label>Warehouse</label><select name="warehouseId">
<% if(warehouses!=null) for(WarehouseDTO w:warehouses){ %><option value="<%=w.getWarehouseId()%>" <%=isEdit&&o.getWarehouseId()==w.getWarehouseId()?"selected":""%>><%=w.getWarehouseName()%></option><% } %>
</select></div></div>
<div class="form-row">
<div class="form-group"><label>Status</label><select name="status">
<% String[] statuses={"PENDING","DISPATCHED","DELIVERED","RETURNED","CANCELLED"};
   for(String s:statuses){ %><option value="<%=s%>" <%=isEdit&&s.equals(o.getStatus())?"selected":""%>><%=s%></option><% } %>
</select></div>
<div class="form-group"><label>COD</label><input type="checkbox" name="hasCod" <%=isEdit&&o.isHasCod()?"checked":""%> style="width:auto;margin-top:8px"/></div></div>
<div class="form-row">
<div class="form-group"><label>COD Amount</label><input type="number" step="0.01" name="codAmount" value="<%=isEdit&&o.getCodAmount()!=null?o.getCodAmount():""%>"/></div>
<div class="form-group"><label>Total Amount</label><input type="number" step="0.01" name="totalAmount" value="<%=isEdit&&o.getTotalAmount()!=null?o.getTotalAmount():""%>"/></div></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/OrderServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
