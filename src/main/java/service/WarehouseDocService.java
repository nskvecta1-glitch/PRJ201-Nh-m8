package service;
import DAO.OutboundDocDAO;
import DAO.InboundDocDAO;
import DAO.StockLedgerDAO;
import DTO.warehouse.OutboundDocDTO;
import DTO.warehouse.InboundDocDTO;
import DTO.warehouse.StockLedgerDTO;
import java.util.List;
public class WarehouseDocService {
    private final OutboundDocDAO outDAO = new OutboundDocDAO();
    private final InboundDocDAO inDAO = new InboundDocDAO();
    private final StockLedgerDAO ledgerDAO = new StockLedgerDAO();
    public List<OutboundDocDTO> getAllOutbound() throws Exception { return outDAO.getAll(); }
    public OutboundDocDTO getOutboundById(int id) throws Exception { return outDAO.getById(id); }
    public List<OutboundDocDTO> searchOutbound(String from, String to, Integer warehouseId, String status) throws Exception {
        return outDAO.search(from, to, warehouseId, status);
    }
    public void saveOutbound(OutboundDocDTO o) throws Exception {
        if (o.getOutboundId() == 0) outDAO.insert(o); else outDAO.update(o);
    }
    public void deleteOutbound(int id) throws Exception { outDAO.delete(id); }
    public List<InboundDocDTO> getAllInbound() throws Exception { return inDAO.getAll(); }
    public InboundDocDTO getInboundById(int id) throws Exception { return inDAO.getById(id); }
    public void saveInbound(InboundDocDTO i) throws Exception {
        if (i.getInboundId() == 0) inDAO.insert(i); else inDAO.update(i);
    }
    public void deleteInbound(int id) throws Exception { inDAO.delete(id); }
    public List<StockLedgerDTO> getLedger() throws Exception { return ledgerDAO.getAll(); }
    public void addLedgerEntry(StockLedgerDTO s) throws Exception { ledgerDAO.insert(s); }
}
