package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {

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



    public LoginFrame() {
        super("Login");
        setSize(400,200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(false);
        addActiveComponent();
        setLocationRelativeTo(null);
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
                ClassFrame cf = new ClassFrame();
                cf.setLocationRelativeTo(null);
                cf.setVisible(true);
                dispose();

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



    public static void main(String[] args) {
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
    }

}


