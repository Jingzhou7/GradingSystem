package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private double weight;
    private List<Assignment> assignments;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.weight = 0;
        assignments = new ArrayList<>();
        assignments.add(new Assignment(categoryName + " 1"));
        assignments.add(new Assignment(categoryName + " 2"));
        assignments.add(new Assignment(categoryName + " 3"));
        assignments.add(new Assignment(categoryName + " 4"));
    }
    public Category(String name, double weight) {
        this.categoryName = name;
        this.weight = weight;
        assignments = new ArrayList<>();
        assignments.add(new Assignment(categoryName + " 1"));
        assignments.add(new Assignment(categoryName + " 2"));
        assignments.add(new Assignment(categoryName + " 3"));
        assignments.add(new Assignment(categoryName + " 4"));
    }
    // constructor
    public Category(String name, double weight, List<Assignment> assignments) {
        this.categoryName = name;
        this.weight = weight;
        this.assignments = assignments;

    }

    // getters and setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    //methods
    public Category addCategory(String name) {
        Category res = new Category(name, 0);
        return res;
    }


    public List<Assignment> getAllAssignments() {
        return assignments;
    }

    public Assignment getAssignment(String assignmentName) {
        Assignment res = null;
        for(Assignment a : assignments) {
            if(a.getAssignmentName().equals(assignmentName)) {
                res = a;
            }
        }
        return res;
    }
}
