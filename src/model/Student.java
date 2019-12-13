package model;

import java.util.ArrayList;

public class Student {
    private static int count = 0;
    private Name name;
    private String email;
    private int sid;

    private ArrayList<Comment> comments;
    private ArrayList<BonusPoints> bonusPoints;
    private ArrayList<Grade> grades;

    private boolean scoreUp;

    public Student(Name name) {
        this.name = name;
        setEmail("cs@bu.edu");
        setSid(0);
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grades = new ArrayList<Grade>();
        scoreUp=false;
    }

    public Student(String fname, String lname, String mname, String email) {
        name = new Name(fname, mname, lname);
        this.email = email;
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grades = new ArrayList<Grade>();
        scoreUp=false;
    }

    public Student(Name name, String email, int sid) {
        this.name = name;
        this.email = email;
        this.sid = sid;
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grades = new ArrayList<Grade>();
        scoreUp=false;
    }

    public Student(Name name, String email, ArrayList<Comment> comments, ArrayList<BonusPoints> bonusPoints, ArrayList<Grade> grades){
        this.name = name;
        this.email = email;
        this.sid = count;
        count+=1;
        this.comments = comments;
        this.bonusPoints = bonusPoints;
        this.grades = grades;
        scoreUp=false;
    }

    public Student(Name name, String email, int sid, ArrayList<Comment> comments, ArrayList<BonusPoints> bonusPoints, ArrayList<Grade> grades){
        this.name = name;
        this.email = email;
        this.sid = sid;
        this.comments = comments;
        this.bonusPoints = bonusPoints;
        this.grades = grades;
    }

    public static int getCount() {
        return count;
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

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public Grade getGrade(Assignment assignment) {
        Grade res = null;
        for(Grade g : grades) {
            if(g.getAssignment().equals(assignment)) {
                res = g;
            }
        }
        return res;
    }

    public void setGrade(int id, double rawscore) {
        Grade g = grades.get(id);
        g.setRawScore(rawscore);
        grades.set(id, g);
    }

    @Override
    public String toString() {
        return name.toString() + " " + email + " " + sid;
    }

    public static void setCount(int c) {
        count = c;
    }

    public int getId() {
        return sid;
    }


    public Double getTotalScaledScore(){
        double sum=0;
        for(int i=0;i<grades.size();i++){
            sum+=grades.get(i).getScaledScore();
        }
        return sum;
    }
    public Double getAllBonusPoints(){
        double sum=0;
        for(int i=0;i<grades.size();i++){
            sum+=grades.get(i).getBonusPoint();
        }
        for(int i=0;i<bonusPoints.size();i++){
            sum+=bonusPoints.get(i).getValue();
        }
        return sum;
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public boolean isScoreUp() {
        return scoreUp;
    }

    public void setScoreUp(boolean scoreUp) {
        this.scoreUp = scoreUp;
    }

    public ArrayList<BonusPoints> getBonusPoints(){
        return bonusPoints;
    }
}
