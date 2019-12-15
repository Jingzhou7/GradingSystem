package ui;

import GradingSystem.GradingSystem;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradingFrame extends JFrame {
    private GradingSystem gs;
    private Course course;
    private Category category;
    private Assignment assignment;

    private JPanel mainPanel;
    private JButton backButton;
    private JTable gradeTable;
    private JButton editModeButton;
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
                if (checkScore()) {
                    AssignmentFrame a = new AssignmentFrame(gs, course, category);
                    dispose();
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Saving from GradingFrame");
                gs.save();
                System.exit(0);
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
            for (Student s : allStudents) {
                Grade g = s.getGrade(assignment);
                if (g == null) {
                    Object[] obj = {s.getName(), s.getSid()};
                    model.addRow(obj);
                } else {
                    Object[] obj = {s.getName(), s.getSid(), s.getGrade(assignment).getRawScore(), s.getGrade(assignment).getScaledScore()};
                    model.addRow(obj);
                }
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
            if (model.getValueAt(i, 2) != null) {
                double tmp = Double.parseDouble(model.getValueAt(i, 2).toString());
                if (tmp > assignment.getMaxPoint() || tmp < (0 - assignment.getMaxPoint())) {
                    msg = msg + "score exceeds total score of the assignment";
                    scoreSave = false;
                    break;
                } else {
                    double totalScore = assignment.getMaxPoint();
                    Grade g = course.getAllStudents().get(i).getGrade(assignment);
                    course.getAllStudents().get(i).getGrades().add(g);
                    if (g == null) {
                        g = new Grade(assignment);
                        if (tmp >= 0) {
                            g.setRawScore(tmp);
                        } else {
                            g.setRawScore(totalScore + tmp);
                        }
                    } else {
                        if (tmp >= 0) {
                            g.setRawScore(tmp);
                        } else {
                            g.setRawScore(totalScore + tmp);
                        }
                    }
                }
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
