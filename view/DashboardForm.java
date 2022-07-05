package view;

import Model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardForm extends JDialog{

    private JPanel dashboardManagement;
    private JTable showTable;
    private JTextField txtCourseName;
    private JButton btnUpdate;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnRefresh;
    private JTextField txtCourseId;
    private JTextArea txtaDescription;

    public DashboardForm(JFrame parent){
        super(parent);
        setTitle("Dashboard");
        setContentPane(dashboardManagement);
        setMinimumSize(new Dimension(700, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        DashboardForm form = new DashboardForm(null);
        form.setTitle("Dashboard");
        form.setVisible(true);
    }

    public void btnRefreshListener(ActionListener btnRefreshListener){
        btnRefresh.addActionListener(btnRefreshListener);
    }

    public void btnAddListener(ActionListener btnAddListener){
        btnAdd.addActionListener(btnAddListener);
    }

    public void btnUpdateListener(ActionListener btnUpdateListener){
        btnUpdate.addActionListener(btnUpdateListener);
    }

    public void btnRemoveListener(ActionListener btnRemoveListener){
        btnRemove.addActionListener(btnRemoveListener);
    }
    public void setData(){
        String courseId = txtCourseId.getText();
        String courseName = txtCourseName.getText();
        String courseDescription = txtaDescription.getText();


        courses = addDataToDatabase(courseId, courseName, courseDescription);

        if(courses != null){
            JOptionPane.showMessageDialog(null, "Successfully");
        }
    }

    public void getData(){
        List<Course> list = getCourse();

        DefaultTableModel dft = (DefaultTableModel)showTable.getModel();
        dft.setRowCount(0);

        if (dft.getColumnCount() == 0) {
            dft.addColumn("id");
            dft.addColumn("Name");
            dft.addColumn( "Description");
        }
        for (Course c : list) {
           String id = c.courseId;
           String name = c.courseName;
           String description = c.courseDescription;
           dft.addRow(new Object[] { id, name, description});

        }
    }
    public Course courses;
    private Course addDataToDatabase(String courseId, String courseName, String courseDescription){
        Course courses = null;
        final String DB_URL = "jdbc:mariadb://localhost:3306/management";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn =  DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO course(courseId, courseName, courseDescription)" + " VALUES(?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, courseId);
            preparedStmt.setString(2, courseName);
            preparedStmt.setString(3, courseDescription);

            int addedRow = preparedStmt.executeUpdate();

            if(addedRow > 0){
                courses = new Course();

                courses.courseId = courseId;
                courses.courseName = courseName;
                courses.courseDescription = courseDescription;

            }

            stmt.close();
            conn.close();
        }catch (SQLException e) { e.printStackTrace();
        }
       return courses;
    }
    private List<Course> getCourse(){
        List<Course> list = new ArrayList<>();
        final String DB_URL = "jdbc:mariadb://localhost:3306/management";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM `course`";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                Course v2 = new Course();
                    v2.courseId = rs.getString("courseId");
                    v2.courseName =  rs.getString("courseName");;
                    v2.courseDescription = rs.getString("courseDescription");
                    list.add(v2);
            }

        } catch (SQLException e) { e.printStackTrace();}
        return list;
    }

        public void UpdateListener(){
            String courseId = txtCourseId.getText();
            String courseName = txtCourseName.getText();
            String courseDescription = txtaDescription.getText();

            final String DB_URL = "jdbc:mariadb://localhost:3306/management";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                Statement stmt = conn.createStatement();
                String sql = "UPDATE course SET courseName = ?, courseDescription = ? WHERE courseId =? ";

                PreparedStatement preparedStmt = conn.prepareStatement(sql);

                preparedStmt.setString(1, courseName);
                preparedStmt.setString(2, courseDescription);
                preparedStmt.setString(3, courseId);
                preparedStmt.executeQuery();

                JOptionPane.showMessageDialog(null, "Updated!!");
            }catch (SQLException e) {
                e.printStackTrace();

                JOptionPane.showMessageDialog(null,"Error!!");
            }



        }

        public void removeListener(){
            String courseId = txtCourseId.getText();

            final String DB_URL = "jdbc:mariadb://localhost:3306/management";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM course WHERE courseId = ? ";

                PreparedStatement preparedStmt = conn.prepareStatement(sql);

                preparedStmt.setString(1, courseId);
                preparedStmt.executeQuery();

                JOptionPane.showMessageDialog(null, "Deleted!!");
            }catch (SQLException e) {
                e.printStackTrace();

                JOptionPane.showMessageDialog(null,"Error!!");
            }

        }

}


