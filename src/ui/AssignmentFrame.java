package ui;

import GradingSystem.GradingSystem;
import model.Assignment;
import model.Category;
import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignmentFrame extends JFrame{
    private GradingSystem gs;
    private Course course;
    private Category category;

    private JButton backButton;
    private JTable assignmentTable;
    private JButton viewGradesButton;
    private JButton addAssignmentButton;
    private JButton deleteAssignmentButton;
    private JButton modifyWeightsButton;
    private JPanel ButtonPanel;
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JPanel ChoicePanel;


    public AssignmentFrame(GradingSystem gs, Course course, Category category) {
        this.gs = gs;
        this.course = course;
        this.category = category;


        setName("Category");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        addActiveComponent();


    }

    private void addActiveComponent() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
        addAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                new AddAssignmentFrame(gs, course, category);
                dispose();
            }
        });
        modifyWeightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                new ModifyAssignmentFrame();
                dispose();
            }
        });
    }

    private void createUIComponents() {



        String [] assignmentHeader={"Assignment Name","Weight%", "Release Date", "Due Date", "Total Score"};
        List<Assignment> allAssignments = category.getAllAssignments();
        DefaultTableModel assignmentModel = new DefaultTableModel(assignmentHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(allAssignments.size() != 0) {
            for(int i = 0;i < allAssignments.size();i++) {
                Object[] obj = {allAssignments.get(i).getAssignmentName(), allAssignments.get(i).getWeight(), allAssignments.get(i).getReleaseDate(), allAssignments.get(i).getDueDate(), allAssignments.get(i).getMaxPoint()};
                assignmentModel.addRow(obj);
            }
        }
        assignmentTable = new JTable(assignmentModel);

        deleteAssignmentButton = new JButton("Delete Assignment");
        deleteAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int selected = assignmentTable.getSelectedRow();
                if (selected != -1) {

                    //remove from the List of classes
                    String assignmentName = assignmentModel.getValueAt(selected, 0).toString();
                    Assignment targetAssignment = category.getAssignment(assignmentName);
                    category.getAllAssignments().remove(targetAssignment);

                    //remove the entry in the table
                    assignmentModel.removeRow(assignmentTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected assignment deleted successfully");

                }
                else{
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        viewGradesButton = new JButton("View Grades");
        viewGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = assignmentTable.getSelectedRow();
                if (selected != -1) {
                    String assignmentName = assignmentModel.getValueAt(selected, 0).toString();
                    Assignment currentAssignment = category.getAssignment(assignmentName);
                    new GradingFrame(gs, course, category, currentAssignment);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

    }
}
