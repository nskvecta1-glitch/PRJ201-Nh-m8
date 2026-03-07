<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.alert.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Case Detail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<% ReconciliationCaseDTO c=(ReconciliationCaseDTO)request.getAttribute("case"); %>
<h2>Case #<%=c!=null?c.getCaseId():""%></h2>
<% if(c!=null){ %>
<div class="card">
<p><strong>Alert ID:</strong> <%=c.getAlertId()%> &nbsp; <strong>Status:</strong> <%=c.getCaseStatus()%> &nbsp; <strong>Assigned To:</strong> <%=c.getAssignedTo()%></p>
<p><strong>Note:</strong> <%=c.getResolutionNote()!=null?c.getResolutionNote():""%></p>
</div>
<form method="post" action="${pageContext.request.contextPath}/AlertServlet">
<input type="hidden" name="type" value="case"/>
<input type="hidden" name="caseId" value="<%=c.getCaseId()%>"/>
<input type="hidden" name="alertId" value="<%=c.getAlertId()%>"/>
<div class="card"><h3>Update Case</h3>
<div class="form-group"><label>Status</label><select name="caseStatus">
<% String[] ss={"OPEN","IN_PROGRESS","RESOLVED","CLOSED"};
   for(String s:ss){ %><option value="<%=s%>" <%=s.equals(c.getCaseStatus())?"selected":""%>><%=s%></option><% } %>
</select></div>
<div class="form-group"><label>Assigned To (User ID)</label><input type="number" name="assignedTo" value="<%=c.getAssignedTo()%>"/></div>
<div class="form-group"><label>Resolution Note</label><textarea name="resolutionNote"><%=c.getResolutionNote()!=null?c.getResolutionNote():""%></textarea></div>
<button type="submit" class="btn btn-primary">Update</button></div>
</form>
<h3>Actions Log</h3>
<table><tr><th>#</th><th>By (User ID)</th><th>Note</th><th>Time</th></tr>
<% List<AlertActionDTO> actions=(List<AlertActionDTO>)request.getAttribute("actions");
   if(actions!=null) for(AlertActionDTO a:actions){ %>
<tr><td><%=a.getActionId()%></td><td><%=a.getActionBy()%></td><td><%=a.getActionNote()!=null?a.getActionNote():""%></td><td><%=a.getActionTime()!=null?a.getActionTime():""%></td></tr>
<% } %></table>
<form method="post" action="${pageContext.request.contextPath}/AlertServlet" style="margin-top:16px">
<input type="hidden" name="type" value="action"/>
<input type="hidden" name="caseId" value="<%=c.getCaseId()%>"/>
<div class="form-group"><label>Add Note</label><textarea name="actionNote" required></textarea></div>
<button type="submit" class="btn btn-success">Add Note</button>
</form>
<% } %>
<br/><a href="${pageContext.request.contextPath}/AlertServlet?action=cases" class="btn btn-warning">Back</a>
</div></body></html>
