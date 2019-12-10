package ui;

import GradingSystem.GradingSystem;
import model.Assignment;
import model.Category;
import model.Course;

import javax.swing.*;
import java.awt.*;

public class GradingFrame extends JFrame{
    private GradingSystem gs;
    private Course course;
    private Category category;
    private Assignment assignment;

    private JPanel mainPanel;
    private JButton backButton;
    private JTable table1;
    private JButton button1;
    private JButton button2;
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


    }
}
