package controller;
import DTO.warehouse.OutboundDocDTO;
import service.WarehouseDocService;
import service.DeliveryOrderService;
import service.WarehouseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/OutboundServlet")
public class OutboundServlet extends HttpServlet {
    private final WarehouseDocService service = new WarehouseDocService();
    private final DeliveryOrderService orderService = new DeliveryOrderService();
    private final WarehouseService whService = new WarehouseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("doc", service.getOutboundById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("orders", orderService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/warehouse/outboundForm.jsp").forward(req, resp); break;
                case "delete":
                    service.deleteOutbound(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/OutboundServlet"); break;
                case "new":
                    req.setAttribute("orders", orderService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/warehouse/outboundForm.jsp").forward(req, resp); break;
                case "search":
                    req.setAttribute("docs", service.searchOutbound(req.getParameter("from"), req.getParameter("to"),
                        req.getParameter("warehouseId") != null && !req.getParameter("warehouseId").isEmpty() ? Integer.parseInt(req.getParameter("warehouseId")) : null,
                        req.getParameter("status")));
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/search/searchStockDocs.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("docs", service.getAllOutbound());
                    req.setAttribute("inboundDocs", service.getAllInbound());
                    req.setAttribute("tab", "outbound");
                    req.getRequestDispatcher("/warehouse/stockDocs.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("outboundId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        OutboundDocDTO o = new OutboundDocDTO(id, Integer.parseInt(req.getParameter("refOrderId")),
            Integer.parseInt(req.getParameter("warehouseId")), LocalDateTime.now(), req.getParameter("status"));
        try { service.saveOutbound(o); resp.sendRedirect(req.getContextPath() + "/OutboundServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
