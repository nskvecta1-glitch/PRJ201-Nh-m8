package service;
import DAO.InvoiceDAO;
import DAO.PaymentDAO;
import DTO.accounting.InvoiceDTO;
import DTO.accounting.PaymentDTO;
import java.util.List;
public class AccountingService {
    private final InvoiceDAO invDAO = new InvoiceDAO();
    private final PaymentDAO payDAO = new PaymentDAO();
    public List<InvoiceDTO> getAllInvoices() throws Exception { return invDAO.getAll(); }
    public InvoiceDTO getInvoiceById(int id) throws Exception { return invDAO.getById(id); }
    public List<InvoiceDTO> getInvoicesByOrder(int orderId) throws Exception { return invDAO.getByOrderId(orderId); }
    public void saveInvoice(InvoiceDTO i) throws Exception {
        if (i.getInvoiceId() == 0) invDAO.insert(i); else invDAO.update(i);
    }
    public void deleteInvoice(int id) throws Exception { invDAO.delete(id); }
    public List<PaymentDTO> getAllPayments() throws Exception { return payDAO.getAll(); }
    public List<PaymentDTO> getPaymentsByInvoice(int invoiceId) throws Exception { return payDAO.getByInvoiceId(invoiceId); }
    public void savePayment(PaymentDTO p) throws Exception {
        if (p.getPaymentId() == 0) payDAO.insert(p); else payDAO.update(p);
    }
    public void deletePayment(int id) throws Exception { payDAO.delete(id); }
}
