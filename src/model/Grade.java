package model;

public class Grade {
    private static int count = 0;
    private int gradeid;
    private Assignment assignment;
    private double rawScore;
    private double bonusPoint;
    private String letterGrade;
    private String comment;


    public Grade() {

        setBonusPoint(0);
        setComment(null);
        setLetterGrade(null);
    }

    public Grade(Assignment assignment, double rawScore) {
        this.assignment = assignment;
        this.rawScore = rawScore;
        setBonusPoint(0);
        setComment(null);
        setLetterGrade(null);
    }

    public Grade(Assignment a, double rawScore, double bonusp, String letterGrade, String comment){
        this.gradeid = count;
        count+=1;
        this.bonusPoint = bonusp;
        this.letterGrade = letterGrade;
        this.comment = comment;
    }

    public Grade(int id, Assignment a, double rawScore, double bonusp, String lettergrade, String comment) {
        this.gradeid = id;
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

    //output letter grade based on the scaled score (out of 100)
    public static String scoreToLetterGrade(double score) {
        if(score > 100 || score < 60) return "Please make your score out of 100";

        if(score > 97) return "A+";
        else if (score > 93) return "A";
        else if (score > 90) return "A-";
        else if (score > 87) return "B+";
        else if (score > 83) return "B";
        else if (score > 80) return "B-";
        else if (score > 77) return "C+";
        else if (score > 73) return "C";
        else if (score > 70) return "C-";
        else if (score > 67) return "D+";
        else if (score > 63) return "D";
        else if (score > 60) return "D-";
        else return "F";
    }

    public static void setCount(int c) {
        count = c;
    }

    public void setRawScore(double rawscore) {
        this.rawScore = rawscore;
    }

    public int getId() {
        return gradeid;
    }

    public double getRawScore() {
        return rawScore;
    }

    public Assignment getAssignment(){
        return assignment;

    public double getScaledScore() {
        return rawScore/assignment.getMaxPoint();

    }
}
