package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassFrame extends JFrame {

    private JPanel mainPanel;
    private JButton backBtn;
    private JButton addClassButton;
    private JButton selectSemesterButton;
    private JButton removeClassButton;
    private JButton modifyButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JPanel ButtonPanel;
    private JPanel OptionPanel;

    public ClassFrame() {
        setName("Classes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        addActiveComponent();

    }

    private void addActiveComponent() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginFrame lf = new LoginFrame();
                lf.setVisible(true);
                dispose();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryFrame catf = new CategoryFrame();
                catf.pack();
                catf.setLocationRelativeTo(null);
                catf.setVisible(true);
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
        table1 = new JTable(model);

        removeClassButton = new JButton("Remove Class");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if(table1.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(table1.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                }
            }
        });
    }
}