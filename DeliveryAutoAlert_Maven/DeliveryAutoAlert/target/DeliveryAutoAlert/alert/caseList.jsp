<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.alert.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Cases</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><h2>Reconciliation Cases</h2>
<table><tr><th>#</th><th>Alert ID</th><th>Status</th><th>Assigned To</th><th>Opened</th><th>Closed</th><th>Actions</th></tr>
<% List<ReconciliationCaseDTO> cases=(List<ReconciliationCaseDTO>)request.getAttribute("cases");
   if(cases!=null) for(ReconciliationCaseDTO c:cases){ %>
<tr><td><%=c.getCaseId()%></td><td><%=c.getAlertId()%></td>
<td><span class="badge <%="OPEN".equals(c.getCaseStatus())?"badge-danger":"RESOLVED".equals(c.getCaseStatus())?"badge-success":"badge-warning"%>"><%=c.getCaseStatus()%></span></td>
<td><%=c.getAssignedTo()%></td>
<td><%=c.getOpenedAt()!=null?c.getOpenedAt():""%></td>
<td><%=c.getClosedAt()!=null?c.getClosedAt():""%></td>
<td><a href="${pageContext.request.contextPath}/AlertServlet?action=caseDetail&id=<%=c.getCaseId()%>" class="btn btn-primary btn-sm">View</a></td></tr>
<% } %></table></div></body></html>
