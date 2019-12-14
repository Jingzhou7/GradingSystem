package model;

public class Comment {
    public static final Comment dummyComment = new Comment("");
    private int id;
    private static int count = 0;
    private String text;

    public Comment(int id, String t){
        this.id = id;
        this.text = t;
    }

    public Comment(String t){
        this.id = count;
        count+=1;
        this.text = t;
    }

    public static int getCount() {
        return count;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return this.text;
    }

    public static void setCount(int c) {
        count = c;
    }

    public boolean isEmptyComment() {
        if(text.equals("")) return true;
        else return false;
    }


}
