package service;
import DAO.ShipmentDAO;
import DAO.ProofOfDeliveryDAO;
import DTO.devlivery.ShipmentDTO;
import DTO.devlivery.ProofOfDeliveryDTO;
import java.util.List;
public class ShipmentService {
    private final ShipmentDAO dao = new ShipmentDAO();
    private final ProofOfDeliveryDAO podDAO = new ProofOfDeliveryDAO();
    public List<ShipmentDTO> getAll() throws Exception { return dao.getAll(); }
    public ShipmentDTO getById(int id) throws Exception { return dao.getById(id); }
    public List<ShipmentDTO> getByOrderId(int orderId) throws Exception { return dao.getByOrderId(orderId); }
    public void save(ShipmentDTO s) throws Exception {
        if (s.getShipmentId() == 0) dao.insert(s); else dao.update(s);
    }
    public void delete(int id) throws Exception { dao.delete(id); }
    public ProofOfDeliveryDTO getPOD(int shipmentId) throws Exception { return podDAO.getByShipmentId(shipmentId); }
    public void savePOD(ProofOfDeliveryDTO p) throws Exception { podDAO.insert(p); }
}
