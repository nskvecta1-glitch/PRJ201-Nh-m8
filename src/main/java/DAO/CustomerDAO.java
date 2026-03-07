package DAO;

import DTO.master.CustomerDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<CustomerDTO> getAll() throws Exception {
        List<CustomerDTO> list = new ArrayList<>();
        String sql = "SELECT customer_id, customer_name, phone, address, email, created_at FROM Customers";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public CustomerDTO getById(int id) throws Exception {
        String sql = "SELECT customer_id, customer_name, phone, address, email, created_at FROM Customers WHERE customer_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(CustomerDTO c) throws Exception {
        String sql = "INSERT INTO Customers(customer_name,phone,address,email) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCustomerName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getEmail());
            ps.executeUpdate();
        }
    }

    public void update(CustomerDTO c) throws Exception {
        String sql = "UPDATE Customers SET customer_name=?,phone=?,address=?,email=? WHERE customer_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCustomerName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getCustomerID());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Customers WHERE customer_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private CustomerDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new CustomerDTO(
            rs.getInt("customer_id"),
            rs.getString("customer_name"),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getString("email"),
            createdAt
        );
    }
}
