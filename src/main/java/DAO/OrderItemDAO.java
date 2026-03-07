package DAO;

import DTO.devlivery.OrderItemDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public List<OrderItemDTO> getByOrderId(int orderId) throws Exception {
        List<OrderItemDTO> list = new ArrayList<>();
        String sql = "SELECT order_item_id, order_id, product_id, quantity, unit_price FROM OrderItems WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(OrderItemDTO item) throws Exception {
        String sql = "INSERT INTO OrderItems(order_id, product_id, quantity, unit_price) VALUES(?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setBigDecimal(4, item.getUnitPrice());
            ps.executeUpdate();
        }
    }

    public void deleteByOrderId(int orderId) throws Exception {
        String sql = "DELETE FROM OrderItems WHERE order_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }

    private OrderItemDTO map(ResultSet rs) throws SQLException {
        return new OrderItemDTO(
            rs.getInt("order_item_id"),
            rs.getInt("order_id"),
            rs.getInt("product_id"),
            rs.getInt("quantity"),
            rs.getBigDecimal("unit_price")
        );
    }
}
