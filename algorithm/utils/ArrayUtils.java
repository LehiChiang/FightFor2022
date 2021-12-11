package utils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组工具类
 */
public class ArrayUtils{

    /**
     * 可变参数构造泛型一维数组
     *
     * @param arrays 可变参数
     * @param <T>    泛型
     * @return 构造好的数组
     */
    public static <T> T[] newArray(T... arrays) {
        return (T[]) newArrayWithReflect(arrays);
    }

    private static Object newArrayWithReflect(Object arrays) {
        Class cls = arrays.getClass();
        if (!cls.isArray()) return null;
        Class componentType = cls.getComponentType();
        Object newArray = Array.newInstance(componentType, Array.getLength(arrays));
        System.arraycopy(arrays, 0, newArray, 0, Array.getLength(arrays));
        return newArray;
    }

    /**
     * 可变参数构造泛型二维数组
     *
     * @param arrays 可变参数
     * @param <T>    泛型
     * @return 构造好的数组
     */
    public static <T> T[][] new2DArray(T[]... arrays) {
        return arrays;
    }

    /**
     * 打印数组（数值型）
     * 数值型包括：整形，浮点型，字符串，字符型，布尔型
     *
     * @param array 泛型数组
     * @param <T>   无返回值，直接打印
     */
    public static <T> void print(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * 打印二维数组（数值型）
     * 数值型包括：整形，浮点型，字符串，字符型，布尔型
     *
     * @param <T>   无返回值，直接打印
     * @param array 泛型数组
     */
    public static <T> void print2D(int[][] array) {
        for (int[] subArray : array) {
            System.out.println(Arrays.toString(subArray));
        }
    }
}
