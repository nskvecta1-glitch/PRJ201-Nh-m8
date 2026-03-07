package service;
import DAO.DeliveryOrderDAO;
import DAO.OrderItemDAO;
import DTO.devlivery.DeliveryOrderDTO;
import DTO.devlivery.OrderItemDTO;
import java.util.List;
public class DeliveryOrderService {
    private final DeliveryOrderDAO dao = new DeliveryOrderDAO();
    private final OrderItemDAO itemDAO = new OrderItemDAO();
    public List<DeliveryOrderDTO> getAll() throws Exception { return dao.getAll(); }
    public DeliveryOrderDTO getById(int id) throws Exception { return dao.getById(id); }
    public List<DeliveryOrderDTO> search(String status, String from, String to, Integer customerId, Integer warehouseId) throws Exception {
        return dao.search(status, from, to, customerId, warehouseId);
    }
    public void save(DeliveryOrderDTO o) throws Exception {
        if (o.getOrderId() == 0) dao.insert(o); else dao.update(o);
    }
    public void delete(int id) throws Exception { dao.delete(id); }
    public List<OrderItemDTO> getItems(int orderId) throws Exception { return itemDAO.getByOrderId(orderId); }
    public void saveItem(OrderItemDTO item) throws Exception { itemDAO.insert(item); }
    public void deleteItems(int orderId) throws Exception { itemDAO.deleteByOrderId(orderId); }
}
