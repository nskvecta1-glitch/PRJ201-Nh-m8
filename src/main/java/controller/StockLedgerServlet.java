package controller;
import service.WarehouseDocService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/StockLedgerServlet")
public class StockLedgerServlet extends HttpServlet {
    private final WarehouseDocService service = new WarehouseDocService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("ledger", service.getLedger());
            req.getRequestDispatcher("/warehouse/stockLedger.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
