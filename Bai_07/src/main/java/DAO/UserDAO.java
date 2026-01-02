package DAO;

import database.JdbcUtil;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAOinterface<User>  {

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public int insert(User user) {
        int result = 0;
        try {
            Connection con = JdbcUtil.getConnection();

            Statement st = con.createStatement();

            String sql = "INSERT INTO users(username, email, password) " +
                    "VALUES ('" + user.getUsername() + "', '" +
                    user.getEmail() + "', '" +
                    user.getPassword() + "')";

            result = st.executeUpdate(sql);

            System.out.println("Execute " + sql);
            System.out.println("Number of line executed: " + result);

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

            Statement st = con.createStatement();

            String sql = "UPDATE users " +
                    "SET username = '" + user.getUsername() + "'" +
                    ", email = '" + user.getEmail() + "'" +
                    ", password = '" + user.getPassword() + "'" +
                    " WHERE user_id = " + user.getId();

            result = st.executeUpdate(sql);

            System.out.println("Execute " + sql);
            System.out.println("Number of line executed: " + result);

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

            Statement st = con.createStatement();

            String sql = "DELETE FROM users WHERE " +
                   "user_id = " + user.getId();

            result = st.executeUpdate(sql);

            System.out.println("Execute " + sql);
            System.out.println("Number of line executed: " + result);

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

            Statement st = con.createStatement();

            String sql = "SELECT * FROM users";
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);
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

            Statement st = con.createStatement();

            String sql = "SELECT * FROM users WHERE user_id = "+ user.getId() + "";
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
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
