package DAO;

import DTO.alert.AlertEventDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertEventDAO {

    public List<AlertEventDTO> getAll() throws Exception {
        List<AlertEventDTO> list = new ArrayList<>();
        String sql = "SELECT alert_id, rule_id, ref_type, ref_id, risk_score, created_at, status FROM AlertEvents ORDER BY created_at DESC";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public AlertEventDTO getById(int id) throws Exception {
        String sql = "SELECT alert_id, rule_id, ref_type, ref_id, risk_score, created_at, status FROM AlertEvents WHERE alert_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<AlertEventDTO> getByStatus(String status) throws Exception {
        List<AlertEventDTO> list = new ArrayList<>();
        String sql = "SELECT alert_id, rule_id, ref_type, ref_id, risk_score, created_at, status FROM AlertEvents WHERE status=? ORDER BY created_at DESC";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(AlertEventDTO a) throws Exception {
        String sql = "INSERT INTO AlertEvents(rule_id, ref_type, ref_id, risk_score, status) VALUES(?,?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getRuleId());
            ps.setString(2, a.getRefType());
            ps.setInt(3, a.getRefId());
            ps.setDouble(4, a.getRiskScore());
            ps.setString(5, a.getStatus());
            ps.executeUpdate();
        }
    }

    public void updateStatus(int alertId, String status) throws Exception {
        String sql = "UPDATE AlertEvents SET status=? WHERE alert_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, alertId);
            ps.executeUpdate();
        }
    }

    private AlertEventDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new AlertEventDTO(
            rs.getInt("alert_id"),
            rs.getInt("rule_id"),
            rs.getString("ref_type"),
            rs.getInt("ref_id"),
            rs.getDouble("risk_score"),
            createdAt,
            rs.getString("status")
        );
    }
}
