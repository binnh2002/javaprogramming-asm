import Controller.loginAndRegister;
import Model.User;
import view.DashboardForm;
import view.LoginForm;
import view.RegistrationForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       RegistrationForm reg = new RegistrationForm(null);
       LoginForm login = new LoginForm(null);
       DashboardForm dboard = new DashboardForm(null);
        loginAndRegister log = new loginAndRegister(login, reg, dboard);
        login.setVisible(true);
    }
}