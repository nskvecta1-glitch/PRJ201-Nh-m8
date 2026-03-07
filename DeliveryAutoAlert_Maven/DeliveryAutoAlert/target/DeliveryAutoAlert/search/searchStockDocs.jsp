<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Search Stock Docs</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><h2>Search Outbound Documents</h2>
<% List<WarehouseDTO> warehouses=(List<WarehouseDTO>)request.getAttribute("warehouses"); %>
<form method="get" action="${pageContext.request.contextPath}/OutboundServlet" class="search-bar">
<input type="hidden" name="action" value="search"/>
<div class="form-group"><label>From</label><input type="date" name="from"/></div>
<div class="form-group"><label>To</label><input type="date" name="to"/></div>
<div class="form-group"><label>Warehouse</label><select name="warehouseId"><option value="">All</option>
<% if(warehouses!=null) for(WarehouseDTO w:warehouses){ %><option value="<%=w.getWarehouseId()%>"><%=w.getWarehouseName()%></option><% } %>
</select></div>
<div class="form-group"><label>Status</label><select name="status"><option value="">All</option>
<% String[] ss={"DRAFT","CONFIRMED","CANCELLED"}; for(String s:ss){ %><option value="<%=s%>"><%=s%></option><% } %>
</select></div>
<div class="form-group" style="align-self:flex-end"><button type="submit" class="btn btn-primary">Search</button></div>
</form>
<% List<OutboundDocDTO> docs=(List<OutboundDocDTO>)request.getAttribute("docs"); if(docs!=null){ %>
<table><tr><th>#</th><th>Order ID</th><th>Warehouse ID</th><th>Date</th><th>Status</th></tr>
<% for(OutboundDocDTO d:docs){ %>
<tr><td><%=d.getOutboundId()%></td><td><%=d.getRefOrderId()%></td><td><%=d.getWarehouseId()%></td>
<td><%=d.getOutboundDate()!=null?d.getOutboundDate():""%></td><td><span class="badge badge-info"><%=d.getStatus()%></span></td></tr>
<% } %></table><% } %>
</div></body></html>
