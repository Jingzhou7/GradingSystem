package model;

public class BonusPoints {
    private int id;
    private static int count = 0;
    private double value;

    public BonusPoints(int id, double d){
        this.id = id;
        this.value = d;
    }

    public BonusPoints(double d){
        this.id = count;
        count+=1;
        this.value = d;
    }

    public static int getCount() {
        return count;
    }

    public void setValue(double d) {
        this.value = d;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public static void setCount(int c) {
        count = c;
    }
}
