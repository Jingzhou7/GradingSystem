package GradingSystem;

import model.*;
import ui.CommentFrame;
import ui.LoginFrame;
import ui.StudentGradeFrame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the overall system that holds all information on the system
 */
public class GradingSystem {

    public static final String FALL_2019 = "Fall 2019";
    public static final String SPRING_2019 = "Spring 2019";
    public static final String FALL_2018 = "Fall 2018";
    public static final String SPRING_2018 = "Spring 2018";
    ArrayList<String> semesters;
    ArrayList<Course> courses;



    public GradingSystem() {

        semesters = new ArrayList<>();
        courses = new ArrayList<>();
        semesters.add(FALL_2019);
        semesters.add(SPRING_2019);
        semesters.add(FALL_2018);
        semesters.add(SPRING_2018);
        importPreviousData();

    }

    private void importPreviousData() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(new Name("Alice")));
        students.add(new Student(new Name("Bob")));
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Exam",50));
        categories.add(new Category("Participation",10));
        categories.add(new Category("Homework", 15));
        categories.add(new Category("Programing Assignment",25));

        Course course1 = new Course(1, FALL_2019, "CS591", categories, students);
        Course course2 = new Course(2, FALL_2019, "CS112 S1", categories, students);
        Course course3 = new Course(3, FALL_2019, "CS112 S2", categories, students);
        Course course4 = new Course(4, FALL_2019, "CS112 S3", categories, students);

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);


    }

    public Course getCourse(int courseIndex) {
        Course res = null;
        for(Course c : courses) {
            if(c.getCourseIndex() == courseIndex) {
                res = c;
            }
        }
        return res;
    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public boolean addCourse(String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        int index = courses.size() + 1;
        Course newCourse = new Course(index, semester, courseName, categories, students);
        courses.add(newCourse);
        return true;
    }

    public boolean addCourse(String courseName) {
        int index = courses.size() + 1;
        Course newCourse = new Course(index, courseName);
        courses.add(newCourse);
        return true;
    }

    public boolean removeCourse(Course oldCourse) {
        if(courses.contains(oldCourse)) {
            courses.remove(oldCourse);
            return true;
        } else{
            return false; //no such class found
        }

    }



    //starting point of the grading system
    public void run() {
        LoginFrame lf = new LoginFrame(this);
    }


}
