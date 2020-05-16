package com.company;


public class Main {

    public static void main(String[] args) {
        Directory d = new Directory(1,3);
       // d.grow();
        //d.display(true);
        d.insert(16,"10000");
        d.insert(4,"00100");
        d.insert(6,"00110");
        d.insert(22,"10110");
        d.insert(24,"11000");
        d.insert(10,"01010");
        d.insert(31,"11111");
        d.insert(7,"00111");
        d.insert(9,"01001");
        d.insert(20,"10100");
        d.insert(26,"11101");
        d.display(true);
       /* Bucket b = new Bucket(1,3);
        b.insert(1,"01");
        b.insert(2,"10");
        System.out.println(b.copy());*/








    }
}
