package se.chap5;

import java.lang.reflect.Array;

public class code_5_7_6 {
    public static void main(String[] args) {
        code_5_7_6 code = new code_5_7_6();
        String[] array = {"apple", "milk", "peach", "cow"};
        System.out.println(array.length);
        array = (String[]) code.expandArray(array, 10);
        System.out.println(array.length);
        System.out.println(array[8]);
    }

    public Object expandArray(Object array, int newLength) {
        Class arrayClass = array.getClass();
        if (!arrayClass.isArray())
            return null;
        Class componentType = arrayClass.getComponentType();
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(array, 0, newArray, 0, Math.min(Array.getLength(array), newLength));
        return newArray;
    }
}
