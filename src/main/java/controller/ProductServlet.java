package controller;
import DTO.master.ProductDTO;
import service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private final ProductService service = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("product", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/catalog/productForm.jsp").forward(req, resp); break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/ProductServlet"); break;
                case "new":
                    req.getRequestDispatcher("/catalog/productForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("products", service.getAll());
                    req.getRequestDispatcher("/catalog/productList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("productId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        BigDecimal price = req.getParameter("price") != null && !req.getParameter("price").isEmpty()
            ? new BigDecimal(req.getParameter("price")) : BigDecimal.ZERO;
        ProductDTO p = new ProductDTO(id, req.getParameter("sku"), req.getParameter("productName"), price, LocalDateTime.now());
        try { service.save(p); resp.sendRedirect(req.getContextPath() + "/ProductServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
