<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.alert.*" %>
<!DOCTYPE html><html><head><title>Alert Rules</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><h2>Alert Rules</h2>
<div class="card" style="max-width:500px">
<h3>Add Rule</h3>
<form method="post" action="${pageContext.request.contextPath}/AlertServlet">
<div class="form-group"><label>Rule Name</label><input type="text" name="ruleName" required/></div>
<div class="form-group"><label>Rule Type</label><select name="ruleType">
<% String[] types={"LATE_DELIVERY","QTY_MISMATCH","COD_MISMATCH","MISSING_POD","NEG_STOCK"};
   for(String t:types){ %><option value="<%=t%>"><%=t%></option><% } %>
</select></div>
<div class="form-group"><label>Threshold</label><input type="number" step="0.01" name="thresholdValue"/></div>
<div class="form-group"><label>Severity</label><select name="severity">
<% String[] sevs={"LOW","MEDIUM","HIGH","CRITICAL"}; for(String s:sevs){ %><option value="<%=s%>"><%=s%></option><% } %>
</select></div>
<button type="submit" class="btn btn-success">Add Rule</button>
</form></div>
<table><tr><th>#</th><th>Name</th><th>Type</th><th>Threshold</th><th>Severity</th></tr>
<% List<AlertRuleDTO> rules=(List<AlertRuleDTO>)request.getAttribute("rules");
   if(rules!=null) for(AlertRuleDTO r:rules){ %>
<tr><td><%=r.getRuleId()%></td><td><%=r.getRuleName()%></td><td><%=r.getRuleType()%></td>
<td><%=r.getThresholdValue()%></td>
<td><span class="badge <%="CRITICAL".equals(r.getSeverity())?"badge-danger":"HIGH".equals(r.getSeverity())?"badge-warning":"badge-success"%>"><%=r.getSeverity()%></span></td></tr>
<% } %></table></div></body></html>
