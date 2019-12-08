package ui;

import GradingSystem.GradingSystem;
import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddStudentFrame();
            }
        });

        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        viewGradesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryFrame();
            }
        });

        deleteCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        modifyCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyCategoryFrame();
            }
        });
    }

    private void createUIComponents() {
        String [] studentHeader={"Student name", "Email", "Section"};
        String [][] studentData={{"Kaijia You", "caydenyo@bu.edu", "1"}, {"Peiqing Lu", "lupeiqing@bu.edu", "1"},  {"Jingzhou Xue", "xuejingzhou@bu.edu", "2"}, {"Harsh", "harsh@bu.edu", "3"}};
        DefaultTableModel studentModel = new DefaultTableModel(studentData,studentHeader);

        studentTable = new JTable(studentModel);

        String [] categoryHeader={"Category", "Weight(%)"};
        String [][] categoryData={{"Participation", "15"}, {"Assignment", "25"},  {"Exam", "60"}};
        DefaultTableModel categoryModel = new DefaultTableModel(categoryData,categoryHeader);


        categoryTable = new JTable(categoryModel);

    }
}
