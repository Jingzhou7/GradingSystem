package model;

public class Student {
    private Name name;
    private String email;
    private String sid;
    private String[] comments;
    private double[] bonusPoints;
    private Grade grade;

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

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public double[] getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(double[] bonusPoints) {
        this.bonusPoints = bonusPoints;
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
