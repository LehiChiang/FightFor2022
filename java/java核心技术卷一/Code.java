package java核心技术卷一;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Objects;

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
    private Position position = Position.PRODUCT;

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

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Employee employee = (Employee) o;
         return id == employee.id && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name);
     }

     @Override
     public int hashCode() {
         return Objects.hash(id, name, salary);
     }
 }

 enum Position {
    DEVELOPMENT, SALE, PRODUCT, SUPPORT
 }