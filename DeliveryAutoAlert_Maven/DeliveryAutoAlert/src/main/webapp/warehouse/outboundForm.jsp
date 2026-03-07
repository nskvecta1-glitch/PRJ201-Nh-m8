<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*,DTO.devlivery.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Outbound Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% OutboundDocDTO d=(OutboundDocDTO)request.getAttribute("doc"); boolean isEdit=d!=null;
   List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders");
   List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses"); %>
<h2><%=isEdit?"Edit":"New"%> Outbound Doc</h2>
<form method="post" action="${pageContext.request.contextPath}/OutboundServlet">
<% if(isEdit){ %><input type="hidden" name="outboundId" value="<%=d.getOutboundId()%>"><% } %>
<div class="form-group"><label>Order</label><select name="refOrderId">
<% if(orders!=null) for(DeliveryOrderDTO o:orders){ %><option value="<%=o.getOrderId()%>" <%=isEdit&&d.getRefOrderId()==o.getOrderId()?"selected":""%>><%=o.getOrderCode()%></option><% } %>
</select></div>
<div class="form-group"><label>Warehouse</label><select name="warehouseId">
<% if(warehouses!=null) for(WarehouseDTO w:warehouses){ %><option value="<%=w.getWarehouseId()%>" <%=isEdit&&d.getWarehouseId()==w.getWarehouseId()?"selected":""%>><%=w.getWarehouseName()%></option><% } %>
</select></div>
<div class="form-group"><label>Status</label><select name="status">
<% String[] ss={"DRAFT","CONFIRMED","CANCELLED"}; for(String s:ss){ %><option value="<%=s%>" <%=isEdit&&s.equals(d.getStatus())?"selected":""%>><%=s%></option><% } %>
</select></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/OutboundServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
