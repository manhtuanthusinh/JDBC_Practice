package DAO;

import database.JdbcUtil;
import model.User;

import java.sql.Connection;
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
}
