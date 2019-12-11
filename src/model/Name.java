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

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        String[] name = new String[3];
        name[0] = fname;
        name[1] = mname;
        name[2] = lname;
        String realName = "";
        for (int i = 0;i < 3;i++){
            if (name[i] != null || !name[i].equals("")){
                realName = realName + name[i] + " ";
            }
        }
        return (realName);
    }

}
