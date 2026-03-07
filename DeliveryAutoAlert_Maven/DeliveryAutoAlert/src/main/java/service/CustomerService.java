package service;
import DAO.CustomerDAO;
import DTO.master.CustomerDTO;
import java.util.List;
public class CustomerService {
    private final CustomerDAO dao = new CustomerDAO();
    public List<CustomerDTO> getAll() throws Exception { return dao.getAll(); }
    public CustomerDTO getById(int id) throws Exception { return dao.getById(id); }
    public void save(CustomerDTO c) throws Exception {
        if (c.getCustomerID() == 0) dao.insert(c); else dao.update(c);
    }
    public void delete(int id) throws Exception { dao.delete(id); }
}
