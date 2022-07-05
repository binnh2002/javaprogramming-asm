package Controller;

import Model.User;
import view.DashboardForm;
import view.LoginForm;
import view.RegistrationForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAndRegister {
    private LoginForm loginForm;
    private RegistrationForm registrationForm;

    private DashboardForm dashboardForm;

    public loginAndRegister(LoginForm loginForm, RegistrationForm registrationForm, DashboardForm dashboardForm) {
        this.loginForm = loginForm;
        this.registrationForm = registrationForm;
        this.dashboardForm = dashboardForm;

        this.registrationForm.addRegistrationListener(new RegisterListener());
        this.registrationForm.addBackToLogin(new BackToLoginListener());
        this.registrationForm.btnExitListener(new regExitListener());

        this.loginForm.btnLoginListener(new LoginListener());
        this.loginForm.btnNewAccountListener(new NewAccountListener());
        this.loginForm.btnExitListener(new logExitListener());

        this.dashboardForm.btnAddListener(new AddListener());
        this.dashboardForm.btnRefreshListener(new RefreshListener());
        this.dashboardForm.btnUpdateListener(new UpdateListener());
        this.dashboardForm.btnRemoveListener(new RemoveListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.loginUser();
            if (loginForm.user != null) {
                loginForm.dispose();
                dashboardForm.setVisible(true);
            }

        }
    }

    class NewAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            loginForm.setVisible(false);
            registrationForm.setVisible(true);
        }
    }
    class logExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.dispose();
        }
    }
    class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                registrationForm.registerUser();

            if (registrationForm.user != null){
                loginForm.setVisible(true);
            }

            } catch (Exception c){
                c.printStackTrace();
            }

        }

    }
    class BackToLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            registrationForm.setVisible(false);
            loginForm.setVisible(true);
        }
    }

    class regExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registrationForm.dispose();
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dashboardForm.setData();
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dashboardForm.getData();
        }
    }

    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dashboardForm.UpdateListener();
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          dashboardForm.removeListener();
        }
    }

}
