package model;

import java.util.List;

public class Category {
    private String categoryName;
    private double weight;
    private List<Assignment> assignments;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // constructor
    public Category(String name, double weight) {
        this.categoryName = name;
        this.weight = weight;
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


}
