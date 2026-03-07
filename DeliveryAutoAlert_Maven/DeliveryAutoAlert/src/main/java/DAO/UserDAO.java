package DAO;

import DTO.master.UserDTO;
import utils.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDTO login(String username, String passwordHash) throws Exception {
        String sql = "SELECT user_id, username, password_hash, full_name, email, role_id, is_active, created_at FROM Users WHERE username=? AND password_hash=? AND is_active=1";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<UserDTO> getAll() throws Exception {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, full_name, email, role_id, is_active, created_at FROM Users";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public UserDTO getById(int id) throws Exception {
        String sql = "SELECT user_id, username, password_hash, full_name, email, role_id, is_active, created_at FROM Users WHERE user_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public void insert(UserDTO u) throws Exception {
        String sql = "INSERT INTO Users(username, password_hash, full_name, email, role_id, is_active) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPasswordhash());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getRoleID());
            ps.setBoolean(6, u.isIsActive());
            ps.executeUpdate();
        }
    }

    public void update(UserDTO u) throws Exception {
        String sql = "UPDATE Users SET username=?, full_name=?, email=?, role_id=?, is_active=? WHERE user_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getFullname());
            ps.setString(3, u.getEmail());
            ps.setInt(4, u.getRoleID());
            ps.setBoolean(5, u.isIsActive());
            ps.setInt(6, u.getUserID());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Users WHERE user_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private UserDTO map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
        return new UserDTO(
            rs.getInt("user_id"),
            rs.getString("username"),
            rs.getString("password_hash"),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getInt("role_id"),
            rs.getBoolean("is_active"),
            createdAt
        );
    }
}
