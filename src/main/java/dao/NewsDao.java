package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.News;
import utils.JdbcHelper;

public class NewsDao {

    // Lấy toàn bộ tin
    public List<News> findAll() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News ORDER BY PostedDate DESC";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(read(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tin nổi bật (Home = 1)
    public List<News> findHomeNews() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE Home = 1 ORDER BY PostedDate DESC";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(read(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tin theo ID
    public News findById(String id) {
        String sql = "SELECT * FROM News WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return read(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm tin
    public boolean insert(News n) {
        String sql = """
            INSERT INTO News (Id, Title, Content, Image, PostedDate, Author,
                              ViewCount, CategoryId, Home)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, n.getId());
            ps.setString(2, n.getTitle());
            ps.setString(3, n.getContent());
            ps.setString(4, n.getImage());
            ps.setDate(5, new java.sql.Date(n.getPostedDate().getTime()));
            ps.setString(6, n.getAuthor());
            ps.setInt(7, n.getViewCount());
            ps.setString(8, n.getCategoryId());
            ps.setBoolean(9, n.isHome());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật tin
    public boolean update(News n) {
        String sql = """
            UPDATE News SET
            Title = ?, Content = ?, Image = ?, PostedDate = ?, Author = ?,
            ViewCount = ?, CategoryId = ?, Home = ?
            WHERE Id = ?
        """;

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, n.getTitle());
            ps.setString(2, n.getContent());
            ps.setString(3, n.getImage());
            ps.setDate(4, new java.sql.Date(n.getPostedDate().getTime()));
            ps.setString(5, n.getAuthor());
            ps.setInt(6, n.getViewCount());
            ps.setString(7, n.getCategoryId());
            ps.setBoolean(8, n.isHome());
            ps.setString(9, n.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá tin
    public boolean delete(String id) {
        String sql = "DELETE FROM News WHERE Id = ?";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm đọc dữ liệu từ ResultSet
    private News read(ResultSet rs) throws Exception {
        News n = new News();
        n.setId(rs.getString("Id"));
        n.setTitle(rs.getString("Title"));
        n.setContent(rs.getString("Content"));
        n.setImage(rs.getString("Image"));
        n.setPostedDate(rs.getDate("PostedDate"));
        n.setAuthor(rs.getString("Author"));
        n.setViewCount(rs.getInt("ViewCount"));
        n.setCategoryId(rs.getString("CategoryId"));
        n.setHome(rs.getBoolean("Home"));
        return n;
    }
    
    public List<News> findByAuthor(String author) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE Author = ? ORDER BY PostedDate DESC";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(read(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    public void increaseView(String id) {
        String sql = "UPDATE News SET ViewCount = ViewCount + 1 WHERE Id = ?";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public List<News> findByCategory(String categoryId) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE CategoryId = ? ORDER BY PostedDate DESC";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(read(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean existsById(String id) {
        String sql = "SELECT Id FROM News WHERE Id = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    public List<News> findAllOrderById() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News ORDER BY TRY_CAST(SUBSTRING(Id, 2, LEN(Id)) AS INT) DESC";

        try (Connection con = JdbcHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("Id"));
                n.setTitle(rs.getString("Title"));
                n.setContent(rs.getString("Content"));
                n.setImage(rs.getString("Image"));
                n.setPostedDate(rs.getDate("PostedDate"));
                n.setAuthor(rs.getString("Author"));
                n.setViewCount(rs.getInt("ViewCount"));
                n.setCategoryId(rs.getString("CategoryId"));
                n.setHome(rs.getBoolean("Home"));

                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    
}
