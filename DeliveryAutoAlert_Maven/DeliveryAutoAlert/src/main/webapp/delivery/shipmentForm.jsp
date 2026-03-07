<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>Shipment Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% ShipmentDTO s=(ShipmentDTO)request.getAttribute("shipment"); boolean isEdit=s!=null;
   List<DeliveryOrderDTO> orders=(List<DeliveryOrderDTO>)request.getAttribute("orders"); %>
<h2><%=isEdit?"Edit":"New"%> Shipment</h2>
<form method="post" action="${pageContext.request.contextPath}/ShipmentServlet">
<% if(isEdit){ %><input type="hidden" name="shipmentId" value="<%=s.getShipmentId()%>"><% } %>
<div class="form-group"><label>Order</label><select name="orderId">
<% if(orders!=null) for(DeliveryOrderDTO o:orders){ %><option value="<%=o.getOrderId()%>" <%=isEdit&&s.getOrderId()==o.getOrderId()?"selected":""%>><%=o.getOrderCode()%></option><% } %>
</select></div>
<div class="form-group"><label>Status</label><select name="deliveryStatus">
<% String[] ss={"IN_TRANSIT","DELIVERED","FAILED","RETURNED"};
   for(String st:ss){ %><option value="<%=st%>" <%=isEdit&&st.equals(s.getDeliveryStatus())?"selected":""%>><%=st%></option><% } %>
</select></div>
<div class="form-group"><label>Route</label><input type="text" name="route" value="<%=isEdit&&s.getRoute()!=null?s.getRoute():""%>"/></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/ShipmentServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
