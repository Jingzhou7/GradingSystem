package model;

import java.util.Date;

public class Assignment {
    private String assignmentName;
    private double weight;
    private Date releaseDate;
    private Date dueDate;
    private double maxPoint;  // set the max score of the assignment for score calculation

    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
    }


}
