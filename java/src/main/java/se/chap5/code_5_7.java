package se.chap5;

import se.chap4.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class code_5_7 {
    public static void code_5_7_4() {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter class name(e.g.java.util.Date): ");
        name = in.next();
        try {
            //首先用cl保存获取的name.class
            Class cl = Class.forName(name);
            //获得超类supercl
            Class supercl = cl.getSuperclass();
            //获取cl的修饰语（public/private/final...）
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class)
                System.out.print("extends " + supercl.getName());

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /*
     * 输出所有的构造器
     */

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors) {
            //构造器的名字
            String name = c.getName();
            System.out.print(" ");
            //构造器的修饰符
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");
            //构造器的参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /*
     * 输出所有的方法
     */
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            //方法的获取返回类型
            Class retType = m.getReturnType();
            //方法的名称
            String name = m.getName();

            System.out.print(" ");

            //方法的修饰符
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name + "(");

            //将参数类型打印出来
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /*
     * 输出所有的域
     */
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            //域的类型
            Class type = f.getType();
            //域的名称
            String name = f.getName();
            System.out.print(" ");
            //域的修饰符
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
                System.out.println(type.getName() + " " + name + " ");
            }
        }
    }

    public static void code_5_7_5() throws NoSuchFieldException, IllegalAccessException {
        var harry = new Employee("David", 10000);
        Class cls = harry.getClass();
        Field f = cls.getDeclaredField("name");
        Object value = f.get(harry);
        System.out.println(value);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        code_5_7.code_5_7_5();
    }
}
