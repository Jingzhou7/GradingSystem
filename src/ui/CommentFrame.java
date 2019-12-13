package ui;

import GradingSystem.GradingSystem;
import model.Comment;
import model.Course;
import model.Grade;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommentFrame extends JFrame{
    private GradingSystem gs;
    private Course course;
    private Student student;
    private JButton backButton;
    private JTable table;
    private JTable assTable;
    private JPanel mainPanel;

    public CommentFrame(GradingSystem gs, Course course, Student student) {
        this.gs = gs;
        this.course = course;
        this.student=student;

        setName("Comment");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        backButton=new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new StudentGradeFrame(gs, course);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String [] header={"                                                     Overall Comment"};
        ArrayList<Comment> comments=student.getComments();
        DefaultTableModel commentModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(comments.size()!=0){
            for(int i=0;i<comments.size();i++){
                Object[] obj={comments.get(i).getText()};
                commentModel.addRow(obj);
            }
        }
        table=new JTable(commentModel);

        String [] assHeader={"Assignment","Comment"};
        DefaultTableModel assCommentModel = new DefaultTableModel(assHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ArrayList<Grade> grades=student.getGrades();
        if(grades.size()!=0){
            for(int i=0;i<grades.size();i++){
                Object[] obj={grades.get(i).getAssignment().getAssignmentName(),grades.get(i).getComment()};
                assCommentModel.addRow(obj);
            }
        }
        assTable=new JTable(assCommentModel);
    }
}
