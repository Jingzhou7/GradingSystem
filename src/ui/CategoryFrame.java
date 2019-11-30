package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryFrame extends JFrame{

    private JPanel mainPanel;
    private JButton addClassButton;
    private JButton selectSemesterButton;
    private JButton removeClassButton;
    private JButton modifyButton;
    private JComboBox comboBox1;
    private JButton backBtn;
    private JTable table1;
    private JPanel myPanel;

    public CategoryFrame() {
        setName("Category");
        setVisible(false);
        setContentPane(myPanel);
        addActiveComponent();
    }

    private void addActiveComponent() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClassFrame cf = new ClassFrame();
                cf.setLocationRelativeTo(null);
                cf.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        String [] header={"Class Name","Section", "Semester", "Student Count"};
        String [][] data={{"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"},
                {"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"}};
        DefaultTableModel model = new DefaultTableModel(data, header);
        table1 = new JTable(data, header);
    }
}
