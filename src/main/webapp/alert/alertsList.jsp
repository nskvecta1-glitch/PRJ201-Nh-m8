<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.alert.*" %>
<!DOCTYPE html><html><head><title>Alerts</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Alert Events</h2>
<a href="${pageContext.request.contextPath}/AlertServlet?action=rules" class="btn btn-primary">Manage Rules</a></div>
<table><tr><th>#</th><th>Rule ID</th><th>Ref Type</th><th>Ref ID</th><th>Risk Score</th><th>Status</th><th>Created</th><th>Actions</th></tr>
<% List<AlertEventDTO> alerts=(List<AlertEventDTO>)request.getAttribute("alerts");
   if(alerts!=null) for(AlertEventDTO a:alerts){ %>
<tr><td><%=a.getAlertId()%></td><td><%=a.getRuleId()%></td><td><%=a.getRefType()!=null?a.getRefType():""%></td>
<td><%=a.getRefId()%></td>
<td><span class="badge <%=a.getRiskScore()>70?"badge-danger":a.getRiskScore()>40?"badge-warning":"badge-success"%>"><%=String.format("%.1f",a.getRiskScore())%></span></td>
<td><span class="badge badge-info"><%=a.getStatus()!=null?a.getStatus():""%></span></td>
<td><%=a.getCreatedAt()!=null?a.getCreatedAt():""%></td>
<td><a href="${pageContext.request.contextPath}/AlertServlet?action=detail&id=<%=a.getAlertId()%>" class="btn btn-primary btn-sm">View</a></td></tr>
<% } %></table></div></body></html>
