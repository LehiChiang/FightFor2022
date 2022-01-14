# 《左程云100道算法题》

## 1. 绳子能够覆盖的最多点数

**问题描述**

输入：

- 一根绳子的长度 **l**
- 一个数组，数组中包含了一堆坐标点

输出：
用绳子覆盖坐标点，最多能覆盖的点数

**思路**

可以使用双指针来做，前一个指针用**right**表示，后一个指针用 **left** 表示。如果两个指针间的长度小于**len**，那么就检查 **left**和 **right**之间的长度和 **len**的关系。如果长度比**len**要小的话，那么 **right**就加 **1**. 反之，**left+1**。

复杂度：O(n)

```java
public class MrZuoCode {

    public int maxCover(int[] nums, int len) {
        int max = 0;
        int left = 0, right = 0;
        while (left < nums.length) {
            while (right < nums.length && nums[right] - nums[left] <= len)
                right++;
            max = Math.max(max, right - left);
            left++;
        }
        return max;
    }

    public static void main(String[] args) {
        MrZuoCode code = new MrZuoCode();
        System.out.println(code.maxCover(new int[]{1,3,4,7,9,10}, 4));
        System.out.println(code.maxCover(new int[]{1,4,7,10}, 2));
    }
}
```



## 2. 最小交换次数

一个数组中只有两种字符`'G'`和`'B'`，可以让所有的`G`都放在左侧，所有的`B`都放在右侧 ，或者可以让所有的`G`都放在右侧，所有的`B`都放在左侧 。但是只能在相邻字符之间进行交换操作，请问至少需要交换几次？

```java
    public static int minSwapStep(String s) {
        int BStart = 0, BSwapCount = 0;
        int GStart = 0, GSwapCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                BSwapCount += i - BStart;
                BStart++;
            }
            if (s.charAt(i) == 'G') {
                GSwapCount += i - GStart;
                GStart++;
            }
        }
        return Math.min(BSwapCount, GSwapCount);
    }
```



## 3. 目标和

给你一个整数数组 `nums` 和一个整数 `target` 。向数组中的每个整数前添加 `'+'` 或 `'-'` ，然后串联起所有整数，可以构造一个 表达式 ：

例如，`nums = [2, 1]` ，可以在 `2` 之前添加 `'+'` ，在 `1` 之前添加 '-' ，然后串联起来得到表达式 `"+2-1"` 。

返回可以通过上述方法构造的、运算结果等于 `target` 的不同 表达式 的数目。

```java

```

