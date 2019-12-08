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
    private JTable table1;
    private JComboBox semesterCB;
    private JPanel ButtonPanel;
    private JPanel OptionPanel;

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

        semesterCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //todo: based on the selected item in the comboBox, reset the table

            }
        });

        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddCourseFrame(gs);
                dispose();

            }
        });



    }




    private void createUIComponents() {

        String [] header={"Class ID", "Class Name", "Semester", "Student Count"};
        ArrayList<Course> allCourses = gs.getAllCourses();

        DefaultTableModel model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(allCourses.size() != 0) {
            for(int i = 0;i < allCourses.size();i++) {
                Object[] obj = {allCourses.get(i).getCourseIndex(), allCourses.get(i).getCourseName(), allCourses.get(i).getSemester(), allCourses.get(i).getStudents().size()+1};
                model.addRow(obj);
            }
        }


        table1 = new JTable(model);


        removeClassButton = new JButton("Remove Class");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for selected row first
                JButton source = (JButton) e.getSource();
                int selected = table1.getSelectedRow();
                if (selected != -1) {

                    //remove from the List of classes
                    int courseIndex = Integer.parseInt(model.getValueAt(selected, 0).toString());
                    Course targetCourse = gs.getCourse(courseIndex);
                    gs.getAllCourses().remove(targetCourse);

                    //remove the entry in the table
                    model.removeRow(table1.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                }
                else{
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });


        modifyButton = new JButton("Modify Class");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = table1.getSelectedRow();
                if (selected != -1) {
                    int index = (int) model.getValueAt(selected, 0);
                    Course currentCourse = gs.getCourse(index);
                    new CourseDetailFrame(gs, currentCourse);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });


    }
}
