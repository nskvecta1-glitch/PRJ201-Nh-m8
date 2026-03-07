package controller;

import DTO.master.CustomerDTO;
import service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("customer", service.getById(id));
                    req.getRequestDispatcher("/catalog/customerForm.jsp").forward(req, resp);
                    break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/CustomerServlet");
                    break;
                case "new":
                    req.getRequestDispatcher("/catalog/customerForm.jsp").forward(req, resp);
                    break;
                default:
                    req.setAttribute("customers", service.getAll());
                    req.getRequestDispatcher("/catalog/customerList.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("customerId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        CustomerDTO c = new CustomerDTO(id,
            req.getParameter("customerName"),
            req.getParameter("phone"),
            req.getParameter("address"),
            req.getParameter("email"),
            LocalDateTime.now());
        try {
            service.save(c);
            resp.sendRedirect(req.getContextPath() + "/CustomerServlet");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
