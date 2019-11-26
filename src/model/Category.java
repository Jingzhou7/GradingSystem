package model;

public class Category {
    private String categoryName;
    private double weight;


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
