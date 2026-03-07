package controller;
import DTO.accounting.InvoiceDTO;
import service.AccountingService;
import service.DeliveryOrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    private final AccountingService service = new AccountingService();
    private final DeliveryOrderService orderService = new DeliveryOrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "edit":
                    req.setAttribute("invoice", service.getInvoiceById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("orders", orderService.getAll());
                    req.getRequestDispatcher("/accounting/invoiceForm.jsp").forward(req, resp); break;
                case "detail":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("invoice", service.getInvoiceById(id));
                    req.setAttribute("payments", service.getPaymentsByInvoice(id));
                    req.getRequestDispatcher("/accounting/invoiceDetail.jsp").forward(req, resp); break;
                case "delete":
                    service.deleteInvoice(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/InvoiceServlet"); break;
                case "new":
                    req.setAttribute("orders", orderService.getAll());
                    req.getRequestDispatcher("/accounting/invoiceForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("invoices", service.getAllInvoices());
                    req.getRequestDispatcher("/accounting/invoiceList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("invoiceId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        BigDecimal total = req.getParameter("totalAmount") != null && !req.getParameter("totalAmount").isEmpty() ? new BigDecimal(req.getParameter("totalAmount")) : BigDecimal.ZERO;
        InvoiceDTO inv = new InvoiceDTO(id, Integer.parseInt(req.getParameter("orderId")), LocalDateTime.now(), total, req.getParameter("status"));
        try { service.saveInvoice(inv); resp.sendRedirect(req.getContextPath() + "/InvoiceServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
