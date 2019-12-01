package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AssignmentFrame {
    private JButton backButton;
    private JTable table1;
    private JButton button2;
    private JButton addAssignmentButton;
    private JButton deleteAssignmentButton;
    private JButton modifyWeightsButton;
    private JPanel ButtonPanel;
    private JPanel MainPanel;
    private JComboBox comboBox1;
    private JPanel ChoicePanel;

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
