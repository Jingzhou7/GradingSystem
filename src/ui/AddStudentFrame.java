package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentFrame extends JFrame{

    private JPanel mainPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField middleNameTextField;
    private JTextField sectionTextField;
    private JButton clearAllBtn;
    private JButton confirmBtn;
    private JButton backBtn;
    private JPanel inforPanel;
    private JPanel checkPanel;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel middleNameLbl;
    private JLabel sectionLbl;

    public AddStudentFrame() {
        setName("Add student frame");
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        addActiveComponent();
    }

    private void addActiveComponent() {
        clearAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                middleNameTextField.setText("");
                sectionTextField.setText("");

            }
        });
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
}
