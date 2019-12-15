package model;

public class Grade {
    private static int count = 0;
    private int gradeid;
    private Assignment assignment;
    private double rawScore;
    private double bonusPoint;
    private String letterGrade;
    private String comment;


    public Grade(Assignment assignment) {
        this.gradeid = ++count;
        this.assignment = assignment;
        this.rawScore = 0;
        this.bonusPoint = 0;
        this.letterGrade = "";
        this.comment = "";

    }

    public Grade(int id, Assignment a, double rawScore, double bonusp, String letterGrade, String comment) {
        this.gradeid = id;
        this.gradeid = count;
        count+=1;
        this.bonusPoint = bonusp;
        this.letterGrade = letterGrade;
        this.comment = comment;
        this.assignment = a;
        this.rawScore = rawScore;

    }

    public static int getCount() {
        return count;
    }

    public double getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(double bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public double getRawScore() {
        return rawScore;
    }


    public static void setCount(int c) {
        count = c;
    }

    public void setRawScore(double rawscore) {
        this.rawScore = rawscore;
    }

    public double getScaledScore() {
        return rawScore / assignment.getMaxPoint() * 100;
    }

    public int getId() {
        return gradeid;
    }
}
