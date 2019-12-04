package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyCategoryFrame extends JFrame{
    private JPanel mainPanel;
    private JButton confirmBtn;
    private JButton backBtn;
    private JTable categoryTable;
    private JPanel checkPanel;
    private JScrollPane tablePanel;

    public ModifyCategoryFrame(){
        setName("Modify category");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String [] categoryHeader={"Category", "Weight(%)"};
        String [][] categoryData={{"Participation", "15"}, {"Assignment", "25"},  {"Exam", "60"}};
        DefaultTableModel categoryModel = new DefaultTableModel(categoryData,categoryHeader);


        categoryTable = new JTable(categoryModel);
    }
}
