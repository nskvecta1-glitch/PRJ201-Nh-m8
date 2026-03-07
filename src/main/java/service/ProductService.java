package service;
import DAO.ProductDAO;
import DTO.master.ProductDTO;
import java.util.List;
public class ProductService {
    private final ProductDAO dao = new ProductDAO();
    public List<ProductDTO> getAll() throws Exception { return dao.getAll(); }
    public ProductDTO getById(int id) throws Exception { return dao.getById(id); }
    public void save(ProductDTO p) throws Exception {
        if (p.getProductId() == 0) dao.insert(p); else dao.update(p);
    }
    public void delete(int id) throws Exception { dao.delete(id); }
}
