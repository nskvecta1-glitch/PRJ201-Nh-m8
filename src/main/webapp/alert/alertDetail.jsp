<%@ page contentType="text/html;charset=UTF-8" import="DTO.alert.*" %>
<!DOCTYPE html><html><head><title>Alert Detail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<% AlertEventDTO a=(AlertEventDTO)request.getAttribute("alert"); %>
<h2>Alert #<%=a!=null?a.getAlertId():""%></h2>
<% if(a!=null){ %>
<div class="card">
<p><strong>Rule ID:</strong> <%=a.getRuleId()%></p>
<p><strong>Ref Type:</strong> <%=a.getRefType()%> &nbsp; <strong>Ref ID:</strong> <%=a.getRefId()%></p>
<p><strong>Risk Score:</strong> <%=String.format("%.2f",a.getRiskScore())%></p>
<p><strong>Status:</strong> <%=a.getStatus()%></p>
<p><strong>Created:</strong> <%=a.getCreatedAt()%></p>
</div>
<form method="post" action="${pageContext.request.contextPath}/AlertServlet">
<input type="hidden" name="type" value="case"/>
<input type="hidden" name="alertId" value="<%=a.getAlertId()%>"/>
<div class="card"><h3>Create Case</h3>
<div class="form-group"><label>Status</label><select name="caseStatus">
<option value="OPEN">OPEN</option><option value="IN_PROGRESS">IN_PROGRESS</option></select></div>
<div class="form-group"><label>Assigned To (User ID)</label><input type="number" name="assignedTo" required/></div>
<div class="form-group"><label>Note</label><textarea name="resolutionNote"></textarea></div>
<button type="submit" class="btn btn-success">Create Case</button></div>
</form><% } %>
<a href="${pageContext.request.contextPath}/AlertServlet" class="btn btn-warning">Back</a>
</div></body></html>
