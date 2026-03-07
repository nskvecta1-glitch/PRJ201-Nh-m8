<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*" %>
<!DOCTYPE html><html><head><title>Stock Ledger</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><h2>Stock Ledger</h2>
<table><tr><th>#</th><th>Product ID</th><th>Warehouse ID</th><th>Change Qty</th><th>Ref Type</th><th>Ref ID</th><th>Date</th></tr>
<% List<StockLedgerDTO> ledger=(List<StockLedgerDTO>)request.getAttribute("ledger");
   if(ledger!=null) for(StockLedgerDTO l:ledger){ %>
<tr><td><%=l.getLedgerId()%></td><td><%=l.getProductId()%></td><td><%=l.getWarehouseId()%></td>
<td style="color:<%=l.getChangeQty()>=0?"green":"red"%>"><%=l.getChangeQty()%></td>
<td><%=l.getRefType()!=null?l.getRefType():""%></td><td><%=l.getRefId()%></td>
<td><%=l.getCreatedAt()!=null?l.getCreatedAt():""%></td></tr>
<% } %></table></div></body></html>
