package ui;

import model.Course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField classNameTF;
    private JLabel classNameLbl;
    private JButton confirmButton;
    private JButton cancelButton;

    public AddClassFrame() {
        setName("Add Class frame");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        addActiveComponent();


    }

    private void addActiveComponent() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //todo: confirm adding a class
                // use the information filled to create a class object and add to the classes list

                Course newCourse = new Course();


            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });




    }
}
