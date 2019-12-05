package ui;

import GradingSystem.GradingSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassFrame extends JFrame {

    private GradingSystem gs;
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

    public ClassFrame(GradingSystem gs) {
        setName("Classes");
        this.gs = gs;
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

        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new addClassFrame();

            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CourseDetailFrame(gs);
                dispose();
            }
        });

    }




    private void createUIComponents() {

        String [] header={"Class ID", "Class Name","Section", "Semester", "Student Count"};
        String [][] data={{"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"},
                {"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"},
                {"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"},
                {"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"},
                {"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"},
                {"1", "CS101", "S1", "FALL19", "100"}, {"1", "CS330", "S1", "FALL19", "85"},  {"1", "CS591", "S1", "FALL19", "30"}, {"1", "CS591", "S2", "FALL19", "30"}};

        DefaultTableModel model = new DefaultTableModel(data, header);
        table1 = new JTable(model);

        removeClassButton = new JButton("Remove Class");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for selected row first
                JButton source = (JButton) e.getSource();
                int selected = table1.getSelectedRow();
                if (selected != -1) {
                    Integer classID = (Integer) model.getValueAt(selected, 0);
                    model.Class targetClass = gs.getClass(classID);



//                    boolean checkBalance = checkEnough(bc.getAccount(account_index).getBalance(), b.getCloseAccountFee());
//                    if (checkBalance) {
//                        Transaction check = bc.removeAccount(account_index, b.getCloseAccountFee());
//                        if (check != null) {
//                            JOptionPane.showMessageDialog(source, "Account deleted");
//                            b.recentTransactions.put(b.getTransactionCounter(), check);
//                            b.setTransactionCounter(b.getTransactionCounter() + 1);
//                            place(bc);
//                            frame.dispose();
//                        } else {
//                            JOptionPane.showMessageDialog(source, "Account did not exist or did not belong to this customer");
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(source, "Insufficient funds in account to delete. The fee is: " + b.getCloseAccountFee());
//                    }
                }
                else{
                    JOptionPane.showMessageDialog(source, "Please select a row.");
                }







                if(table1.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(table1.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                }
            }
        });
    }
}
