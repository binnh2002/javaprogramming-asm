package view;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.Normalizer;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField txtEmail;
    private JButton btnLogin;
    private JButton btnNewAccount;
    private JPasswordField pfPassword;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public char[] getPassword() {
        return pfPassword.getPassword();
    }
    public User user;
    public User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mariadb://localhost:3306/management";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.fullName = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
