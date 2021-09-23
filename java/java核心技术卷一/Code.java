package java核心技术卷一;

import java.text.NumberFormat;
import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(a);
    }

    public void add10(int a) {
        a += 10;
    }
}
 class Employee{
    private static int nextId;

    private int id;
    private String name;
    private double salary;

     {
         id = nextId;
         nextId++;
     }

     public Employee(String name, double salary) {
         this.name = name;
         this.salary = salary;
     }

     public Employee() {
         name = "unknown";
         salary = 0;
     }
 }