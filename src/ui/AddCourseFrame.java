package ui;

import GradingSystem.GradingSystem;
import model.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCourseFrame extends JFrame {
    private GradingSystem gs;
    private JPanel mainPanel;
    private JTextField classNameTF;
    private JLabel classNameLbl;
    private JButton confirmButton;
    private JButton cancelButton;
    private JComboBox comboBox1;
    private JLabel templeteLbl;
    private JLabel sectionLbl;
    private JCheckBox yesCheckBox;

    public AddCourseFrame(GradingSystem gs) {
        this.gs = gs;
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
                String courseName = classNameTF.getText();
                boolean createSection = yesCheckBox.isSelected();
                String templete = comboBox1.getSelectedItem().toString();
                if(templete.equals("nah")) {
                    if (!createSection) {
                        gs.addCourse(courseName);
                        dispose();
                        new CourseFrame(gs);
                    } else {
                        gs.addSection(courseName);
                        dispose();
                        new CourseFrame(gs);
                    }

                } else {
                    if (!createSection) {

                        gs.addCourseWithTemplete(courseName, templete);
                        dispose();
                        new CourseFrame(gs);
                    } else {
                        gs.addSectionWithTemplete(courseName, templete);
                        dispose();
                        new CourseFrame(gs);
                    }

                }


            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new CourseFrame(gs);
            }
        });




    }
}
