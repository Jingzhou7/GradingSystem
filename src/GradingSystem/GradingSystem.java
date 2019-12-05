package GradingSystem;

import model.Assignment;
import model.Category;
import model.Section;
import model.Student;
import model.Class;
import ui.LoginFrame;

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
    List<Class> classes;



    public GradingSystem() {
        assignments = new ArrayList<>();
        categories = new ArrayList<>();
        students = new ArrayList<>();
        semesters = new ArrayList<>();
        classes = new ArrayList<>();

        categories.add(new Category("Exam"));
        categories.add(new Category("Programming Assignment"));
        categories.add(new Category("Participation"));

    }

    public Class getClass(int classId) {

        return classes.get(classId);
    }

    public boolean addClass(String className, int classId, List<Category> categories, List<Assignment> assignments, String semester, List<Student> students) {
        Class newClass = new Class(className, classId, categories, assignments, semester, students);
        classes.add(newClass);
        return true;
    }

    public boolean removeClass(Class oldClass) {
        if(classes.contains(oldClass)) {
            classes.remove(oldClass);
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
