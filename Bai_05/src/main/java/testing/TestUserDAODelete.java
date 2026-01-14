package testing;

import DAO.UserDAO;
import model.User;

public class TestUserDAODelete {
    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            User user = new User(i, "Testing_" + i, "", "");
            UserDAO.getInstance().delete(user);
        }

    }
}
