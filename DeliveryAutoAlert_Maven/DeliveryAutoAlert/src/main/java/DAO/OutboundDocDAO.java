package DAO;

import DTO.warehouse.OutboundDocDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OutboundDocDAO {

    public List<OutboundDocDTO> getAll() throws Exception {
        List<OutboundDocDTO> list = new ArrayList<>();
        String sql = "SELECT outbound_id, ref_order_id, warehouse_id, outbound_date, status FROM OutboundDocs";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public OutboundDocDTO getById(int id) throws Exception {
        String sql = "SELECT outbound_id, ref_order_id, warehouse_id, outbound_date, status FROM OutboundDocs WHERE outbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<OutboundDocDTO> search(String fromDate, String toDate, Integer warehouseId, String status) throws Exception {
        List<OutboundDocDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT outbound_id, ref_order_id, warehouse_id, outbound_date, status FROM OutboundDocs WHERE 1=1");
        if (fromDate != null && !fromDate.isEmpty()) sql.append(" AND outbound_date >= ?");
        if (toDate != null && !toDate.isEmpty()) sql.append(" AND outbound_date <= ?");
        if (warehouseId != null) sql.append(" AND warehouse_id=?");
        if (status != null && !status.isEmpty()) sql.append(" AND status=?");

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int i = 1;
            if (fromDate != null && !fromDate.isEmpty()) ps.setString(i++, fromDate);
            if (toDate != null && !toDate.isEmpty()) ps.setString(i++, toDate);
            if (warehouseId != null) ps.setInt(i++, warehouseId);
            if (status != null && !status.isEmpty()) ps.setString(i++, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(OutboundDocDTO o) throws Exception {
        String sql = "INSERT INTO OutboundDocs(ref_order_id, warehouse_id, status) VALUES(?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getRefOrderId());
            ps.setInt(2, o.getWarehouseId());
            ps.setString(3, o.getStatus());
            ps.executeUpdate();
        }
    }

    public void update(OutboundDocDTO o) throws Exception {
        String sql = "UPDATE OutboundDocs SET ref_order_id=?, warehouse_id=?, status=? WHERE outbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getRefOrderId());
            ps.setInt(2, o.getWarehouseId());
            ps.setString(3, o.getStatus());
            ps.setInt(4, o.getOutboundId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM OutboundDocs WHERE outbound_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private OutboundDocDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("outbound_date");
        LocalDateTime outboundDate = ts != null ? ts.toLocalDateTime() : null;
        return new OutboundDocDTO(
            rs.getInt("outbound_id"),
            rs.getInt("ref_order_id"),
            rs.getInt("warehouse_id"),
            outboundDate,
            rs.getString("status")
        );
    }
}
