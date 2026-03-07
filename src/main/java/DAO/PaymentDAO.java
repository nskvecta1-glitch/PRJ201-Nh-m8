package DAO;

import DTO.accounting.PaymentDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public List<PaymentDTO> getAll() throws Exception {
        List<PaymentDTO> list = new ArrayList<>();
        String sql = "SELECT payment_id, invoice_id, payment_date, amount, payment_method, status FROM Payments";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<PaymentDTO> getByInvoiceId(int invoiceId) throws Exception {
        List<PaymentDTO> list = new ArrayList<>();
        String sql = "SELECT payment_id, invoice_id, payment_date, amount, payment_method, status FROM Payments WHERE invoice_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(PaymentDTO p) throws Exception {
        String sql = "INSERT INTO Payments(invoice_id, amount, payment_method, status) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getInvoiceId());
            ps.setBigDecimal(2, p.getAmount());
            ps.setString(3, p.getPaymentMethod());
            ps.setString(4, p.getStatus());
            ps.executeUpdate();
        }
    }

    public void update(PaymentDTO p) throws Exception {
        String sql = "UPDATE Payments SET invoice_id=?, amount=?, payment_method=?, status=? WHERE payment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getInvoiceId());
            ps.setBigDecimal(2, p.getAmount());
            ps.setString(3, p.getPaymentMethod());
            ps.setString(4, p.getStatus());
            ps.setInt(5, p.getPaymentId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Payments WHERE payment_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private PaymentDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("payment_date");
        LocalDateTime paymentDate = ts != null ? ts.toLocalDateTime() : null;
        PaymentDTO p = new PaymentDTO();
        p.setPaymentId(rs.getInt("payment_id"));
        p.setInvoiceId(rs.getInt("invoice_id"));
        p.setPaymentDate(paymentDate);
        p.setAmount(rs.getBigDecimal("amount"));
        p.setPaymentMethod(rs.getString("payment_method"));
        p.setStatus(rs.getString("status"));
        return p;
    }
}
