package Controller;

import Model.User;
import view.LoginForm;
import view.RegistrationForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAndRegister {
    private LoginForm loginForm;
    private RegistrationForm registrationForm;

    public loginAndRegister(LoginForm loginForm, RegistrationForm registrationForm) {
        this.loginForm = loginForm;
        this.registrationForm = registrationForm;

        this.registrationForm.addRegistrationListener(new RegisterListener());
        this.registrationForm.addCancelListener(new CancelListener());

    }

    class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                registrationForm.registerUser();
                if (registrationForm.user != null) {
                    System.out.println("Successfully registered user " + registrationForm.user.fullName);
                    registrationForm.setVisible(false);
                    loginForm.setVisible(true);
                }else {
                    System.out.println("Failed to register user ");
                }

            } catch (Exception c){
                c.printStackTrace();
            }

        }

    }
    class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            registrationForm.dispose();
        }
    }

}
