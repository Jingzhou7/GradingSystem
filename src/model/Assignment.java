package model;


import java.time.LocalDate;

public class Assignment {
    private String assignmentName;
    private double weight;
    private LocalDate releaseDate;
    private LocalDate dueDate;
    private double maxPoint;  // set the max score of the assignment for score calculation

    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
        weight = 0;
        releaseDate = LocalDate.of(2019,1,1);
        dueDate = LocalDate.of(2019,10,10);
        maxPoint = 0;
    }

    public Assignment(String assignmentName, double weight, LocalDate releaseDate, LocalDate dueDate, double maxPoint) {
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.releaseDate = releaseDate;
        this.dueDate = dueDate;
        this.maxPoint = maxPoint;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(double maxPoint) {
        this.maxPoint = maxPoint;
    }
}
