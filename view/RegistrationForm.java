package view;

import Model.User;
import Controller.loginAndRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog {

    private JPanel registerPanel;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfRepassword;
    private JButton btnRegister;
    private JButton btnCancel;

    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    // If the registerbutton is clicked execute a method

    public void addRegistrationListener(ActionListener listenerRegistration) {
        btnRegister.addActionListener(listenerRegistration);
    }

    public void addCancelListener(ActionListener listenerCancel) {
        btnCancel.addActionListener(listenerCancel);
    }

    public void registerUser() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String rePassword = String.valueOf(pfRepassword.getPassword());
        String fullName = firstName + "" + lastName;

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(fullName, email, password);
        if (user != null) {
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUserToDatabase(String fullName, String email, String password){
        User user = null;
        final String DB_URL = "jdbc:mariadb://localhost:3306/management";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // connected to database server

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users(fullName, email, password)" + "VALUES(?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, fullName);
            preparedStmt.setString(2, email);
            preparedStmt.setString(3, password);

            //insert row into the table
            int addedRow = preparedStmt.executeUpdate();
            if (addedRow > 0) {
                user = new User();
                user.fullName = fullName;
                user.email = email;
                user.password = password;

            }
            stmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
