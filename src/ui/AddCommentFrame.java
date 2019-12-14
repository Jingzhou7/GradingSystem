package ui;

import GradingSystem.GradingSystem;
import model.Comment;
import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCommentFrame extends JFrame {
    private GradingSystem gs;
    private Course course;
    private Student student;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel checkPanel;
    private JTextField commentTextfield;
    private JLabel commentLabel;
    private JButton confirmButton;
    private JButton backBtn;

    public AddCommentFrame(GradingSystem gs, Course course, Student student) {
        this.gs = gs;
        this.course = course;
        this.student = student;

        setName("Add Comment");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentGradeFrame(gs, course);
                dispose();
            }
        });
        addActiveComponent();
    }

    private void addActiveComponent() {
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                String inp = commentTextfield.getText();
                if (inp == "") {
                    JOptionPane.showMessageDialog(source, "The input is empty");
                } else {
                    student.addComment(new Comment(inp));
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
