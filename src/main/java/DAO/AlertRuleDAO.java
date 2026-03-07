package DAO;

import DTO.alert.AlertRuleDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertRuleDAO {

    public List<AlertRuleDTO> getAll() throws Exception {
        List<AlertRuleDTO> list = new ArrayList<>();
        String sql = "SELECT rule_id, rule_name, rule_type, threshold_value, severity FROM AlertRules";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AlertRuleDTO r = new AlertRuleDTO();
                r.setRuleId(rs.getInt("rule_id"));
                r.setRuleName(rs.getString("rule_name"));
                r.setRuleType(rs.getString("rule_type"));
                r.setThresholdValue(rs.getBigDecimal("threshold_value"));
                r.setSeverity(rs.getString("severity"));
                list.add(r);
            }
        }
        return list;
    }

    public void insert(AlertRuleDTO r) throws Exception {
        String sql = "INSERT INTO AlertRules(rule_name, rule_type, threshold_value, severity) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getRuleName());
            ps.setString(2, r.getRuleType());
            ps.setBigDecimal(3, r.getThresholdValue());
            ps.setString(4, r.getSeverity());
            ps.executeUpdate();
        }
    }
}
