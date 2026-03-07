package DAO;

import DTO.warehouse.StockLedgerDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockLedgerDAO {

    public List<StockLedgerDTO> getAll() throws Exception {
        List<StockLedgerDTO> list = new ArrayList<>();
        String sql = "SELECT ledger_id, product_id, warehouse_id, change_qty, ref_type, ref_id, created_at FROM StockLedger";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<StockLedgerDTO> getByWarehouseAndProduct(int warehouseId, int productId) throws Exception {
        List<StockLedgerDTO> list = new ArrayList<>();
        String sql = "SELECT ledger_id, product_id, warehouse_id, change_qty, ref_type, ref_id, created_at FROM StockLedger WHERE warehouse_id=? AND product_id=? ORDER BY created_at DESC";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, warehouseId);
            ps.setInt(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void insert(StockLedgerDTO s) throws Exception {
        String sql = "INSERT INTO StockLedger(product_id, warehouse_id, change_qty, ref_type, ref_id) VALUES(?,?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getProductId());
            ps.setInt(2, s.getWarehouseId());
            ps.setInt(3, s.getChangeQty());
            ps.setString(4, s.getRefType());
            ps.setInt(5, s.getRefId());
            ps.executeUpdate();
        }
    }

    private StockLedgerDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new StockLedgerDTO(
            rs.getInt("ledger_id"),
            rs.getInt("product_id"),
            rs.getInt("warehouse_id"),
            rs.getInt("change_qty"),
            rs.getString("ref_type"),
            rs.getInt("ref_id"),
            createdAt
        );
    }
}
