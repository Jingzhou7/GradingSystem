package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField categoryNameTextField;
    private JButton confirmBtn;
    private JButton backBtn;
    private JPanel inforPanel;
    private JPanel checkPanel;
    private JLabel categoryNameLbl;

    public AddCategoryFrame(){
        setName("Add category");
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


}
