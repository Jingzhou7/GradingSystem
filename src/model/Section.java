package model;

import java.util.ArrayList;
import java.util.List;

public class Section extends Course {
    private String sectionId;

    public Section(int sectionIndex, String courseName) {
        super(sectionIndex, courseName);
        setCourseName(courseName + " Section " + sectionId);
    }

    public Section(int sectionIndex, String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        super(sectionIndex, semester, courseName, categories, students);
        setCourseName(courseName + " Section " + sectionId);
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
}
