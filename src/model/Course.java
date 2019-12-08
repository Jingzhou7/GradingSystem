package model;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class Course {
    public static final String CURRENT_SEMESTER = "Fall 2019";
    private int courseIndex;
    private String semester;
    private String courseName;
    private ArrayList<Category> categories;
    private ArrayList<Student> students;

    //constructor
    public Course(int courseIndex, String courseName) {

        setCourseIndex(courseIndex);
        setSemester(CURRENT_SEMESTER);
        setCourseName(courseName);
        categories = new ArrayList<>();
        students = new ArrayList<>();
        categories.add(new Category("Exam"));
        categories.add(new Category("Programming Assignment"));
        categories.add(new Category("Participation"));
    }


    public Course(int courseIndex, String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        this.courseIndex = courseIndex;
        this.semester = semester;
        this.courseName = courseName;
        this.categories = categories;
        this.students = students;
        categories.add(new Category("Exam"));
        categories.add(new Category("Programming Assignment"));
        categories.add(new Category("Participation"));
    }

    public int getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(int courseIndex) {
        this.courseIndex = courseIndex;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean addCategory(String name) {
        Category newCategory = new Category(name);
        categories.add(newCategory);
        return true;
    }


}
