package DAO;

import DTO.warehouse.InboundDocDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InboundDocDAO {

    public List<InboundDocDTO> getAll() throws Exception {
        List<InboundDocDTO> list = new ArrayList<>();
        String sql = "SELECT inbound_id, ref_order_id, warehouse_id, inbound_date, reason FROM InboundDocs";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public InboundDocDTO getById(int id) throws Exception {
        String sql = "SELECT inbound_id, ref_order_id, warehouse_id, inbound_date, reason FROM InboundDocs WHERE inbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(InboundDocDTO i) throws Exception {
        String sql = "INSERT INTO InboundDocs(ref_order_id, warehouse_id, reason) VALUES(?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, i.getRefOrderId());
            ps.setInt(2, i.getWarehouseId());
            ps.setString(3, i.getReason());
            ps.executeUpdate();
        }
    }

    public void update(InboundDocDTO i) throws Exception {
        String sql = "UPDATE InboundDocs SET ref_order_id=?, warehouse_id=?, reason=? WHERE inbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, i.getRefOrderId());
            ps.setInt(2, i.getWarehouseId());
            ps.setString(3, i.getReason());
            ps.setInt(4, i.getInboundId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM InboundDocs WHERE inbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private InboundDocDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("inbound_date");
        LocalDateTime inboundDate = ts != null ? ts.toLocalDateTime() : null;
        return new InboundDocDTO(
            rs.getInt("inbound_id"),
            rs.getInt("ref_order_id"),
            rs.getInt("warehouse_id"),
            inboundDate,
            rs.getString("reason")
        );
    }
}
