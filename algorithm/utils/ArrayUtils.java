package utils;

/**
 * 数组工具类
 */
public class ArrayUtils {

    /**
     * 可变参数构造泛型一维数组
     * @param arrays 可变参数
     * @param <T> 泛型
     * @return 构造好的数组
     */
    public static <T> T[] newArray(T... arrays) {
        return arrays;
    }

    /**
     * 可变参数构造泛型二维数组
     * @param arrays 可变参数
     * @param <T> 泛型
     * @return 构造好的数组
     */
    public static <T> T[][] new2DArray(T[]... arrays) {
        return arrays;
    }

    /**
     * 打印数组（数值型）
     * 数值型包括：整形，浮点型，字符串，字符型，布尔型
     * @param array 泛型数组
     * @param <T> 无返回值，直接打印
     */
    public static <T> void printArrayValue(T[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (T num : array) {
            sb.append(num).append(',');
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb);
    }

    /**
     * 打印二维数组（数值型）
     * 数值型包括：整形，浮点型，字符串，字符型，布尔型
     * @param array 泛型数组
     * @param <T> 无返回值，直接打印
     */
    public static <T> void print2DArrayValue(T[][] array) {
        for (int i = 0 ; i < array.length ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (T num : array[i]) {
                sb.append(num).append(',');
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
            System.out.println(sb);
        }
    }
}