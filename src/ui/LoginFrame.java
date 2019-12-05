package ui;

import GradingSystem.GradingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {

    private GradingSystem gs;
    private JLabel lblName;
    private JLabel lblPwd;
    private JLabel lblWelcome;

    private JTextField tfName;
    private JPasswordField pfPwd;
    private JPanel pane;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton btnSignIn;
    private JButton btnSignUp;


    private final String USERNAME = "1";
    private final String PASSWORD = "1";

    public LoginFrame(GradingSystem gs) {
        super("Login");
        this.gs = gs;
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addActiveComponent();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addActiveComponent() {

        this.lblName = new JLabel("Username: ");

        this.tfName = new JTextField(10);

        this.lblPwd = new JLabel("Password: ");

        this.pfPwd = new JPasswordField(10);

        this.btnSignIn = new JButton("SignIn");
        this.btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //todo: check the user input
                // go to the next page or deny access
                JButton source = (JButton) actionEvent.getSource();
                String name = tfName.getText();
                String pwd = new String(pfPwd.getPassword());
                if(name.equals(USERNAME) && pwd.equals(PASSWORD)) {
                    CourseFrame cf = new CourseFrame(gs);
                    cf.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(source, "Wrong username or password");
                }


            }
        });

        this.pane = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First Column

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;
        this.pane.add(lblName, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        this.pane.add(lblPwd, gc);

        // Second Column

        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 1;
        this.pane.add(tfName, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        this.pane.add(pfPwd, gc);

        //Third Column
        gc.weighty = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;


        gc.gridx = 1;
        gc.gridy = 3;
        this.pane.add(btnSignIn, gc);
        this.pane.setBackground(new Color(50,115,220,30));

        lblWelcome = new JLabel("Welcome to the Grading System");


        this.bottomPanel = new JPanel();
        this.bottomPanel.setBackground(new Color(50,115,220,60));

        this.topPanel = new JPanel();
        topPanel.add(lblWelcome);
        this.bottomPanel.setBackground(new Color(50,115,220,60));

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(pane, BorderLayout.CENTER);

    }


}


