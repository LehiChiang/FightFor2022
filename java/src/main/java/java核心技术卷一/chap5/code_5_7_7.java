package java核心技术卷一.chap5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class code_5_7_7 {

    // 使用反射调用Math.max方法
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int num1 = 5, num2 = 10;
        Method method = Math.class.getMethod("max", int.class, int.class);
        int res = (int) method.invoke(null, num1, num2);
        System.out.println(res);
    }
}
