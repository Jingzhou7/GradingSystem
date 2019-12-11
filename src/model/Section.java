package model;

import java.util.ArrayList;

public class Section extends Course {
    private int sectionId;

    public Section(int sectionIndex, String courseName) {
        super(courseName);
        setCourseName(courseName + " Section " + sectionId);
        sectionId = sectionIndex;
    }

    public Section(String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students, int sid){
        super(semester, courseName, categories, students);
        this.sectionId = sid;
        setCourseName(courseName + " Section " + sectionId);
    }

    public Section(int courseid, String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students, int sid) {
        super(courseid, semester, courseName, categories, students);
        this.sectionId = sid;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
