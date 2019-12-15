package model;


import java.sql.Date;

public class Assignment{
    private static int count = 0;
    private int id;
    private String assignmentName;
    private double weight;
    private Date releaseDate;
    private Date dueDate;
    private double maxPoint;  // set the max score of the assignment for score calculation

    public Assignment(String assignmentName) {
        this.id = ++count;
        this.assignmentName = assignmentName;
        weight = 0;
        releaseDate = new Date(119,1,1);
        dueDate = new Date(119,2,2);
        maxPoint = 100;
    }

    public Assignment(int id, String assignmentName, double weight, Date releaseDate, Date dueDate, double maxPoint) {
        this.id = id;
        count++;
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.releaseDate = releaseDate;
        this.dueDate = dueDate;
        this.maxPoint = maxPoint;
    }

    public static int getCount() {
        return count;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(double maxPoint) {
        this.maxPoint = maxPoint;
    }

    public static void setCount(int c) {
        count = c;
    }

    public int getId() {
        return id;
    }


    public boolean equals(Assignment assignment) {
        return assignment.getAssignmentName().equals(this.assignmentName);
    }
}
