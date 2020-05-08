package com.company;


public class Main {

    public static void main(String[] args) {
        Directory d = new Directory(1,3);
        d.insert(16,"10000",true);
        d.insert(4,"00100",true);
        d.insert(6,"00110",true);
        d.insert(22,"10110",true);
        d.insert(24,"11000",true);
        d.insert(10,"01010",true);
        d.insert(31,"1111",true);
        d.display(true);






    }
}
