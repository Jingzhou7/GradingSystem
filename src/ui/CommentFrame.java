package ui;

import GradingSystem.GradingSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentFrame extends JFrame{
    private JButton backButton;
    private JTable table1;
    private JTable table2;
    private JPanel mainPanel;

    public CommentFrame(GradingSystem gs) throws HeadlessException {
        setName("Comment");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String [] header={"                                                     Overall Comment"};
        String [][] data={{"Hardworking student"}};
        DefaultTableModel model = new DefaultTableModel(data, header);
        table1 = new JTable(data, header);
        String [] header1={"Assignment","Comment"};
        String [][] data1={{"HW1", "late submission"}};
        DefaultTableModel model1 = new DefaultTableModel(data, header);
        table2 = new JTable(data1, header1);
    }
}
