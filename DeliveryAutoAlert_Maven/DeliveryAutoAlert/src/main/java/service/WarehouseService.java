package service;
import DAO.WarehouseDAO;
import DTO.master.WarehouseDTO;
import java.util.List;
public class WarehouseService {
    private final WarehouseDAO dao = new WarehouseDAO();
    public List<WarehouseDTO> getAll() throws Exception { return dao.getAll(); }
    public WarehouseDTO getById(int id) throws Exception { return dao.getById(id); }
    public void save(WarehouseDTO w) throws Exception {
        if (w.getWarehouseId() == 0) dao.insert(w); else dao.update(w);
    }
    public void delete(int id) throws Exception { dao.delete(id); }
}
