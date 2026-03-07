<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Search Orders</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><h2>Search Orders</h2>
<% List<CustomerDTO> customers=(List<CustomerDTO>)request.getAttribute("customers");
   List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses"); %>
<form method="get" action="${pageContext.request.contextPath}/OrderServlet" class="search-bar">
<input type="hidden" name="action" value="search"/>
<div class="form-group"><label>Status</label><select name="status">
<option value="">All</option>
<% String[] ss={"PENDING","DISPATCHED","DELIVERED","RETURNED","CANCELLED"}; for(String s:ss){ %><option value="<%=s%>"><%=s%></option><% } %>
</select></div>
<div class="form-group"><label>From</label><input type="date" name="from"/></div>
<div class="form-group"><label>To</label><input type="date" name="to"/></div>
<div class="form-group"><label>Customer</label><select name="customerId"><option value="">All</option>
<% if(customers!=null) for(CustomerDTO c:customers){ %><option value="<%=c.getCustomerID()%>"><%=c.getCustomerName()%></option><% } %>
</select></div>
<div class="form-group"><label>Warehouse</label><select name="warehouseId"><option value="">All</option>
<% if(warehouses!=null) for(WarehouseDTO w:warehouses){ %><option value="<%=w.getWarehouseId()%>"><%=w.getWarehouseName()%></option><% } %>
</select></div>
<div class="form-group" style="align-self:flex-end"><button type="submit" class="btn btn-primary">Search</button></div>
</form>
<% List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders"); if(orders!=null){ %>
<table><tr><th>#</th><th>Code</th><th>Customer</th><th>Warehouse</th><th>Status</th><th>COD</th><th>Total</th><th>Action</th></tr>
<% for(DeliveryOrderDTO o:orders){ %>
<tr><td><%=o.getOrderId()%></td><td><%=o.getOrderCode()%></td><td><%=o.getCustomerId()%></td><td><%=o.getWarehouseId()%></td>
<td><span class="badge badge-info"><%=o.getStatus()%></span></td><td><%=o.isHasCod()?"Yes":"No"%></td><td><%=o.getTotalAmount()%></td>
<td><a href="${pageContext.request.contextPath}/OrderServlet?action=detail&id=<%=o.getOrderId()%>" class="btn btn-primary btn-sm">View</a></td></tr>
<% } %></table><% } %>
</div></body></html>
