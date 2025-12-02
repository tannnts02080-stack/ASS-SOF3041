package dao;

import entity.Newsletter;
import utils.JdbcHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsletterDao {

    public boolean insert(String email) {
        String sql = "INSERT INTO Newsletter (Email, SubscribedDate) VALUES (?, GETDATE())";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Newsletter> findAll() {
        List<Newsletter> list = new ArrayList<>();
        String sql = "SELECT * FROM Newsletter ORDER BY Id DESC";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setId(rs.getInt("Id"));
                n.setEmail(rs.getString("Email"));
                n.setSubscribedDate(rs.getDate("SubscribedDate"));
                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Newsletter WHERE Id = ?";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
