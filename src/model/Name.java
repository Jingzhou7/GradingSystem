package model;

public class Name {
    private String fname;
    private String lname;
    private String mname;

    public Name(String name) {
        this.fname  = name;
        this.mname = "";
        this.lname = "";
    }

    public Name(String fname, String mname, String lname) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }

}
