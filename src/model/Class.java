package model;

import java.util.List;

public class Class {
    private String className;
    private String classId;
    private List<Section> sections;
    private List<Category> categories;
    private List<Assignment> assignments;
    private String semester;

    //no-arg constructor
    public Class() {
        className = "CS591";

    }
    //constructor
    public Class(String className, String classId, List<Section> sections, List<Category> categories, List<Assignment> assignments, String semester) {
        this.className = className;
        this.classId = classId;
        this.sections = sections;
        this.categories = categories;
        this.assignments = assignments;
        this.semester = semester;
    }



}
