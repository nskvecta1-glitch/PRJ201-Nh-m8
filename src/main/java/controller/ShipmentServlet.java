package controller;
import DTO.devlivery.ShipmentDTO;
import DTO.devlivery.ProofOfDeliveryDTO;
import service.ShipmentService;
import service.DeliveryOrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/ShipmentServlet")
public class ShipmentServlet extends HttpServlet {
    private final ShipmentService service = new ShipmentService();
    private final DeliveryOrderService orderService = new DeliveryOrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("shipment", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("orders", orderService.getAll());
                    req.getRequestDispatcher("/delivery/shipmentForm.jsp").forward(req, resp); break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/ShipmentServlet"); break;
                case "new":
                    req.setAttribute("orders", orderService.getAll());
                    req.getRequestDispatcher("/delivery/shipmentForm.jsp").forward(req, resp); break;
                case "pod":
                    int shipId = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("shipment", service.getById(shipId));
                    req.setAttribute("pod", service.getPOD(shipId));
                    req.getRequestDispatcher("/delivery/podDetail.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("shipments", service.getAll());
                    req.getRequestDispatcher("/delivery/shipmentList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        try {
            if ("pod".equals(type)) {
                ProofOfDeliveryDTO p = new ProofOfDeliveryDTO(0, Integer.parseInt(req.getParameter("shipmentId")),
                    LocalDateTime.now(), req.getParameter("receiverName"), req.getParameter("podImageUrl"));
                service.savePOD(p);
                resp.sendRedirect(req.getContextPath() + "/ShipmentServlet?action=pod&id=" + req.getParameter("shipmentId"));
            } else {
                String idStr = req.getParameter("shipmentId");
                int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
                ShipmentDTO s = new ShipmentDTO(id, Integer.parseInt(req.getParameter("orderId")),
                    LocalDateTime.now(), req.getParameter("deliveryStatus"), req.getParameter("route"));
                service.save(s);
                resp.sendRedirect(req.getContextPath() + "/ShipmentServlet");
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
}
