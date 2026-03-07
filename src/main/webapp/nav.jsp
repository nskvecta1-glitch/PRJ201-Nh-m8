<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
  <div class="nav-group">
    <strong style="margin-right:20px;font-size:15px;">DeliveryAutoAlert</strong>
    <a href="${pageContext.request.contextPath}/CustomerServlet">Customers</a>
    <a href="${pageContext.request.contextPath}/ProductServlet">Products</a>
    <a href="${pageContext.request.contextPath}/WarehouseServlet">Warehouses</a>
    <a href="${pageContext.request.contextPath}/OrderServlet">Orders</a>
    <a href="${pageContext.request.contextPath}/ShipmentServlet">Shipments</a>
    <a href="${pageContext.request.contextPath}/OutboundServlet">Outbound</a>
    <a href="${pageContext.request.contextPath}/InboundServlet">Inbound</a>
    <a href="${pageContext.request.contextPath}/StockLedgerServlet">Stock</a>
    <a href="${pageContext.request.contextPath}/InvoiceServlet">Invoices</a>
    <a href="${pageContext.request.contextPath}/PaymentServlet">Payments</a>
    <a href="${pageContext.request.contextPath}/AlertServlet">Alerts</a>
    <a href="${pageContext.request.contextPath}/AlertServlet?action=cases">Cases</a>
    <a href="${pageContext.request.contextPath}/UserServlet">Users</a>
  </div>
  <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
</nav>
