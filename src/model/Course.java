package model;

import java.util.ArrayList;
import java.util.Arrays;


public class Course {
    public static final String CURRENT_SEMESTER = "Fall 2019";
    private static int count;
    private int courseIndex;
    private String semester;
    private String courseName;
    private ArrayList<Category> categories;
    private ArrayList<Student> students;


    //statistics for the whole course based on the total score

    private double mean = 0 ;
    private double median = 0;
    private double standard_deviation = 0;
    private double high = 0;
    private double low = 0;



    //constructor
    public Course(String courseName) {
        courseIndex = ++count;
        this.semester = CURRENT_SEMESTER;
        this.courseName = courseName;
        categories = new ArrayList<>();
        students = new ArrayList<>();
        initLists();
        calculateStatistics();
    }


    public Course(int courseIndex, String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students) {
        //System.out.println("count: " + count);
        this.courseIndex = courseIndex;
        count++;
        this.semester = semester;
        this.courseName = courseName;
        this.categories = categories;
        this.students = students;
        initLists();
        calculateStatistics();
    }

    public Course(String semester, String courseName, ArrayList<Category> categories, ArrayList<Student> students){
        //System.out.println("count: " + count);
        this.courseIndex = ++count;
        this.semester = semester;
        this.courseName = courseName;
        this.categories = categories;
        this.students = students;
        initLists();
        calculateStatistics();
    }

    private void initLists(){
        if(categories.size() == 0) {
            Category newCategory = new Category("Exam");
            newCategory.setWeight(100);
            categories.add(newCategory);
        }
        if(students.size() == 0) {
            Student newStudent = new Student(new Name("Alice"));
            ArrayList<Grade> dummyGrades = new ArrayList<>();
            for(Category c : categories) {
                for(Assignment a : c.getAllAssignments()) {
                    Grade dummyGrade = new Grade(a);
                    dummyGrades.add(dummyGrade);
                }
            }
            newStudent.setGrades(dummyGrades);
            students.add(newStudent);
        }
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

    public boolean removeCategory(Category c){
        if (categories.contains(c)){
            categories.remove(c);
            return true;
        }

        return false;
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

    public boolean addStudent(String fname, String mname, String lname, String email) {
        Name newName = new Name(fname, mname, lname);
        Student newStudent = new Student(newName);
        newStudent.setEmail(email);

        students.add(newStudent);
        return true;
    }

    //getter and setter for statistics


    public static String getCurrentSemester() {
        return CURRENT_SEMESTER;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getStandard_deviation() {
        return standard_deviation;
    }

    public void setStandard_deviation(double standard_deviation) {
        this.standard_deviation = standard_deviation;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void calculateStatistics() {
        //initialize statistics and update alongside
        int studentCount = students.size();
        if (studentCount == 0) {
            setHigh(0);
            setLow(0);
            setMean(0);
            setMedian(0);
            setStandard_deviation(0);
        } else {
            System.out.println("");
            double totalScore = 0;
            double avg;
            double med;
            double highScore = 0;
            double lowScore = 100;
            double sd = 0;
            double[] allTotalScores = new double[studentCount];

            for (int i = 0; i < studentCount; i++) {
                double sScore = students.get(i).getTotalScoreWeighted();
                totalScore += sScore;
                if (sScore > highScore) highScore = sScore;
                if (sScore < lowScore) lowScore = sScore;
                allTotalScores[i] = sScore;
            }


            Arrays.sort(allTotalScores);
            if (studentCount % 2 == 1) {
                //studentCount is odd
                med = allTotalScores[(studentCount - 1) / 2];
            } else {
                //studentCount is even
                med = (allTotalScores[studentCount / 2] + allTotalScores[studentCount / 2 - 1]) / 2;
            }

            avg = totalScore / studentCount;

            for (double d : allTotalScores) {
                sd += Math.pow(d - avg, 2);
            }
            sd = Math.sqrt(sd / studentCount);


            setHigh(highScore);
            setLow(lowScore);
            setMean(avg);
            setMedian(med);
            setStandard_deviation(sd);
        }

    }


    public Category getCategoryByAssignment(Assignment assignment) {
        Category ret = null;
        for(Category c : categories) {
            if(c.containsAssignment(assignment)) {
                ret = c;
            }
        }
        return ret;
    }

    public void setWeightedTotalScoreForAll() {
        for(Student s : students) {
            double score = 0;
            ArrayList<Grade> gg = s.getGrades();
            if(gg.size() == 0) break;
            for(Grade g : gg) {
                Assignment aa = g.getAssignment();
                Category cc = getCategoryByAssignment(aa);
                score += g.getScaledScore() * aa.getWeight() * cc.getWeight() / 10000;
            }

            s.setTotalScoreWeighted(score);
        }
    }

}
