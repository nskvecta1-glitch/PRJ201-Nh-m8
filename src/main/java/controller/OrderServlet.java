package controller;
import DTO.devlivery.DeliveryOrderDTO;
import service.CustomerService;
import service.DeliveryOrderService;
import service.WarehouseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private final DeliveryOrderService service = new DeliveryOrderService();
    private final CustomerService custService = new CustomerService();
    private final WarehouseService whService = new WarehouseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("order", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("customers", custService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/delivery/orderForm.jsp").forward(req, resp); break;
                case "detail":
                    req.setAttribute("order", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("items", service.getItems(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/delivery/orderDetail.jsp").forward(req, resp); break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/OrderServlet"); break;
                case "new":
                    req.setAttribute("customers", custService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/delivery/orderForm.jsp").forward(req, resp); break;
                case "search":
                    req.setAttribute("orders", service.search(
                        req.getParameter("status"), req.getParameter("from"),
                        req.getParameter("to"),
                        req.getParameter("customerId") != null && !req.getParameter("customerId").isEmpty() ? Integer.parseInt(req.getParameter("customerId")) : null,
                        req.getParameter("warehouseId") != null && !req.getParameter("warehouseId").isEmpty() ? Integer.parseInt(req.getParameter("warehouseId")) : null));
                    req.setAttribute("customers", custService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/search/searchOrders.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("orders", service.getAll());
                    req.getRequestDispatcher("/delivery/orderList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("orderId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        BigDecimal cod = req.getParameter("codAmount") != null && !req.getParameter("codAmount").isEmpty() ? new BigDecimal(req.getParameter("codAmount")) : BigDecimal.ZERO;
        BigDecimal total = req.getParameter("totalAmount") != null && !req.getParameter("totalAmount").isEmpty() ? new BigDecimal(req.getParameter("totalAmount")) : BigDecimal.ZERO;
        DeliveryOrderDTO o = new DeliveryOrderDTO(id, req.getParameter("orderCode"),
            Integer.parseInt(req.getParameter("customerId")), Integer.parseInt(req.getParameter("warehouseId")),
            LocalDateTime.now(), req.getParameter("status"), "on".equals(req.getParameter("hasCod")), cod, total, LocalDateTime.now());
        try { service.save(o); resp.sendRedirect(req.getContextPath() + "/OrderServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
