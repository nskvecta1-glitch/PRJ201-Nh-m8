<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*,DTO.devlivery.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Inbound Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% InboundDocDTO d=(InboundDocDTO)request.getAttribute("doc"); boolean isEdit=d!=null;
   List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders");
   List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses"); %>
<h2><%=isEdit?"Edit":"New"%> Inbound Doc</h2>
<form method="post" action="${pageContext.request.contextPath}/InboundServlet">
<% if(isEdit){ %><input type="hidden" name="inboundId" value="<%=d.getInboundId()%>"><% } %>
<div class="form-group"><label>Order</label><select name="refOrderId">
<% if(orders!=null) for(DeliveryOrderDTO o:orders){ %><option value="<%=o.getOrderId()%>" <%=isEdit&&d.getRefOrderId()==o.getOrderId()?"selected":""%>><%=o.getOrderCode()%></option><% } %>
</select></div>
<div class="form-group"><label>Warehouse</label><select name="warehouseId">
<% if(warehouses!=null) for(WarehouseDTO w:warehouses){ %><option value="<%=w.getWarehouseId()%>" <%=isEdit&&d.getWarehouseId()==w.getWarehouseId()?"selected":""%>><%=w.getWarehouseName()%></option><% } %>
</select></div>
<div class="form-group"><label>Reason</label><textarea name="reason"><%=isEdit&&d.getReason()!=null?d.getReason():""%></textarea></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/InboundServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
