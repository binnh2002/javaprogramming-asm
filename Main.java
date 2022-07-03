import Controller.loginAndRegister;
import Model.User;
import view.LoginForm;
import view.RegistrationForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       RegistrationForm reg = new RegistrationForm(null);
       LoginForm login = new LoginForm(null);
        loginAndRegister log = new loginAndRegister(login, reg);

        login.setVisible(true);
        /*String url = "jdbc:mariadb://localhost:3306/management";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }*/
    }
}