package ui;

import GradingSystem.GradingSystem;
import model.Category;
import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailFrame extends JFrame{
    private GradingSystem gs;
    private Course course;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton importStudentsBtn;
    private JButton addStudentBtn;
    private JButton deleteStudentBtn;
    private JButton viewGradesBtn;
    private JButton addCategoryBtn;
    private JButton deleteCategoryBtn;
    private JButton modifyCategoryBtn;
    private JTable studentTable;
    private JComboBox comboBox1;
    private JPanel ButtonPanel;
    private JPanel ChoicePanel;
    private JTable categoryTable;
    private JButton modifyAssignmentsButton;
    private JScrollPane studentScroll;
    private JScrollPane categoryScroll;
    private DefaultTableModel categoryModel;


    public CourseDetailFrame(GradingSystem gs, Course course) {
        this.gs = gs;
        this.course = course;

        setName("Course detail frame");

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        addActiveComponent();

        categoryScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                double totalWeight = 0;
                if (categoryTable.isEditing()){
                    categoryTable.getCellEditor().stopCellEditing();
                }
                for (int i = 0;i < course.getAllCategories().size();i++){
                    if (Double.parseDouble(categoryModel.getValueAt(i, 1).toString()) == 0){
                        Object[] options ={ "ok" };
                        JOptionPane.showOptionDialog(null, "The weight of category cannot be 0", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        break;
                    }
                }
                for (int i = 0;i < course.getAllCategories().size();i++){
                    course.getAllCategories().get(i).setCategoryName(categoryModel.getValueAt(i, 0).toString());
                    course.getAllCategories().get(i).setWeight(Double.parseDouble(categoryModel.getValueAt(i, 1).toString()));
                    totalWeight += Double.parseDouble(categoryModel.getValueAt(i, 1).toString());
                }
                if (totalWeight != 100){
                    Object[] options ={ "ok" };
                    JOptionPane.showOptionDialog(null, "The total weight of all categories is not up to 100%", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
            }
        });
    }

    private void addActiveComponent() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseFrame(gs);
                dispose();
            }
        });

        importStudentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showDialog(new JLabel(), "select");
                File file = chooser.getSelectedFile();
                String excelFileName = file.getAbsoluteFile().toString();
                System.out.println(excelFileName);
                // read Excel
                List<Student> readResult = MyExcelUtil.readExcel(excelFileName);
                for (int i = 0;i < readResult.size();i++){
                    System.out.println(readResult.get(i).toString());
                }
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddStudentFrame(gs, course);
                dispose();
            }
        });



        viewGradesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentGradeFrame(gs, course);
                dispose();
            }
        });

        addCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] obj = {"please enter category name", 0};
                categoryModel.addRow(obj);
                course.addCategory("null");
            }
        });



        modifyCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyCategoryFrame(gs, course);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        String [] studentHeader={"Student name", "Email", "Student ID"};
        ArrayList<Student> allStudents = course.getAllStudents();
        DefaultTableModel studentModel = new DefaultTableModel(studentHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(allStudents.size() != 0) {
            for(int i = 0;i < allStudents.size();i++) {
                Object[] obj = {allStudents.get(i).getName(), allStudents.get(i).getEmail(), allStudents.get(i).getSid()};
                studentModel.addRow(obj);
            }
        }
        studentTable = new JTable(studentModel);

        String [] categoryHeader={"Category", "Weight(%)"};

        categoryModel = new DefaultTableModel(categoryHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        ArrayList<Category> allCategories = course.getAllCategories();
        if(allCategories.size() != 0) {
            for(int i = 0;i < allCategories.size();i++) {
                Object[] obj = {allCategories.get(i).getCategoryName(), allCategories.get(i).getWeight()};
                categoryModel.addRow(obj);
            }
        }
        categoryTable = new JTable(categoryModel);

        deleteStudentBtn = new JButton("Delete Student");
        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int selected = studentTable.getSelectedRow();
                if (selected != -1) {

                    //remove from the List of classes
                    int studentId = Integer.parseInt(studentModel.getValueAt(selected, 2).toString());
                    Student targetStudent = course.getStudent(studentId);
                    course.getAllStudents().remove(targetStudent);

                    //remove the entry in the table
                    studentModel.removeRow(studentTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected student deleted successfully");

                }
                else{
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        deleteCategoryBtn = new JButton("Delete Category");
        deleteCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int selected = categoryTable.getSelectedRow();
                if (selected != -1) {

                    //remove from the List of classes
                    String categoryName = categoryModel.getValueAt(selected, 0).toString();
                    Category targetcategory = course.getCategory(categoryName);
                    course.getAllStudents().remove(targetcategory);

                    //remove the entry in the table
                    categoryModel.removeRow(categoryTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected category deleted successfully");

                }
                else{
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

        modifyAssignmentsButton = new JButton("Modify Assignments");
        modifyAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton source = (JButton) actionEvent.getSource();
                int selected = categoryTable.getSelectedRow();
                if (selected != -1) {
                    String categoryName = categoryModel.getValueAt(selected, 0).toString();
                    Category currentCategory = course.getCategory(categoryName);
                    new AssignmentFrame(gs, course, currentCategory);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }
            }
        });

    }
}
