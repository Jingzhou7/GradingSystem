package model;

import java.util.List;

public class Section {
    private List<Student> students;
    private String sectionId;

    public Section(List<Student> students, String sectionId) {
        this.students = students;
        this.sectionId = sectionId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
}
