package ui;

import GradingSystem.GradingSystem;
import model.Category;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class ModifyCategoryFrame extends JFrame{
    private JPanel mainPanel;
    private JButton confirmBtn;
    private JButton backBtn;
    private JTable categoryTable;
    private JPanel checkPanel;
    private JScrollPane tablePanel;
    private Course course;
    private DefaultTableModel categoryModel;

    public ModifyCategoryFrame(GradingSystem gs, Course course){
        this.course = course;
        setName("Modify category");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (categoryTable.isEditing()){
                    categoryTable.getCellEditor().stopCellEditing();
                }
                for (int i = 0;i < course.getAllCategories().size();i++){
                    course.getAllCategories().get(i).setWeight(Double.parseDouble(categoryModel.getValueAt(i, 1).toString()));
                }
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseDetailFrame(gs, course);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String [] categoryHeader={"Category", "Weight(%)"};
        ArrayList<Category> allCategories = course.getAllCategories();
        categoryModel = new DefaultTableModel(categoryHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1){
                    return true;
                }else {
                    return false;
                }
            }
        };
        if(allCategories.size() != 0) {
            for(int i = 0;i < allCategories.size();i++) {
                Object[] obj = {allCategories.get(i).getCategoryName(), allCategories.get(i).getWeight()};
                categoryModel.addRow(obj);
            }
        }
        categoryTable = new JTable(categoryModel);
//        categoryTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }
}
