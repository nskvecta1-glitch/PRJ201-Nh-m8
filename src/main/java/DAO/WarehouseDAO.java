package DAO;

import DTO.master.WarehouseDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {

    public List<WarehouseDTO> getAll() throws Exception {
        List<WarehouseDTO> list = new ArrayList<>();
        String sql = "SELECT warehouse_id, warehouse_name, location, created_at FROM Warehouses";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public WarehouseDTO getById(int id) throws Exception {
        String sql = "SELECT warehouse_id, warehouse_name, location, created_at FROM Warehouses WHERE warehouse_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(WarehouseDTO w) throws Exception {
        String sql = "INSERT INTO Warehouses(warehouse_name, location) VALUES(?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, w.getWarehouseName());
            ps.setString(2, w.getLocation());
            ps.executeUpdate();
        }
    }

    public void update(WarehouseDTO w) throws Exception {
        String sql = "UPDATE Warehouses SET warehouse_name=?, location=? WHERE warehouse_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, w.getWarehouseName());
            ps.setString(2, w.getLocation());
            ps.setInt(3, w.getWarehouseId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Warehouses WHERE warehouse_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private WarehouseDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new WarehouseDTO(
            rs.getInt("warehouse_id"),
            rs.getString("warehouse_name"),
            rs.getString("location"),
            createdAt
        );
    }
}
