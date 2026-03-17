<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.warehouse.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Stock Documents — DeliveryAutoAlert</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<%@ include file="/nav.jsp" %>
<div class="container">

  <div class="page-header">
    <h2>Stock Documents</h2>
    <p>Manage inbound and outbound warehouse documents in one place.</p>
  </div>

  <%
    String tab = request.getParameter("tab");
    if (tab == null || tab.isEmpty()) tab = "outbound";
    boolean isOutbound = "outbound".equals(tab);
  %>

  <!-- Tab bar -->
  <div class="tab-bar">
    <a href="${pageContext.request.contextPath}/OutboundServlet?action=stockDocs&tab=outbound"
       class="tab-btn <%= isOutbound ? "active" : "" %>">
      &#8599; Outbound
    </a>
    <a href="${pageContext.request.contextPath}/InboundServlet?action=stockDocs&tab=inbound"
       class="tab-btn <%= !isOutbound ? "active" : "" %>">
      &#8600; Inbound
    </a>
  </div>

  <% if (isOutbound) { %>

    <!-- OUTBOUND TAB -->
    <div class="toolbar">
      <span style="color:var(--text-secondary);font-size:13px;">
        Showing outbound dispatch records
      </span>
      <div class="toolbar-actions">
        <a href="${pageContext.request.contextPath}/OutboundServlet?action=search" class="btn btn-secondary btn-sm">&#128269; Search</a>
        <a href="${pageContext.request.contextPath}/OutboundServlet?action=new" class="btn btn-primary">+ New Outbound</a>
      </div>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Type</th>
            <th>Order ID</th>
            <th>Warehouse ID</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
        <%
          List<OutboundDocDTO> outDocs = (List<OutboundDocDTO>) request.getAttribute("docs");
          if (outDocs != null && !outDocs.isEmpty()) {
            for (OutboundDocDTO d : outDocs) {
        %>
          <tr>
            <td><%=d.getOutboundId()%></td>
            <td><span class="type-out">&#8599; OUT</span></td>
            <td><%=d.getRefOrderId()%></td>
            <td><%=d.getWarehouseId()%></td>
            <td><%=d.getOutboundDate() != null ? d.getOutboundDate() : "—"%></td>
            <td>
              <%
                String st = d.getStatus() != null ? d.getStatus() : "";
                String badge = "CONFIRMED".equals(st) ? "badge-success"
                             : "CANCELLED".equals(st) ? "badge-danger"
                             : "badge-neutral";
              %>
              <span class="badge <%=badge%>"><%=st%></span>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/OutboundServlet?action=edit&id=<%=d.getOutboundId()%>" class="btn btn-warning btn-xs">Edit</a>
              <a href="${pageContext.request.contextPath}/OutboundServlet?action=delete&id=<%=d.getOutboundId()%>" class="btn btn-danger btn-xs" onclick="return confirm('Delete this outbound doc?')">Delete</a>
            </td>
          </tr>
        <% } } else { %>
          <tr>
            <td colspan="7">
              <div class="empty-state">
                <div class="empty-state-icon">&#128230;</div>
                <p>No outbound documents found.</p>
              </div>
            </td>
          </tr>
        <% } %>
        </tbody>
      </table>
    </div>

  <% } else { %>

    <!-- INBOUND TAB -->
    <div class="toolbar">
      <span style="color:var(--text-secondary);font-size:13px;">
        Showing inbound return &amp; receipt records
      </span>
      <div class="toolbar-actions">
        <a href="${pageContext.request.contextPath}/InboundServlet?action=new" class="btn btn-primary">+ New Inbound</a>
      </div>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Type</th>
            <th>Order ID</th>
            <th>Warehouse ID</th>
            <th>Date</th>
            <th>Reason</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
        <%
          List<InboundDocDTO> inDocs = (List<InboundDocDTO>) request.getAttribute("docs");
          if (inDocs != null && !inDocs.isEmpty()) {
            for (InboundDocDTO d : inDocs) {
        %>
          <tr>
            <td><%=d.getInboundId()%></td>
            <td><span class="type-in">&#8600; IN</span></td>
            <td><%=d.getRefOrderId()%></td>
            <td><%=d.getWarehouseId()%></td>
            <td><%=d.getInboundDate() != null ? d.getInboundDate() : "—"%></td>
            <td><%=d.getReason() != null ? d.getReason() : "—"%></td>
            <td>
              <a href="${pageContext.request.contextPath}/InboundServlet?action=edit&id=<%=d.getInboundId()%>" class="btn btn-warning btn-xs">Edit</a>
              <a href="${pageContext.request.contextPath}/InboundServlet?action=delete&id=<%=d.getInboundId()%>" class="btn btn-danger btn-xs" onclick="return confirm('Delete this inbound doc?')">Delete</a>
            </td>
          </tr>
        <% } } else { %>
          <tr>
            <td colspan="7">
              <div class="empty-state">
                <div class="empty-state-icon">&#128229;</div>
                <p>No inbound documents found.</p>
              </div>
            </td>
          </tr>
        <% } %>
        </tbody>
      </table>
    </div>

  <% } %>

</div>
</body>
</html>
