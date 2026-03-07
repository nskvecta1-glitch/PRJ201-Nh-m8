package DAO;

import DTO.devlivery.ProofOfDeliveryDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProofOfDeliveryDAO {

    public ProofOfDeliveryDTO getByShipmentId(int shipmentId) throws Exception {
        String sql = "SELECT pod_id, shipment_id, delivered_at, receiver_name, pod_image_url FROM ProofOfDelivery WHERE shipment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, shipmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<ProofOfDeliveryDTO> getAll() throws Exception {
        List<ProofOfDeliveryDTO> list = new ArrayList<>();
        String sql = "SELECT pod_id, shipment_id, delivered_at, receiver_name, pod_image_url FROM ProofOfDelivery";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public void insert(ProofOfDeliveryDTO p) throws Exception {
        String sql = "INSERT INTO ProofOfDelivery(shipment_id, delivered_at, receiver_name, pod_image_url) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getShipmentId());
            ps.setTimestamp(2, p.getDeliveredAt() != null ? Timestamp.valueOf(p.getDeliveredAt()) : null);
            ps.setString(3, p.getReceiverName());
            ps.setString(4, p.getPodImageUrl());
            ps.executeUpdate();
        }
    }

    public void delete(int podId) throws Exception {
        String sql = "DELETE FROM ProofOfDelivery WHERE pod_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, podId);
            ps.executeUpdate();
        }
    }

    private ProofOfDeliveryDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("delivered_at");
        LocalDateTime deliveredAt = ts != null ? ts.toLocalDateTime() : null;
        return new ProofOfDeliveryDTO(
            rs.getInt("pod_id"),
            rs.getInt("shipment_id"),
            deliveredAt,
            rs.getString("receiver_name"),
            rs.getString("pod_image_url")
        );
    }
}
