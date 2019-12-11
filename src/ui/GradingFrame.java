package ui;

import GradingSystem.GradingSystem;
import model.Assignment;
import model.Category;
import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GradingFrame extends JFrame{
    private GradingSystem gs;
    private Course course;
    private Category category;
    private Assignment assignment;

    private JPanel mainPanel;
    private JButton backButton;
    private JTable gradeTable;
    private JButton button1;
    private JButton editModeButton;
    private JButton button3;
    private JButton button4;
    private JComboBox comboBox1;


    public GradingFrame(GradingSystem gs, Course course, Category category, Assignment currentAssignment) {
        this.gs = gs;
        this.course = course;
        this.category = category;
        this.assignment = currentAssignment;
        setName("View Grades");
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);


        editModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

    }


    private void createUIComponents() {

        String [] header={"Student Name", "Student ID", "Raw Score", "Scaled score"};

        ArrayList<Student> allStudents = course.getAllStudents();
        DefaultTableModel model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(allStudents.size() != 0) {
            for(int i = 0;i < allStudents.size();i++) {
                Object[] obj = {allStudents.get(i).getName(), allStudents.get(i).getSid(), allStudents.get(i).getGrade(assignment).getRawScore(), allStudents.get(i).getGrade(assignment).getScaledScore()};
                model.addRow(obj);
            }
        }
        gradeTable = new JTable(model);

        editModeButton = new JButton("Edit Mode");
        editModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


    }
}
