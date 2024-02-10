package leetcode.editor.cn;

import java.util.Random;

class RandomPi {
    public static void main(String[] args) {
        Random random = new Random();
        double cnt = 0;
        long freq = 1000000000;
        for (int i = 0; i < freq; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (Math.sqrt(x * x + y * y) < 1)
                cnt++;
        }
        System.out.println(4 * cnt / freq);
    }
}
