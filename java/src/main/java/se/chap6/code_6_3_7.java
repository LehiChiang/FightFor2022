package se.chap6;

public class code_6_3_7 {
    public static void main(String[] args) {
        var array = new int[20];
        for (int i = 0; i < 20; i++)
            array[i] = (int) (100 * Math.random());
        ArrayAlg.Pair pair = ArrayAlg.minmax(array);
        System.out.println(pair.getFirstNum());
        System.out.println(pair.getSecondNum());
    }
}

class ArrayAlg {

    public static Pair minmax(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int v : array) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }

    public static class Pair {
        private final int firstNum;
        private final int secondNum;

        public Pair(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }

        public int getFirstNum() {
            return firstNum;
        }

        public int getSecondNum() {
            return secondNum;
        }
    }
}
