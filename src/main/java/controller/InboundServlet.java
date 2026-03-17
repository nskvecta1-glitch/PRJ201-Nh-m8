package controller;
import DTO.warehouse.InboundDocDTO;
import service.WarehouseDocService;
import service.DeliveryOrderService;
import service.WarehouseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/InboundServlet")
public class InboundServlet extends HttpServlet {
    private final WarehouseDocService service = new WarehouseDocService();
    private final DeliveryOrderService orderService = new DeliveryOrderService();
    private final WarehouseService whService = new WarehouseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("doc", service.getInboundById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("orders", orderService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/warehouse/inboundForm.jsp").forward(req, resp); break;
                case "delete":
                    service.deleteInbound(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/InboundServlet"); break;
                case "new":
                    req.setAttribute("orders", orderService.getAll());
                    req.setAttribute("warehouses", whService.getAll());
                    req.getRequestDispatcher("/warehouse/inboundForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("docs", service.getAllOutbound());
                    req.setAttribute("inboundDocs", service.getAllInbound());
                    req.setAttribute("tab", "inbound");
                    req.getRequestDispatcher("/warehouse/stockDocs.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("inboundId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        InboundDocDTO i = new InboundDocDTO(id, Integer.parseInt(req.getParameter("refOrderId")),
            Integer.parseInt(req.getParameter("warehouseId")), LocalDateTime.now(), req.getParameter("reason"));
        try { service.saveInbound(i); resp.sendRedirect(req.getContextPath() + "/InboundServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
