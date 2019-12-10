package ui;

import GradingSystem.GradingSystem;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeFrame extends JFrame{
    private GradingSystem gs;
    private Course course;

    private JPanel mainPanel;
    private JButton backButton;
    private JTable table1;
    private JPanel ButtonPanel;
    private JButton ShowComment;
    private JButton AddComment;
    private JPanel ChoicePanel;

    public StudentGradeFrame(GradingSystem gs, Course course) {

        this.gs = gs;
        this.course = course;

        setName("Student Grade");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String [] header={"Name","Bonus Points", "Raw Scaled Points", "Final Grade"};
        String [][] data={{"James", "12", "88", "B"}};
        DefaultTableModel model = new DefaultTableModel(data, header);
        table1 = new JTable(data, header);
    }
}
