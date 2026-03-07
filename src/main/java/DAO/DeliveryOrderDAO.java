package DAO;

import DTO.devlivery.DeliveryOrderDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryOrderDAO {

    public List<DeliveryOrderDTO> getAll() throws Exception {
        List<DeliveryOrderDTO> list = new ArrayList<>();
        String sql = "SELECT order_id, order_code, customer_id, warehouse_id, order_date, status, has_cod, cod_amount, total_amount, created_at FROM DeliveryOrders";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public DeliveryOrderDTO getById(int id) throws Exception {
        String sql = "SELECT order_id, order_code, customer_id, warehouse_id, order_date, status, has_cod, cod_amount, total_amount, created_at FROM DeliveryOrders WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<DeliveryOrderDTO> search(String status, String fromDate, String toDate, Integer customerId, Integer warehouseId) throws Exception {
        List<DeliveryOrderDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT order_id, order_code, customer_id, warehouse_id, order_date, status, has_cod, cod_amount, total_amount, created_at FROM DeliveryOrders WHERE 1=1");
        if (status != null && !status.isEmpty()) sql.append(" AND status=?");
        if (fromDate != null && !fromDate.isEmpty()) sql.append(" AND order_date >= ?");
        if (toDate != null && !toDate.isEmpty()) sql.append(" AND order_date <= ?");
        if (customerId != null) sql.append(" AND customer_id=?");
        if (warehouseId != null) sql.append(" AND warehouse_id=?");

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int i = 1;
            if (status != null && !status.isEmpty()) ps.setString(i++, status);
            if (fromDate != null && !fromDate.isEmpty()) ps.setString(i++, fromDate);
            if (toDate != null && !toDate.isEmpty()) ps.setString(i++, toDate);
            if (customerId != null) ps.setInt(i++, customerId);
            if (warehouseId != null) ps.setInt(i++, warehouseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(DeliveryOrderDTO o) throws Exception {
        String sql = "INSERT INTO DeliveryOrders(order_code, customer_id, warehouse_id, status, has_cod, cod_amount, total_amount) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getOrderCode());
            ps.setInt(2, o.getCustomerId());
            ps.setInt(3, o.getWarehouseId());
            ps.setString(4, o.getStatus());
            ps.setBoolean(5, o.isHasCod());
            ps.setBigDecimal(6, o.getCodAmount());
            ps.setBigDecimal(7, o.getTotalAmount());
            ps.executeUpdate();
        }
    }

    public void update(DeliveryOrderDTO o) throws Exception {
        String sql = "UPDATE DeliveryOrders SET order_code=?, customer_id=?, warehouse_id=?, status=?, has_cod=?, cod_amount=?, total_amount=? WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getOrderCode());
            ps.setInt(2, o.getCustomerId());
            ps.setInt(3, o.getWarehouseId());
            ps.setString(4, o.getStatus());
            ps.setBoolean(5, o.isHasCod());
            ps.setBigDecimal(6, o.getCodAmount());
            ps.setBigDecimal(7, o.getTotalAmount());
            ps.setInt(8, o.getOrderId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM DeliveryOrders WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private DeliveryOrderDTO map(ResultSet rs) throws SQLException {
        Timestamp tsOrder = rs.getTimestamp("order_date");
        Timestamp tsCreated = rs.getTimestamp("created_at");
        return new DeliveryOrderDTO(
            rs.getInt("order_id"),
            rs.getString("order_code"),
            rs.getInt("customer_id"),
            rs.getInt("warehouse_id"),
            tsOrder != null ? tsOrder.toLocalDateTime() : null,
            rs.getString("status"),
            rs.getBoolean("has_cod"),
            rs.getBigDecimal("cod_amount"),
            rs.getBigDecimal("total_amount"),
            tsCreated != null ? tsCreated.toLocalDateTime() : null
        );
    }
}
