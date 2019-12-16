package GradingSystem;

import db.SQLConnection;
import model.*;
import ui.LoginFrame;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the overall system that holds all information on the system
 */
public class GradingSystem {

    private String current_semester = "Fall 2019";

    ArrayList<String> semesters;
    ArrayList<Course> courses;
    ArrayList<Category> cats;

    private String USERNAME = "1";
    private String PASSWORD = "1";

    private ArrayList<Integer> deletedAssignments;
    private ArrayList<Integer> deletedBonusPoints;
    private ArrayList<Integer> deletedCategory;
    private ArrayList<Integer> deletedCourses;
    private ArrayList<Integer> deletedGrades;
    private ArrayList<Integer> deletedStudents;
    private ArrayList<Integer> deletedComments;

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

        Assignment.setCount(Integer.parseInt(temp[4]));
        Category.setCount(Integer.parseInt(temp[3]));
        Course.setCount(Integer.parseInt(temp[2]));
        Grade.setCount(Integer.parseInt(temp[8]));
        Student.setCount(Integer.parseInt(temp[5]));
        BonusPoints.setCount(Integer.parseInt(temp[6]));
        Comment.setCount(Integer.parseInt(temp[7]));

        deletedAssignments = new ArrayList<>();
        deletedBonusPoints = new ArrayList<>();
        deletedCategory = new ArrayList<>();
        deletedComments = new ArrayList<>();
        deletedCourses = new ArrayList<>();
        deletedGrades = new ArrayList<>();
        deletedStudents = new ArrayList<>();
        //importPreviousData();
        cats = new ArrayList<>();
//        cats.add(new Category("Exam",50));
//        cats.add(new Category("Participation",10));
//        cats.add(new Category("Homework", 15));
//        cats.add(new Category("Programing Assignment",25));
    }

    private void importPreviousData() {
//        ArrayList<Student> students = new ArrayList<>();
//        students.add(new Student(new Name("Alice")));
//        students.add(new Student(new Name("Bob")));


//        Course course1 = new Course(1, FALL_2019, "CS591", categories, students);
//        Course course2 = new Course(2, FALL_2019, "CS112 S1", categories, students);
//        Course course3 = new Course(3, FALL_2019, "CS112 S2", categories, students);
//        Course course4 = new Course(4, FALL_2019, "CS112 S3", categories, students);

//        courses.add(course1);
//        courses.add(course2);
//        courses.add(course3);
//        courses.add(course4);


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

    public ArrayList<Course> getCoursesBySemester(String semester) {
        ArrayList<Course> res = new ArrayList<>();
        for(Course c : courses) {
            if(c.getSemester().equals(semester)) {
                res.add(c);
            }
        }
        return res;
    }

    public boolean addCourse(String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        int index = courses.size() + 1;
        Course newCourse = new Course(index, semester, courseName, cats, students);
        courses.add(newCourse);
        return true;
    }

    public boolean addCourse(String courseName) {
        Course newCourse = new Course(current_semester, courseName, new ArrayList<Category>(), new ArrayList<Student>());
        courses.add(newCourse);
        return true;
    }
    public boolean addSection(String sectionName) {
        //check existed sections, and increment the section id
        int id = 0;
        for(Course c : courses) {
            if(c.getCourseName().contains(sectionName)) {
                id+=1;
            }
        }
        Course newSection = new Section(current_semester, sectionName, new ArrayList<Category>(), new ArrayList<Student>(), id);
        courses.add(newSection);
        return true;
    }

    public boolean addCourseWithTemplete(String courseName, String courseTemplete) {
        //Course templeteCourse = new Course("tmp");
        for(Course c : courses) {
            if(c.getCourseName().equals(courseTemplete)) {
                ArrayList<Category> templeteCategories = c.getAllCategories();
                ArrayList<Category> temp = new ArrayList<Category>();
                for (Category cc:templeteCategories){
                    ArrayList<Assignment> tempA = new ArrayList<Assignment>();
                    for(Assignment a:cc.getAllAssignments()){
                        Assignment copy = new Assignment(a.getAssignmentName());
                        copy.setWeight(a.getWeight());
                        copy.setReleaseDate(a.getReleaseDate());
                        copy.setDueDate(a.getDueDate());
                        copy.setMaxPoint(a.getMaxPoint());
                        tempA.add(copy);
                    }
                    Category copyC = new Category(cc.getCategoryName(), cc.getWeight(), tempA);
                    temp.add(copyC);
                }
                Course newCourse = new Course(current_semester, courseName, temp, new ArrayList<Student>());
                courses.add(newCourse);
                return true;
            }
        }
        return false;
    }

    public boolean addSectionWithTemplete(String sectionName, String sectionTemplete) {
        //check existed sections, and increment the section id
        //Section templeteSection = new Section(1, "tmp");
        ArrayList<Category> templeteCategories = new ArrayList<Category>();
        int id = 0;
        for(Course c : courses) {
            if(c.getCourseName().contains(sectionName)) {
                id+=1;
            }
            if(c.getCourseName().contains(sectionTemplete)) {
                templeteCategories = c.getAllCategories();
            }
        }
        Course newSection = new Section(current_semester, sectionName, templeteCategories, new ArrayList<Student>(), id);
        courses.add(newSection);
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
        String n1 = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        String n7 = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
                n1 = String.valueOf(rs.getInt("numcourses"));
                n2 = String.valueOf(rs.getInt("numcategories"));
                n3 = String.valueOf(rs.getInt("numassignment"));
                n4 = String.valueOf(rs.getInt("numstudent"));
                n5 = String.valueOf(rs.getInt("numbonuspoints"));
                n6 = String.valueOf(rs.getInt("numstudentcomments"));
                n7 = String.valueOf(rs.getInt("numgrades"));
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any getUser()");
        }
        return new String[]{username, password, n1, n2, n3, n4, n5, n6, n7};
    }

    public boolean updateUser(String p, int[] counts){
        //System.out.println(counts[0]);
        String sql = "update login set password = '" + PASSWORD + "', numcourses = " + counts[0] +  ", numcategories = " + counts[1] + ", numassignment = " + counts[2] + ", numstudent = " + counts[3] + ", numbonuspoints = " + counts[4] + ", numstudentcomments = " + counts[5] + ", numgrades =" + counts[6] + " where username = '" + USERNAME + "'";

       //String sql = "update login set password = '" + PASSWORD + "', numcourses = " + counts[0] + " where username = '" + USERNAME + "'";

        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to change customer's password!");
            return false;
        }
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
            System.out.println("don't get any getUniqueSemesters()");
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

                if (sid == null)
                    all.add(new Course(id, semester, name, categories, students));
                else
                    all.add(new Section(id, semester, name, categories, students, sid));

            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any allCourses()");
        }
        return all;
    }

    public Course getCourseFromDB(int cid){
        String sql = "select * from course where courseid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Course a = null;
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
                if (sid == null)
                    a = new Course(id, semester, name, categories, students);
                else
                    a = new Section(id, semester, name, categories, students, sid);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any getCourseFromDB()");
        }
        return a;
    }

    public boolean insertCourse(Course c){
        boolean success = true;
        String sql = null;
        if (c instanceof Section){
            sql = "insert into course (courseid, semester, name, sectionid) values (?, ?, ?, ?)";
        }
        else {
            sql = "insert into course (courseid, semester, name) values (?, ?, ?)";
        }
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, c.getCourseIndex());
            pst.setString(2, c.getSemester());
            pst.setString(3, c.getCourseName());
            if (c instanceof Section) {
                pst.setInt(4, ((Section) c).getSectionId());
            }
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + c.getCourseName() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateCourse(Course c, int cid){
        String sql = "update course set semester = '" + c.getSemester() + "', name = '" + c.getCourseName() + "' where courseid = " + cid;
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            System.out.println("Updated course " + c.getCourseName());
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to update course " + c.getCourseName());
            return false;
        }
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
            System.out.println("don't get any getCategories()");
        }
        return all;
    }

    public Category getCategoryFromDB(int cid){
        String sql = "select * from category where categoryid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Category a = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("categoryid");
                double weight = rs.getDouble("weight");
                String name = rs.getString("name");
                ArrayList<Assignment> assignments = getAssignments(id);
                a = new Category(id, name, weight, assignments);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any getCategoryFromDB()");
        }
        return a;
    }

    public boolean insertCategory(Category c, int cid){
        boolean success = true;
        String sql = "insert into category (categoryid, courseid, weight, name) values (?, ?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, c.getId());
            pst.setInt(2, cid);
            pst.setDouble(3, c.getWeight());
            pst.setString(4, c.getCategoryName());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + c.getCategoryName() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateCategory(Category c){
        String sql = "update category set weight = " + c.getWeight() + ", name = '" + c.getCategoryName() + "' where categoryid = " + c.getId();
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to update category!");
            return false;
        }
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
            System.out.println("don't get any getAssignments()");
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
            System.out.println("don't get any getAssignment()");
        }
        return a;
    }

    public boolean insertAssignment(Assignment a, int cid){
        boolean success = true;
        String sql = "insert into assignment (assignmentid, categoryid, weight, releasedate, duedate, maxpoints, name) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, a.getId());
            pst.setInt(2, cid);
            pst.setDouble(3, a.getWeight());
            pst.setDate(4, a.getReleaseDate());
            pst.setDate(5, a.getDueDate());
            pst.setDouble(6, a.getMaxPoint());
            pst.setString(7, a.getAssignmentName());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + a.getAssignmentName() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateAssignment(Assignment a){
        String sql = "update assignment set weight = " + a.getWeight() + ", releasedate = '" + a.getReleaseDate() + "', duedate = '" + a.getDueDate()  + "', maxpoints = " + a.getMaxPoint() + ", name = '" + a.getAssignmentName() + "' where assignmentid = " + a.getId();
        System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to update assignment!");
            return false;
        }
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
                ArrayList<BonusPoints> bonuspoints = getPoints(id);
                ArrayList<Comment> comments = getComments(id);
                ArrayList<Grade> grades = getGrades(id);

                Student a = new Student(new Name(fname, mname, lname), email, id, comments, bonuspoints, grades);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any getStudents()");
        }
        return all;
    }

    public Student getStudentFromDB(int sid){
        String sql = "select * from student where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Student a = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentid");
                String fname = rs.getString("fname");
                String mname = rs.getString("mname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                ArrayList<BonusPoints> bonuspoints = getPoints(id);
                ArrayList<Comment> comments = getComments(id);
                ArrayList<Grade> grades = getGrades(id);

                a = new Student(new Name(fname, mname, lname), email, id, comments, bonuspoints, grades);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any getStudentFromDB()");
        }
        return a;
    }

    public boolean insertStudent(Student s, int cid){
        boolean success = true;
        String sql = "insert into student (studentid, courseid, fname, mname, lname, email) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, s.getId());
            pst.setInt(2, cid);
            pst.setString(3, s.getName().getFname());
            pst.setString(4, s.getName().getMname());
            pst.setString(5, s.getName().getLname());
            pst.setString(6, s.getEmail());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + s.getName() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateStudent(Student s){
        String sql = "update student set fname = '" + s.getName().getFname() + "', mname = '" + s.getName().getMname() + "', lname = '" + s.getName().getLname()  + "', email = '" + s.getEmail() +  "' where studentid = " + s.getId();
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to update student!");
            return false;
        }
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

    private ArrayList<Grade> getGradesforAssignment(int aid) {
        String sql = "select * from grade where assignmentid = " + aid;
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

    private Grade getGradeFromDB(int gid) {
        String sql = "select * from grade where gradeid = " + gid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Grade b = null;
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
                b = new Grade(id, a, rawscore, bonusp, lettergrade, comment);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return b;
    }

    public boolean insertGrade(Grade g, int sid){
        boolean success = true;
        String sql = "insert into grade (gradeid, rawscore, assignmentid, studentid, bonuspoints, lettergrade, comment) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, g.getId());
            pst.setDouble(2, g.getRawScore());
            pst.setInt(3, g.getAssignment().getId());
            pst.setInt(4, sid);
            pst.setDouble(5, g.getBonusPoint());
            pst.setString(6, g.getLetterGrade());
            pst.setString(7, g.getComment());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + g.getRawScore() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateGrade(Grade g){
        String sql = "update grade set rawscore = " + g.getRawScore() + ", bonuspoints = " + g.getBonusPoint() + ", lettergrade = '" + g.getLetterGrade() + "', comment = '" + g.getComment() +  "' where gradeid = " + g.getId();
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to change customer's password!");
            return false;
        }
    }

    private ArrayList<Comment> getComments(int sid) {
        String sql = "select * from studentcomments where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<Comment> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("commentid");
                String c = rs.getString("comment");
                Comment a = new Comment(id, c);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    private Comment getCommentFromDB(int cid){
        String sql = "select * from studentcomments where commentid = " + cid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        Comment a = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("commentid");
                String c = rs.getString("comment");
                a = new Comment(id, c);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return a;
    }

    public boolean insertComment(Comment c, int sid){
        boolean success = true;
        String sql = "insert into studentcomments (commentid, studentid, comment) values (?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, c.getId());
            pst.setInt(2, sid);
            pst.setString(3, c.getText());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + c.getText() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateComment(Comment c){
        String sql = "update studentcomments set comment = '" + c.getText()  + "' where commentid = " + c.getId();
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to change customer's password!");
            return false;
        }
    }

    private ArrayList<BonusPoints> getPoints(int sid) {
        String sql = "select * from bonuspoints where studentid = " + sid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        ArrayList<BonusPoints> all = new ArrayList<>();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("pointid");
                double v = rs.getDouble("points");
                BonusPoints a = new BonusPoints(id, v);
                all.add(a);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return all;
    }

    private BonusPoints getBonusPointFromDB(int bid){
        String sql = "select * from bonuspoints where pointid = " + bid;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        BonusPoints a = null;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("pointid");
                double c = rs.getDouble("points");
                a = new BonusPoints(id, c);
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return a;
    }

    public boolean insertBonusPoints(BonusPoints b, int sid){
        boolean success = true;
        String sql = "insert into bonuspoints (pointid, studentid, points) values (?, ?, ?)";
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.setInt(1, b.getId());
            pst.setInt(2, sid);
            pst.setDouble(3, b.getValue());
            pst.execute();
            sc.close();
        }catch(Exception e) {
            System.out.println("Failed to add " + b.getValue() + " !");
            success = false;
        }
        return success;
    }

    public boolean updateBonusPoints(BonusPoints b){
        String sql = "update bonuspoints set points = '" + b.getValue()  + "' where pointid = " + b.getId();
        //System.out.println(sql);
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            //e.getStackTrace();
            System.out.println("Failed to change customer's password!");
            return false;
        }
    }

    public String getUSERNAME(){
        return USERNAME;
    }

    public String getPASSWORD(){
        return PASSWORD;
    }

    public void save(){
        int[] counts = {Course.getCount(), Category.getCount(), Assignment.getCount(), Student.getCount(), BonusPoints.getCount(), Comment.getCount(), Grade.getCount()};

        updateUser(PASSWORD, counts);
        delete();
        for (Course c : courses){
            Course temp = getCourseFromDB(c.getCourseIndex());

            if (temp == null){
                insertCourse(c);
            }
            else if (!c.getCourseName().equals(temp.getCourseName()) || !c.getSemester().equals(temp.getSemester())){
                updateCourse(c, temp.getCourseIndex());
            }

            saveCategories(c.getAllCategories(), c.getCourseIndex());
            saveStudents(c.getAllStudents(), c.getCourseIndex());
        }
    }

    private void delete() {
        for (Integer i: deletedAssignments) {
            deleteAssignments(i);
        }
        for (Integer i: deletedBonusPoints) {
            deleteBonusPoints(i);
        }
        for (Integer i: deletedCategory){
            deleteCategory(i);
        }

        for(Integer i: deletedComments) {
            deleteComments(i);
        }

        for(Integer i: deletedCourses) {
            deleteCourses(i);
        }
        for (Integer i: deletedGrades) {
            deleteGrades(i);
        }
        for (Integer i: deletedStudents) {
            deleteStudents(i);
        }
    }

    private boolean deleteAssignments(int id) {
        String sql = "delete from assignment where assignmentid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteBonusPoints(int id) {
        String sql = "delete from bonuspoints where pointid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteCategory(int id) {
        String sql = "delete from category where categoryid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteCourses(int id) {
        String sql = "delete from course where courseid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteComments(int id) {
        String sql = "delete from studentcomments where commentid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteGrades(int id) {
        String sql = "delete from grade where gradeid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    private boolean deleteStudents(int id) {
        String sql = "delete from student where studentid = " + id;
        PreparedStatement pst = null;
        SQLConnection sc = new SQLConnection();
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            pst.execute();
            sc.close();
            return true;
        }catch (Exception e) {
            System.out.println("Failed to delete account " + id);
            return false;
        }
    }

    public void saveCategories(ArrayList<Category> allCategories, int cid) {
        for (Category c: allCategories){
            Category temp = getCategoryFromDB(c.getId());
            if (temp == null){
                insertCategory(c, cid);
            }
            else if (c.getWeight() != temp.getWeight() || !c.getCategoryName().equals(temp.getCategoryName())){
                updateCategory(c);
            }

            //delete
            saveAssignments(c.getAllAssignments(), c.getId());
        }
    }

    private void saveAssignments(List<Assignment> allAssignments, int cid) {
        for (Assignment a:allAssignments){
            Assignment temp = getAssignment(a.getId());
            if(temp==null){
                insertAssignment(a, cid);
            }
            else if (a.getWeight() != temp.getWeight() || !a.getReleaseDate().equals(temp.getReleaseDate()) || !a.getDueDate().equals(temp.getDueDate()) || a.getMaxPoint() != temp.getMaxPoint() || !a.getAssignmentName().equals(temp.getAssignmentName())){
                updateAssignment(a);
            }
        }

        //delete
    }

    private void saveStudents(ArrayList<Student> allStudents, int cid) {
        for (Student s: allStudents){
            Student temp = getStudentFromDB(s.getId());
            if (temp == null){
                insertStudent(s, cid);
            }
            else if (!s.getName().getFname().equals(temp.getName().getFname()) || !s.getName().getMname().equals(temp.getName().getMname()) || !s.getName().getLname().equals(temp.getName().getLname()) || !s.getEmail().equals(temp.getEmail())){
                updateStudent(s);
            }

            //delete
            saveComments(s.getComments(), s.getId());
            saveBonusPoints(s.getBonusPoints(), s.getId());
            saveGrades(s.getGrades(), s.getId());
        }
    }

    private void saveGrades(ArrayList<Grade> grade, int id) {
        for (Grade g: grade){
            Grade temp = getGradeFromDB(g.getId());
            if (temp == null){
                insertGrade(g, id);
            }
            else if (g.getRawScore() != temp.getRawScore() || g.getBonusPoint() != temp.getBonusPoint() || !g.getLetterGrade().equals(temp.getLetterGrade()) || !g.getComment().equals(temp.getComment())){
                updateGrade(g);
            }
        }
    }

    private void saveComments(ArrayList<Comment> comments, int id) {
        for (Comment c: comments){
            Comment temp = getCommentFromDB(c.getId());
            if (temp == null){
                insertComment(c, id);
            }
            else if (!c.getText().equals(temp.getText())){
                updateComment(c);
            }

            //delete
        }
    }

    private void saveBonusPoints(ArrayList<BonusPoints> bonusPoints, int id) {
        for (BonusPoints b: bonusPoints){
            BonusPoints temp = getBonusPointFromDB(b.getId());

            if(temp == null){
                insertBonusPoints(b, id);
            }
            else if (b.getValue() != temp.getValue()){
                updateBonusPoints(b);
            }

            //delete
        }
    }

    public void addDeletedCourse(Course c){
        deletedCourses.add(c.getCourseIndex());
        ArrayList<Student> studentDescendent = c.getAllStudents();
        for (Student s: studentDescendent){
            addDeletedStudent(s);
        }

        if (!(c instanceof Section)) {
            ArrayList<Category> descendents = c.getAllCategories();
            for (Category ct : descendents) {
                addDeletedCategory(ct);
            }
        }
        else{
            int count = 0;
            for (Course cc:courses){
                if (cc.getCourseName() == c.getCourseName()){
                    count+=1;
                }
            }
            if (count == 1){
                ArrayList<Category> descendents = c.getAllCategories();
                for (Category ct : descendents) {
                    addDeletedCategory(ct);
                }
            }
        }
        // get all students and categories with this course and add
    }

    public void addDeletedStudent(Student s){
        deletedStudents.add(s.getId());
        ArrayList<BonusPoints> bpDescendants = s.getBonusPoints();
        for (BonusPoints b: bpDescendants){
            addDeletedBonusPoints(b);
        }
        ArrayList<Grade> gradeDescendants = s.getGrades();
        for (Grade g: gradeDescendants){
            addDeletedGrades(g);
        }
        ArrayList<Comment> commentDescendants = s.getComments();
        for (Comment c: commentDescendants){
            addDeletedComments(c);
        }
        //get all bonuspoints, grades, comments with this student and add
    }

    public void addDeletedCategory(Category c){
        deletedCategory.add(c.getId());
        ArrayList<Assignment> assignmentDescendants = (ArrayList<Assignment>) c.getAllAssignments();
        for (Assignment a:assignmentDescendants){
            addDeletedAssignment(a);
        }
        //get all assignments with this category and add
    }

    public void addDeletedAssignment(Assignment a){
        deletedAssignments.add(a.getId());
        ArrayList<Grade> gradeDescendents = getGradesforAssignment(a.getId());
        for (Grade g: gradeDescendents){
            addDeletedGrades(g);
        }
        //get all grades with this assignment and add
    }

    public void addDeletedBonusPoints(BonusPoints b){
        deletedBonusPoints.add(b.getId());
    }

    public void addDeletedComments(Comment c){
        deletedComments.add(c.getId());
    }

    public void addDeletedGrades(Grade g){
        if (!deletedGrades.contains(g.getId())) {
            deletedGrades.add(g.getId());
        }
    }

    public String getCurrent_semester() {
        return current_semester;
    }

    public void setCurrent_semester(String current_semester) {
        this.current_semester = current_semester;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
