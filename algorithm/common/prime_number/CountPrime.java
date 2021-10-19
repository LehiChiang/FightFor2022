package common.prime_number;

import java.util.Arrays;

public class CountPrime {
    /**
     * 返回 1 - n 之间素数的个数
     * @param n
     * @return
     */
    public int countPrime(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j = j + i)
                    isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        CountPrime countPrime = new CountPrime();
        System.out.println(countPrime.countPrime(9));
    }
}
