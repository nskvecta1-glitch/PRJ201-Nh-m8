package controller;
import DTO.accounting.PaymentDTO;
import service.AccountingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private final AccountingService service = new AccountingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "delete":
                    service.deletePayment(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/PaymentServlet"); break;
                case "new":
                    req.setAttribute("invoices", service.getAllInvoices());
                    req.getRequestDispatcher("/accounting/paymentForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("payments", service.getAllPayments());
                    req.getRequestDispatcher("/accounting/paymentList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal amount = req.getParameter("amount") != null && !req.getParameter("amount").isEmpty() ? new BigDecimal(req.getParameter("amount")) : BigDecimal.ZERO;
        PaymentDTO p = new PaymentDTO();
        p.setInvoiceId(Integer.parseInt(req.getParameter("invoiceId")));
        p.setAmount(amount);
        p.setPaymentMethod(req.getParameter("paymentMethod"));
        p.setStatus(req.getParameter("status"));
        try { service.savePayment(p); resp.sendRedirect(req.getContextPath() + "/PaymentServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
