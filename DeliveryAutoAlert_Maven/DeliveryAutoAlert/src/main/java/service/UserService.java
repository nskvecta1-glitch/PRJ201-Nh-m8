package service;
import DAO.UserDAO;
import DAO.RoleDAO;
import DTO.master.UserDTO;
import DTO.master.RoleDTO;
import java.util.List;
public class UserService {
    private final UserDAO dao = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();
    public UserDTO login(String username, String password) throws Exception {
        String hash = hashPassword(password);
        return dao.login(username, hash);
    }
    public List<UserDTO> getAll() throws Exception { return dao.getAll(); }
    public UserDTO getById(int id) throws Exception { return dao.getById(id); }
    public List<RoleDTO> getAllRoles() throws Exception { return roleDAO.getAll(); }
    public void save(UserDTO u) throws Exception {
        if (u.getUserID() == 0) {
            u.setPasswordhash(hashPassword(u.getPasswordhash()));
            dao.insert(u);
        } else { dao.update(u); }
    }
    public void delete(int id) throws Exception { dao.delete(id); }
    public static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) { return password; }
    }
}
