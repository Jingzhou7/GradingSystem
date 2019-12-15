package model;

public class Comment {
    public static final Comment dummyComment = new Comment("");

    private static int count = 0;
    private String text;
    private int id;

    public Comment(int id, String t){
        this.id = id;
        count++;
        this.text = t;
    }

    public Comment(String t){
        this.id = ++count;
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
