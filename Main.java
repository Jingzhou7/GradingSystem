package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Grading Program");
        frame.setLayout(new GridLayout());

        JPanel j = new JPanel();

        GridLayout layout = new GridLayout(5,1);
        layout.setHgap(10);
        layout.setVgap(10);
        j.setLayout(layout);
        System.out.println(j.getLayout());
        j.add(new JButton("Back"));
        j.add(new JButton("Add"));
        j.add(new JButton("Remove"));
        j.add(new JButton("Select Semester"));
        j.add(new JButton("Modify"));

        JPanel j2 = new JPanel();
        GridLayout layout2 = new GridLayout(1,1);
        j2.setLayout(layout2);


        String [] header={"Class Name","Section", "Semester", "Student Count"};
        String [][] data={{"CS101", "S1", "FALL19", "100"}, {"CS330", "S1", "FALL19", "85"},  {"CS591", "S1", "FALL19", "30"}, {"CS591", "S2", "FALL19", "30"}};
        DefaultTableModel model = new DefaultTableModel(data,header);

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        j2.add(new JScrollPane(table));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, j, j2);

        frame.setContentPane(splitPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
