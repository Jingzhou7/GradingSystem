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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AssignmentFrame extends JFrame {
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
    private JPanel ChoicePanel;
    private JLabel categoryLbl;

    private DefaultTableModel assignmentModel;

    public AssignmentFrame(GradingSystem gs, Course course, Category category) {
        this.gs = gs;
        this.course = course;
        this.category = category;


        categoryLbl.setText("Current Viewing Category: " + category.getCategoryName());
        setName("Category");
        setVisible(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        addActiveComponent();


    }

    private void addActiveComponent() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkVals()) {
                    new CourseDetailFrame(gs, course);
                    dispose();
                }
            }
        });
        addAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                new AddAssignmentFrame(gs, course, category);
                Assignment a = new Assignment("Please modify this row");
                Object[] obj = {"Please modify this row", 0, 0, 0, 0};
                assignmentModel.addRow(obj);
                category.addAssignment(a);
                //dispose();
            }
        });
        modifyWeightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                new ModifyAssignmentFrame();
                //dispose();
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Saving from AssignmentFrame");
                if (checkVals()){
                    gs.save();
                    System.exit(0);
                }

            }
        });
    }

    private boolean checkVals(){

        double totalWeight = 0;
        boolean weightSave = true;
        String msg = "";
        if (assignmentTable.isEditing()){
            assignmentTable.getCellEditor().stopCellEditing();
        }
        if (assignmentModel.getRowCount() != 0) {
            try {
                for (int i = 0; i < category.getAllAssignments().size(); i++) {
                    if (Double.parseDouble(assignmentModel.getValueAt(i, 1).toString()) <= 0) {
                        msg = msg + "The weight of category cannot be 0. ";
                        weightSave = false;
                        break;
                    }
                }
                if (weightSave) {
                    for (int i = 0; i < category.getAllAssignments().size(); i++) {
                        totalWeight += Double.parseDouble(assignmentModel.getValueAt(i, 1).toString());
                    }
                    if (totalWeight != 100) {
                        msg = msg + "The total weight of all categories is not up to 100%";
                        weightSave = false;
                        Object[] options = {"ok"};
                        JOptionPane.showOptionDialog(null, msg, "Fail", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    } else {
                        for (int i = 0; i < category.getAllAssignments().size(); i++) {
                            category.getAllAssignments().get(i).setAssignmentName(assignmentModel.getValueAt(i, 0).toString());
                            category.getAllAssignments().get(i).setWeight(Double.parseDouble(assignmentModel.getValueAt(i, 1).toString()));
                            String rd = assignmentModel.getValueAt(i, 2).toString();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date parsed = null;
                            parsed = format.parse(rd);
                            java.sql.Date sql = new java.sql.Date(parsed.getTime());
                            System.out.println(sql);
                            category.getAllAssignments().get(i).setReleaseDate(sql);
                            String dd = assignmentModel.getValueAt(i, 3).toString();
                            parsed = null;
                            parsed = format.parse(dd);
                            sql = new java.sql.Date(parsed.getTime());
                            System.out.println(sql);
                            category.getAllAssignments().get(i).setDueDate(sql);
                            category.getAllAssignments().get(i).setMaxPoint(Double.parseDouble(assignmentModel.getValueAt(i, 4).toString()));
                        }
                    }
                } else {
                    Object[] options = {"ok"};
                    JOptionPane.showOptionDialog(null, msg, "Fail", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Number Format Incorrect");
                weightSave = false;
            } catch (HeadlessException e) {
                //e.printStackTrace();
                weightSave = false;
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Date Format Incorrect");
                //e.printStackTrace();
                weightSave = false;
            }
        }
        return weightSave;
    }

    private void createUIComponents() {


        String[] assignmentHeader = {"Assignment Name", "Weight%", "Release Date", "Due Date", "Total Score"};
        List<Assignment> allAssignments = category.getAllAssignments();
        assignmentModel = new DefaultTableModel(assignmentHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        if (allAssignments.size() != 0) {
            for (int i = 0; i < allAssignments.size(); i++) {
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
                    if (targetAssignment != null) {
                        gs.addDeletedAssignment(targetAssignment);
                    }

                    //remove the entry in the table
                    assignmentModel.removeRow(assignmentTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected assignment deleted successfully");

                } else {
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
                if (checkVals()) {
                    if (selected != -1) {
                        String assignmentName = assignmentModel.getValueAt(selected, 0).toString();
                        Assignment currentAssignment = category.getAssignment(assignmentName);
                        new GradingFrame(gs, course, category, currentAssignment);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(source, "Please select a row.");
                    }
                }
            }
        });

    }

}
