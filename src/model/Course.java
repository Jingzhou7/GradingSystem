package model;

import java.util.ArrayList;


public class Course {
    public static final String CURRENT_SEMESTER = "Fall 2019";
    private static int count;
    private int courseIndex;
    private String semester;
    private String courseName;
    private ArrayList<Category> categories;
    private ArrayList<Student> students;

    //constructor
    public Course(String courseName) {

        courseIndex = count;
        count+=1;
        setSemester(CURRENT_SEMESTER);
        setCourseName(courseName);
        categories = new ArrayList<>();
        students = new ArrayList<>();
        categories.add(new Category("Exam", 50));
        categories.add(new Category("Programming Assignment",30));
        categories.add(new Category("Participation",20));
    }


    public Course(int courseIndex, String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        this.courseIndex = courseIndex;
        this.semester = semester;
        this.courseName = courseName;
        this.categories = categories;
        this.students = students;
    }

    public Course(String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students){
        this.courseIndex = count;
        count+=1;
        this.semester = semester;
        this.courseName = courseName;
        this.categories = categories;
        this.students = students;
    }

    public static int getCount() {
        return count;
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

    public ArrayList<Category> getAllCategories() {
        return categories;
    }

    public Category getCategory(String categoryName) {
        Category res = null;
        for(Category c : categories) {
            if(c.getCategoryName().equals(categoryName)) {
                res = c;
            }
        }
        return res;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student getStudent(int studentId) {
        Student res = null;
        for(Student s : students) {
            if(s.getSid() == (studentId)) {
                res = s;
            }
        }
        return res;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean addCategory(String name) {
        Category newCategory = new Category(name);
        categories.add(newCategory);
        return true;
    }

    public static void setCount(int c) {
        count = c;
    }
}
