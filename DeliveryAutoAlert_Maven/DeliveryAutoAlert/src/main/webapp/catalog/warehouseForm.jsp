<%@ page contentType="text/html;charset=UTF-8" import="DTO.master.*" %>
<!DOCTYPE html><html><head><title>Warehouse Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% WarehouseDTO w=(WarehouseDTO)request.getAttribute("warehouse"); boolean isEdit=w!=null; %>
<h2><%=isEdit?"Edit":"New"%> Warehouse</h2>
<form method="post" action="${pageContext.request.contextPath}/WarehouseServlet">
<% if(isEdit){ %><input type="hidden" name="warehouseId" value="<%=w.getWarehouseId()%>"><% } %>
<div class="form-group"><label>Name</label><input type="text" name="warehouseName" value="<%=isEdit?w.getWarehouseName():""%>" required/></div>
<div class="form-group"><label>Location</label><textarea name="location"><%=isEdit&&w.getLocation()!=null?w.getLocation():""%></textarea></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/WarehouseServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
