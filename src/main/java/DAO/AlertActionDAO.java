package DAO;

import DTO.alert.AlertActionDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertActionDAO {

    public List<AlertActionDTO> getByCaseId(int caseId) throws Exception {
        List<AlertActionDTO> list = new ArrayList<>();
        String sql = "SELECT action_id, case_id, action_by, action_note, action_time FROM AlertActions WHERE case_id=? ORDER BY action_time";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, caseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(AlertActionDTO a) throws Exception {
        String sql = "INSERT INTO AlertActions(case_id, action_by, action_note) VALUES(?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getCaseId());
            ps.setInt(2, a.getActionBy());
            ps.setString(3, a.getActionNote());
            ps.executeUpdate();
        }
    }

    private AlertActionDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("action_time");
        LocalDateTime actionTime = ts != null ? ts.toLocalDateTime() : null;
        return new AlertActionDTO(
            rs.getInt("action_id"),
            rs.getInt("case_id"),
            rs.getInt("action_by"),
            rs.getString("action_note"),
            actionTime
        );
    }
}
