package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import utils.JdbcHelper;

public class UserDao {

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(read(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User findById(String id) {
        String sql = "SELECT * FROM Users WHERE Id = ?";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return read(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(User u) {
        String sql = """
            INSERT INTO Users(Id, Password, Fullname, Birthday, Mobile, Email, Role)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getId());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setDate(4, u.getBirthday());
            ps.setString(5, u.getPhone());
            ps.setString(6, u.getEmail());
            ps.setInt(7, u.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(User u) {
        String sql = """
            UPDATE Users SET 
            Password=?, Fullname=?, Birthday=?, Mobile=?, Email=?, Role=?
            WHERE Id=?
        """;

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getPassword());
            ps.setString(2, u.getFullname());
            ps.setDate(3, u.getBirthday());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getEmail());
            ps.setInt(6, u.getRole());
            ps.setString(7, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Users WHERE Id=?";

        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private User read(ResultSet rs) throws Exception {
        User u = new User();
        u.setId(rs.getString("Id"));
        u.setPassword(rs.getString("Password"));
        u.setFullname(rs.getString("Fullname"));
        u.setBirthday(rs.getDate("Birthday"));
        u.setPhone(rs.getString("Mobile"));
        u.setEmail(rs.getString("Email"));
        u.setRole(rs.getInt("Role"));
        return u;
    }
    
 // Tìm user theo ID + Password (dùng cho login)
    public User findByIdAndPassword(String id, String password) {
        String sql = "SELECT * FROM Users WHERE Id = ? AND Password = ?";
        try (Connection conn = JdbcHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);       // <-- nhớ là số 1
            ps.setString(2, password); // <-- số 2

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getString("Id"));
                u.setPassword(rs.getString("Password"));
                u.setFullname(rs.getString("Fullname"));
                u.setBirthday(rs.getDate("Birthday"));
                u.setGender(rs.getBoolean("Gender"));
                u.setPhone(rs.getString("Mobile"));
                u.setEmail(rs.getString("Email"));
                u.setRole(rs.getInt("Role"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    
}
