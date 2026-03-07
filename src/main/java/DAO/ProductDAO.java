package DAO;

import DTO.master.ProductDTO;
import utils.DBUtils;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<ProductDTO> getAll() throws Exception {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT product_id, sku, product_name, price, created_at FROM Products";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public ProductDTO getById(int id) throws Exception {
        String sql = "SELECT product_id, sku, product_name, price, created_at FROM Products WHERE product_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(ProductDTO p) throws Exception {
        String sql = "INSERT INTO Products(sku, product_name, price) VALUES(?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getSku());
            ps.setString(2, p.getProductName());
            ps.setBigDecimal(3, p.getPrice());
            ps.executeUpdate();
        }
    }

    public void update(ProductDTO p) throws Exception {
        String sql = "UPDATE Products SET sku=?, product_name=?, price=? WHERE product_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getSku());
            ps.setString(2, p.getProductName());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getProductId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Products WHERE product_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private ProductDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new ProductDTO(
            rs.getInt("product_id"),
            rs.getString("sku"),
            rs.getString("product_name"),
            rs.getBigDecimal("price"),
            createdAt
        );
    }
}
