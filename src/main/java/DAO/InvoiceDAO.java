package DAO;

import DTO.accounting.InvoiceDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    public List<InvoiceDTO> getAll() throws Exception {
        List<InvoiceDTO> list = new ArrayList<>();
        String sql = "SELECT invoice_id, order_id, invoice_date, total_amount, status FROM Invoices";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public InvoiceDTO getById(int id) throws Exception {
        String sql = "SELECT invoice_id, order_id, invoice_date, total_amount, status FROM Invoices WHERE invoice_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<InvoiceDTO> getByOrderId(int orderId) throws Exception {
        List<InvoiceDTO> list = new ArrayList<>();
        String sql = "SELECT invoice_id, order_id, invoice_date, total_amount, status FROM Invoices WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(InvoiceDTO inv) throws Exception {
        String sql = "INSERT INTO Invoices(order_id, total_amount, status) VALUES(?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inv.getOrderId());
            ps.setBigDecimal(2, inv.getTotalAmount());
            ps.setString(3, inv.getStatus());
            ps.executeUpdate();
        }
    }

    public void update(InvoiceDTO inv) throws Exception {
        String sql = "UPDATE Invoices SET order_id=?, total_amount=?, status=? WHERE invoice_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inv.getOrderId());
            ps.setBigDecimal(2, inv.getTotalAmount());
            ps.setString(3, inv.getStatus());
            ps.setInt(4, inv.getInvoiceId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Invoices WHERE invoice_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private InvoiceDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("invoice_date");
        LocalDateTime invoiceDate = ts != null ? ts.toLocalDateTime() : null;
        return new InvoiceDTO(
            rs.getInt("invoice_id"),
            rs.getInt("order_id"),
            invoiceDate,
            rs.getBigDecimal("total_amount"),
            rs.getString("status")
        );
    }
}
