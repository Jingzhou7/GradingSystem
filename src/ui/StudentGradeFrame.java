package ui;

import GradingSystem.GradingSystem;
import model.Category;
import model.Course;
import model.Grade;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeFrame extends JFrame {
    private GradingSystem gs;
    private Course course;

    private JPanel mainPanel;
    private JButton backButton;
    private JTable studentGradeTable;
    private JPanel ButtonPanel;
    private JButton showComment;
    private JButton addComment;
    private JPanel ChoicePanel;
    private JButton scoreButton;
    DefaultTableModel studentGradeModel;

    public StudentGradeFrame(GradingSystem gs, Course course) {

        this.gs = gs;
        this.course = course;

        setName("Student grade frame");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        addActiveComponent();
    }

    public void addActiveComponent() {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
    }

    private DefaultTableModel getModel(ArrayList<Student> allStudents) {
        String[] header = {"Student ID", "Name", "Bonus Points", "Raw Scaled Points", "Final Grade"};
        DefaultTableModel studentGradeModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (allStudents.size() != 0) {
            String score;
            for (int i = 0; i < allStudents.size(); i++) {
                if (allStudents.get(i).isScoreUp()) {
                    score = Grade.scoreUp(allStudents.get(i).getTotalScaledScore());
                } else {
                    score = Grade.scoreToLetterGrade(allStudents.get(i).getTotalScaledScore());
                }
                Object[] obj = {allStudents.get(i).getSid(), allStudents.get(i).getName(), allStudents.get(i).getAllBonusPoints(), allStudents.get(i).getTotalScaledScore(), score};
                studentGradeModel.addRow(obj);
            }
        }
        return studentGradeModel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        ArrayList<Student> allStudents = course.getAllStudents();
        studentGradeModel = getModel(allStudents);
        studentGradeTable = new JTable(studentGradeModel);

        addComment = new JButton("Add Comment");
        addComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int selected = studentGradeTable.getSelectedRow();
                if (selected != -1) {

                    //get the student that is added comment
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    new AddCommentFrame(gs, course, targetStudent);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        showComment = new JButton("Show Comment");
        showComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = studentGradeTable.getSelectedRow();
                if (selected != -1) {
                    //get the student that is added comment
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    new CommentFrame(gs, course, targetStudent);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        scoreButton = new JButton("Score Up");
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = studentGradeTable.getSelectedRow();
                if (selected != -1) {
                    //get the student that is added comment
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    if (!targetStudent.isScoreUp()) {
                        targetStudent.setScoreUp(true);
                        studentGradeModel = getModel(allStudents);
                        studentGradeTable = new JTable(studentGradeModel);
                    } else {
                        JOptionPane.showMessageDialog(source, "This student has been scored up.");
                    }
                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });
    }

}
