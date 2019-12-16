package ui;

import GradingSystem.GradingSystem;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JTextField textField1;
    private JLabel statsLbl;
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
        //backButton = new JButton("Back");
        addActiveComponent();

        statsLbl.setText(course.outputStats());
    }

    private void addActiveComponent() {
        ArrayList<Student> allStudents = course.getAllStudents();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save();
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });

    }

    private DefaultTableModel getModel(ArrayList<Student> allStudents) {
        String[] header = {"Student ID", "Name", "Bonus Points", "Raw Score", "Weighted Score", "Final Grade", "Write Comment"};
        DefaultTableModel studentGradeModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6 || column == 2) return true;
                else return false;
            }
        };
        if (allStudents.size() != 0) {

            for (int i = 0; i < allStudents.size(); i++) {
                Object[] obj = {allStudents.get(i).getSid(), allStudents.get(i).getName(), allStudents.get(i).getAllBonusPoints(), allStudents.get(i).getRawTotalScore(), allStudents.get(i).getTotalScoreWeighted(), allStudents.get(i).scoreToLetterGrade(), allStudents.get(i).getLastComment().getText()};
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
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = studentGradeTable.getSelectedRow();
                if (selected != -1) {
                    //get the student that is added comment
                    save();
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    AddCommentFrame acf = new AddCommentFrame(gs, course, targetStudent);
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
                    if (textField1.getText().equals(""))
                        JOptionPane.showMessageDialog(source, "Please enter the amount you want to bump.");
                    int bumpScore = Integer.parseInt(textField1.getText());
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    if (!targetStudent.isScoreUp()) {
                        targetStudent.setScoreUp(true);
                        studentGradeModel.removeRow(selected);
                        int plusScore = targetStudent.bumpUpScoreBy(bumpScore);
                        //check if plusScore is too large. at most maxScore- highScore
                        Object[] obj = {targetStudent.getSid(), targetStudent.getName(), targetStudent.getAllBonusPoints(), targetStudent.getRawTotalScore(), targetStudent.getTotalScoreWeighted(), targetStudent.scoreToLetterGrade(), targetStudent.getLastComment().getText()};
                        studentGradeModel.insertRow(selected, obj);
                    } else {
                        JOptionPane.showMessageDialog(source, "This student has been scored up.");
                    }
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
                    //get the student that is shown comment
                    int studentId = Integer.parseInt(studentGradeModel.getValueAt(selected, 0).toString());
                    Student targetStudent = course.getStudent(studentId);
                    save();
                    new CommentFrame(gs, course, targetStudent);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Saving from AssignmentFrame");
                save();
                gs.save();
                System.exit(0);
            }
        });
    }

    private void save() {
        if (studentGradeTable.isEditing()) {
            studentGradeTable.getCellEditor().stopCellEditing();
        }

        for (int i = 0; i < course.getAllStudents().size(); i++) {
            String text = studentGradeModel.getValueAt(i, 6).toString();
            ArrayList<Comment> allComments = course.getAllStudents().get(i).getComments();
            double bp = 0;
            if(studentGradeModel.getValueAt(i, 2) != null) {
                bp = Double.parseDouble(studentGradeModel.getValueAt(i, 2).toString());
                course.getAllStudents().get(i).getBonusPoints().add(new BonusPoints(bp));
            }
            boolean commentExist = false;
            for (Comment c : allComments) {
                if (c.getText().equals(text)) {
                    commentExist = true;
                }
            }
            if (!commentExist) {
                Comment tmpComment = new Comment(text);
                if (!tmpComment.isEmptyComment())
                    course.getAllStudents().get(i).addComment(tmpComment);
            }




        }

    }



}
