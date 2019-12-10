package GradingSystem;

import model.*;
import ui.LoginFrame;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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

    private String USERNAME = "1";
    private String PASSWORD = "1";


    public GradingSystem() {

        semesters = getUniqueSemesters();
        courses = getCourses();
//        semesters.add(FALL_2019);
//        semesters.add(SPRING_2019);
//        semesters.add(FALL_2018);
//        semesters.add(SPRING_2018);
//        importPreviousData();

        String[] temp = getUser();
        USERNAME = temp[0];
        PASSWORD = temp[1];

        Assignment.setCount();
        Category.setCount();
        Course.setCount();
        Grade.setCount();
        Student.setCount();
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


    public String[] getUser() {
        //just for testing
        String sql = "select * from login";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        String username = null;
        String password = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return new String[]{username, password};
    }

    public ArrayList<String> getUniqueSemesters(){
        String sql = "select distinct semester from course";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<String> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String semester = rs.getString("semester");
                all.add(semester);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    public ArrayList<Course> getCourses(){
        String sql = "select * from course";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Course> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("courseid");
                String semester = rs.getString("semester");
                String name = rs.getString("name");
                ArrayList<Category> categories = getCategories(id);
                ArrayList<Student> students = getStudents(id);
                Integer sid = (Integer) rs.getObject("sectionid");
                Course a = null;
                if (sid == null)
                    a = new Course(id, semester, name, categories, students);
                else
                    a = new Section(id, semester, name, categories, students, sid);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    public ArrayList<Category> getCategories(int cid){
        String sql = "select * from category where courseid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Category> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("categoryid");
                double weight = rs.getDouble("weight");
                String name = rs.getString("name");
                ArrayList<Assignment> assignments = getAssignments(id);
                Category a = new Category(id, name, weight, assignments);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    public ArrayList<Assignment> getAssignments(int cid){
        String sql = "select * from assignment where categoryid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Assignment> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("assignmentid");
                String name = rs.getString("name");
                double weight = rs.getDouble("weight");
                Date rdate = rs.getDate("releasedate");
                Date ddate = rs.getDate("duedate");
                double points = rs.getDouble("maxpoints");
                Assignment a = new Assignment(id, name, weight, rdate, ddate, points);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    public ArrayList<Student> getStudents(int cid){
        String sql = "select * from student where courseid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Student> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentid");
                String fname = rs.getString("fname");
                String mname = rs.getString("mname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                ArrayList<Double> bonuspoints = getPoints(id);
                ArrayList<String> comments = getComments(id);
                ArrayList<Grade> grades = getGrades(id);

                Student a = new Student(new Name(fname, mname, lname), email, id, comments, bonuspoints, grades);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    private ArrayList<Grade> getGrades(int sid) {
        String sql = "select * from grade where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Grade> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("gradeid");
                double rawscore = rs.getDouble("rawscore");
                double bonusp = rs.getDouble("bonuspoints");
                String lettergrade = rs.getString("lettergrade");
                String comment = rs.getString("comment");
                Assignment a = getAssignment(rs.getInt("assignmentid"));
                Grade b = new Grade(id, a, rawscore, bonusp, lettergrade, comment);
                all.add(b);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    private Assignment getAssignment(int aid) {
        String sql = "select * from assignment where assignmentid = " + aid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Assignment a = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("assignmentid");
                String name = rs.getString("name");
                double weight = rs.getDouble("weight");
                Date rdate = rs.getDate("releasedate");
                Date ddate = rs.getDate("duedate");
                double points = rs.getDouble("maxpoints");
                a = new Assignment(id, name, weight, rdate, ddate, points);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return a;
    }

    private ArrayList<String> getComments(int sid) {
        String sql = "select * from studentcomments where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<String> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String a = rs.getString("comment");
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    private ArrayList<Double> getPoints(int sid) {
        String sql = "select * from bonuspoints where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Double> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Double a = (Double) rs.getDouble("points");
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    public String getUSERNAME(){
        return USERNAME;
    }

    public String getPASSWORD(){
        return PASSWORD;
    }

    public void save(){

    }

}
