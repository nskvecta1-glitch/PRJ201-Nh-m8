package DAO;

import DTO.alert.ReconciliationCaseDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReconciliationCaseDAO {

    public List<ReconciliationCaseDTO> getAll() throws Exception {
        List<ReconciliationCaseDTO> list = new ArrayList<>();
        String sql = "SELECT case_id, alert_id, case_status, assigned_to, opened_at, closed_at, resolution_note FROM ReconciliationCases ORDER BY opened_at DESC";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public ReconciliationCaseDTO getById(int id) throws Exception {
        String sql = "SELECT case_id, alert_id, case_status, assigned_to, opened_at, closed_at, resolution_note FROM ReconciliationCases WHERE case_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(ReconciliationCaseDTO c) throws Exception {
        String sql = "INSERT INTO ReconciliationCases(alert_id, case_status, assigned_to, resolution_note) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getAlertId());
            ps.setString(2, c.getCaseStatus());
            ps.setInt(3, c.getAssignedTo());
            ps.setString(4, c.getResolutionNote());
            ps.executeUpdate();
        }
    }

    public void update(ReconciliationCaseDTO c) throws Exception {
        String sql = "UPDATE ReconciliationCases SET case_status=?, assigned_to=?, closed_at=?, resolution_note=? WHERE case_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCaseStatus());
            ps.setInt(2, c.getAssignedTo());
            ps.setTimestamp(3, c.getClosedAt() != null ? Timestamp.valueOf(c.getClosedAt()) : null);
            ps.setString(4, c.getResolutionNote());
            ps.setInt(5, c.getCaseId());
            ps.executeUpdate();
        }
    }

    private ReconciliationCaseDTO map(ResultSet rs) throws SQLException {
        Timestamp tsOpen = rs.getTimestamp("opened_at");
        Timestamp tsClose = rs.getTimestamp("closed_at");
        return new ReconciliationCaseDTO(
            rs.getInt("case_id"),
            rs.getInt("alert_id"),
            rs.getString("case_status"),
            rs.getInt("assigned_to"),
            tsOpen != null ? tsOpen.toLocalDateTime() : null,
            tsClose != null ? tsClose.toLocalDateTime() : null,
            rs.getString("resolution_note")
        );
    }
}
