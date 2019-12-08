package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Name name;
    private String email;
    private String sid;
    private List<String> comments;
    private double[] bonusPoints;
    private Grade grade;


    public Student(Name name) {
        this.name = name;
        setEmail("cs@bu.edu");
        setSid("U00000000");
        comments = new ArrayList<>();
        bonusPoints = new double[]{};
        grade = new Grade();
    }

    public Student(Name name, String email, String sid) {
        this.name = name;
        this.email = email;
        this.sid = sid;
        comments = new ArrayList<>();
        bonusPoints = new double[]{};
        grade = new Grade();
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name.toString();
    }

}
