package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static int count = 0;
    private int id;
    private String categoryName;
    private double weight;
    private List<Assignment> assignments;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.weight = 0;
        this.id = count;
        count+=1;
        assignments = new ArrayList<>();
        assignments.add(new Assignment(categoryName + " 1"));
        assignments.add(new Assignment(categoryName + " 2"));
        assignments.add(new Assignment(categoryName + " 3"));
        assignments.add(new Assignment(categoryName + " 4"));
    }
    public Category(String name, double weight) {
        this.categoryName = name;
        this.weight = weight;
        this.id = count;
        count+=1;
        assignments = new ArrayList<>();
        assignments.add(new Assignment(categoryName + " 1"));
        assignments.add(new Assignment(categoryName + " 2"));
        assignments.add(new Assignment(categoryName + " 3"));
        assignments.add(new Assignment(categoryName + " 4"));
    }
    // constructor
    public Category(String name, double weight, ArrayList<Assignment> assignments){
        this.id = count;
        count+=1;
        this.categoryName = name;
        this.weight = weight;
        this.assignments = assignments;
    }

    public Category(int id, String name, double weight, List<Assignment> assignments) {
        this.id = id;
        count+=1;
        this.categoryName = name;
        this.weight = weight;
        this.assignments = assignments;

    }

    public static int getCount() {
        return count;
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

    public static void setCount(int c) {
        count = c;
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

    public int getId() {
        return id;
    }


    public boolean containsAssignment(Assignment assignment) {
        for(Assignment a : assignments) {
            if(a.equals(assignment)) {
                return true;
            }
        }
        return false;
    }
}
