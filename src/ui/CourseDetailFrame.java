package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseDetailFrame extends JFrame {
    private JButton importStudentsBtn;
    private JButton addStudentBtn;
    private JButton deleteStudentBtn;
    private JButton showGradesBtn;
    private JButton addCategoryBtn;
    private JButton deleteCategoryBtn;
    private JButton backBtn;
    private JTable studentTable;
    private JTable categoryTable;

    public CourseDetailFrame(){
        super("Course detail page");
        setLayout(new GridLayout());
        setSize(400,200);
        addComponent();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void addComponent(){
        JPanel j = new JPanel();

        GridLayout layout = new GridLayout(7,1);
        layout.setHgap(5);
        layout.setVgap(5);
        j.setLayout(layout);
        System.out.println(j.getLayout());
        importStudentsBtn = new JButton("Import students");
        addStudentBtn = new JButton("Add student");
        deleteStudentBtn = new JButton("Delete students");
        showGradesBtn = new JButton("Show grades");
        addCategoryBtn = new JButton("Add category");
        deleteCategoryBtn = new JButton("Delete category");
        backBtn = new JButton("Back");

        j.add(importStudentsBtn);
        j.add(addStudentBtn);
        j.add(deleteStudentBtn);
        j.add(showGradesBtn);
        j.add(addCategoryBtn);
        j.add(deleteCategoryBtn);
        j.add(backBtn);

        JPanel j2 = new JPanel();
        GridLayout layout2 = new GridLayout(2,1);
        j2.setLayout(layout2);


        String [] studentHeader={"Student name", "Email", "Section"};
        String [][] studentData={{"Kaijia You", "caydenyo@bu.edu", "1"}, {"Peiqing Lu", "lupeiqing@bu.edu", "1"},  {"Jingzhou Xue", "xuejingzhou@bu.edu", "2"}, {"Harsh", "harsh@bu.edu", "3"}};
        DefaultTableModel studentModel = new DefaultTableModel(studentData,studentHeader);

        studentTable = new JTable(studentModel);
        studentTable.setFillsViewportHeight(true);

        j2.add(new JScrollPane(studentTable));

        String [] categoryHeader={"Category", "Weight(%)"};
        String [][] categoryData={{"Participation", "15"}, {"Assignment", "25"},  {"Exam", "60"}};
        DefaultTableModel categoryModel = new DefaultTableModel(categoryData,categoryHeader);
//        DefaultTableModel tabelModel = new DefaultTableModel(categoryHeader, 0){
//            @Override
//            public boolean isCellEditable(int row, int column){
//                return false;
//            }
//        };

        categoryTable = new JTable(categoryModel);
        categoryTable.setFillsViewportHeight(true);

        j2.add(new JScrollPane(categoryTable));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, j, j2);
        setContentPane(splitPane);
    }
}
