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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GradingFrame extends JFrame {
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
    private JScrollPane gradeScroll;
    private JLabel assignmentLbl;
    private DefaultTableModel model;


    public GradingFrame(GradingSystem gs, Course course, Category category, Assignment currentAssignment) {
        this.gs = gs;
        this.course = course;
        this.category = category;
        this.assignment = currentAssignment;
        assignmentLbl.setText(currentAssignment.getAssignmentName() + "(Total Score: " + currentAssignment.getMaxPoint() + ")");
        setName("View Grades");
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkScore()) {
                    AssignmentFrame a = new AssignmentFrame(gs, course, category);
                    dispose();
                }
            }
        });
    }


    private void createUIComponents() {

        String[] header = {"Student Name", "Student ID", "Raw Score", "Scaled score"};

        ArrayList<Student> allStudents = course.getAllStudents();
        model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else
                    return false;
            }
        };

        if (allStudents.size() != 0) {
            for (int i = 0; i < allStudents.size(); i++) {
                Object[] obj = {allStudents.get(i).getName(), allStudents.get(i).getSid(), allStudents.get(i).getGrade(assignment).getRawScore(), allStudents.get(i).getGrade(assignment).getScaledScore()};
                model.addRow(obj);
            }
        }
        gradeTable = new JTable(model);



    }

    private boolean checkScore() {
        boolean scoreSave = true;
        String msg = "";
        if (gradeTable.isEditing()) {
            gradeTable.getCellEditor().stopCellEditing();
        }
        for (int i = 0; i < course.getAllStudents().size(); i++) {
            double tmp = Double.parseDouble(model.getValueAt(i, 2).toString());
            if (tmp > assignment.getMaxPoint() || tmp < (0 - assignment.getMaxPoint())) {
                msg = msg + "score exceeds total score of the assignment";
                scoreSave = false;
                break;
            }
        }
        for (int i = 0; i < course.getAllStudents().size(); i++) {
            course.getAllCategories().get(i).setCategoryName(model.getValueAt(i, 2).toString());
            double tmp = Double.parseDouble(model.getValueAt(i, 2).toString());
            double totalScore = assignment.getMaxPoint();

            if(tmp >= 0) {
                course.getAllStudents().get(i).getGrade(assignment).setRawScore(tmp);
            } else {
                course.getAllStudents().get(i).getGrade(assignment).setRawScore(totalScore + tmp);
            }

        }

        if (!scoreSave) {
            Object[] options = {"ok"};
            JOptionPane.showOptionDialog(null, msg, "Fail", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            return scoreSave;
        }

        return scoreSave;
    }

}
