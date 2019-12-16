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
    private double totalScoreWeighted = 0;
    private double rawTotalScore = 0;

    private boolean scoreUp;

    public Student(Name name) {
        this.name = name;
        this.email = "cs@bu.edu";
        this.sid = ++count;
        comments = new ArrayList<>();
        bonusPoints = new ArrayList<>();
        grades = new ArrayList<>();
        scoreUp=false;
        setRawTotalScore();

    }



    public Student(Name name, String email, int sid, ArrayList<Comment> comments, ArrayList<BonusPoints> bonusPoints, ArrayList<Grade> grades){
        this.name = name;
        this.email = email;
        this.sid = sid;
        count++;
        this.comments = comments;
        this.bonusPoints = bonusPoints;
        this.grades = grades;
        setRawTotalScore();
    }



    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
        setRawTotalScore();
    }

    public double getTotalScoreWeighted() {
        return totalScoreWeighted;
    }

    public void setTotalScoreWeighted(double totalScoreWeighted) {
        this.totalScoreWeighted = totalScoreWeighted;
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
    public Comment deleteComment(String comment){
        Comment t=null;
        for(int i=0;i<comments.size();i++){
            t=comments.get(i);
            if(t.getText().equals(comment)){
                break;
            }
        }
        comments.remove(t);
        return t;
    }
    public int deleteAssComment(String assName,String comment){
        Grade g=null;
        int id=-1;
        for(int i=0;i<grades.size();i++){
            if(grades.get(i).getAssignment().getAssignmentName().equals(assName)&&grades.get(i).getComment().equals(comment)){
                g=grades.get(i);
                g.setComment(null);
                break;
            }
        }
        return id;
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
        if(grades.size() == 0) return res;
        else {
            for (Grade g : grades) {
                if (g.getAssignment().getAssignmentName().equals(assignment.getAssignmentName())) {
                    res = g;
                }
            }
            return res;
        }
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


    public double getRawTotalScore() {
        System.out.println(name + " has: ");
        for(Grade g : grades) {
            System.out.println(g.getAssignment().getAssignmentName() + " has score : " + g.getRawScore());
        }

        return rawTotalScore;
    }

    public void setRawTotalScore() {
        double rawTotal = 0;
        if(grades.size() != 0) {
            for(Grade g : grades) {
                rawTotal += g.getRawScore();
            }
        }
        this.rawTotalScore = rawTotal;
    }
    //    public double getWeightedTotalScore() {
//
//        double sum=0;
//        for(Grade g : grades) {
//            double ss = g.getScaledScore();
//            Assignment a = g.getAssignment();
//            double w = a.getWeight();
//            double weightedScore = ss*w/100;
//
//        }
//        return sum;
//    }

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

    public Comment getLastComment() {
        if(comments.size() == 0) return Comment.dummyComment;

        int index = comments.size()-1;

        return comments.get(index);
    }

    //output letter grade based on the scaled score (out of 100)
    public String scoreToLetterGrade() {
        if(totalScoreWeighted > 100 || totalScoreWeighted < 0) return "Please make your score out of 100";

        if(totalScoreWeighted > 97) return "A+";
        else if (totalScoreWeighted > 93) return "A";
        else if (totalScoreWeighted > 90) return "A-";
        else if (totalScoreWeighted > 87) return "B+";
        else if (totalScoreWeighted > 83) return "B";
        else if (totalScoreWeighted > 80) return "B-";
        else if (totalScoreWeighted > 77) return "C+";
        else if (totalScoreWeighted > 73) return "C";
        else if (totalScoreWeighted > 70) return "C-";
        else if (totalScoreWeighted > 67) return "D+";
        else if (totalScoreWeighted > 63) return "D";
        else if (totalScoreWeighted > 60) return "D-";
        else return "F";
    }

    public int bumpUpScoreBy(int bumpUpScore) {
        int res = bumpUpScore;
        setTotalScoreWeighted(totalScoreWeighted + bumpUpScore);
        return res;
    }
}
