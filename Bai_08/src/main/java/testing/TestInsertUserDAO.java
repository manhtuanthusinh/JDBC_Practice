package testing;

import DAO.UserDAO;
import model.User;

public class TestInsertUserDAO {
    public static void main(String[] args) {
        System.out.println("============ TEST INSERT ============");
        User newUser = new User("Tu", "Tu@123", "12345");
        UserDAO.getInstance().insert(newUser);
    }
}
