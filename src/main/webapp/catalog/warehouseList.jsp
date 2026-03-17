<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Warehouses</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<%@ include file="/nav.jsp" %>
<div class="container">

  <div class="page-header">
    <h2>Warehouses</h2>
    <p>Manage warehouse locations. Each warehouse has its own product catalog.</p>
  </div>

  <div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/WarehouseServlet?action=new" class="btn btn-success">+ New Warehouse</a>
  </div>

  <div class="table-wrap">
    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Location</th>
          <th>Products</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
      <%
        List<WarehouseDTO> warehouses = (List<WarehouseDTO>) request.getAttribute("warehouses");
        if (warehouses != null && !warehouses.isEmpty()) {
          for (WarehouseDTO w : warehouses) {
      %>
        <tr>
          <td><%=w.getWarehouseId()%></td>
          <td><strong><%=w.getWarehouseName()%></strong></td>
          <td><%=w.getLocation() != null ? w.getLocation() : "—"%></td>
          <td>
            <a href="${pageContext.request.contextPath}/ProductServlet?warehouseId=<%=w.getWarehouseId()%>"
               class="btn btn-ghost btn-xs">&#128230; View Products</a>
          </td>
          <td>
            <a href="${pageContext.request.contextPath}/WarehouseServlet?action=edit&id=<%=w.getWarehouseId()%>" class="btn btn-warning btn-xs">Edit</a>
            <a href="${pageContext.request.contextPath}/WarehouseServlet?action=delete&id=<%=w.getWarehouseId()%>" class="btn btn-danger btn-xs" onclick="return confirm('Delete this warehouse?')">Delete</a>
          </td>
        </tr>
      <% } } else { %>
        <tr><td colspan="5" style="text-align:center;padding:40px;color:#9ca3af;">No warehouses found.</td></tr>
      <% } %>
      </tbody>
    </table>
  </div>

</div>
</body>
</html>
