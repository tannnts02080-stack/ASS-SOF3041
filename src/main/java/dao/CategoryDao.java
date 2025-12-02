package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import utils.JdbcHelper;

public class CategoryDao {

    /**
     * Lấy tất cả category
     */
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getString("Id"));
                c.setName(rs.getString("Name"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Tìm category theo id
     */
    public Category findById(String id) {
        String sql = "SELECT * FROM Categories WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getString("Id"));
                    c.setName(rs.getString("Name"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Thêm category mới
     */
    public boolean insert(Category c) {
        String sql = "INSERT INTO Categories (Id, Name) VALUES (?, ?)";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cập nhật category
     */
    public boolean update(Category c) {
        String sql = "UPDATE Categories SET Name = ? WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Xóa category theo id
     */
    public boolean delete(String id) {
        String sql = "DELETE FROM Categories WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /*KHÔNG THÊM MÃ TRÙNG*/
    /**
     * Kiểm tra mã category đã tồn tại chưa
     */
    public boolean exists(String id) {
        String sql = "SELECT COUNT(*) FROM Categories WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNextId() {
        String sql = "SELECT MAX(Id) FROM Categories";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String maxId = rs.getString(1); // CT05
                if (maxId == null) return "CT01";

                int num = Integer.parseInt(maxId.substring(2));
                num++;
                return String.format("CT%02d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "CT01";
    }


    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM Categories WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
}
