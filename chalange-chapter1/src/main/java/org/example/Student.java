package org.example;

public class Student {
    public String name;
    public String nickName;
    public  int score;
    private   boolean lulus;

    public Integer hp;

    private  int a;

    public boolean isLulus() {
        return lulus;
    }

    public void setLulus(boolean lulus) {
        this.lulus = lulus;
    }

    //method
    public void printStudent(){
        System.out.println("Nama "+name);
        System.out.println("No Hp "+ hp);
        System.out.println("lulus"+lulus  );
    }


}
