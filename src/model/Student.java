package model;

public class Student {
    private Name name;
    private String email;
    private String sid;
    private String[] comments;
    private double[] bonusPoints;
    private Grade grade;


    @Override
    public String toString() {
        return name.toString();
    }

}
