package model;

import java.util.ArrayList;

public class Student {
    private static int count = 0;
    private Name name;
    private String email;
    private int sid;
    private ArrayList<String> comments;
    private ArrayList<Double> bonusPoints;
    private ArrayList<Grade> grade;

    public Student(Name name) {
        this.name = name;
        setEmail("cs@bu.edu");
        setSid(0);
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grade = new ArrayList<Grade>();
    }

    public Student(Name name, String email, int sid) {
        this.name = name;
        this.email = email;
        this.sid = sid;
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grade = new ArrayList<Grade>();
    }

    public Student(Name name, String email, ArrayList<String> comments, ArrayList<Double> bonusPoints, ArrayList<Grade> grades){
        this.name = name;
        this.email = email;
        this.sid = count;
        count+=1;
        this.comments = comments;
        this.bonusPoints = bonusPoints;
        this.grade = grades;
    }

    public Student(Name name, String email, int sid, ArrayList<String> comments, ArrayList<Double> bonusPoints, ArrayList<Grade> grades){
        this.name = name;
        this.email = email;
        this.sid = sid;
        this.comments = comments;
        this.bonusPoints = bonusPoints;
        this.grade = grades;
    }

    public Name getName() {
        return name;
    }

    public void setName(String fname, String lname, String mname) {
        this.name.setFname(fname);
        this.name.setLname(lname);
        this.name.setMname(mname);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public ArrayList<Grade> getGrade() {
        return grade;
    }

    public void setGrade(int id, double rawscore) {
        Grade g = grade.get(id);
        g.setRawScore(rawscore);
        grade.set(id, g);
    }

    @Override
    public String toString() {
        return name.toString() + " " + email + " " + sid;
    }

    public static void setCount() {
        ITSQLConn a = new ITSQLConn();
        count = a.getCourseIDStart("student");
    }
    public Double getTotalScaledScore(){
        double sum=0;
        for(int i=0;i<grade.size();i++){
            sum+=grade.get(i).getScaledScore();
        }
        return sum;
    }
    public Double getAllBonusPoints(){
        double sum=0;
        for(int i=0;i<grade.size();i++){
            sum+=grade.get(i).getBonusPoint();
        }
        for(int i=0;i<bonusPoints.size();i++){
            sum+=bonusPoints.get(i);
        }
        return sum;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
}
