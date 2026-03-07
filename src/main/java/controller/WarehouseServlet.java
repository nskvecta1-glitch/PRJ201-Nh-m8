package controller;
import DTO.master.WarehouseDTO;
import service.WarehouseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/WarehouseServlet")
public class WarehouseServlet extends HttpServlet {
    private final WarehouseService service = new WarehouseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("warehouse", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/catalog/warehouseForm.jsp").forward(req, resp); break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/WarehouseServlet"); break;
                case "new":
                    req.getRequestDispatcher("/catalog/warehouseForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("warehouses", service.getAll());
                    req.getRequestDispatcher("/catalog/warehouseList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("warehouseId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        WarehouseDTO w = new WarehouseDTO(id, req.getParameter("warehouseName"), req.getParameter("location"), LocalDateTime.now());
        try { service.save(w); resp.sendRedirect(req.getContextPath() + "/WarehouseServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
