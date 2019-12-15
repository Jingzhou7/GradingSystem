package ui;

import GradingSystem.GradingSystem;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseFrame extends JFrame {

    private GradingSystem gs;
    private JPanel mainPanel;
    private JButton backBtn;
    private JButton addClassButton;
    private JButton removeClassButton;
    private JButton modifyButton;
    private JTable courseTable;
    private JPanel ButtonPanel;
    private JPanel OptionPanel;
    private JLabel greetingLbl;
    private JButton changePasswordButton;
    private JButton changeSemesterButton;
    private DefaultTableModel courseModel;

    public CourseFrame(GradingSystem gs) {

        this.gs = gs;

        setName("All Courses");

        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        addActiveComponent();

    }

    private void addActiveComponent() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new LoginFrame(gs);
                dispose();
            }
        });


        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddCourseFrame(gs);
                dispose();

            }
        });

        changeSemesterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                String name = JOptionPane.showInputDialog(source, "Set Semester: ");
                if (name != null) {
                    gs.setCurrent_semester(name);
                }

                new CourseFrame(gs);
                dispose();
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                String name = JOptionPane.showInputDialog(source, "Set Password: ");
                if (name != null) {
                    gs.setPASSWORD(name);
                }
            }
        });

    }


    private void createUIComponents() {

        String[] header = {"Class ID", "Class Name", "Semester", "Student Count"};
        ArrayList<Course> currentSemesterCourses = gs.getCoursesBySemester(gs.getCurrent_semester());
        courseModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        if (currentSemesterCourses.size() != 0) {
            for (int i = 0; i < currentSemesterCourses.size(); i++) {
                Object[] obj = {currentSemesterCourses.get(i).getCourseIndex(), currentSemesterCourses.get(i).getCourseName(), currentSemesterCourses.get(i).getSemester(), currentSemesterCourses.get(i).getAllStudents().size()};
                courseModel.addRow(obj);

            }
        }

        courseTable = new JTable(courseModel);


        removeClassButton = new JButton("Remove Class");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for selected row first
                JButton source = (JButton) e.getSource();
                int selected = courseTable.getSelectedRow();
                if (selected != -1) {

                    //remove from the List of classes
                    int courseIndex = Integer.parseInt(courseModel.getValueAt(selected, 0).toString());
                    Course targetCourse = gs.getCourse(courseIndex);
                    gs.addDeletedCourse(targetCourse);
                    gs.getAllCourses().remove(targetCourse);

                    //remove the entry in the table
                    courseModel.removeRow(courseTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });


        modifyButton = new JButton("Modify Class");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = courseTable.getSelectedRow();
                if (selected != -1) {
                    int index = (int) courseModel.getValueAt(selected, 0);
                    Course currentCourse = gs.getCourse(index);
                    new CourseDetailFrame(gs, currentCourse);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });


    }

}
