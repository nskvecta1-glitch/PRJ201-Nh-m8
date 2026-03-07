<%@ page contentType="text/html;charset=UTF-8" import="DTO.devlivery.*" %>
<!DOCTYPE html><html><head><title>POD</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<% ShipmentDTO ship=(ShipmentDTO)request.getAttribute("shipment");
   ProofOfDeliveryDTO pod=(ProofOfDeliveryDTO)request.getAttribute("pod"); %>
<h2>Proof of Delivery — Shipment #<%=ship!=null?ship.getShipmentId():""%></h2>
<% if(pod!=null){ %>
<div class="card"><p><strong>Receiver:</strong> <%=pod.getReceiverName()!=null?pod.getReceiverName():""%></p>
<p><strong>Delivered At:</strong> <%=pod.getDeliveredAt()!=null?pod.getDeliveredAt():""%></p>
<% if(pod.getPodImageUrl()!=null&&!pod.getPodImageUrl().isEmpty()){ %>
<p><strong>Image:</strong> <a href="<%=pod.getPodImageUrl()%>" target="_blank">View</a></p><% } %>
</div>
<% } else { %>
<div class="card"><p>No POD uploaded yet.</p>
<form method="post" action="${pageContext.request.contextPath}/ShipmentServlet">
<input type="hidden" name="type" value="pod"/>
<input type="hidden" name="shipmentId" value="<%=ship!=null?ship.getShipmentId():""%>"/>
<div class="form-group"><label>Receiver Name</label><input type="text" name="receiverName" required/></div>
<div class="form-group"><label>Image URL</label><input type="text" name="podImageUrl"/></div>
<button type="submit" class="btn btn-success">Upload POD</button>
</form></div><% } %>
<a href="${pageContext.request.contextPath}/ShipmentServlet" class="btn btn-warning">Back</a>
</div></body></html>
