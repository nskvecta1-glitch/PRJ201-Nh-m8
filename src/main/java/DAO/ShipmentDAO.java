package DAO;

import DTO.devlivery.ShipmentDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDAO {

    public List<ShipmentDTO> getAll() throws Exception {
        List<ShipmentDTO> list = new ArrayList<>();
        String sql = "SELECT shipment_id, order_id, ship_date, delivery_status, route FROM Shipments";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public ShipmentDTO getById(int id) throws Exception {
        String sql = "SELECT shipment_id, order_id, ship_date, delivery_status, route FROM Shipments WHERE shipment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<ShipmentDTO> getByOrderId(int orderId) throws Exception {
        List<ShipmentDTO> list = new ArrayList<>();
        String sql = "SELECT shipment_id, order_id, ship_date, delivery_status, route FROM Shipments WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(ShipmentDTO s) throws Exception {
        String sql = "INSERT INTO Shipments(order_id, ship_date, delivery_status, route) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getOrderId());
            ps.setTimestamp(2, s.getShipDate() != null ? Timestamp.valueOf(s.getShipDate()) : null);
            ps.setString(3, s.getDeliveryStatus());
            ps.setString(4, s.getRoute());
            ps.executeUpdate();
        }
    }

    public void update(ShipmentDTO s) throws Exception {
        String sql = "UPDATE Shipments SET order_id=?, ship_date=?, delivery_status=?, route=? WHERE shipment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getOrderId());
            ps.setTimestamp(2, s.getShipDate() != null ? Timestamp.valueOf(s.getShipDate()) : null);
            ps.setString(3, s.getDeliveryStatus());
            ps.setString(4, s.getRoute());
            ps.setInt(5, s.getShipmentId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Shipments WHERE shipment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private ShipmentDTO map(ResultSet rs) throws SQLException {
        ShipmentDTO s = new ShipmentDTO();
        s.setShipmentId(rs.getInt("shipment_id"));
        s.setOrderId(rs.getInt("order_id"));
        Timestamp ts = rs.getTimestamp("ship_date");
        s.setShipDate(ts != null ? ts.toLocalDateTime() : null);
        s.setDeliveryStatus(rs.getString("delivery_status"));
        s.setRoute(rs.getString("route"));
        return s;
    }
}
