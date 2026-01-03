package DAO;

import database.JdbcUtil;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements DAOinterface<User>  {

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public int insert(User user) {
        int result = 0;
        try {
            Connection con = JdbcUtil.getConnection();

            String sql = "INSERT INTO users(username, email, password) " +
                    " VALUES (?, ?, ?)";

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getEmail());
            psmt.setString(3, user.getPassword());

            result = psmt.executeUpdate();
            System.out.println(result + " rows inserted");

            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int update(User user) {
        int result = 0;
        try {
            Connection con = JdbcUtil.getConnection();

            String sql = "UPDATE users \n" +
                    "SET username = ?, password = ?, email = ?\n" +
                    "WHERE user_id = ?\n";

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getEmail());
            psmt.setInt(4, user.getId());

            result = psmt.executeUpdate();
            System.out.println(result + " rows changed");

            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int delete(User user) {
        int result = 0;
        try {
            Connection con = JdbcUtil.getConnection();

            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1, user.getId());

            result = psmt.executeUpdate();
            System.out.println(result + " rows deleted");

            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public ArrayList<User> SelectAll() {
        ArrayList<User> usertable = new ArrayList<>();
        try {
            Connection con = JdbcUtil.getConnection();

            String sql = "SELECT * FROM users";
            PreparedStatement psmt = con.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");

                User user = new User(id, username, email);
                usertable.add(user);
            }

            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usertable;
    }

    @Override
    public User SelectByID(User user) {
        User result = null;

        try {
            Connection con = JdbcUtil.getConnection();

            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1, user.getId());

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");

                result = new User(id, username, email);
            }

            JdbcUtil.closeConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    @Override
    public ArrayList<User> SelectByCondition(String condition) {
        return null;
    }
}
