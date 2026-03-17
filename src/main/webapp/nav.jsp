<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
  <div style="display:flex;align-items:center;flex:1;min-width:0;">
    <span class="nav-brand">
      <span class="nav-brand-icon">&#128230;</span>
      DeliveryAutoAlert
    </span>
    <div class="nav-group">
      <a href="${pageContext.request.contextPath}/CustomerServlet">Customers</a>
      <a href="${pageContext.request.contextPath}/WarehouseServlet">Warehouses</a>
      <a href="${pageContext.request.contextPath}/OrderServlet">Orders</a>
      <a href="${pageContext.request.contextPath}/ShipmentServlet">Shipments</a>
      <a href="${pageContext.request.contextPath}/OutboundServlet?action=stockDocs&tab=outbound">Stock Docs</a>
      <a href="${pageContext.request.contextPath}/StockLedgerServlet">Ledger</a>
      <a href="${pageContext.request.contextPath}/InvoiceServlet">Invoices</a>
      <a href="${pageContext.request.contextPath}/PaymentServlet">Payments</a>
      <a href="${pageContext.request.contextPath}/AlertServlet">Alerts</a>
      <a href="${pageContext.request.contextPath}/AlertServlet?action=cases">Cases</a>
      <a href="${pageContext.request.contextPath}/UserServlet">Users</a>
    </div>
  </div>
  <div class="nav-right">
    <a href="${pageContext.request.contextPath}/LogoutServlet" class="nav-logout">Sign out</a>
  </div>
</nav>
