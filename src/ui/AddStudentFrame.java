package ui;

import GradingSystem.GradingSystem;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentFrame extends JFrame {

    private JPanel mainPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField middleNameTextField;
    private JTextField emailTextField;
    private JButton clearAllBtn;
    private JButton confirmBtn;
    private JButton backBtn;
    private JPanel inforPanel;
    private JPanel checkPanel;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel middleNameLbl;
    private JLabel emailLbl;

    public AddStudentFrame(GradingSystem gs, Course course) {
        setName("Add student frame");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        clearAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                middleNameTextField.setText("");
                emailTextField.setText("");
            }
        });
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fname = firstNameTextField.getText();
                String lname = lastNameTextField.getText();
                String mname = middleNameTextField.getText();
                String email = emailTextField.getText();
                course.addStudent(fname, lname, mname, email);
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
    }

}
