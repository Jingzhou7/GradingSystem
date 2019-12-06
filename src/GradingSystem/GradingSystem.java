package GradingSystem;

import model.Assignment;
import model.Category;
import model.Student;
import model.Course;
import ui.CommentFrame;
import ui.LoginFrame;
import ui.StudentGradeFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the overall system that holds all information on the system
 */
public class GradingSystem {
    List<Assignment> assignments;
    List<Category> categories;
    List<Student> students;
    List<String> semesters;
    List<Course> courses;



    public GradingSystem() {
        assignments = new ArrayList<>();
        categories = new ArrayList<>();
        students = new ArrayList<>();
        semesters = new ArrayList<>();
        courses = new ArrayList<>();

        categories.add(new Category("Exam"));
        categories.add(new Category("Programming Assignment"));
        categories.add(new Category("Participation"));

    }

    public Course getCourse(int classId) {

        return courses.get(classId);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(String className, int classId, List<Category> categories, List<Assignment> assignments, String semester, List<Student> students) {
        Course newCourse = new Course(className, classId, categories, assignments, semester, students);
        courses.add(newCourse);
        return true;
    }

    public boolean removeCourse(Course oldCourse) {
        if(courses.contains(oldCourse)) {
            courses.remove(oldCourse);
            return true;
        } else
            return false; //no such class found
    }

    public boolean addCategory(String name) {
        Category newCategory = new Category(name);
        categories.add(newCategory);
        return true;
    }

    //starting point of the grading system
    public void run() {
        LoginFrame lf = new LoginFrame(this);
    }


}
