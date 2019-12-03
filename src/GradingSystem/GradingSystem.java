package GradingSystem;

import model.Assignment;
import model.Category;
import model.Section;
import model.Student;
import model.Class;

import java.util.List;

/**
 * This is the overall system that holds all information on the system
 */
public class GradingSystem {
    List<Assignment> assignments;
    List<Category> categories;
    List<Student> students;

    public GradingSystem() {

    }


    public void addClass(String className, String classId, List<Section> sections, List<Category> categories, List<Assignment> assignments) {
        Class myClass = new Class(className, classId, sections, categories, assignments);

    }

}
