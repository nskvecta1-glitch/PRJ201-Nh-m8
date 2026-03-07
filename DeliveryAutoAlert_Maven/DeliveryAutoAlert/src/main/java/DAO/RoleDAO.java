package DAO;

import DTO.master.RoleDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    public List<RoleDTO> getAll() throws Exception {
        List<RoleDTO> list = new ArrayList<>();
        String sql = "SELECT role_id, role_name, description FROM Roles";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("description")));
            }
        }
        return list;
    }

    public RoleDTO getById(int id) throws Exception {
        String sql = "SELECT role_id, role_name, description FROM Roles WHERE role_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new RoleDTO(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("description"));
            }
        }
        return null;
    }
}
