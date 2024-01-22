| 最新修改时间 | 2024年1月17日                      |
| ------------ | ---------------------------------- |
| 修改内容     | 重新排版，优化笔记内容             |
| 笔记说明     | 按照数据结构分类，内部按照算法细分 |



# 一. 数组

## 【1】二分法

### 1. 二分查找的边界

```java
left = 0, right = nums.length;
while(left < right){
    return mid;
    left = mid + 1;
    right = mid;
    return left;
}
```

或者

```java
left = 0, right = nums.length - 1;
while(left <= right){
    return mid;
    left = mid + 1;
    right = mid - 1;
    return left;
}
```

！！！**注意**：如果是二分查找不返回mid的话，那么一般`right = nums.length - 1`，比如**山脉数组，数字峰值**等，其他的与第一种写法一样。

### 2. 二分查找左边界

```java
    /**
     * 二分搜索左边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最左边出现的目标值数字的索引
     */
    public static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                right = mid;
            else if (nums[mid] > target)
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1;
        }
        return left < nums.length && nums[left] == target ? left : -1;
    }
```



### 3. 二分查找右边界

```java
    /**
     * 二分搜索的右边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最右边出现的目标值数字的索引
     */
    public static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                left = mid + 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return right > 0 && nums[right - 1] == target ? right - 1 : -1;
    }
```



### 4. 搜索旋转排序数组

**示例 1：**

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

查看当前 `mid` 为分割位置分割出来的两个部分 `[l, mid]` 和 `[mid + 1, r]` 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 `target` 在不在这个部分：

<img src="https://assets.leetcode-cn.com/solution-static/33/33_fig1.png" alt="fig1" style="zoom:90%;" />

- 如果 `[l, mid - 1]` 是有序数组，且 target 的大小满足 `[nums[l],nums[mid])`，则我们应该将搜索范围缩小至 `[l, mid - 1]`，否则在 `[mid + 1, r]` 中寻找。
- 如果 `[mid, r]` 是有序数组，且 target 的大小满足 `(nums[mid+1],nums[r]]`，则我们应该将搜索范围缩小至 `[mid + 1, r]`，否则在 `[l, mid - 1]` 中寻找。

```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[0] <= nums[mid]) { // 有序的半边
                if (target >= nums[0] && target < nums[mid]) { // 这里要加上=，以便搜索第一个元素
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else { // 无序的半边
                if (target > nums[mid] && target <= nums[nums.length - 1]) { // 这里要加上=，以便搜索最后一个元素
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return -1;
    }
}
```



### 5. 寻找旋转排序数组中的最小值

已知一个长度为 `n` 的数组，预先按照升序排列，经由 `1` 到 `n` 次 **旋转** 后，得到输入数组。例如，原数组 `nums = [0,1,2,4,5,6,7]` 在变化后可能得到：

- 若旋转 `4` 次，则可以得到 `[4,5,6,7,0,1,2]`
- 若旋转 `7` 次，则可以得到 `[0,1,2,4,5,6,7]`

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` **旋转一次** 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

给你一个元素值 **互不相同** 的数组 `nums` ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 **最小元素** 。

**示例 1：**

```
输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
```

**示例 3：**

```
输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
```

**`pivot`和`high`的元素之间比较即可**

<img src="https://assets.leetcode-cn.com/solution-static/153/2.png" alt="fig2" style="zoom:80%;" />

<img src="https://assets.leetcode-cn.com/solution-static/153/3.png" alt="fig3" style="zoom:80%;" />

代码：

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
        }
        return nums[left];
    }
}
```



### 6. 寻找旋转排序数组中的最小值 II

给你一个可能存在 **重复** 元素值的数组 `nums` ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 **最小元素** 。

**示例 1：**

```
输入：nums = [1,3,5]
输出：1
```

**示例 2：**

```
输入：nums = [2,2,2,0,1]
输出：0
```



和I一样，只拿`mid`的元素和`right`的元素比较 ，来判断。这里多了重复元素的情况，所以，**如果`mid`元素和`right`元素相同，那么`right--`。因为总会有`mid`能兜住最小值**。

```java
public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return nums[left];
    }
```



### 7. 寻找两个正序数组的中位数

我们只有用到二分的方法才能达到。我们不妨用另一种思路，题目是求中位数，其实就是求第 k 小数的一种特殊情况，而求第 k 小数有一种算法。由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数。看下边一个例子。

假设我们要找第 7 小的数字。

![image.png](https://pic.leetcode-cn.com/735ea8129ab5b56b7058c6286217fa4bb5f8a198e4c8b2172fe0f75b29a966cd-image.png)

我们比较两个数组的第 k/2 个数字，如果 k 是奇数，向下取整。也就是比较第 3 个数字，上边数组中的 4 和下边数组中的 3，如果哪个小，就表明该数组的前 k/2 个数字都不是第 k 小数字，所以可以排除。也就是 1，2，3 这三个数字不可能是第 7 小的数字，我们可以把它排除掉。将 1349和 45678910 两个数组作为新的数组进行比较。

更一般的情况 A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ... ，如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字。

A 数组中比 A[k/2] 小的数有 k/2-1 个，B 数组中，B[k/2] 比 A[k/2] 小，假设 B[k/2] 前边的数字都比 A[k/2] 小，也只有 k/2-1 个，所以比 A[k/2] 小的数字最多有 k/1-1+k/2-1=k-2个，所以 A[k/2] 最多是第 k-1 小的数。而比 A[k/2] 小的数更不可能是第 k 小的数了，所以可以把它们排除。

橙色的部分表示已经去掉的数字。

![image.png](https://pic.leetcode-cn.com/09b8649cd2b8bbea74f7f632b098fed5f8404530ff44b5a0b54a360b3cf7dd8f-image.png)

由于我们已经排除掉了 3 个数字，就是这 3 个数字一定在最前边，所以在两个新数组中，我们只需要找第 7 - 3 = 4 小的数字就可以了，也就是 k = 4。此时两个数组，比较第 2 个数字，3 < 5，所以我们可以把小的那个数组中的 1 ，3 排除掉了。

![image.png](https://pic.leetcode-cn.com/f2d72fd3dff109ad810895b9a0c8d8782f47df6b2f24f9de72704961bc547fcb-image.png)

我们又排除掉 2 个数字，所以现在找第 4 - 2 = 2 小的数字就可以了。此时比较两个数组中的第 k / 2 = 1 个数，4 == 4，怎么办呢？由于两个数相等，所以我们无论去掉哪个数组中的都行，因为去掉 1 个总会保留 1 个的，所以没有影响。为了统一，我们就假设 4 > 4 吧，所以此时将下边的 4 去掉。

![image.png](https://pic.leetcode-cn.com/3c89a8ea29f2e19057b57242c8bc37c5f09b6796b96c30f3d42caea21c12f294-image.png)

由于又去掉 1 个数字，此时我们要找第 1 小的数字，所以只需判断两个数组中第一个数字哪个小就可以了，也就是 4。

所以第 7 小的数字是 4。

我们每次都是取 k/2 的数进行比较，有时候可能会遇到数组长度小于 k/2的时候。

![image.png](https://pic.leetcode-cn.com/ad87d1f63a9bbd99e12605686290800ce61b03f9fb98d87f1d8c020d404421ac-image.png)

此时 k / 2 等于 3，而上边的数组长度是 2，我们此时将箭头指向它的末尾就可以了。这样的话，由于 2 < 3，所以就会导致上边的数组 1，2 都被排除。造成下边的情况。

![image.png](https://pic.leetcode-cn.com/7ea1963f184b1dcaddf951326ccbe7aa09cfbb9ebee7fffb2ede131853b3d1de-image.png)

由于 2 个元素被排除，所以此时 k = 5，又由于上边的数组已经空了，我们只需要返回下边的数组的第 5 个数字就可以了。

从上边可以看到，无论是找第奇数个还是第偶数个数字，对我们的算法并没有影响，而且在算法进行中，k 的值都有可能从奇数变为偶数，最终都会变为 1 或者由于一个数组空了，直接返回结果。

所以我们采用递归的思路，为了防止数组长度小于 k/2，所以每次比较 min(k/2，len(数组) 对应的数字，把小的那个对应的数组的数字排除，将两个新数组进入递归，并且 k 要减去排除的数字的个数。递归出口就是当 k=1 或者其中一个数字长度是 0 了。



**二分法**

```Java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int totalLen = len1 + len2;
        if (totalLen % 2 == 1) {
            return findKElement(nums1, 0, nums2, 0, (totalLen / 2) + 1);
        } else {
            int num1 = findKElement(nums1, 0, nums2, 0, totalLen / 2);
            int num2 = findKElement(nums1, 0, nums2, 0, (totalLen / 2) + 1);
            return (num1 + num2) / 2.0;
        }
    }

    private int findKElement(int[] nums1, int start1, int[] nums2, int start2, int k) {
        // len表示待选的数组长度
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        if (len1 > len2)
            return findKElement(nums2, start2, nums1, start1, k);
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(nums1.length - start1, k / 2) - 1;
        int j = start2 + Math.min(nums2.length - start2, k / 2) - 1;
        if (nums1[i] <= nums2[j]) {
            return findKElement(nums1, i + 1, nums2, start2, k - (i - start1 + 1));
        } else {
            return findKElement(nums1, start1, nums2, j + 1, k - (j - start2 + 1));
        }
    }
```

复杂度分析

- 时间复杂度：`O(log(m+n))`，其中 `m` 和 `n` 分别是数组`nums1` 和 `nums2`的长度。初始时有 `k=(m+n)/2` 或 `k=(m+n)/2+1`，每一轮循环可以将查找范围减少一半，因此时间复杂度是`O(log(m+n))`。
- 空间复杂度：`O(1)`。



**切分数组法**

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return partArray(nums1, nums2);
    }
    private double partArray(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        // 分割左右两半的元素数量
        int leftSize = (m + n + 1) / 2;
        // 在[0-m]区间找一个分界线分割
        int left = 0, right = m;
        while (left < right) {
            int i = left + (right - left) / 2;
            int j = leftSize - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = left;
        int j = leftSize - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if ((m + n) % 2 == 1)
            return Math.max(nums1LeftMax, nums2LeftMax);
        else
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
    }
```



### 8. 两个有序数组第k小的数

**详细解法见上题目文字解说**

```java
private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
    int len1 = end1 - start1 + 1;
    int len2 = end2 - start2 + 1;
    //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
    if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
    if (len1 == 0) return nums2[start2 + k - 1];

    if (k == 1) return Math.min(nums1[start1], nums2[start2]);

    int i = start1 + Math.min(len1, k / 2) - 1;
    int j = start2 + Math.min(len2, k / 2) - 1;

    if (nums1[i] > nums2[j]) {
        return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
    }
    else {
        return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
    }
}
```

时间复杂度：每进行一次循环，我们就减少 k/2 个元素，所以时间复杂度是 O(log(k)，而 k=(m+n)/2，所以最终的复杂也就是 O(log(m+n）。

空间复杂度：虽然我们用到了递归，但是可以看到这个递归属于尾递归，所以编译器不需要不停地堆栈，所以空间复杂度为 O(1)。



### 9. 0~n-1有序数组中唯一缺失的数

一个长度为`n-1`的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围`0～n-1`之内。在范围`0～n-1`内的`n`个数字中有且只有一个数字不在该数组中，请找出这个数字。

```
示例 1: 
输入: [0,1,3]
输出: 2

示例 2: 
输入: [0,1,2,3,4,5,6,7,9]
输出: 8 
```

**代码**：

```java
public int missingNumber(int[] nums) {
    int l = 0, r = nums.length;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == mid)
            l = mid + 1;
        else if (nums[mid] > mid)
            r = mid;
    }
    return l >= nums.length ? nums.length : nums[l] - 1;
}
```



## 【2】前缀和

### 1. 和为K的子数组

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的子数组的个数* 。

子数组是数组中元素的连续非空序列。

**示例 1：**

```
输入：nums = [1,1,1], k = 2
输出：2
```

**示例 2：**

```
输入：nums = [1,2,3], k = 3
输出：2
```

**提示：**

- `1 <= nums.length <= 2 * 104`
- `-1000 <= nums[i] <= 1000`
- `-107 <= k <= 107`

```java
public int subarraySum(int[] nums, int k) {
    // key表示前缀和，value表示前缀和的数量
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int res = 0, preSum = 0;
    for (int i = 0; i < nums.length; i++) {
        preSum += nums[i];
        if (map.containsKey(preSum - k))
            res += map.get(preSum - k);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
    }
    return res;
}
```



### 2. 二维数组的前缀和

给定一个二维矩阵 `matrix`，以下类型的多个请求：

- 计算其子矩形范围内元素的总和，该子矩阵的 **左上角** 为 `(row1, col1)` ，**右下角** 为 `(row2, col2)` 。

实现 `NumMatrix` 类：

- `NumMatrix(int[][] matrix)` 给定整数矩阵 `matrix` 进行初始化
- `int sumRegion(int row1, int col1, int row2, int col2)` 返回 **左上角** `(row1, col1)` 、**右下角** `(row2, col2)` 所描述的子矩阵的元素 **总和** 。

**示例 1：**

![img](https://pic.leetcode-cn.com/1626332422-wUpUHT-image.png)

```
输入: 
["NumMatrix","sumRegion","sumRegion","sumRegion"]
[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
输出: 
[null, 8, 11, 12]

解释:
NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
```

 

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 200`
- `-105 <= matrix[i][j] <= 105`
- `0 <= row1 <= row2 < m`
- `0 <= col1 <= col2 < n`
- 最多调用 `104` 次 `sumRegion` 方法

代码：

```java
class NumMatrix {
    // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;
        // 构造前缀和矩阵
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 计算每个矩阵 [0, 0, i, j] 的元素和
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i - 1][j - 1] - preSum[i-1][j-1];
            }
        }
    }

    // 计算子矩阵 [x1, y1, x2, y2] 的元素和
    public int sumRegion(int x1, int y1, int x2, int y2) {
        // 目标矩阵之和由四个相邻矩阵运算获得
        return preSum[x2+1][y2+1] - preSum[x1][y2+1] - preSum[x2+1][y1] + preSum[x1][y1];
    }
}
```



### 3. 和可被K整除的子数组

给定一个整数数组 `nums` 和一个整数 `k` ，返回其中元素之和可被 `k` 整除的（连续、非空） **子数组** 的数目。

**子数组** 是数组的 **连续** 部分。

**示例 1：**

```
输入：nums = [4,5,0,-2,-3,1], k = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
```

**示例 2:**

```
输入: nums = [5], k = 9
输出: 0
```

**解题：**

`preSum % k`作为判断条件

```java
    public int subarraysDivByK(int[] nums, int k) {
        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = (preSum % k + k) % k;
            // (presum - num[j]) % k = 0
            // presum % k = num[j] % k
            // Map里面存的是和presum同模的num[j]出现的次数，
            // 考虑到负数的情况，可以使用Java的取模函数计算
            // int key = Math.floorMod(preSum,k);      !!!
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }
```



### 4. 连续的子数组和

给你一个整数数组 `nums` 和一个整数 `k` ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

- 子数组大小 **至少为 2** ，且
- 子数组元素总和为 `k` 的倍数。

如果存在，返回 `true` ；否则，返回 `false` 。

如果存在一个整数 `n` ，令整数 `x` 符合 `x = n * k` ，则称 `x` 是 `k` 的一个倍数。`0` 始终视为 `k` 的一个倍数。

**示例 1：**

```java
输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
```

**示例 2：**

```java
输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
```

**示例 3：**

```java
输入：nums = [23,2,6,4,7], k = 13
输出：false
```

**提示：**

- `1 <= nums.length <= 105`
- `0 <= nums[i] <= 109`
- `0 <= sum(nums[i]) <= 231 - 1`
- `1 <= k <= 231 - 1`

```java
    public boolean checkSubarraySum(int[] nums, int k) {
        // 上一次出现这个余数的下标位置
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = preSum % k;
            if (map.containsKey(key)) {
                if (i - map.get(key) > 1)
                    return true;
            }
            else map.put(key, i);
        }
        return false;
    }
```



### 5. 统计优美子数组

给你一个整数数组 `nums` 和一个整数 `k`。

如果某个 **连续** 子数组中恰好有 `k` 个奇数数字，我们就认为这个子数组是「**优美子数组**」。

请返回这个数组中「优美子数组」的数目。

**示例 1：**

```
输入：nums = [1,1,2,1,1], k = 3
输出：2
解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
```

**示例 2：**

```
输入：nums = [2,4,6], k = 1
输出：0
解释：数列中不包含任何奇数，所以不存在优美子数组。
```

**示例 3：**

```
输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
输出：16
```

**提示：**

- `1 <= nums.length <= 50000`
- `1 <= nums[i] <= 10^5`
- `1 <= k <= nums.length`

前缀和思想：

注意：这里`preCount`表示的是`nums[0, i-1]`中子数组的个数

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0, preCount = 0;
        for (int i = 0; i < nums.length; i++) {
            preCount += (nums[i] & 1) == 1 ? 1 : 0;
            if (map.containsKey(preCount - k)) {
                res += map.get(preCount - k);
            }
            map.put(preCount, map.getOrDefault(preCount, 0) + 1);
        }
        return res;
    }
}
```

滑动窗口思想：

- 不断右移 `right` 指针来扩大滑动窗口，使其包含 `k` 个奇数；

- 若当前滑动窗口包含了 `k` 个奇数，则如下「计算当前窗口的优美子数组个数」：
  - 统计第 1 个奇数左边的偶数个数 `leftEvenCnt`。 这 `leftEvenCnt` 个偶数都可以作为「优美子数组」的起点，因此起点的选择有 `leftEvenCnt + 1` 种（因为可以一个偶数都不取，因此别忘了 +1 喔）。
  - 统计第 `k` 个奇数右边的偶数个数 `rightEvenCnt` 。 这 `rightEvenCnt` 个偶数都可以作为「优美子数组」的终点，因此终点的选择有 `rightEvenCnt + 1`（因为可以一个偶数都不取，因此别忘了 +1 喔）。
  - 因此「优美子数组」左右起点的选择组合数为 `(leftEvenCnt + 1) * (rightEvenCnt + 1)`。

```java
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0){
                    right++;
                }
                int rightEvenCnt = right - tmp;

                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    left++;
                    leftEvenCnt++;
                }
                 // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (rightEvenCnt + 1) * (leftEvenCnt + 1);
                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }
        }
        return res;
    }
```



### 6. 除自身以外数组的乘积

给你一个整数数组 `nums`，返回 *数组 `answer` ，其中 `answer[i]` 等于 `nums` 中除 `nums[i]` 之外其余各元素的乘积* 。

题目数据 **保证** 数组 `nums`之中任意元素的全部前缀元素和后缀的乘积都在 **32 位** 整数范围内。

请 **不要使用除法，**且在 `O(*n*)` 时间复杂度内完成此题。

**示例 1:**

```
输入: nums = [1,2,3,4]
输出: [24,12,8,6]
```

**示例 2:**

```
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
```

 **代码：**

**方法一：空间复杂度O(n)**

```java
public int[] constructArr(int[] a) {
    int n = a.length;
    int[] leftRes = new int[n];
    leftRes[0] = a[0];
    int[] rightRes = new int[n];
    rightRes[n - 1] = a[n - 1];
    for (int i = 1; i < n; i++) {
        leftRes[i] = a[i] * leftRes[i - 1];
        rightRes[n - i - 1] = a[n - i - 1] * rightRes[n - i];
    }
    int[] res = new int[n];
    res[0] = rightRes[1];
    res[n - 1] = leftRes[n - 2];
    for (int i = 1; i < n - 1; i++) {
        res[i] = leftRes[i - 1] * rightRes[i + 1];
    }
    return res;
}
```

**方法二：空间复杂度O(1)**

```java
public int[] constructArr(int[] a) {
    int[] leftPart = new int[a.length];
    leftPart[0] = 1;
    for (int i = 1; i < leftPart.length; ++i)
        leftPart[i] = leftPart[i - 1] * a[i - 1];
    int tmp = 1;
    for (int i = leftPart.length - 2; i >= 0; --i) {
        tmp *= a[i + 1];
        leftPart[i] = leftPart[i] * tmp;
    }
    return leftPart;
}
```



## 【3】滑动窗口

### 1. 滑动窗口模板

双指针算法的模板一般都可以写成下面的形式(模板)：

```java
for (int i = 0, j = 0; i < n; i++)
{
    while (j < i && check(i, j)) j++;
    // 每道题目的具体逻辑
}
```

因为双指针算法是一种优化时间复杂度的方法，所以我们可以首先写出最朴素的**两层循环**的写法。然后考虑题目中是否具有**单调性**。即当其中一个指针 向后移动时，在希望得到答案的情况下，另一个指针 是不是只能向着一个方向移动。



### 2. 最长连续不重复子序列/子串(下标连续)

```java
输入: nums= [1,2,2,3,5]
输出格式: 3
解释：最长下标连续不重复的子序列是[2,3,5]。它的长度是3
```

**示例 1:**

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

这道题我们只枚举`i`， `j`的话是每次看一下要不要往后走。如果有重复元素的话，就`j++`。一直移动到`j`和`i`之间没有重复元素为止，所以最多是`i`走`n`步，`j`走`n`步，一共走`2n`步。

```java
public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int start = 0, end = -1; start < s.length(); start++) {
            if (end + 1 >= s.length())
                break;
            if (start != 0)
                set.remove(s.charAt(start - 1));
            while (end + 1 < s.length() && !set.contains(s.charAt(end + 1))) {
                set.add(s.charAt(end + 1));
                end++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
```



### 3. 最长不含重复字符的子字符串

和上一题一样的思想！（也是滑动窗口）



## 【4】区间问题

### 1. 合并区间

先把初始区间放到结果集里面，然后遍历每个其他的区间，找边界，更改还是新添加区间！

```java
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(pair -> pair[0]));
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int end = list.get(list.size() - 1)[1];
            if (end >= intervals[i][0]) {
                list.get(list.size() - 1)[1] = Math.max(end, intervals[i][1]);
            } else {
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
```



### 2. 插入区间

给你一个 **无重叠的** *，*按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

**示例 1：**

```
输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]
```

**示例 2：**

```
输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
```

**示例 3：**

```
输入：intervals = [], newInterval = [5,7]
输出：[[5,7]]
```

**示例 4：**

```
输入：intervals = [[1,5]], newInterval = [2,3]
输出：[[1,5]]
```

**示例 5：**

```
输入：intervals = [[1,5]], newInterval = [2,7]
输出：[[1,7]]
```

**思路：**

用指针去扫 `intervals`，最多可能有三个阶段：

1. 不重叠的绿区间，在蓝区间的左边
2. 有重叠的绿区间
3. 不重叠的绿区间，在蓝区间的右边

<img src="https://pic.leetcode-cn.com/1604465027-kDWfBc-image.png" alt="image.png" style="zoom:47%;" />

**代码：**

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        boolean placed = false;
        int left = newInterval[0], right = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[1] < left) // 新区间左侧不重叠部分
                list.add(interval);
            else if (interval[0] > right) { // 新区间右侧不重叠部分
                if (!placed) {
                    list.add(new int[]{left, right});
                    placed = true;
                }
                list.add(interval);
            } else {  // 重叠部分，取交集
                left = Math.min(interval[0], left);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[list.size()][]);
    }
}
```



### 3. 区间列表的交集

给定两个由一些 **闭区间** 组成的列表，`firstList` 和 `secondList` ，其中 `firstList[i] = [starti, endi]` 而 `secondList[j] = [startj, endj]` 。每个区间列表都是成对 **不相交** 的，并且 **已经排序** 。

返回这 **两个区间列表的交集** 。

形式上，**闭区间** `[a, b]`（其中 `a <= b`）表示实数 `x` 的集合，而 `a <= x <= b` 。

两个闭区间的 **交集** 是一组实数，要么为空集，要么为闭区间。例如，`[1, 3]` 和 `[2, 4]` 的交集为 `[2, 3]` 。

**示例 1：**

<img src="https://assets.leetcode.com/uploads/2019/01/30/interval1.png" alt="img" style="zoom:50%;" />

```
输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```

**示例 2：**

```
输入：firstList = [[1,3],[5,9]], secondList = []
输出：[]
```

**示例 3：**

```
输入：firstList = [], secondList = [[4,8],[10,12]]
输出：[]
```

**示例 4：**

```
输入：firstList = [[1,7]], secondList = [[3,10]]
输出：[[3,7]]
```

**代码：**

双指针，根据当前指针对应区间的相交情况，计算相交段计入结果并不断移动指针**（注意，每次选择A或B中的一个指针移动）**

判断两个区间是否相交使用的是`&&`条件判断。

```java
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0)
            return new int[][]{};
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int firstStart = firstList[i][0], firstEnd = firstList[i][1];
            int secondStart = secondList[j][0], secondEnd = secondList[j][1];
            if (firstEnd >= secondStart && firstStart <= secondEnd) { // 求交集
                list.add(new int[]{Math.max(firstStart, secondStart), Math.min(firstEnd, secondEnd)});
            }
            if (firstEnd < secondEnd)
                i++;
            else j++;
        }
        return list.toArray(new int[list.size()][]);
    }
}
```



### 4. 无重叠区间

给定一个区间的集合 `intervals` ，其中 `intervals[i] = [starti, endi]` 。返回 *需要移除区间的最小数量，使剩余区间互不重叠* 。

**示例 1:**

```
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。
```

**代码：**

本题的题意可以表达为，你今天有好几个活动，每个活动都可以用区间`[start, end]`表示开始和结束的时间，请问你今天最多能参加几个活动呢？

那我们可能会自然的想到，**优先**选择参加那些**结束时间早**的，因为这样可以留下更多的时间参加其余的活动。如果有多个结束时间相同的，我们选择开始时间晚的，因为这样也有助于参加更多的活动。

那我们的解题思路就很清晰了。

1. 先把intervals做个优先级排序。排在前面的优先级高于排在后面的。排序规则为：按照结束时间从早到晚排序，结束时间相同的，开始时间晚的排在前面。
2. 遍历排序好的intervals，如果后面的活动和前面的活动冲突了，就选择移除后面的活动。

**按照右边界排序，从左向右记录非交叉区间的个数。最后用区间总数减去非交叉区间的个数就是需要移除的区间个数了。此时问题就是要求非交叉区间的最大个数。**

```java
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                ans++;
                right = intervals[i][1];
            }
        }
        return intervals.length - ans;
    }
```



## 【5】双指针

### 1. 最短无序连续子数组

给你一个整数数组 `nums` ，你需要找出一个 **连续子数组** ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 **最短** 子数组，并输出它的长度。

**示例 1：**

```
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
```

**示例 2：**

```
输入：nums = [1,2,3,4]
输出：0
```

**示例 3：**

```
输入：nums = [1]
输出：0
```

**提示：**

- `1 <= nums.length <= 104`

- `-105 <= nums[i] <= 105`

  

同时从前往后和从后往前遍历，分别得到要排序数组的右边界和左边界；
**寻找右边界：**
从前往后遍历的过程中，用`max`记录遍历过的最大值，如果`max`小于当前的`nums[i]`*（因为如果是排序了的话，数组元素值依次递增，`nums[i] > max`才对）*，说明`nums[i]`的位置不正确，属于需要排序的数组，因此将右边界更新为`left`，然后更新`max`；这样最终可以找到需要排序的数组的右边界，右边界之后的元素都大于`max`；
**寻找左边界：**
从后往前遍历的过程中，用`min`记录遍历过的最小值，如果`min`小于当前的`nums[j]`，说明`nums[j]`的位置不正确，应该属于需要排序的数组，因此将左边界更新为`right`，然后更新`min`；这样最终可以找到需要排序的数组的左边界，左边界之前的元素都小于`min`；

```java
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int max = nums[0], min = nums[n - 1];
        // 从左往右寻找异常值的最远位置，异常值定义为数组连续递增性的破坏点
        for (int i = 0; i < n ;i++) {
            if (nums[i] >= max)
                max = nums[i];
            else left = i;
        }
        // 从右往左寻找异常值的最远位置，异常值定义为数组连续递减性的破坏点
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] <= min)
                min = nums[i];
            else right = i;
        }
        // 数字本身就是有序的情况，直接返回0；
        if (left == 0 && right == n - 1)
            return 0;
        return left - right + 1;
    }
```



### 2. 三数之和


```java
public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int third = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                while (third > j && nums[i] + nums[j] + nums[third] > 0) third--;
                if (third > j && nums[i] + nums[j] + nums[third] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }
        }
        return res;
    }
```



### 3. 最接近的三数之和

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = 10000000;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                int third = nums.length - 1;
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                while (j < third) {
                    int sum = nums[i] + nums[j] + nums[third];
                    if (sum == target)
                        return target;
                    if (Math.abs(sum - target) < Math.abs(res - target)) {
                        res = sum;
                    }
                    third--;
                }
            }
        }
        return res;
    }
}
```



### 4. N数之和

```java
public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int numsLen = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2) {
            return res;
        } else if (n == 2) {
            int low = start, high = numsLen - 1;
            while (low < high) {
                int leftNum = nums[low], rightNum = nums[high];
                int sum = leftNum + rightNum;
                if (sum == target) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[low]);
                    temp.add(nums[high]);
                    res.add(temp);
                    while (low < high && nums[low] == leftNum) low++;
                    while (low < high && nums[high] == rightNum) high--;
                } else if (sum < target) {
                    while (low < high && nums[low] == leftNum) low++;
                } else {
                    while (low < high && nums[high] == rightNum) high--;
                }
            }
        } else {
            for (int i = start; i < numsLen; i++) {
                List<List<Integer>> partSumList = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> list : partSumList) {
                    list.add(nums[i]);
                    res.add(list);
                }
                while (i < numsLen - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }
```



### 5. 和为s的连续正数序列

返回一些连续的序列，这些序列的和与target相等的所有组合

**示例 1：**

```
输入：target = 12
输出：[[3, 4, 5]]
解释：在上述示例中，存在一个连续正整数序列的和为 12，为 [3, 4, 5]。
```

**示例 2：**

```
输入：target = 18
输出：[[3,4,5,6],[5,6,7]]
解释：在上述示例中，存在两个连续正整数序列的和分别为 18，分别为 [3, 4, 5, 6] 和 [5, 6, 7]。
```

**代码：**

```java
public int[][] continuedCombineSum(int target) {
    List<int[]> res = new ArrayList<>();
    int sum = 0;
    for (int i = 1, j = 1; j < target; ) {
        while (sum < target) {
            sum += j;
            j++;
        }
        while (sum > target) {
            sum -= i;
            i++;
        }
        if (sum == target) {
            int[] array = new int[j - i];
            for (int k = i; k < j; k++)
                array[k - i] = k;
            res.add(array);
            sum -= i;
            i++;
        }
    }
    return res.toArray(new int[res.size()][]);
}
```





## 【6】单调栈

单调栈，就是一个堆栈，里面的元素的按照大小在栈中满足一定的单调性。也就说是，就是**递增存储元素**、或**递减存储元素**，当该**单调性（递增性、或递减性）被打破**时要进行**适当出栈**。

单调栈也分为 **递增单调栈** 和 **递减单调栈**

- 递增单调栈：从栈底 到 栈顶 保存的数据是从小到大
- 递减单调栈：从栈底 到 栈顶 保存的数据是从大到小

![image](https://img2020.cnblogs.com/blog/1339590/202107/1339590-20210721094802772-483809702.png)

通过样例可以看出，对于**递减单调栈**：

- **最大数据**一定**在栈中**（未出栈）
- 栈中最终**残余参数**是**递减**



**二、适用场景**

按照单调栈（以递减单调栈为例）规律：

- **最大元素**一定**在栈中**（未出栈）
- 栈中最终**残余元素**是**递减**

基于单调栈的特点，单调栈可以用于：
[预置输入]：

1. **未排序**序列

2. **单向比较**（向左看、向右看）。（备注：如果双向，可分解为向右一次单调栈、向左一次单调栈）

3. 比较元素间**大小规律**

4. 筛选并处理序列中

   不符合递减/递增规律的元素

   。如：

   - 筛选一定规律的子序列并处理（坡的最大宽度场景）；
   - 计算某规律下元素之间间距相关的等。

5. 单调栈能很好的对一组元素的峰、谷进行筛选处理，遇到和峰谷相关的场景，可以考虑用单调栈算法。
6. 



### 1. 每日温度

给定一个数组，返回一个大小相同的数组。返回的数组的第i个位置的值，是原数组中的第i个元素，至少往右走多少步，才能遇到一个比自己大的元素（如果之后没有比自己大的元素，或者已经是最后一个元素，则在返回数组的对应位置放上-1）。

```
样例：
输入: 5, 3, 1, 2, 4
输出: -1 3 1 1 -1
```

**代码：**

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
```



### 2. 单调栈

如何求数组中当前元素左右最近的比当前的元素小的元素的索引？

```java
    public static int[][] getNearLessNoRepeat(int[] nums) {
        int[][] res = new int[nums.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int index = stack.pop();
                res[index][0] = stack.isEmpty() ? -1 : stack.peek();
                res[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            res[index][0] = stack.isEmpty() ? -1 : stack.peek();
            res[index][1] = -1;
        }
        return res;
    }
```

寻找左右两边最大最小值的情况可以考虑使用单调栈结果，实现形式主要有以下三种：

- 可以使用最大值，最小值数组辅助查询

- 也可使用左右指针，`left, left_value, right, right_value`

- 单调栈


类似问题有以下几种：

- 接雨水问题：就左右两边最大值的最小的



### 3. 接雨水问题

给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

```
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
```

**示例 2：**

```
输入：height = [4,2,0,3,2,5]
输出：9
```

```java
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIndex = stack.pop();
                int left = stack.isEmpty() ? curIndex : stack.peek();
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]) - height[curIndex];
                ans += curHeight * curWidth;
            }
            stack.push(i);
        }
        return ans;
    }
```



### 4. 柱状图中最大的矩形

要求出柱状图中的最大矩形，可以枚举宽和高。固定宽枚举高或者固定高枚举宽。这里选择固定高枚举宽度比较方便。可以使用暴力的方法枚举出所能容纳的最小宽度范围，然后乘以高度即可得到面积。

暴力法：

```java
    public int largestRectangleArea(int[] heights) {
        int area = heights[0], leftMin = 0, rightMin = 0;
        for (int height = 0; height < heights.length; height++) {
            leftMin = height - 1;
            while (leftMin >= 0 && heights[leftMin] >= heights[height])
                leftMin--;

            rightMin = height + 1;
            while (rightMin < heights.length && heights[rightMin] >= heights[height])
                rightMin++;

            area = Math.max(area, (rightMin - leftMin - 1) * heights[height]);
        }
        return area;
    }
```

那么寻找左右最小值的过程就可以引入单调栈了。

单调栈解法：

```java
    public int largestRectangleArea(int[] heights) {
        int area = heights[0], n = heights.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        Arrays.fill(rightMin, n);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                rightMin[stack.peek()] = i;
                stack.pop();
            }
            leftMin[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            area = Math.max(area, (rightMin[i] - leftMin[i] - 1) * heights[i]);
        }
        return area;
    }
```



### 5. 求区间最小数乘区间和的最大值

首先来看一下面经原文

> - 挑选一个区间，区间值为区间和乘以区间内最小的数的值，求区间值最大的区间（2021.1 字节跳动-国际化-前端）[2]
> - 无序数组，求一个值最大的区间，区间计算方案为：区间和 * 区间最小值（2020.09 字节跳动-电商-后端）[3]
> - [3,1,6,4,5,2]，对于任意子序列可以计算一个X值，X=sum(subArray) * min(subArray)，求最大X（2020.07 字节跳动-商业化-前端）[4]

**这其实是18年头条的校招笔试题目！**

题目描述

给定一个数组，要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：区间中的最小数 * 区间所有数的和。

数组中的元素都是非负数。

输入两行，第一行n表示数组长度，第二行为数组序列。输出最大值。

> > **输入**
> > 3
> > 6 2 1
> > **输出**
> > 36
> > **解释：**满足条件区间是[6] = 6 * 6 = 36;

题目分析

方法一：暴力。题目是找max(区间和 * 区间最小值)，而满足的区间最小值一定是数组的某个元素。因此可以枚举数组，枚举的每个元素（设为x）作为区间最小值，在x左右两侧找到第一个比x小的元素，分别记录左右边界的下标为l,r，寻找边界时计算当前区间的和。那么以x为区间最小值的最大计算区间就是`[l+1,r-1]区间和*x`，枚举时更新最大值。整个算法的时间复杂度是
方法二：单调栈。方法一中找每个元素左右边界的复杂度是O(N)，通过单调栈的数据结构可以将其优化为O(1)。因此优化后整个算法的时间复杂度可以达到O(N)。

ps：本题实际就是上一题的改编题。

参考代码

```c++
//单调栈，时间复杂度O(N)
#include <iostream>
#include <vector>
#include <stack>
using namespace std;
const int N = 500000+10;
int a[N];
int dp[N];
stack<int> s;
int main()
{
    int n,res=0;
    cin >> n;
    for(int i = 0; i < n; i ++) cin >> a[i];
    //前缀和便于快速求区间和，例如求[l,r]区间和=dp[r+1]-dp[l]。l和r的取值范围是[0,n)
    for(int i = 1; i <= n; i ++) dp[i] = dp[i-1] + a[i-1]; 
    for(int i = 0; i < n; i ++) {
        while(!s.empty() && a[i] <= a[s.top()]) {
            int peak = a[s.top()];
            s.pop();
            int l = s.empty()? -1 : s.top();
            int r = i; 
            //l和r是边界，因此区间是[l+1,r-1]，其区间和dp[r+1]-dp[l]
            int dist = dp[r] - dp[l+1];
            res = max(res,peak*dist);
        }
        s.push(i);
    }
    while(!s.empty())
    {
        int peak = a[s.top()];
        s.pop();
        int l = s.empty()? -1 : s.top();
        int r = n; 
        
        int dist = dp[r] - dp[l+1];
        res = max(res,peak*dist);
    }
    cout << res << endl; 
}
```



## 【7】矩阵打印

### 1. ZigZag打印

定义A，B两点，A往右走，不能走再往下走；B往下走，不能走再往右走；

```java
    public static void PrintZigZag(int[][] matrix) {
        int ACol = 0, ARow = 0, BCol = 0, BRow = 0;
        int EndCol = matrix[0].length - 1;
        int EndRow = matrix.length - 1;
        boolean fromUp = false;
        while (ARow != EndRow + 1) {
            printRoute(matrix, ARow, ACol, BRow, BCol, fromUp);
            ARow = ACol == EndCol ? ARow + 1 : ARow; // 这四行不能换
            ACol = ACol == EndCol ? ACol : ACol + 1;
            BCol = BRow == EndRow ? BCol + 1 : BCol;
            BRow = BRow == EndRow ? BRow : BRow + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printRoute(int[][] matrix, int aRow, int aCol, int bRow, int bCol, boolean fromUp) {
        if (fromUp) {
            while (aRow <= bRow) {
                System.out.print(matrix[aRow++][aCol--] + " ");
            }
        } else {
            while (bRow >= aRow) {
                System.out.print(matrix[bRow--][bCol++] + " ");
            }
        }
    }
```



### 2. 旋转打印

<img src="https://img-blog.csdnimg.cn/20190722140141231.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxODUxNTMx,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />

定义A，B两点，A点在左上角，B点在右下角

```java
public class PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		// 我们自己找两个点，用于确定第一圈
		int aX = 0;
		int aY = 0;
		// b点，我们用二维数组的大小来确定最右下角的坐标
		int bX = matrix.length - 1;
		int bY = matrix[0].length - 1;
		// 第一圈循环完了再循环第二圈，当a点的x轴大于b点的x轴，并且a的y大于b的y时，说明已经全部扫描一圈了
		while (aX <= bX && aY <= bY) {
			printEdge(matrix, aX++, aY++, bX--, bY--);
		}
	}

	public static void printEdge(int[][] m, int aX, int aY, int bX, int bY) {
		if (aX == bX) {
			// a,b点的横坐标相同，说明这个矩阵就一行了
			for (int i = aY; i <= bY; i++) {
				System.out.print(m[aX][i] + " ");
			}
		} else if (aY == bY) {
			// a,b点的纵坐标相同，这个矩阵就只有一列了
			for (int i = aX; i <= bX; i++) {
				System.out.print(m[i][aY] + " ");
			}
		} else {
			int curX = aX;
			int curY = aY;
			while (curX != bX) {
				System.out.print(m[aX][curX] + " ");
				curX++;
			}
			while (curY != bY) {
				System.out.print(m[curY][bX] + " ");
				curY++;
			}
			while (curX != aX) {
				System.out.print(m[bY][curX] + " ");
				curX--;
			}
			while (curY != aY) {
				System.out.print(m[curY][aY] + " ");
				curY--;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);
	}
}
```



### 3. 原地旋转矩阵(正方形)

将矩阵顺时针旋转`90`度

解法：将第`i`行和第`length - i - 1`行交换，这样数组的行就是倒序的了，然后按照对角线反转元素就行。

```java
public void rotate(int[][] matrix) {
        // 先0维度反转
        int len = matrix.length;
        for (int row = 0; row < len / 2; row++) {
            for (int col = 0; col < len; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[len - row - 1][col];
                matrix[len - row - 1][col] = temp;
            }
        }
        // 再对角线元素交换位置
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
```



## 【8】其他

### 1. 快速选择

给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

示例 2:

```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```

**核心思想：**

利用快速排序，正确放置轴值元素位置的思想，找到第n-k位置上的数字，使得数组能够局部排序，即得到的轴值元素为第K大的数字。

**代码：**

```java
class Solution {
    private Random random = new Random();

    // 寻找nums中倒数第nums.length - k 位置上的数字
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int left, int right) {
        int index = random_partition(nums, left, right);
        if (index == nums.length - k)
            return nums[nums.length - k];
        else if (index < nums.length - k)
            return quickSelect(nums, k, index + 1, right);
        else
            return quickSelect(nums, k, left, index - 1);
    }

    private int random_partition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(nums, left, i);
        return partition(nums, left, right);
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
```



### 2. 堆

两个操作，（一）建堆`buildHeap` （二）维护堆`heapify`

（一）从元素一般的位置上，从后往前建堆；

（二）维护堆的意思就是确保**堆中的所有节点**，都是父节点大于/小于子节点的；

**初始化堆的时间复杂度分析**

初始化堆的时候，对于每个非叶子结点，都要调用上述函数，将它与它的孩子结点进行比较和交换，顺序是从后向前。

以操作2作为基本操作，对每一层都完全铺满的堆进行分析，

设元素个数为n，则堆的高度`k=log（n+1）≈log n`，非叶子结点的个数为`2^（k-1）-1`。假设每个非叶子结点都需要进行调整，则第i层的非叶子结点需要的操作次数为`k-i`，第`i`层共有`2^（i-1）`个结点，则第i层的所有结点所做的操作为`k*2^（i-1）- i*2^（i-1）`，共`k-1`层非叶子结点，总的操作次数为 <img src="https://img2020.cnblogs.com/blog/2003943/202007/2003943-20200709195627860-488581102.png" alt="img" style="zoom:67%;" />

化简可得，上式=`2^k-k+1`，将`k=log（n+1）≈log n`代入，得`n - log n +1`，

所以，初始化堆的复杂度为`O(n)`

**调整堆的间复杂度分析**

调整堆的复杂度计算和初始化堆差不多，

假设根节点和排在最后的序号为m的叶子结点交换，并进行调整，那么调整的操作次数 = 原来m结点所在的层数 = 堆的高度（因为m结点在堆的最后）= `log m`

共n个结点，调整的总操作次数为<img src="https://img2020.cnblogs.com/blog/2003943/202007/2003943-20200709201153384-1165719037.png" alt="img" style="zoom:67%;" />

化简可得，上式=`log (n-1)! ≈ n*log n`， 所以，调整堆的复杂度为`O(n*log n)`

所以，总体复杂度为O(n*log n)

```java
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i); // 1. 先将最后元素和0号元素交换位置
            heapSize--; // 2. heapSize减少一个
            maxHeapify(nums, 0, heapSize); // 3. 堆化,参数一定要找对！
        }
        return nums[0];
    }

    private void maxHeapify(int[] nums, int i, int heapSize) { // 堆化是从上往下的
        int left = 2 * i + 1, right = 2 * i +
            2, maxIndex = i;
        if (left < heapSize && nums[left] > nums[maxIndex]) // 这里只是找出最大的索引
            maxIndex = left;
        if (right < heapSize && nums[right] > nums[maxIndex])
            maxIndex = right;
        if (maxIndex != i) { // 如果索引发生变化，则交换
            swap(nums, maxIndex, i);
            maxHeapify(nums, maxIndex, heapSize);
        }
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; --i) { // 将非叶子节点进行堆化
            maxHeapify(nums, i, heapSize);
        }
    }

    private void swap(int[] nums, int maxIndex, int i) {
        int temp = nums[maxIndex];
        nums[maxIndex] = nums[i];
        nums[i] = temp;
    }
```



### 【×】环形子数组的最大和

这题一共有两种情况（也就是相当于比上一题多了一种最大子数组和是首尾连接的情况）
下面的这个子数组指最大和的子数组

第一种情况：这个子数组不是环状的，就是说首尾不相连。
第二种情况：这个子数组一部分在首部，一部分在尾部，我们可以将这第二种情况转换成第一种情况

所以这最大的环形子数组和 = `max(最大子数组和，数组总和-最小子数组和)`

统一注释，`total`为数组的总和，`maxSum`为最大子数组和，`minSum`为最小子数组和，`curMax`为包含当前元素的最大子数组和，`curMin`为包含当前元素的最小子数组和

```java
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0, maxSum = nums[0], minSum = nums[0], curMax = 0, curMin = 0;
        for (int num : nums) {
            curMax = Math.max(curMax + num, num);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + num, num);
            minSum = Math.min(minSum, curMin);
            sum += num;
        }
        return maxSum > 0 ? Math.max(sum - minSum, maxSum) : maxSum;
    }
```



前缀和+最小值队列

```java
    public int maxSubarraySumCircular(int[] nums) {
        int N = nums.length;
        int[] preSum = new int[2 * N + 1];
        for (int i = 0; i < 2 * N; i++)
            preSum[i + 1] = preSum[i] + nums[i % N];

        int res = nums[0];
        // 最小值队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(0);
        for (int i = 1; i < preSum.length; i++) {
            if (queue.peekFirst() < i - N)
                queue.pollFirst();

            res = Math.max(res, preSum[i] - preSum[queue.peekFirst()]);

            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i])
                queue.pollLast();

            queue.offerLast(i);
        }
        return res;
    }
```



### 【×】最大子矩阵和

返回一个数组 `[r1, c1, r2, c2]`，其中 `r1`, `c1` 分别代表子矩阵左上角的行号和列号，`r2`, `c2` 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

**示例：**

```
输入：
[
   [-1,0],
   [0,-1]
]
输出：[0,1,0,1]
解释：输入中标粗的元素即为输出所表示的矩阵
```



将二维压缩成一维，然后计算每层累计的数组的最大和（和上一题一样）

```java
public int[] getMaxMatrix(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[] compress = new int[n];
    int[] res = new int[4];
    int startRow = 0, startCol = 0;
    int maxSum = Integer.MIN_VALUE, sum;
    for (int i = 0; i < m; i++) {
        Arrays.fill(compress, 0);
        for (int j = i; j < m; j++) {
            sum = 0;
            for (int k = 0; k < n; k++) {
                compress[k] += matrix[j][k];
                if (sum > 0) {
                    sum += compress[k];
                } else {
                    sum = compress[k];
                    startRow = i;
                    startCol = k;
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    res[0] = startRow;
                    res[1] = startCol;
                    res[2] = j;
                    res[3] = k;
                }
            }
        }
    }
    return res;
}
```



### 3. 最长连续序列

```java
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
```

我们的做法可以是从当前元素`num`开始，一次查找`num+1, num+2, ... num+y`是否在数字中，如果都在那么个最后的答案就是`y`。那么这个其实不是最优的，因为如果当前元素是`num+2`的话，那么找到`num+y`，这算一次了。但是如果走到`num`，还要找一遍`num+1, num+2, ... num+y`。那么前面走的那一遍就白走了。所以我们就直接找的最小的元素`num`，从`num`开始`+1`的找。既然`num`是数组中的最小值，那么数组中肯定不存在`num-1`，所以`num-1`就是我们判断最小值的条件。如果`num-1`在数组里，`num`就不是最小值，跳过。如果`num-1`不在数组中，那么`num`就是最小值，这个时候开始往上找。

```java
public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int currentNum = num
                    
                int currentLongest = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentLongest += 1;
                }
                res = Math.max(res, currentLongest);
            }
        }
        return res;
    }
```



### 4. 乘积最大子数组

给你一个整数数组 `nums` ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

**示例 1:**

```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```

**示例 2:**

```
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
```

由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值`imin`。对于当前数字，不管正负，都要分别×最大乘积和最小乘积，然后选最大的当结果。

```java
    public int maxProduct(int[] nums) {
        int imax = nums[0], imin = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int i_max = imax, i_min = imin;
            imax = Math.max(i_min * nums[i], Math.max(i_max * nums[i], nums[i]));
            imin = Math.min(i_max * nums[i], Math.min(i_min * nums[i], nums[i]));
            max = i_max > max ? i_max : max;
        }
        return max;
    }
```







### 5. 下一个排列

注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：

- 我们要找到下一个排列，比当前的排列要大，并且增幅要小，那么我们就要尽量把排列的后几位数字让他升序排布，例如（12384567）肯定比（12384576）要小的。我们的结果要往这样的一个趋势构造。
- 那我们怎么决定从哪位开始给末尾的数字进行升序排布呢？我们从后往前遍历元素值，直到找到破坏递增顺序的第一个元素，那么这个元素就是最远不满足末尾数字升序排布的临界位置。
- 如果我们直接将临界值之后的元素，反转，使其变成递增的序列，那么我们可能得到的结果比原排列还要小，比如，原排列1238**5764**，反转后1238**5467**，我们要反转后的结果比原排列要大的话，那么我们还需要交换元素，交换元素的原则就是从后面递增的序列中，选择第一个比分界点值大的元素，这样增幅也是最小的，并交换，那么最终结果就是答案了。最终结果1238**6457**。

<img src="https://assets.leetcode-cn.com/solution-static/31/31.gif" alt="fig1" style="zoom:67%;" />

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int p = nums.length - 2;
        while (p >= 0 && nums[p] >= nums[p + 1]) p--;
        reverse(nums, p + 1, nums.length - 1);
        int j = p + 1;
        if (j != 0) {
            while (j < nums.length) {
                if (nums[j] > nums[p]) break;
                else j++;
            }
            swap(nums, p, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i ,j);
            i++;
            j--;
        }
    }
}
```





### 6. 荷兰国旗

给定一个包含红色、白色和蓝色、共 `n` 个元素的数组 `nums` ，**[原地](https://baike.baidu.com/item/原地算法)**对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 `0`、 `1` 和 `2` 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。

**示例 1：**

```
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```

**示例 2：**

```
输入：nums = [2,0,1]
输出：[0,1,2]
```

和快排的`partition`思想一样，正常的状态下，**两个指针`i,j`分别指向`1，2`序列的第一个元素。如果`1，2`没都出现的时候，那么`i, j`两个指针是指在一起的**。初始状态下`i = 0, j = 0`。

```java
public void sortColors(int[] nums) {
        int i = 0, j = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == 0) {
                swap(nums, i, k);
                if (i < j) {
                    swap(nums, j, k);
                }
                i++;
                j++;
            } else if (nums[k] == 1) {
                swap(nums, j, k);
                j++;
            }
        }
    }
```



### 7. 摆动排序

给你一个整数数组 `nums`，将它重新排列成 `nums[0] < nums[1] > nums[2] < nums[3]...` 的顺序。

方法：

- 使用快速选择，选择好中位数
- 使用3-way-partition将小于中位数的数字都放在中位数的左边，大于中位数的数字放在中位数的右边
- 逆序分别选择两半数字，进行插入组成摆动的排序

```java
class Solution {
    private Random random = new Random();
    public void wiggleSort(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 找出中位数
        int mid_index = quickSelect(nums, left, right, left + (right - left) / 2);
        // 左右两边分开
        threePartPartition(nums, mid_index);
        System.out.println(Arrays.toString(nums));
        // 交缠在一起
        int[] leftPart = Arrays.copyOfRange(nums, left, mid_index + 1);
        int[] rightPart = Arrays.copyOfRange(nums, mid_index + 1, nums.length);

        System.out.println(Arrays.toString(leftPart));
        System.out.println(Arrays.toString(rightPart));

        int len = 0, m = leftPart.length - 1, n = rightPart.length - 1;
        boolean fromLeft = true;
        while (len < nums.length) {
            if (fromLeft) {
                nums[len] = leftPart[m--];
                fromLeft = false;
            } else {
                nums[len] = rightPart[n--];
                fromLeft = true;
            }
            len++;
        }

        System.out.println(Arrays.toString(nums));
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int index = randomPartition(nums, start, end);
        if (k == index)
            return index;
        else if (k < index)
            return quickSelect(nums, start, index - 1, k);
        else
            return quickSelect(nums, index + 1, end, k);
    }

    private int randomPartition(int[] nums, int left, int right) {
        int random_index = random.nextInt(right - left + 1);
        swap(nums, right, left + random_index);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int start, int end) {
        int i = start - 1, j = start, r = nums[end];
        for (; j < end; j++) {
            if (nums[j] < r) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, end);
        return i;
    }

    private void threePartPartition(int[] nums, int mid) {
        int r = nums[mid], i = 0, j = 0, end = nums.length - 1;
        for (; j < end; j++) {
            if (nums[j] < r) {
                swap(nums, i++, j++);
            } else if (nums[j] > r) {
                swap(nums, end--, j);
            } else {
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```



### 8. 数组中的逆序对

归并排序并统计前一个元素大于后一个元素的数量

```java
    private int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        this.tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);

    }

    private int mergeSort(int l, int r) {
        if (l >= r)
            return 0;
        int mid = l + (r - l) / 2;
        return mergeSort(l, mid) + mergeSort(mid + 1, r) + merge(l, mid, r);
    }

    private int merge(int l, int mid, int r) {
        int res = 0;
        for (int i = l ; i <= r; i++)
            tmp[i] = nums[i];
        int p1 = l, p2 = mid + 1, p = l;
        while (p1 <= mid && p2 <= r) {
            if (tmp[p1] > tmp[p2]) {
                nums[p++] = tmp[p2++];
                res += mid - p1 + 1;
            }
            else
                nums[p++] = tmp[p1++];
        }
        while (p1 <= mid)
            nums[p++] = tmp[p1++];
        while (p2 <= r)
            nums[p++] = tmp[p2++];
        return res;
    }
```



### 9. 缺失的第一个正数

给你一个未排序的整数数组 `nums` ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 `O(n)` 并且只使用常数级别 额外空间的解决方案。

**示例 1：**

```
输入：nums = [1,2,0]
输出：3
```

**示例 2：**

```
输入：nums = [3,4,-1,1]
输出：2
```

**示例 3：**

```
输入：nums = [7,8,9,11,12]
输出：1
```

最早知道这个思路是在《剑指 Offe》这本书上看到的，感兴趣的朋友不妨做一下这道问题：剑指 Offer 03. 数组中重复的数字。下面简要叙述：

- 由于题目要求我们「只能使用常数级别的空间」，而要找的数一定在 [1, N + 1] 左闭右闭（这里 N 是数组的长度）这个区间里。因此，我们可以就把原始的数组当做哈希表来使用。事实上，哈希表其实本身也是一个数组；
- 我们要找的数就在 [1, N + 1] 里，最后 N + 1 这个元素我们不用找。因为在前面的 N 个元素都找不到的情况下，我们才返回 N + 1；
- 那么，我们可以采取这样的思路：就把 1 这个数放到下标为 0 的位置， 2 这个数放到下标为 1 的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第 1 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数。
- 这个思想就相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 `i` 的数映射到下标为 `i - 1` 的位置。

```java
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int num, int num1) {
        int tmp = nums[num];
        nums[num] = nums[num1];
        nums[num1] = tmp;
    }
```



### 和最小的K个数对

```
输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
输出: [1,2],[1,4],[1,6]
解释: 返回序列中的前 3 对数：
     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

不用求出全部的数对然后崽选出k个。先放一部分。

```java
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(indexPair -> nums1[indexPair[0]] + nums2[indexPair[1]]));
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++)
            queue.offer(new int[]{i, 0});
        while (k-- > 0 && !queue.isEmpty()) {
            int[] pair = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pair[0]]);
            list.add(nums2[pair[1]]);
            res.add(list);
            if (pair[1] + 1 < n) {
                queue.offer(new int[]{pair[0], pair[1] + 1});
            }
        }
        return res;
    }
```



### 最大的异或值

异或运算的性质

解决这个问题，我们首先需要利用异或运算的一个性质：**如果 a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立。**

这道题找最大值的思路是这样的：因为两两异或可以得到一个值，在所有的两两异或得到的值中，一定有一个最大值，我们推测这个最大值应该是什么样的？即根据“最大值”的存在性解题（一定存在）。于是有如下思考：

1、二进制下，我们希望一个数尽可能大，即希望越高位上越能够出现“1”，这样这个数就是所求的最大数，这是贪心算法的思想。

2、于是，我们可以从最高位开始，到最低位，首先假设高位是 “1”，把这 n 个数全部遍历一遍，看看这一位是不是真的可以是“1”，否则这一位就得是“0”，判断的依据是上面“异或运算的性质”，即下面的第 3 点；

3、如果 a ^ b = max 成立 ，max 表示当前得到的“最大值”，那么一定有 max ^ b = a 成立。我们可以先假设当前数位上的值为 “1”，再把当前得到的数与这个 n 个数的 前缀（因为是从高位到低位看，所以称为“前缀”）进行异或运算，放在一个哈希表中，再依次把所有 前缀 与这个假设的“最大值”进行异或以后得到的结果放到哈希表里查询一下，如果查得到，就说明这个数位上可以是“1”，否则就只能是 0（看起来很晕，可以看代码理解）。

一种极端的情况是，这 n 个数在某一个数位上全部是 0 ，那么任意两个数异或以后都只能是 0，那么假设当前数位是 1 这件事情就不成立。

4、如何得到前缀，可以用掩码（mask），掩码可以进行如下构造，将掩码与原数依次进行 “与” 运算，就能得到前缀。

```java
    public int findMaximumXOR(int[] nums) {
        int mask = 0, res = 0;
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int targetMax = res | (1 << i);
            for (int prefix : set) {
                if (set.contains(targetMax ^ prefix)) {
                    res = targetMax;
                    break;
                }
            }
        }
        return res;
    }
```



### 排序数组中只出现一次的数字

方法一：位运算方法

方法二：二分搜索

难点是如何挪动左右指针！

如果两个连续的数字相等，那么一定是第一个数在偶数的索引，第二个数在奇数索引的位置上。

那么只出现一次的数字也是在偶数的索引上。

那么只出现一次的数字打破这个规律后，两个连续相等的数字第一个数字一定是在奇数索引位置上，第二个数在偶数索引上。

所以就是看当前位置是偶数索引还是奇数索引来决定移动哪个指针？

```java
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[mid + 1]) {
                if (mid % 2 == 1) right = mid;
                else left = mid + 1;
            } else {
                if (mid % 2 == 0) right = mid;
                else left = mid + 1;
            }
        }
        return nums[left];
    }
```



### 划分为k个相等的子集

给定一个整数数组 `nums` 和一个正整数 `k`，找出是否有可能把这个数组分成 `k` 个非空子集，其总和都相等。

**示例 1：**

```
输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
输出： True
说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
```

**示例 2:**

```
输入: nums = [1,2,3,4], k = 3
输出: false
```

```java
    private boolean[] visited;
    private int subsetSum;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 既然要分成K个相等和的子集，那么nums所有元素的和应该能背K整除，不能整除的，肯定分不成相等和的子集。
        if (sum % k != 0)
            return false;
        visited = new boolean[nums.length];
        subsetSum = sum / k;
        return backtracking(nums, k, 0, 0);
    }

    private boolean backtracking(int[] nums, int k, int curSubsetSum, int curIndex) {
        // 当K为1时，说明前K-1个子集都已经凑成了。sum还能把K整除，所以剩下的这最后一个的K肯定能构成。
        if (k == 1)
            return true;
        // 当前和等于子集和的时候，说明当前已经找到了一个子集，那么k--，其他变量重新开始进行搜索。
        if (curSubsetSum == subsetSum)
            return backtracking(nums,k - 1, 0, 0);
        // 逐元素搜索，将符合条件的元素加到curSubsetSum中，进行递归。
        for (int i = curIndex; i < nums.length; i++) {
            if (!visited[i] && curSubsetSum + nums[i] <= subsetSum) {
                visited[i] = true;
                if (backtracking(nums, k, curSubsetSum + nums[i], i + 1))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
```



### 子数组最大平均数

给你一个由 `n` 个元素组成的整数数组 `nums` 和一个整数 `k` 。请你找出平均数最大且 **长度为 `k`** 的连续子数组，并输出该最大平均数。任何误差小于 `10-5` 的答案都将被视为正确答案。

**示例 1：**

```
输入：nums = [1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
```

**示例 2：**

```
输入：nums = [5], k = 1
输出：5.00000
```

滑动窗口解法：

```java
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0, maxAvg = -10000000;
        int start = 0, end = 0;
        while (end < nums.length) {
            sum += nums[end];
            if (end - start + 1 == k) {
                maxAvg = Math.max(maxAvg, sum / k);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return maxAvg;
    }
```



### 和大于等于target的长度最小的子数组

给定一个含有 `n` 个正整数的数组和一个正整数 `target` **。**

找出该数组中满足其和 `≥ target` 的长度最小的 **连续子数组** `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0` 。

**示例 1：**

```
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

**示例 2：**

```
输入：target = 4, nums = [1,4,4]
输出：1
```

**示例 3：**

```
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
```

滑动窗口解法：

```java
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0, res = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
```



### 无重复数字的最长子数组的和

本题对应力扣的[1695. 删除子数组的最大得分](https://leetcode-cn.com/problems/maximum-erasure-value/)

给一个数组，求出其中的最长连续子数组的和，且子数组不包含重复元素。

**示例 1：**

```
输入：nums = [4,2,4,5,6]
输出：17
解释：最优子数组是 [2,4,5,6]
```

**示例 2：**

```
输入：nums = [5,2,1,2,5,2,1,2,5]
输出：8
解释：最优子数组是 [5,2,1] 或 [1,2,5]
```

滑动窗口解法：（和无重复字符的最长子串思想一样）

```java
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0, maxScore = Integer.MIN_VALUE;
        int start = 0, end = 0;
        while (end < nums.length) {
             if (end > 0) {
                 set.remove(nums[start]);
                 sum -= nums[start];
                 start++;
             }
             while (end < nums.length && !set.contains(nums[end])) {
                 set.add(nums[end]);
                 sum += nums[end];
                 end++;
             }
             maxScore = Math.max(maxScore, sum);
        }
        return maxScore;
    }
```



### 字典树排序

给你一个整数 `n` ，按字典序返回范围 `[1, n]` 内所有整数。

你必须设计一个时间复杂度为 `O(n)` 且使用 `O(1)` 额外空间的算法。

**示例 1：**

```
输入：n = 13
输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
```

**示例 2：**

```
输入：n = 2
输出：[1,2]
```

深度优先：

```java
    private List<Integer> res;
    public List<Integer> lexicalOrder(int n) {
        res = new ArrayList<>();
        dfs(0, 1, n);
        return res;
    }

    private void dfs(int base, int start, int n) {
        if (base > n)
            return;
        for (int i = start; i < 10; i++) {
            int num = base + i;
            if (num <= n) {
                res.add(num);
                dfs(num * 10, 0, n);
            }
        }
    }
```





# 二. 链表

### 1. 链表中返回中间节点

```Java
public class OutBDNode {
    /**
     * 无头结点链表，奇数节点个数返回中间节点，偶数节点个数返回下中间节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrLowMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 无头结点链表，奇数节点个数返回中间节点，偶数节点个数返回上中间节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrUpMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 带头结点链表，奇数节点个数返回中间节点，偶数节点个数返回上中间前一个节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrUpMidPreNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow.next : slow;
    }

    /**
     * 带头结点链表，奇数节点个数返回中间节点，偶数节点个数返回下中间前一个节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrDownMidPreNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow : slow.next;
    }
}

```



### 2. BST转换成排序的循环双向链表

`left`指向前驱节点，`right`指向后继节点

```java
public TreeListNode treeToDoublyList(TreeListNode root) {
    if (root == null)
        return null;
    dfs(root);
    head.left = tail;
    tail.right = head;
    return head;
}

private void dfs(TreeListNode root) {
    if (root == null) return;
    dfs(root.left);
    if (tail != null)
        tail.right = root;
    else
        head = root;
    root.left = tail;
    tail = root;
    dfs(root.right);
}
```



### 单向链表的快速排序

**算法思想**：对于一个链表，以head节点的值作为key，然后遍历之后的节点，可以得到一个小于key的链表和大于等于key的链表；由此递归可以对两个链表分别进行快速。这里用到了快速排序的思想即经过一趟排序能够将小于key的元素放在一边，将大于等于key的元素放在另一边。

```java
    //快速排序
    public static void quickSort(ListNode begin, ListNode end){
        if(begin == null || begin == end)
            return;

        ListNode index = paration(begin, end);
        quickSort(begin, index);
        quickSort(index.next, end);
    }

    /**
     * 划分函数，以头结点值为基准元素进行划分
     * @param begin
     * @param end
     * @return
     */
    public static ListNode paration(ListNode begin, ListNode end){
        if(begin == null || begin == end)
            return begin;

        int val = begin.val;  //基准元素
        ListNode index = begin, cur = begin.next;

        while(cur != end){
            if(cur.val < val){  //交换
                index = index.next;
                int tmp = cur.val;
                cur.val = index.val;
                index.val = tmp;
            }
            cur = cur.next;
        }


        begin.val = index.val;
        index.val = val;

        return index;
    }
```



### 双向链表的快速排序

```java
/**
 * 双向链表快排
 */
public class DoubleLinkedListQuickSort {

    static class Node {
        int value;
        Node pre;
        Node next;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.value);
            }
            return this.value + "->" + this.next.toString();
        }
    }

    /**
     * 参数为头节点和尾节点
     */
    public static void quickSort(Node head, Node tail) {
        if (head == null || tail == null || head == tail || head.next == tail) {
            return;
        }

        if (head != tail) {
            Node mid = getMid(head, tail);
            quickSort(head, mid);
            quickSort(mid.next, tail);
        }
    }

    public static Node getMid(Node start, Node end) {
        int base = start.value;
        while (start != end) {
            while(start != end && base <= end.value) {
                end = end.pre;
            }
            start.value = end.value;
            while(start != end && base >= start.value) {
                start = start.next;
            }
            end.value = start.value;
        }
        start.value = base;
        return start;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(2);
        Node node5 = new Node(1);
        Node node6 = new Node(0);
        node1.next = node2;
        node2.pre = node1;
        node2.next = node3;
        node3.pre = node2;
        node3.next = node4;
        node4.pre = node3;
        node4.next = node5;
        node5.pre = node4;
        node5.next = node6;
        node6.pre = node5;
        System.out.println("Origin link: " + node1);
        quickSort(node1, node6);
        System.out.println("Sorted link: " + node1);
    }
}
```



### 链表两数相加(高位头结点)

给你两个 **非空** 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

**示例1：**

<img src="https://pic.leetcode-cn.com/1626420025-fZfzMX-image.png" alt="img" style="zoom:50%;" />

```
输入：l1 = [7,2,4,3], l2 = [5,6,4]
输出：[7,8,0,7]
```

**示例2：**

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[8,0,7]
```

**示例3：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

用栈和各位模拟加法：

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = new ListNode(-1);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            carry += stack1.isEmpty() ? 0 : stack1.pop();
            carry += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(carry % 10);
            carry /= 10;
            node.next = ans.next;
            ans.next = node;
        }
        if (carry == 1) {
            ListNode carryNode = new ListNode(1);
            carryNode.next = ans.next;
            ans.next = carryNode;
        }
        return ans.next;
    }
```



### 两两交换链表中的节点

给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

**示例 1：**

<img src="https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg" alt="img" style="zoom:67%;" />

```
输入：head = [1,2,3,4]
输出：[2,1,4,3]
```

**示例 2：**

```
输入：head = []
输出：[]
```

**示例 3：**

```
输入：head = [1]
输出：[1]
```

方法一：迭代法

<img src="https://assets.leetcode-cn.com/solution-static/24/1.png" alt="img" style="zoom: 25%;" />

<img src="https://assets.leetcode-cn.com/solution-static/24/3.png" alt="img" style="zoom:25%;" />

<img src="https://assets.leetcode-cn.com/solution-static/24/4.png" alt="img" style="zoom:25%;" />

<img src="https://assets.leetcode-cn.com/solution-static/24/5.png" alt="img" style="zoom:25%;" />

<img src="https://assets.leetcode-cn.com/solution-static/24/6.png" alt="img" style="zoom:25%;" />

```java
    public ListNode swapPairs(ListNode head) {
        ListNode newNode = new ListNode(-1, head);
        ListNode tmp = newNode;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tmp = node1;
        }
        return newNode.next;
    }
```

方法二：递归法

可以通过递归的方式实现两两交换链表中的节点。

递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。

如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。

用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。最后返回新的链表的头节点 newHead。

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
```



### 7. 复制带随机指针的链表

先用一个循环把新旧链表对应的两个结点捆绑在一个`HashMap`二元组里，然后再用一个循环完成对新链表每个结点的`next`域和`random`域的赋值，学习到了！

```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        for (; node != null; node = node.next) {
            map.put(node, new Node(node.val));
        }
        node = head;
        for (; node != null; node = node.next) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
        }
        return map.get(head);
    }
}

```

这种空间复杂度和时间复杂度都是`O(n)`。

方法二的做法也很常见，构建+拆分链表。我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表 $A \rightarrow B \rightarrow C$，我们可以将其拆分为 $A→A'→B→B'→C→C'$。对于任意一个原节点 $S$，其拷贝节点$ S'$即为其后继节点。这样，我们可以直接找到每一个拷贝节点 $S'$的随机指针应当指向的节点，即为其原节点 $S$ 的随机指针指向的节点 $T$ 的后继节点$ T'$。需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。

```java
class Solution {
    public Node copyRandomList(Node head) {
        return getNodeInPlace(head);
    }

    public Node getNodeInPlace(Node head) {
        if (head == null)
            return head;
        // 将复制的节点放置在源节点的后面 A -> A' -> B -> B'
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }
        // 把random域串起来
        for (Node node = head; node != null; node = node.next.next)
            node.next.random = (node.random != null) ? node.random.next : null;
        // 把源节点，复制节点分开成两个链表
        Node newhead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = newNode.next;
            newNode.next = (newNode.next != null) ? newNode.next.next : null;
        }
        return newhead;
    }
}
```



### 排序链表

归并排序版本

```java
public ListNode sortRecurAndMerge(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next; // 错误一：应该是head.next
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode leftPart = sortRecurAndMerge(head);
        ListNode rightPart = sortRecurAndMerge(tmp);
        ListNode newList = new ListNode(-1);
        ListNode pointer = newList;
        while (leftPart != null && rightPart != null) {
            if (leftPart.val < rightPart.val) {
                pointer.next = leftPart;
                leftPart = leftPart.next;
            } else {
                pointer.next = rightPart;
                rightPart = rightPart.next;
            }
            pointer = pointer.next;
        }
        pointer.next = leftPart == null ? rightPart : leftPart;
        return newList.next;
    }

    public ListNode sortList(ListNode head) {
        return sortRecurAndMerge(head);
    }
```

迭代版本

```java
/**
     * 迭代原地排序排序
     * @param head
     * @return
     */
    public ListNode sortIterative(ListNode head) {
        if (head == null)
            return head;
        int len = 0;
        ListNode count = head;
        while (count != null) {
            len++;
            count = count.next;
        }
        ListNode dummyNode = new ListNode(-1, head);
        for (int subLen = 1; subLen < len; subLen = subLen * 2) {
             ListNode pre = dummyNode, cur = dummyNode.next;
             while (cur != null) {
                 ListNode head1 = cur;
                 for (int i = 1; i < subLen && cur.next != null; i++) {
                     cur = cur.next;
                 }
                 ListNode head2 = cur.next;
                 cur.next = null;
                 cur = head2;
                 for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                     cur = cur.next;
                 }
                 ListNode next = null;
                 if (cur != null) {
                     next = cur.next;
                     cur.next = null;
                 }
                 ListNode sortedSubList = mergeTwoLists(head1, head2);
                 pre.next = sortedSubList;
                 while (pre.next != null) {
                     pre = pre.next;
                 }
                 cur = next; // 错误二：不用将所有节点都连上，将cur指向剩余的借点。
             }
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(-1);
        ListNode pointer = newList, leftPart = l1, rightPart = l2;
        while (leftPart != null && rightPart != null) {
            if (leftPart.val < rightPart.val) {
                pointer.next = leftPart;
                leftPart = leftPart.next;
            } else {
                pointer.next = rightPart;
                rightPart = rightPart.next;
            }
            pointer = pointer.next;
        }
        pointer.next = leftPart == null ? rightPart : leftPart;
        return newList.next;
    }

    public ListNode sortList(ListNode head) {
        return sortIterative(head);
    }
```



### 删除排序链表中的重复元素 II

给定一个已排序的链表的头 `head` ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。

```
示例 1：

输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

就一个cur指针指向头结点，然后判断`cur.next`和`cur.next.next`的值是否相等。

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newNode = new ListNode(-1);
        newNode.next = head;
        ListNode cur = newNode;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val != cur.next.next.val) {
                cur = cur.next;// cur指向下一个
            } else {
                int num = cur.next.val;// 记录重复的节点值
                while (cur.next != null && cur.next.val == num) {
                    cur.next = cur.next.next;
                }// 开始一个个删掉
            }
        }
        return newNode.next;
    }
}
```



### 二叉搜索树与双向循环链表的转换

思路：二叉树搜索时中序遍历有序，那么我们从中序遍历开始。既然是双向循环链表，那么需要两个指针分别指向头部节点`head`，和尾部节点`tail`。双向链表的构造过程是`tail.right = cur; cur.left = tail;` 然后`tail = cur`。`tail`指针向后移动一个。最后`cur`指针为空时，所有节点的双向链构造完成，最后将头尾相连。

```java
    TreeListNode head, tail;
    public TreeListNode treeToDoublyList(TreeListNode root) {
        if (root == null)
            return null;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private void dfs(TreeListNode root) {
        if (root == null) return;
        dfs(root.left);
        if (tail != null)
            tail.right = root;
        else
            head = root;
        root.left = tail;
        tail = root;
        dfs(root.right);
    }
```



### K个一组翻转链表

```java
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newList = new ListNode(-1), pre = newList;
        ListNode start = head, end = head;
        while (end != null) {
            int count = 0;
            while (end != null && count != k - 1) {
                end = end.next;
                count++;
            }
            if (end == null) {
                pre.next = start;
            } else {
                ListNode newStart = end.next;
                end.next = null;
                ListNode oldStart = reversePartListNode(start);
                pre.next = oldStart;
                while (pre.next != null) {
                    pre = pre.next;
                }
                start = newStart;
                end = newStart;
            }
        }
        return newList.next;
    }

    private ListNode reversePartListNode(ListNode start) {
        if (start.next == null)
            return start;
        ListNode remainNode = reversePartListNode(start.next);
        start.next.next = start;
        start.next = null;
        return remainNode;
    }
```



### 排序的循环链表中插入节点

```java
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node node = head;
        Node insertedNode = new Node(insertVal);
        while (true) {
            // 情况一：在顺序位置插入新节点
            if (node.next.val >= insertVal && node.val <= insertVal)
                break;
            // 情况二：在最大值和最小值中间
            if (node.next.val < node.val) {
                // 大于最大值的节点或者小于最小值的节点
                if (node.val <= insertVal || node.next.val >= insertVal)
                    break;
            }
            // 情况三：只有一个节点的情况下直接break
            if (node.next == head)
                break;
            node = node.next;
        }
        insertedNode.next = node.next;
        node.next = insertedNode;
        return head;
    }
```



### 差分数组

<img src="https://mmbiz.qpic.cn/sz_mmbiz_jpg/gibkIz0MVqdGFL8VaGGr0vzRcmibenAMtM7WPic7ibdW5LJEcJnaUnz45Kc6Uy1ozVaSWBGSfLGzAJxL1ziaAG9WhgQ/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:33%;" />

通过这个`diff`差分数组是可以反推出原始数组`nums`的，代码逻辑如下：

```java
int[] res = new int[diff.length];
// 根据差分数组构造结果数组
res[0] = diff[0];
for (int i = 1; i < diff.length; i++) {
    res[i] = res[i - 1] + diff[i];
}
```

**这样构造差分数组`diff`，就可以快速进行区间增减的操作**，如果你想对区间`nums[i..j]`的元素全部加 3，那么只需要让`diff[i] += 3`，然后再让`diff[j+1] -= 3`即可：

<img src="https://mmbiz.qpic.cn/sz_mmbiz_jpg/gibkIz0MVqdGFL8VaGGr0vzRcmibenAMtMrmOvrjeoHOICN9ZsZD2CMn4oruTVicz6evf6fflQxPTqSnkO5bpQ7CQ/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:33%;" />

```java
// 差分数组工具类
class Difference {
    // 差分数组
    private int[] diff;

    /* 输入一个初始数组，区间操作将在这个数组上进行 */
    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        // 根据初始数组构造差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /* 给闭区间 [i,j] 增加 val（可以是负数）*/
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    /* 返回结果数组 */
    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
```



### 扁平化多级双向链表

<img src="https://assets.leetcode.com/uploads/2021/11/09/flatten11.jpg" class="css-btcloa-ZoomImage e13l6k8o5" style="zoom: 50%;" >

```java
class Solution {
    public Node flatten(Node head) {
        flattenNode(head);
        return head;
    }

    private Node flattenNode(Node head) {
        Node last = head;
        while (head != null) {
            if (head.child == null) {
                last = head;
                head = head.next;
            } else {
                Node tmp = head.next;
                Node childLast = flattenNode(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;
                if (childLast != null) childLast.next = tmp;
                if (tmp != null) tmp.prev = childLast;
                last = head;
                head = childLast;
            }
        }
        return last;
    }
}
```



# 三. 字符串

### 1. 翻转单词顺序

```
输入: "the sky is blue"
输出: "blue is sky the"
```

**示例 2：**

```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```

**示例 3：**

```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```

代码实现，双指针

```java
    public String reverseWords(String s) {
        s = s.trim();
        int end = s.length() - 1, start = end;
        StringBuilder stringBuilder = new StringBuilder();
        while (start >= 0) {
            while (start >= 0 && s.charAt(start) != ' ') start--;
            stringBuilder.append(s, start + 1, end + 1).append(' ');
            while (start >= 0 && s.charAt(start) == ' ') start--;
            end = start;
        }
        return stringBuilder.toString().trim();
    }
```



### 2. 滑动窗口框架

最长***模板

```java
// 最长模板：初始化res, bestRes, left, right
while(右指针没有到结尾) {
    窗口扩大，加入right对应元素，更新当前res
    while(res不满足条件) {
        缩小窗口，移除left对应元素，left右移
    }
    更新最优结果bestRes
    right++;
}
return bestRes;
```

最短***模板

```java
// 最短模板：初始化res, bestRes, left, right
while(右指针没有到结尾) {
    窗口扩大，加入right对应元素，更新当前res
    while(res满足条件) {
        更新最优结果bestRes
        缩小窗口，移除left对应元素，left右移
    }
    right++;
}
return bestRes;
```



### 无重复字符的最长子串

**滑动窗口法：**

```java
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int right = 0, res = 0, left = 0;
        while (right < s.length()){
            if (right != 0)
                set.remove(s.charAt(left++));
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
```

**位置记忆：**

```java
HashMap<Character, Integer> map = new HashMap<>();
int res = 0, left = 0;
for (int i = 0; i < s.length(); i++) {
    if (map.containsKey(s.charAt(i)))
        left = Math.max(left, map.get(s.charAt(i)) + 1);
    map.put(s.charAt(i), i);
    res = Math.max(res, i - left + 1);
}
return res;
```



### 最小覆盖子串

给你一个字符串 `s` 、一个字符串 `t` 。返回 `s` 中涵盖 `t` 所有字符的最小子串。如果 `s` 中不存在涵盖 `t` 所有字符的子串，则返回空字符串 `""` 。

**注意：**

- 对于 `t` 中重复字符，我们寻找的子字符串中该字符数量必须不少于 `t` 中该字符数量。
- 如果 `s` 中存在这样的子串，我们保证它是唯一的答案。

**示例 1：**

```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```

**示例 2：**

```
输入：s = "a", t = "a"
输出："a"
```

**示例 3:**

```
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
```

滑动窗口解法：

```java
class minWindowSolution {

    private HashMap<Character, Integer> tStateMap;
    private HashMap<Character, Integer> winStateMap;

    public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        tStateMap = new HashMap<>();
        winStateMap = new HashMap<>();
        for (Character c : t.toCharArray())
            tStateMap.put(c, tStateMap.getOrDefault(c, 0) + 1);
        int end = -1, start = 0, minLen = Integer.MAX_VALUE;
        int ansEnd = -1, ansStart = -1;
        while (end < sLen) {
            // 开始扩张右边界
            end++;
            if (end < sLen && tStateMap.containsKey(s.charAt(end))) {
                winStateMap.put(s.charAt(end), winStateMap.getOrDefault(s.charAt(end), 0) + 1);
            }
            // 不断地将右边界指向的元素加入到winStateMap中去，直到t中的所有字符都在winStateMap中，则开始
            // 缩小左边界！

            // check()是判断t中的所有字符都在winStateMap中有记录？返回true的话说明当前窗口里所有数值已经都包括
            // 了t中的所有字符,那么就开始选择最小的覆盖子串。返回false的话说明当前窗口里所有数值没有包含t串的所有
            // 字符还需要往里添加。

            while (checkTInWinMap() && start <= end) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    ansStart = start;
                    ansEnd = end;
                }

                // 开始缩小左边界，在这个while循环里，保证右边界不发生缩放
                if (tStateMap.containsKey(s.charAt(start))) {
                    winStateMap.put(s.charAt(start), winStateMap.getOrDefault(s.charAt(start), 0) - 1);
                }
                start++;
            }
        }
        return ansStart == -1 ? "" : s.substring(ansStart, ansEnd + 1);
    }

    private boolean checkTInWinMap() {
        for (Map.Entry<Character, Integer> entry : tStateMap.entrySet()) {
            if (winStateMap.getOrDefault(entry.getKey(), 0) < entry.getValue())
                return false;
        }
        return true;
    }
}
```



### 字符串转整数

注意事项：

- 大于最大值和小于最小值的越界问题

  ```java
  if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < (currChar - '0')))
      return Integer.MAX_VALUE;
  if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10)))
      return Integer.MIN_VALUE;
  ```

- 判断空格，符号的操作不能和判断数字的代码放在一个循环里，应该将判断空格和符号的操作单独进行，避免先是数字之后，再出现空格，符号等情况！

  ```java
  "00000 +12ed" / "+-12"
  ```

  上面类似的情况，如果把判断符号和空格的操作放在主体循环里面，也会返回包含的数字。这是不符合题目条件的。题目严格要求空格 符号 数字 这三个顺序。

```java
public int myAtoi(String s) {
        int len = s.length(), index = 0, sign = 1, num = 0;
        while (index < len && s.charAt(index) == ' ') index++;
        if (index < len && (s.charAt(index) == '-' || s.charAt(index) == '+')) {
            sign = s.charAt(index) == '-' ? -sign : sign;
            index++;
        }
        // num > 2^31 - 1
        while (index < len) {
            char currChar = s.charAt(index);
            if (currChar > '9' || currChar < '0')
                break;
            if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < (currChar - '0')))
                return Integer.MAX_VALUE;
            if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10)))
                return Integer.MIN_VALUE;
            num = num * 10 + sign * (currChar - '0');
            index++;
        }
        return num;
    }
```



### 整数转换英文

整个求解过程分为两部分，一个是求1000之内的表达，然后第二个就是求有多少个1000倍。

```java
class Solution {
    private String[] thousands;
    private String[] digits;
    private String[] tens;
    private String[] tys;

    public Solution() {
        thousands = new String[]{"", "Thousand", "Million", "Billion"};
        digits = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        tens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        tys = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    }

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3, unit = 1_000_000_000; i >= 0; i--, unit /= 1_000) {
            int curNum = num / unit;
            if (curNum != 0) {
                String name = getHundredName(curNum);
                stringBuilder.append(name).append(" ").append(thousands[i]).append(" ");
                num %= unit;
            }
        }
        return stringBuilder.toString().trim();
    }

    private String getHundredName(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(digits[num / 100]).append(" Hundred").append(" ");
            num %= 100;
        }
        if (num >= 20 && num < 100) {
            sb.append(tys[num / 10]).append(" ").append(digits[num % 10]).append(" ");
        }
        if (num >= 10 && num < 20) {
            sb.append(tens[num % 10]).append(" ");
        }
        if (num > 0 && num < 10){
            sb.append(digits[num]).append(" ");
        }
        if (num == 0){
            sb.append(digits[num]);
        }
        return sb.toString().trim();
    }
}
```



### 单词拆分

给你一个字符串 `s` 和一个字符串列表 `wordDict` 作为字典。请你判断是否可以利用字典中出现的单词拼接出 `s` 。

**注意：**不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

**示例 1：**

```
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
```

**示例 2：**

```
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。
```

**示例 3：**

```
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
```

这个题是带有查询的回溯

一。 回溯法

```java
   private Map<Integer, Boolean> map;
    private Set<String> set;
    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        map = new HashMap<>();
        return dfs(s,0);
    }

    private boolean dfs(String s, int start) {
        if (start == s.length())
            return true;
        if (map.containsKey(start))
            return map.get(start);
        for (int i = start; i < s.length(); i++) {
            if (set.contains(s.substring(start, i + 1)) && dfs(s, i + 1)) {
                map.put(start, true);
                return true;
            }
        }
        map.put(start, false);
        return false;
    }
```

二。 动态规划
```java
    private boolean dynamicProgramming(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
```



### 通配符匹配

给定一个字符串 (`s`) 和一个字符模式 (`p`) ，实现一个支持 `'?'` 和 `'*'` 的通配符匹配。

```
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
```

两个字符串**完全匹配**才算匹配成功。

**说明:**

- `s` 可能为空，且只包含从 `a-z` 的小写字母。
- `p` 可能为空，且只包含从 `a-z` 的小写字母，以及字符 `?` 和 `*`。

**示例 1:**

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
```

**示例 3:**

```
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
```

**示例 4:**

```
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
```

**示例 5:**

```
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```

**动态规划法：**

```java
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = true;
            else break;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (Character.isLetter(p.charAt(j - 1)))
                    dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
```



### 数字解码为字母

设当前位置为`i`，`s[i]`的选择分两种情况：

第一种是，`s[i]`单独接在解码的后面，独立成帮派，那么这个时候的状态等于`dp[i] + dp[i - 1]`;

第二种是，`s[i]`和`s[i - 1]`拼在一起接在解码的后面，这个时候`s[i - 1]s[i]`要在10-26之间（表示字母），那么这个时候还要加上`dp[i - 2]`的状态。

```java
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <=n; i++) {
            int digit = s.charAt(i - 1) - '0';
            if (digit != 0)
                dp[i] = dp[i] + dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) - '0') * 10 + digit >= 10 && (s.charAt(i - 2) - '0') * 10 + digit <= 26)
                dp[i] = dp[i] + dp[i - 2];
        }
        return dp[n];
    }
```



### 字符串解码

中括号内的字符串为要重复的内容，中括号前面的数字为重复次数，按照这个方式解码字符串

```java
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
输入：s = "3[a2[c]]"
输出："accaccacc"
```

既然涉及编码嵌套的问题，那么第一反应使用`递归或者栈`来做。下面的方法使用栈来实现。

```java
    public String decodeString(String s) {
        Deque<Integer> nStack = new LinkedList<>();
        Deque<String> sStack = new LinkedList<>(){{add("");}};
        for (int i = 0; i < s.length(); i++) {
            while (i < s.length() && Character.isLetter(s.charAt(i)))
                sStack.push(sStack.pop() + s.charAt(i++));
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                int k = 0;
                while (Character.isDigit(s.charAt(i)))
                    k = k * 10 + s.charAt(i++) - '0';
                nStack.push(k);
            }
            if (i < s.length() && s.charAt(i) == '[')
                sStack.push("");
            if (i < s.length() && s.charAt(i) == ']') {
                String popedStr = sStack.pop();
                sStack.push(sStack.pop() + popedStr.repeat(nStack.pop()));
            }
        }
        return sStack.pop();
    }
```



### 字符串的排列

如下题！



### 字符串中的变位词

```java
    public boolean checkInclusion(String s1, String s2) {
         int[] win = new int[26];
         for (int i = 0; i < s1.length(); i++) {
             win[s1.charAt(i) - 'a']++;
         }
         int start = 0, end = 0;
         while (end < s2.length()) {
             win[s2.charAt(end) - 'a']--;
             while (win[s2.charAt(end) - 'a'] < 0) {
                 win[s2.charAt(start) - 'a']++;
                 start++;
             }
             if (end - start + 1 == s1.length()) {
                 return true;
             }
             end++;
         }
         return false;
    }
```



### 字符串中的所有变位词

```java
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;
        int[] sFreq = new int[26];
        int[] pFreq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
        }
        int start = 0, end = 0;
        while (end < s.length()) {
            int c = s.charAt(end) - 'a';
            sFreq[c]++;
            while (sFreq[c] > pFreq[c]) {
                sFreq[s.charAt(start) - 'a']--;
                start++;
            }
            if (end - start + 1 == p.length()) {
                res.add(start);
            }
            end++;
        }
        return res;
    }
```



### 最长回文子串

中心扩展法

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expendAroundCenter(s, i, i);
            int len2 = expendAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expendAroundCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;
    }
}
```



### 回文子串的个数

动态规划法：如果`s[i] == s[j]`，那么`[i, j]`区间是否会构成回文串的条件是`s[i + 1, j - 1]`是不是回文串。并且考虑`[i, j]`区间长度小于2的时候是否是回文子串

```java
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i < 2 || dp[i + 1][j - 1]))) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
```

中心扩张法：以`s[i]`和`s[i, i + 1]`作为核心，分别向两段扩张，如果两段的字符相等，那么这就是一个回文子串。

```java
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = getParCounts(s, res, i, i);
            res = getParCounts(s, res, i, i + 1);
        }
        return res;
    }

    private int getParCounts(String s, int res, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            res++;
            left--;
            right++;
        }
        return res;
    }
```



### 最少回文分割

给定一个字符串 `s`，请将 `s` 分割成一些子串，使每个子串都是回文串。

返回符合要求的 **最少分割次数** 。

**示例 1：**

```
输入：s = "aab"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
```

同样是两种方法：要求出最小的回文分割数，首先要确定能最少分割成多少个回文子串。最少回文串个数减去1就是最小回文分割数。

**中心扩展法：**定义一个一维的`dp`数组，`dp`的一维数组只负责找最短的切割方法，即最少的回文子串。`i`是待判字符串的末端指针，`j`为待判字符串的起始指针，那么`dp[i]`代表字符串s中以i结尾的最小回文子串的数量。并且`dp[i]`的初始应改为`i`，代表最坏情况下字符串中每个字符都不一样，这样最小的回文串个数就是`i`个。如果`s[j, i]`是回文串的话，那么`dp[i]`应该为`dp[i]`和`dp[j]` + 1中最小的那一个。`dp[j]`表示上一个回文子串的个数，而`s[j, i]`是当前构成的回文子串。

```java
    public int minCut(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome(s, j, i - 1)) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[s.length()] - 1;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
```

**二维`dp`法：**空间换时间，先构造二维数组，存所有`j到i（0<=j<=i）`范围判断是不是回文子串。

```java
    public int minCut(String s) {
        // 这个就是空间换时间的部分，与上一解法的isPalindrome功能一样
        boolean[][] dp_state = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || dp_state[j + 1][i - 1])) {
                    dp_state[j][i] = true;
                }
            }
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (dp_state[j][i - 1]) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[s.length()] - 1;
    }
```



### 分割所有的回文字符串

给定一个字符串 `s` ，请将 `s` 分割成一些子串，使每个子串都是 **回文串** ，返回 s 所有可能的分割方案。

```
输入：s = "google"
输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
```

解决思路：求出字符串中所有的子回文串的情况，很显然使用回溯方法来做。还是得有一个辅助的数据结构或函数来判断`s[i, j]`是否是回文串，还是使用二维`dp`或者两边缩小法来判断。方便起见，这里只使用了二维`dp`的方法。

**二维`dp`法：**

```java
    private List<List<String>> res;
    private boolean[][] dp;
    public String[][] partition(String s) {
        res = new ArrayList<>();
        char[] charArray = s.toCharArray();
        // 构造二维状态
        dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (charArray[i] == charArray[j] && (j - i <= 2 || dp[i + 1][j - 1]))
                    dp[i][j] = true;
            }
        }
        // 开始遍历所有情况
        dfs(charArray, 0, new ArrayList<>());
        
        // 转化为结果
        String[][] ans = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = new String[res.get(i).size()];
            for (int j = 0; j < ans[i].length; j++) {
                ans[i][j] = res.get(i).get(j);
            }
        }
        return ans;
    }

    private void dfs(char[] charArray, int index, ArrayList<String> path) {
        if (index == charArray.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < charArray.length; i++) {
            if (dp[index][i]) {
                path.add(new String(charArray, index, i - index + 1));
                dfs(charArray, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }
```



### 索引处的解码字符串

给定一个编码字符串 `S`。请你找出 **解码字符串** 并将其写入磁带。解码时，从编码字符串中 **每次读取一个字符** ，并采取以下步骤：

- 如果所读的字符是字母，则将该字母写在磁带上。
- 如果所读的字符是数字（例如 `d`），则整个当前磁带总共会被重复写 `d-1` 次。

现在，对于给定的编码字符串 `S` 和索引 `K`，查找并返回解码字符串中的第 `K` 个字母。

**示例 1：**

```
输入：S = "leet2code3", K = 10
输出："o"
解释：
解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
字符串中的第 10 个字母是 "o"。
```

**示例 2：**

```
输入：S = "ha22", K = 5
输出："h"
解释：
解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。
```

**示例 3：**

```
输入：S = "a2345678999999999999999", K = 1
输出："a"
解释：
解码后的字符串为 "a" 重复 8301530446056247680 次。第 1 个字母是 "a"。
```

**思路**

如果我们有一个像 `appleappleappleappleappleapple` 这样的解码字符串和一个像 `K=24` 这样的索引，那么如果 `K=4`，答案是相同的。

一般来说，当解码的字符串等于某个长度为 `size` 的单词重复某些次数（例如 `apple` 与 `size=5` 组合重复`6`次）时，索引 `K` 的答案与索引 `K % size` 的答案相同。

我们可以通过逆向工作，跟踪解码字符串的大小来使用这种洞察力。每当解码的字符串等于某些单词 `word` 重复 `d` 次时，我们就可以将 `k` 减少到 `K % (Word.Length)`。

**算法**

首先，找出解码字符串的长度。之后，我们将逆向工作，跟踪 `size`：解析符号 `S[0], S[1], ..., S[i]` 后解码字符串的长度。

如果我们看到一个数字 `S [i]`，则表示在解析 `S [0]，S [1]，...，S [i-1]` 之后解码字符串的大小将是 `size / Integer(S[i])`。 否则，将是 `size - 1`。

```java
    public String decodeAtIndex(String s, int k) {
        long length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                length++;
            else
                length *= s.charAt(i) - '0';
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= length;
            if (k == 0 && Character.isLetter(c))
                return String.valueOf(c);
            if (Character.isDigit(c))
                length /= c - '0';
            else
                length--;
        }
        return "";
    }
```



### 重构字符串

给定一个字符串 `s` ，检查是否能重新排布其中的字母，使得两相邻的字符不同。

返回 *`s` 的任意可能的重新排列。若不可行，返回空字符串 `""`* 。

**示例 1:**

```
输入: s = "aab"
输出: "aba"
```

**示例 2:**

```
输入: s = "aaab"
输出: ""
```

方法一：先统计出每个字符出现的频率，然后求出出现频率最大的那个字符。如果最大频率大于`（n + 1）/ 2`，那么说明必有两个字符重新排布后会相邻。如果频率小于`（n + 1）/ 2`，那么我们就两个两个取出字符，进行拼凑。

```java
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            maxCount = Math.max(maxCount, map.get(s.charAt(i)));
        }
        if (maxCount > (s.length() + 1) / 2)
            return "";
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));
        for (Character key : map.keySet()) {
            queue.offer(key);
        }
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char a = queue.poll();
            char b = queue.poll();
            sb.append(a).append(b);
            if (map.get(a) - 1 != 0) {
                map.put(a, map.get(a) - 1);
                queue.offer(a);
            }
            if (map.get(b) - 1 != 0) {
                map.put(b, map.get(b) - 1);
                queue.offer(b);
            }
        }
        if (queue.size() == 1)
            sb.append(queue.poll());
        return sb.toString();
    }
```

方法二：基于计数的贪心

首先统计每个字母的出现次数，然后根据每个字母的出现次数重构字符串。

当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2 时，出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。

维护偶数下标 `evenIndex` 和奇数下标 `oddIndex`，初始值分别为 0 和 1。遍历每个字母，根据每个字母的出现次数判断字母应该放置在偶数下标还是奇数下标。

首先考虑是否可以放置在奇数下标。根据上述分析可知，只要字母的出现次数不超过字符串的长度的一半（即出现次数小于或等于 n/2），就可以放置在奇数下标，只有当字母的出现次数超过字符串的长度的一半时，才必须放置在偶数下标。字母的出现次数超过字符串的长度的一半只可能发生在 n 是奇数的情况下，且最多只有一个字母的出现次数会超过字符串的长度的一半。

因此通过如下操作在重构的字符串中放置字母。

如果字母的出现次数大于 `0` 且小于或等于 `n/2`，且 `oddIndex` 没有超出数组下标范围，则将字母放置在 `oddIndex`，然后将 `oddIndex` 的值加 2。

如果字母的出现次数大于 `n/2`，或 `oddIndex` 超出数组下标范围，则将字母放置在 `evenIndex`，然后将 `evenIndex` 的值加 2。

如果一个字母出现了多次，则重复上述操作，直到该字母全部放置完毕。

```java
class Solution {
    public String reorganizeString(String s) {
        if (s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }
}
```



### 移掉K位数字

给你一个以字符串表示的非负整数 `num` 和一个整数 `k` ，移除这个数中的 `k` 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。

**示例 1 ：**

```
输入：num = "1432219", k = 3
输出："1219"
解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
```

**示例 2 ：**

```
输入：num = "10200", k = 1
输出："200"
解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
```

**示例 3 ：**

```
输入：num = "10", k = 2
输出："0"
解释：从原数字移除所有的数字，剩余为空就是 0 。
```

**思路**：把高位的数字降低，则对新整数的值影响最大

**把高位的数字降低**：把原整数的所有数字从左到右进行比较，如果发现某一位数字大于它右边的数字，那么在删除该数字后，必然会使该数位的值降低，因为右面比它小的数字顶替了它的位置
（从左向右遍历，删除第1个比右侧数字大的数）

```java
    public String removeKdigits(String num, int k) {
        int len = num.length();
        Deque<Character> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (!queue.isEmpty() && c < queue.peekLast() && k > 0) {
                queue.pollLast();
                k--;
            }
            queue.offerLast(c);
        }
        for (int i = 0; i < k; i++)
            queue.pollLast();
        StringBuilder stringBuilder = new StringBuilder();
        boolean isLeading = true;
        while (!queue.isEmpty()) {
            char c = queue.pollFirst();
            if (isLeading && c == '0')
                continue;
            isLeading = false;
            stringBuilder.append(c);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }
```



### 形成三的最大倍数

给你一个整数数组 `digits`，你可以通过按任意顺序连接其中某些数字来形成 **3** 的倍数，请你返回所能得到的最大的 3 的倍数。

由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。

如果无法得到答案，请返回一个空字符串。

**示例 1：**

```
输入：digits = [8,1,9]
输出："981"
```

**示例 2：**

```
输入：digits = [8,6,7,1,0]
输出："8760"
```

**示例 3：**

```
输入：digits = [1]
输出：""
```

**示例 4：**

```
输入：digits = [0,0,0,0,0,0]
输出："0"
```

思路：
1.能被3整除的条件：所有位上的数字加起来，能被3整除

2.如果模3余1，就找个模3余1，或者2个模3余2，的扔掉就ok

3.如果模3余2，就找个模3余2，或者2个模3余1，的扔掉就ok

4.因为结果是尽量大。所以我们从小到大遍历（从0到9）
先消耗小的digit

```java
    public String largestMultipleOfThree(int[] digits) {
        int[] digitCnt = new int[10];
        int[] modCnt = new int[3];
        int totalSum = 0;
        for (int digit : digits) {
            totalSum += digit;
            digitCnt[digit]++;
            modCnt[digit % 3]++;
        }
        int delMod = 0, delCnt = 0;
        if (totalSum % 3 == 1) {
            if (modCnt[1] >= 1) {
                delMod = 1;
                delCnt = 1;
            } else {
                delMod = 2;
                delCnt = 2;
            }
        } else if (totalSum % 3 == 2) {
            if (modCnt[2] >= 1) {
                delMod = 2;
                delCnt = 1;
            } else {
                delMod = 1;
                delCnt = 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < digitCnt[i]; j++) {
                if (delCnt > 0 && i % 3 == delMod)
                    delCnt--;
                else sb.append((char) (i + '0'));
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0')
            return "0";
        return sb.reverse().toString();
    }
```



# 四. 设计

### 1. Knuth洗牌算法

**共有 n 个不同的数，根据每个位置能够选择什么数，共有n! 种组合。**

**题目要求每次调用 shuffle 时等概率返回某个方案，或者说每个元素都够等概率出现在每个位置中。**

我们可以使用 Knuth 洗牌算法，在O(n) 复杂度内等概率返回某个方案。

具体的，我们从前往后尝试填充 [0,n−1] 该填入什么数时，通过随机当前下标与（剩余的）哪个下标进行值交换来实现。

对于下标x而言，我们从[x,n−1] 中随机出一个位置与 x 进行值交换，当所有位置都进行这样的处理后，我们便得到了一个公平的洗牌方案。

对于下标为0位置，从 [0,n−1] 随机一个位置进行交换，共有 n种选择；下标为 1的位置，从[1,n−1] 随机一个位置进行交换，共有n−1 种选择

**公平性验证**

为什么能做到保证：对于生成的排列，每一个元素都能等概率的出现在每一个位置了。

在这里，我们模拟一下算法的执行过程，同时，对于每一步，计算一下概率值。

我们简单的只是用 5 个数字进行模拟。假设初始的时候，是按照 1，2，3，4，5 进行排列的。

<img src="https://img-blog.csdnimg.cn/20201012223322565.png#pic_center" alt="在这里插入图片描述" style="zoom: 50%;" />

那么，根据这个算法，首先会在这五个元素中选一个元素，和最后一个元素 5 交换位置。假设随机出了 2。
<img src="https://img-blog.csdnimg.cn/20201012223335362.png#pic_center" alt="在这里插入图片描述" style="zoom: 50%;" />
下面，我们计算 2 出现在最后一个位置的概率是多少？非常简单，因为是从 5 个元素中选的嘛，就是 1/5。实际上，根据这一步，任意一个元素出现在最后一个位置的概率，都是 1/5。
<img src="https://img-blog.csdnimg.cn/20201012223350428.png#pic_center" alt="在这里插入图片描述" style="zoom: 50%;" />

下面，根据这个算法，我们就已经不用管 2 了，而是在前面 4 个元素中，随机一个元素，放在倒数第二的位置。假设我们随机的是 3。3 和现在倒数第二个位置的元素 4 交换位置。

<img src="https://img-blog.csdnimg.cn/20201012223404679.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />

下面的计算非常重要。3 出现在这个位置的概率是多少？计算方式是这样的：
<img src="https://img-blog.csdnimg.cn/20201012223415750.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />
其实很简单，因为 3 逃出了第一轮的筛选，概率是 4/5，但是 3 没有逃过这一轮的选择。在这一轮，一共有4个元素，所以 3 被选中的概率是 1/4。因此，最终，3 出现在这个倒数第二的位置，概率是 4/5 * 1/4 = 1/5。

还是 1/5 !

实际上，用这个方法计算，任意一个元素出现在这个倒数第二位置的概率，都是 1/5。

<img src="https://img-blog.csdnimg.cn/2020101222344739.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />

假设我们随机的是 1。
<img src="https://img-blog.csdnimg.cn/20201012223500161.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />

关键是：1 出现在这个位置的概率是多少？计算方式是这样的：

<img src="https://img-blog.csdnimg.cn/20201012223519130.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />

即 1 首先在第一轮没被选中，概率是 4/5，在第二轮又没被选中，概率是 3/4 ，但是在第三轮被选中了，概率是 1/3。乘在一起，4/5 * 3/4 * 1/3 = 1/5。

用这个方法计算，任意一个元素出现在中间位置的概率，都是 1/5。

<img src="https://img-blog.csdnimg.cn/20201012223530369.png#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />

这个过程继续，现在，我们只剩下两个元素了，在剩下的两个元素中，随机选一个，比如是4。将4放到第二个位置。

然后，4 出现在这个位置的概率是多少？4 首先在第一轮没被选中，概率是 4/5；在第二轮又没被选中，概率是 3/4；第三轮还没选中，概率是 2/3，但是在第四轮被选中了，概率是 1/2。乘在一起，4/5 * 3/4 * 2/3 * 1/2 = 1/5。

用这个方法计算，任意一个元素出现在第二个位置的概率，都是 1/5。

最后，就剩下元素5了。它只能在第一个位置呆着了。

那么 5 留在第一个位置的概率是多少？即在前 4 轮，5 都没有选中的概率是多少？

在第一轮没被选中，概率是 4/5；在第二轮又没被选中，概率是 3/4；第三轮还没选中，概率是 2/3，在第四轮依然没有被选中，概率是 1/2。乘在一起，4/5 * 3/4 * 2/3 * 1/2 = 1/5。

<img src="https://img-blog.csdnimg.cn/20201012223553605.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzIzMjk1NQ==,size_16,color_FFFFFF,t_70#pic_center" alt="在这里插入图片描述" style="zoom:50%;" />
算法结束。

你看，在整个过程中，每一个元素出现在每一个位置的概率，都是 1/5 ！

所以，这个算法是**公平**的。整个算法的复杂度是 O(n) 的。

同样的思路，我们也完全可以从前向后依次决定每个位置的数字是谁。不过从前向后，代码会复杂一些，因为生成 [0, i] 范围的随机数比生成 [i, n) 范围的随机数简单，直接对 i+1 求余就好了。

```java
class Solution {

    private int[] initial;
    private int[] nums;
    public Solution(int[] nums) {
        initial = Arrays.copyOf(nums, nums.length);
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.initial;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int length = nums.length;
        Random rnd = new Random();
        for (int i = length - 1 ; i > 0 ; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        return nums;
    }
}
```



### 2. 并查集

https://www.runoob.com/data-structures/union-find-basic.html

一、概念及其介绍

并查集是一种树型的数据结构，用于处理一些不相交集合的合并及查询问题。

并查集的思想是用一个数组表示了整片森林（parent），树的根节点唯一标识了一个集合，我们只要找到了某个元素的的树根，就能确定它在哪个集合里。

二、适用说明

并查集用在一些有 **N** 个元素的集合应用问题中，我们通常是在开始时让每个元素构成一个单元素的集合，然后按一定顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中。这个过程看似并不复杂，但数据量极大，若用其他的数据结构来描述的话，往往在空间上过大，计算机无法承受，也无法在短时间内计算出结果，所以只能用并查集来处理。

我们将用另外一种方式实现并查集。把每一个元素，看做是一个节点并且指向自己的父节点，根节点指向自己。如下图所示，节点 3 指向节点 2，代表 3 和 2 是连接在一起的，节点2本身是根节点，所以指向自己。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-01.png" alt="img" style="zoom:50%;" />

同样用数组表示并查集，但是下面一组元素用 **parent** 表示当前元素指向的父节点，每个元素指向自己，都是独立的。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-02.png" alt="img" style="zoom:50%;" />

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-03.png" alt="img" style="zoom:50%;" />

如果此时操作 **union(4,3)**，将元素 4 指向元素 3：

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-04.png" alt="img" style="zoom:50%;" />

数组也进行相应改变：

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-05.png" alt="img" style="zoom: 50%;" />

判断两个元素是否连接，只需要判断根节点是否相同即可。

如下图，节点 4 和节点 9 的根节点都是 8，所以它们是相连的。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-06.png" alt="img" style="zoom:50%;" />

连接两个元素，只需要找到它们对应的根节点，使根节点相连，那它们就是相连的节点。

假设要使上图中的 6 和 4 相连，只需要把 6 的根节点 5 指向 4 的根节点 8 即可。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/quickUnion-07.png" alt="img" style="zoom:50%;" />

构建这种指向父节点的树形结构， 使用一个数组构建一棵指向父节点的树，parent[i] 表示 i 元素所指向的父节点。

```java
...
private int[] parent;
private int count;  // 数据个数
...
```

查找过程, 查找元素 p 所对应的集合编号，不断去查询自己的父亲节点, 直到到达根节点，根节点的特点 parent[p] == p，O(h) 复杂度, h 为树的高度。

```java
...
private int find(int p){
  assert(p >= 0 && p < count);
  while(p != parent[p])
    p = parent[p];
  return p;
}
...
```

合并元素 p 和元素 q 所属的集合，分别查询两个元素的根节点，使其中一个根节点指向另外一个根节点，两个集合就合并了。这个操作是 O(h) 的时间复杂度，h 为树的高度。

```java
public void unionElements(int p, int q){
  int pRoot = find(p);
  int qRoot = find(q);
  if( pRoot == qRoot )
    return;
  parent[pRoot] = qRoot;
}
```

整合版代码：

```Java
package runoob.union;
/**
 * 第二版unionFind
 */
public class UnionFind2 {
    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;
    private int count;  // 数据个数
    // 构造函数
    public UnionFind2(int count){
        parent = new int[count];
        this.count = count;
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < count ; i ++ )
            parent[i] = i;
    }
    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        assert( p >= 0 && p < count );
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while( p != parent[p] )
            p = parent[p];
        return p;
    }
    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }
    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if( pRoot == qRoot )
            return;
        parent[pRoot] = qRoot;
    }
}
```



三、基于`size`的优化

如果树的层相对较高，当元素数量增多时，产生的消耗就会相对较大。解决这个问题其实很简单，在进行具体指向操作的时候先进行判断，**把元素少的集合根节点指向元素多的根节点，能更高概率的生成一个层数比较低的树**。

构造并查集的时候需要多一个参数，**`sz`** 数组，**`sz[i]`** 表示以 `i` 为根的集合中元素个数。

```java
class UnionFind {
    private int[] parents;
     // 数据个数
    private int count;
    // sz[i]表示以i为根的集合中元素个数
    private int[] sz;

    public UnionFind(int totalNodes) {
        count = totalNodes;
        parents = new int[totalNodes];
        sz = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
            sz[i] = 1;
        }
    }
    
    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if( pRoot == qRoot )
            return;
        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if( sz[pRoot] < sz[qRoot] ){
            parents[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else{
            parents[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
    
    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    int find(int node) {
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[node] == node
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parent[p] = parent[parent[p]];
            node = parent[p];  // 路径压缩
        }
        return node;
    }
    
    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
```

四、基于`rank`的优化

上一小节介绍了并查集基于 size 的优化，但是某些场景下，也会存在某些问题，如下图所示，操作 union(4,2)。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/rank-01.png" alt="img" style="zoom:67%;" />

根据上一小节，size 的优化，元素少的集合根节点指向元素多的根节点。操完后，层数变为4，比之前增多了一层，如下图所示：

<img src="https://www.runoob.com/wp-content/uploads/2020/10/rank-02.png" alt="img" style="zoom:67%;" />

依靠集合的 size 判断指向并不是完全正确的，更准确的是，根据两个集合层数，具体判断根节点的指向，层数少的集合根节点指向层数多的集合根节点，如下图所示，这就是基于 rank 的优化。我们在并查集的属性中，添加 rank 数组，rank[i] 表示以 i 为根的集合所表示的树的层数。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/rank-03.png" alt="img" style="zoom: 67%;" />

```java
class UnionFind {
    private int[] parents;
     // 数据个数
    private int count;
    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind(int totalNodes) {
        count = totalNodes;
        parents = new int[totalNodes];
        rank = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }
    
    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if( pRoot == qRoot )
            return;
        if( rank[pRoot] < rank[qRoot] ){
            parent[pRoot] = qRoot;
        }
        else if( rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 维护rank的值
        }
    }
    
    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    int find(int node) {
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[node] == node
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parents[node] = parents[parents[node]];
            node = parents[node];  // 路径压缩
        }

        return node;
    }
    
    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
```

五、路径压缩

上面的路径优化过程如下图所示：

如下图中，find(4) 的过程就可以路径压缩，让树的层数更少。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/compress-01.png" alt="img" style="zoom:67%;" />

节点 4 往上寻找根节点时，压缩第一步，树的层数就减少了一层：

<img src="https://www.runoob.com/wp-content/uploads/2020/10/compress-02.png" alt="img" style="zoom:67%;" />

节点 2 向上寻找，也不是根节点，那么把元素 2 指向原来父节点的父节点，操后后树的层数相应减少了一层，同时返回根节点 0。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/compress-03.png" alt="img" style="zoom:67%;" />

上述路径压缩并不是最优的方式，我们可以把最初的树压缩成下图所示，层数是最少的。

<img src="https://www.runoob.com/wp-content/uploads/2020/10/conpress-04.png" alt="img" style="zoom:67%;" />

第二种路径压缩方式是使用递归：

```java
public int find(int p) {
    //第二种路径压缩算法
    if (p != parent[p])
        parent[p] = find(parent[p]);
    return parent[p];
}
```



### LRU

- 这题虽然是让你写一种缓存算法，但其实是纯粹的数据结构考察，做题时先给面试官讲一遍`LRU`和`LinkedHashMap`的八股，再自己使用双向链表实现就好，考虑自己写输入输出
- 初始化的时候就建好链表的`head`和`tail`，能显著减少代码的复杂度
- 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
- 哈希表即为普通的哈希映射`（HashMap）`，通过缓存数据的键映射到其在双向链表中的位置。
- 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 `O(1)` 的时间内完成 `get` 或者 `put` 操作。

```java
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class DoubleLinkedList {
        int key; //双链表中加入键的原因是方便在map中获得该节点
        int val;
        DoubleLinkedList preNode;
        DoubleLinkedList nextNode;

        public DoubleLinkedList() {}

        public DoubleLinkedList(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, DoubleLinkedList> map;
    DoubleLinkedList head;
    DoubleLinkedList tail;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DoubleLinkedList();
        tail = new DoubleLinkedList();
        head.nextNode = tail;
        tail.preNode = head;
        this.size = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            moveToHead(map.get(key));
            return map.get(key).val;
        } else
            return -1;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = map.get(key);
        if (node == null) {
            DoubleLinkedList newNode = new DoubleLinkedList(key, value);
            map.put(key, newNode);
            insertToHead(newNode);
            size++;
            if (size > capacity) {
                DoubleLinkedList deletedNode = deleteFromTail();
                map.remove(deletedNode.key); //这一步不能忘了，不光从链表中删除节点，map中也要删除节点
                size--;
            }
        }
        else {
            moveToHead(node);
            node.val = value;
        }
    }

    private DoubleLinkedList deleteFromTail() {
        DoubleLinkedList delete = tail.preNode;
        deleteNode(delete);
        return delete;
    }

    private void deleteNode(DoubleLinkedList delete) {
        delete.nextNode.preNode = delete.preNode;
        delete.preNode.nextNode = delete.nextNode;
    }

    private void insertToHead(DoubleLinkedList newNode) {
        newNode.nextNode = head.nextNode;
        head.nextNode.preNode = newNode;
        head.nextNode = newNode;
        head.nextNode.preNode = head;
    }

    private void moveToHead(DoubleLinkedList doubleLinkedList) {
        deleteNode(doubleLinkedList);
        insertToHead(doubleLinkedList);
    }

    @Override
    public String toString() {
        DoubleLinkedList list = head.nextNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LRUCache: ");
        while (list != tail) {
            stringBuilder.append("[" + list.key + "->" + list.val + "]");
            list = list.nextNode;
        }
        return stringBuilder.toString();
    }
}
```



### LFU

**最不经常使用算法（LFU）：**这个缓存算法使用一个计数器来记录条目被访问的频率。通过使用LFU缓存算法，最低访问数的条目首先被移除。当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 **最近最久未使用** 的键。

```java
class LFUCache {
    private HashMap<Integer, Integer> keyToVal;
    private HashMap<Integer, Integer> keyToFreq;
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int capacity;
    private int minFreq;

    public LFUCache(int capacity) {
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    /**
     * 获取LFU的值
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!keyToVal.containsKey(key))
            return -1;
        addFrequency(key);
        return keyToVal.get(key);
    }

    /**
     * 增加访问频率
     *
     * @param key
     */
    private void addFrequency(int key) {
        // 更新keyToFreq
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        // 更新freqToKeys
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                minFreq += 1;
            }
        }
    }

    /**
     * 设置LFU的键值
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            addFrequency(key);
            return;
        }
        if (capacity <= keyToVal.size()) {
            removeMinFreqKey();
        }
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        minFreq = 1;
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keySet = freqToKeys.get(minFreq);
        int oldKey = keySet.iterator().next();
        keySet.remove(oldKey);
        if (keySet.isEmpty()) {
            freqToKeys.remove(minFreq);
        }
        keyToVal.remove(oldKey);
        keyToFreq.remove(oldKey);
    }
}
```



### 5. 设计一个HashSet

为了实现哈希集合这一数据结构，有以下几

个关键问题需要解决：

- 哈希函数：能够将集合中任意可能的元素映射到一个固定范围的整数值，并将该元素存储到整数值对应的地址上。

- 冲突处理：由于不同元素可能映射到相同的整数值，因此需要在整数值出现「冲突」时，需要进行冲突处理。总的来说，有以下几种策略解决冲突：

  - 链地址法：为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中。

  - 开放地址法：当发现哈希值 h 处产生冲突时，根据某种策略，从 h 出发找到下一个不冲突的位置。

  - 再哈希法：当发现哈希冲突后，使用另一个哈希函数产生一个新的地址。

- 扩容：当哈希表元素过多时，冲突的概率将越来越大，而在哈希表中查询一个元素的效率也会越来越低。因此，需要开辟一块更大的空间，来缓解哈希表中发生的冲突。

使用链地址法解决：

```java
class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }
    
    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }
    
    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
```



### 6. 设计一个HashMap

```java
class MyHashMap {
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList<Pair>[] data;

    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<>();
        }
    }
    
    public void put(int key, int value) {
        int hashKey = hash(key);
        for (Pair pair : data[hashKey]) {
            if (key == pair.getKey()) {
                pair.setValue(value);
                return;
            }
        }
        data[hashKey].offer(new Pair(key, value));
    }
    
    public int get(int key) {
        int hashKey = hash(key);
        for (Pair pair : data[hashKey]) {
            if (key == pair.getKey()) {
                return pair.getValue();
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int hashKey = hash(key);
        for (Pair pair : data[hashKey]) {
            if (key == pair.getKey()) {
                data[hashKey].remove(pair);
                return;
            }
        }
    }

    public int hash(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
```



### 二叉搜索树迭代器

迭代法的中序遍历

```java
    private Deque<TreeNode> queue;
    private TreeNode tree;
    public BSTIterator(TreeNode root) {
        tree = root;
        queue = new LinkedList<>();
    }
    
    public int next() {
        while (tree != null) {
            queue.push(tree);
            tree = tree.left;
        }
        TreeNode node = queue.pop();
        tree = node.right;
        return node.val;
    }
    
    public boolean hasNext() {
        return tree != null || !queue.isEmpty();
    }
```



### Trie树

```java
class Trie {

    private boolean isEnd;
    private Trie[] children;

    public Trie() {
        this.children = new Trie[26];
        this.isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null)
                node.children[ch - 'a'] = new Trie();
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = check(word);
        return node == null ? false : node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = check(prefix);
        return node == null ? false : true;
    }

    private Trie check(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                return null;
            }
            node = node.children[ch - 'a'];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));
    }
}
```



### O(1)时间插入，删除，随机访问一个元素

既然`O(1)`的时间复杂度，那么可以想到的数据结构有`Map`，和`List`

- `Map`可以在常数时间内进行插入和删除，不能常数级别获取随机元素，因为`Map`不支持索引访问

- `List`可以在常数时间内进行插入和随机访问一个元素，不能常数级别删除元素，删除元素需要移动`list`。

综合考虑，两者都使用！元素添加到`List`中，然后删除的时候只删除最后一个元素（将最后一个元素和要删除的元素交换位置，Map中的索引也换）。`Map`提供对删除数字的索引，所以`Map`中存的是元素和索引之间的映射。

```java
class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new LinkedList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val);
            int lastNum = list.get(list.size() - 1);
            list.set(index, lastNum);
            map.put(lastNum, index);
            map.remove(val);
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }
    
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
```



### 日程安排

安排不同时间段的任务，又开始时间和结束时间。时间不冲突的往集合添加，时间重叠的不添加到集合里面。

```java
    private TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> leftSchedule = map.floorEntry(start);
        Map.Entry<Integer, Integer> rightSchedule = map.ceilingEntry(start);
        if ((leftSchedule == null || start >= leftSchedule.getValue()) &&
                (rightSchedule == null || end <= rightSchedule.getKey())) {
            map.put(start, end);
            return true;
        }
        return false;
    }
```



### 按权重生成随机数

给定一个正整数数组 `w` ，其中 `w[i]` 代表下标 `i` 的权重（下标从 `0` 开始），请写一个函数 `pickIndex` ，它可以随机地获取下标 `i`，选取下标 `i` 的概率与 `w[i]` 成正比。

设数组 w 的权重之和为 total。根据题目的要求，我们可以看成将[1,total] 范围内的所有整数分成 n 个部分（其中 n 是数组 w 的长度），第 i个部分恰好包含 w[i] 个整数，并且这 n 个部分两两的交集为空。随后我们在 [1,total] 范围内随机选择一个整数 x，如果整数 x 被包含在第 i 个部分内，我们就返回 i。



```java
    private int[] preSum;
    private int sum;
    private Random random;
    public Solution(int[] w) {
        preSum = new int[w.length];
        preSum[0] = w[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + w[i];
        }
        sum = preSum[preSum.length - 1];
        random = new Random();
    }
    
    public int pickIndex() {
        int randNum = random.nextInt(sum) + 1;
        return getRandIndex(randNum);
    }

    private int getRandIndex(int randNum) {
        int left = 0, right = preSum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (preSum[mid] == randNum)
                return mid;
            else if (preSum[mid] < randNum)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }
```



### 区间内查询数字的频率

请你实现 `RangeFreqQuery` 类：

- `RangeFreqQuery(int[] arr)` 用下标从 **0** 开始的整数数组 `arr` 构造一个类的实例。
- `int query(int left, int right, int value)` 返回子数组 `arr[left...right]` 中 `value` 的 **频率** 。

一个 **子数组** 指的是数组中一段连续的元素。`arr[left...right]` 指的是 `nums` 中包含下标 `left` 和 `right` **在内** 的中间一段连续元素。

**示例 1：**

```
输入：
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
输出：
[null, 1, 2]

解释：
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
```

使用哈希表和二分查找，哈希表统计所有元素的下标，下标使用一个List来维护，既然下标是按顺序放到List里面的，那么List肯定是有序的。

下一步就是使用二分法搜索左右边界的下标了。找到大于等于左边界的第一个位置的下标，找到大于右边界的第一个位置的下标。返回两个下标的差就可以了。

```java
class RangeFreqQuery {

    Map<Integer, List<Integer>> map;
    public RangeFreqQuery(int[] arr) {
       map = new HashMap<>();
       for (int i = 0; i < arr.length; i++) {
           List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
           list.add(i);
           map.put(arr[i], list);
       }
    }
    
    public int query(int left, int right, int value) {
        if (!map.containsKey(value))
            return 0;
        List<Integer> idxList = map.get(value);
        int l = binarySearch(idxList, left, true, false);
        int r = binarySearch(idxList, right, false, true);
        return r - l;
    }

    private int binarySearch(List<Integer> nums, int num, boolean fl, boolean fr) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) > num)
                right = mid;
            else if (nums.get(mid) < num)
                left = mid + 1;
            else {
                if (fl)
                    right = mid;
                if (fr)
                    left = mid + 1;
            }
        }
        return left;
    }
}
```



# 五. 栈

### 出栈顺序的判断

思想：每次将入栈中的元素入栈，然后随即判断当前出栈列表中的第一个元素是不是入栈元素的栈顶元素，如果是就将出栈列表的元素下移动。

```java
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int num : pushed) {
            stack.push(num); // 加入一个元素
            while (j < pushed.length && !stack.isEmpty() && stack.peek() == popped[j]) { // 就判断有没有符合条件的出栈序列
                j++;
                stack.pop();
            }
        }
        return j == popped.length;
    }
```



### （卡特兰数）合法出栈数量

我们把`n`个元素的出栈个数的记为`f(n)`, 那么对于`1,2,3`, 我们很容易得出：

```java
f(1) = 1   //即 1
f(2) = 2   //即 12、21
f(3) = 5   //即 123、132、213、321、231
```

然后我们来考虑`f(4)`, 我们给4个元素编号为`a,b,c,d`, 那么考虑：元素`a`只可能出现在1号位置，2号位置，3号位置和4号位置(很容易理解，一共就4个位置，比如`abcd`,元素`a`就在1号位置)。

分析：

1) 如果元素a在1号位置，那么只可能a进栈，马上出栈，此时还剩元素b、c、d等待操作，就是子问题f(3)；

2) 如果元素a在2号位置，那么一定有一个元素比a先出栈，即有f(1)种可能顺序（只能是b），还剩c、d，即f(2)，   根据乘法原理，一共的顺序个数为`f(1) * f(2)`；

3) 如果元素a在3号位置，那么一定有两个元素比1先出栈，即有f(2)种可能顺序（只能是b、c），还剩d，即f(1)，

  根据乘法原理，一共的顺序个数为`f(2) * f(1)`；

4) 如果元素a在4号位置，那么一定是a先进栈，最后出栈，那么元素b、c、d的出栈顺序即是此小问题的解，即f(3)；

结合所有情况，即`f(4) = f(3) + f(2) * f(1) + f(1) * f(2) + f(3);`

为了规整化，我们定义`f(0) = 1`；于是`f(4)`可以重新写为：

`f(4) = f(0)*f(3) + f(1)*f(2) + f(2) * f(1) + f(3)*f(0)`

然后我们推广到`n`，推广思路和`n=4`时完全一样，于是我们可以得到：

`f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-1)*f(0)`

这就是卡特兰数：

**令h(0)=1,h(1)=1，**`catalan`数满足递推式：

**h(n)= h(0)\*h(n-1)+h(1)\*h(n-2) + ... + h(n-1)h(0) (n>=2)**

递推关系的解为：

**h(n)=C(2n,n)/(n+1) (n=0,1,2,...)**



### 加减法计算器

可以定义一个符号栈和数字栈；其实一个栈也可以实现，每次成双入栈，成双出栈，但是得注意两个入栈和出栈的顺序。

- 去括号，变符号版，这里只有当遇到数字结束的时候才将局部数字加到结果中。

  ```java
  public int calculate(String s) {
      Deque<Integer> ops = new LinkedList<>();
      ops.push(1);
  
      int res = 0, sign = 1, i = 0, n = s.length();
      while (i < n) {
          char c = s.charAt(i);
          if (c == ' ') i++;
          else if (c == '+') {sign = ops.peek();i++;}
          else if (c == '-') {sign = - ops.peek();i++;}
          else if (c == '(') {ops.push(sign);i++;}
          else if (c == ')') {ops.pop();i++;}
          else {
              long num = 0;
              while (i < n && Character.isDigit(s.charAt(i)))
                  num = num * 10 + (s.charAt(i++) - '0');
              res += sign * num;
          }
      }
      return res;
  }
  ```

  

- 栈的思想，有括号先算括号内的，将之前的运算结果存在栈，这里每次循环，遍历到一个字符都将局部数字加到结果中。

  ```java
      public int calculate(String s) {
          Deque<Integer> ops = new LinkedList<>();
          int res = 0, sign = 1, num = 0, n = s.length();
         for (int i = 0; i < n; i++){
              char c = s.charAt(i);
              if (c == ' ')
                  continue;
              else if (c >= '0' && c <= '9') {
                  num = num * 10 + (c - '0');
                  if (i < n - 1 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
                      continue;
              }
              else if (c == '+' || c == '-') { //将前面构成的数字num清零
                  num = 0;
                  sign = c == '+' ? 1 : -1;
              }
              else if (c == '(') { // 遇到（就意味着 开始一个新的计算了，之前计算的res和sign放入栈中，新的res和sign恢复到默认值
                  ops.push(res);
                  ops.push(sign);
                  res = 0;
                  sign = 1;
              }
              else if (c == ')') { // 遇到）就意味着结束一个计算了，将当前的结果和栈中保存的结果进行运算一次
                  sign = ops.pop();
                  num = res;
                  res = ops.pop();
              }
              res += sign * num;
          }
          return res;
      }
  ```

- 栈的思想，有括号先算括号内的，将之前的运算结果存在栈，这里只有当遇到数字结束的时候才将局部数字加到结果中。

  ```java
      public int calculate(String s) {
          Deque<Integer> ops = new LinkedList<>();
          int res = 0, sign = 1, n = s.length();
          for (int i = 0; i < n; i++) {
              char c = s.charAt(i);
              if (c == ' ')
                  continue;
              else if (c >= '0' && c <= '9') {
                  int num = 0;
                  while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9')
                      num = num * 10 + (s.charAt(i++) - '0');
                  res += sign * num;
                  i--; // while循环多加了一次
              } else if (c == '+' || c == '-') { //将前面构成的数字num清零
                  sign = c == '+' ? 1 : -1;
              } else if (c == '(') { // 遇到（就意味着 开始一个新的计算了，之前计算的res和sign放入栈中，新的res和sign恢复到默认值
                  ops.push(res);
                  ops.push(sign);
                  res = 0;
                  sign = 1;
              } else if (c == ')') { // 遇到）就意味着结束一个计算了，将当前的结果和栈中保存的结果进行运算一次
                  res *= ops.pop();
                  res += ops.pop();
              }
          }
          return res;
      }
  ```

  

### 最长有效括号

难点在于，如何区分开最长的有效括号。设置标记一直留在栈底，具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标。

- 对于遇到的每个‘(’ ，我们将它的下标放入栈中
- 对于遇到的每个‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
  如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
  如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
  我们从前往后遍历字符串并更新答案即可。

需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为 -1−1 的元素。

```java
public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
```



正逆向结合法：

在此方法中，我们利用两个计数器left 和 right 。首先，我们从左到右遍历字符串，对于遇到的每个‘(’，我们增加left 计数器，对于遇到的每个‘)’ ，我们增加 right 计数器。每当left 计数器与right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当right 计数器比left 计数器大时，我们将left 和right 计数器同时变回 00。

这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑，重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。

解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：

- 当left 计数器比right 计数器大时，我们将left 和right 计数器同时变回 00

- 当 left 计数器与right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串

  这样我们就能涵盖所有情况从而求解出答案。

```java
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
```



### 括号生成

数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

这个括号的思路和上一题的正逆向结合法很相似，就是计数左右括号的数量，来生成括号。默认`left = 0, right = 0;`

计算左括号右括号的数量，先左括号匹配，左括号数量够了，再追加右括号。

```java
class Solution {
    private List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(n, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(int n, StringBuilder path, int leftNum, int rightNum) {
        // 1. 条件满足的判断，左右括号计数等于n
        if (n * 2 == leftNum + rightNum) {
            res.add(path.toString());
        }
        // 2. 开始回溯生成（
        if (leftNum < n) {
            path.append('(');
            dfs(n, path, leftNum + 1, rightNum);
            path.deleteCharAt(path.length() - 1);
        }
        // 3. 开始回溯生成符合数量的右括号），条件是右括号要小于左括号的时候才生成
        if (rightNum < leftNum) {
            path.append(')');
            dfs(n, path, leftNum, rightNum + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```



# 六. 树

### 1. 非递归遍历

```java
public class OutBDTree {
    /**
     * 非递归-先序遍历
     * 0. 先将根节点入栈
     * 1. 弹出栈，打印元素
     * 2. 有右子树，右子树入栈
     * 3. 有左子树，左子树入栈
     *
     * @param root
     */
    public static void PreOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }

    /**
     * 非递归-后序遍历
     * 0. 先将根节点入栈
     * 1. 弹出栈，压入辅助栈中
     * 2. 有左子树，左子树入栈
     * 3. 有右子树，右子树入栈
     * 4. 辅助栈元素全部弹出
     *
     * @param root
     */
    public static void PostOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
        }
    }

    /**
     * 非递归-中序遍历
     * 1. 一直压入左子树，直到左子树为空
     * 2. 出栈，输出元素
     * 3. 当前节点赋值为右子树
     *
     * @param root
     */
    public static void InOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
        }
    }
}
```



### 2. 统计层中最多的节点数量

```Java
public static int MaxNodeNumsInLayer(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curLayerLastNode = root; // 当前层最后一个节点（最右侧节点）
        TreeNode nextLayerLastNode = null; // 下一层最后一个节点（最右侧节点）
        int curLayerNodeNums = 0; // 当前层节点数量
        int maxNodeNums = 0;  // 最多的节点数量，返回的结果
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                nextLayerLastNode = node.left;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLayerLastNode = node.right;
            }
            curLayerNodeNums++; //统计这一层节点数量
            if (node == curLayerLastNode) {
                maxNodeNums = Math.max(maxNodeNums, curLayerNodeNums);
                curLayerNodeNums = 0;
                curLayerLastNode = nextLayerLastNode;
            }
        }
        return maxNodeNums;
    }
```



### 3. 序列化二叉树

**深度（前序）优先遍历序列化**

```java
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private String rserialize(TreeNode root, String str) {
        if (root == null)
            str += "null,";
        else {
            str += root.val + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeArray = data.split(",");
        List<String> nodeList = new ArrayList<>(Arrays.asList(nodeArray));
        return rdeserialize(nodeList);
    }

    private TreeNode rdeserialize(List<String> nodeList) {
         if (nodeList.get(0).equals("null")) {
             nodeList.remove(0);
             return null;
         }
         TreeNode root = new TreeNode(Integer.parseInt(nodeList.get(0)));
         nodeList.remove(0);
         root.left = rdeserialize(nodeList);
         root.right = rdeserialize(nodeList);
         return root;
    }
```



**层序遍历序列化**

```java
public static TreeNode deserialize(String data, String SEP, String NULL) {
        if (data.isEmpty()) return null;
        String[] values = data.split(SEP);
        if (values.length == 0)
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < values.length; ) {
            TreeNode cur = queue.poll();
            String ch = values[i++];
            if (!ch.equals(NULL)) {
                TreeNode left = new TreeNode(Integer.parseInt(ch));
                cur.left = left;
                queue.offer(cur.left);
            } else {
                cur.left = null;
            }

            String ch2 = values[i++];
            if (!ch2.equals(NULL)) {
                TreeNode right = new TreeNode(Integer.parseInt(ch2));
                cur.right = right;
                queue.offer(cur.right);
            } else {
                cur.right = null;
            }
        }
        return root;
    }
```



### 4. 二叉树中序遍历的后继结点

方法一：迭代式中序遍历

因为中序遍历的顺序是，左子树，根节点，右子树。所以如果寻找p节点下一个节点的话，那么它下一个节点一定出现在右子树中。所以分两种情况讨论。

- 如果p节点**有**右子树，那么下一个节点，一定是**右子树最左侧的节点**
- 如果p节点**没有**右子树，那么它肯定是某个父祖节点的左子树（因为遍历完了），那么下一个节点，一定是**左子树出现的根节点**

```java
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode curNode = null;
        while(!queue.isEmpty() || root != null) {
            while (root != null) {
                queue.offerLast(root);
                root = root.left;
            }
            TreeNode node = queue.pollLast();
            if (curNode != null)
                return node;
            if (node.val == p.val) {
                if (node.right == null)
                    return queue.pollLast();
                else curNode = p;
            }
            root = node.right;
        }
        return null;
    }
```

方法二：指针法**（二叉搜索树！！！）**

`cur`节点指向当前节点，`res`节点指向`cur`的父节点。如果`cur`节点值大于`p`节点值，那么说明`p`的下一个节点可能就是`cur`，`res`指向`cur`。`cur`指向他左边的节点，以此寻找最小节点。如果`cur`节点值小于`p`节点值，那么`cur`就转向右子树搜索。

```java
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode res = null;
        while (cur != null) {
           if (cur.val > p.val) {
               res = cur;
               cur = cur.left;
           } else {
               cur = cur.right;
           }
        }
        return res;
    }
```



### 5. 验证二叉搜索树的后序遍历序列

[验证二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

判断整数数组 `postorder` 是否为某个二叉搜索树的后序遍历结果。

**示例 1：**

<img src="https://pic.leetcode.cn/1694762751-fwHhWX-%E5%89%91%E6%8C%8733%E7%A4%BA%E4%BE%8B1.png" alt="img" style="zoom:50%;" />

```
输入: postorder = [4,9,6,9,8]
输出: false 
解释：从上图可以看出这不是一颗二叉搜索树
```

**示例 2：**

<img src="https://pic.leetcode.cn/1694762510-vVpTic-%E5%89%91%E6%8C%8733.png" alt="img" style="zoom:50%;" />

```
输入: postorder = [4,6,5,9,8]
输出: true 
解释：可构建的二叉搜索树如上图
```

 **方法一：递归法：**

```java

dfs(postorder, 0, postorder.length() - 1);

private boolean dfs(int[] postorder, int start, int end) {
    if (start >= end)
        return true;
    int m = start;
    while (postorder[m] < postorder[end]) m++;
    int n = m;
    while (postorder[n] > postorder[end]) n++;
    return n == end && dfs(postorder, start, m - 1) && dfs(postorder, m, n - 1);
}
```

**方法二：单调栈法：**

我们先来画一个节点多一些的二叉搜索树，然后观察一下他的规律

<img src="https://pic.leetcode-cn.com/1597978800-kbKrIm-image.png" alt="image.png" style="zoom:60%;" />

他的后续遍历结果是 `[3,6,5,9,8,11,13,12,10]`，从前往后不好看，我们来从后往前看`[10,12,13,11,8,9,5,6,3]`

两个数如果`arr[i]<arr[i+1]`，那么`arr[i+1]`一定是`arr[i]`的右子节点，上面的`10`和`12`是挨着的并且`10<12`，所以`12`是`10`的右子节点。同理`12`和`13`，`8`和`9`，`5`和`6`。如果想证明也很简单，因为比`arr[i]`大的肯定都是他的右子节点，如果还是挨着他的，肯定是在后续遍历中所有的右子节点最后一个遍历的，所以他一定是`arr[i]`的右子节点。

看一下降序。如果`arr[i]>arr[i+1]`，那么`arr[i+1]`一定是`arr[0]……arr[i]`中某个节点的左子节点，并且这个值是大于`arr[i+1]`中最小的。比如`13`，`11`是降序的，那么`11`肯定是他前面某一个节点的左子节点，并且这个值是大于`11`中最小的，我们看到`12`和`13`都是大于`11`的，但`12`最小，所以`11`就是`12`的左子节点。同理`8`就是`10`的左子节点。`9`和`5`是降序，`6`和`3`是降序。

**单调递增栈**来解决。遍历数组的所有元素，如果栈为空，就把当前元素压栈。如果栈不为空，并且当前元素大于栈顶元素，说明是升序的，那么就说明当前元素是栈顶元素的右子节点，就把当前元素压栈，如果一直升序，就一直压栈**（把右子树所有的右侧节点都压入栈种）**。当前元素小于栈顶元素，说明是倒序的，**说明当前元素是某个节点的左子节点**，**我们目的是要找到这个左子节点的父节点**，**就让栈顶元素出栈**，直到栈为空或者栈顶元素小于当前值为止，**其中最后一个出栈的就是当前元素的父节点**。

```java
public boolean verifyPostorder(int[] postorder) {
    Deque<Integer> stack = new LinkedList<>();
    int root = Integer.MAX_VALUE;
    for (int i = postorder.length - 1; i >= 0; i--) {
        if (postorder[i] > root)
            return false;
        //当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙，
        //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
        while (!stack.isEmpty() && stack.peek() > postorder[i])
            root = stack.pop();
        stack.push(postorder[i]);
    }
    return true;
}
```



### 6. 路径总和（判断是否）

```
示例 1：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
输出：true

示例 2：
输入：root = [1,2,3], targetSum = 5
输出：false

示例 3：
输入：root = [1,2], targetSum = 0
输出：false
```

**代码：**

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    return dfs(root, targetSum);
}

private boolean dfs(TreeNode root, int targetSum) {
    if (root == null)
        return false;
    if (root.left == null && root.right == null && targetSum == root.val)
        return true;
    return dfs(root.left, targetSum - root.val) || dfs(root.right, targetSum - root.val);
}
```



### 7. 路径总和（输出所有路径）

**示例 1：**

<img src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg" alt="img" style="zoom:50%;" />

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
```

**示例 2：**

<img src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" alt="img" style="zoom:50%;" />

```
输入：root = [1,2,3], targetSum = 5
输出：[]
```

**示例 3：**

```
输入：root = [1,2], targetSum = 0
输出：[]
```

**代码：**

```java
public List<List<Integer>> pathSum(TreeNode root, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    dfs(root, res, target, path);
    return res;
}

private void dfs(TreeNode root, List<List<Integer>> res, int target, Deque<Integer> path) {
    if (root == null)
        return;
    path.add(root.val);
    target -= root.val;
    if (root.left == null && root.right == null && target == 0) {
        res.add(new ArrayList<>(path));
    }
    dfs(root.left, res, target, path);
    dfs(root.right, res, target, path);
    path.removeLast();
}
```



### 删除二叉搜索树中的节点

除某节点，可以用左子树的最大值或者右子树的最小值替换，这里选右子树最小值替换要删除的节点

```java
private TreeNode delete(TreeNode root, int key) {
    if (root == null)
        return root;
    if (root.val == key) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else { 
            TreeNode min = getMinNode(root.right);
            root.val = min.val;;
            root.right = delete(root.right, min.val);
        }
    } else if (root.val < key) {
        root.right = delete(root.right, key);
    } else {
        root.left = delete(root.left, key);
    }
    return root;
}
```



### 二叉搜索树的最近公共祖先

既然是二叉搜索树的祖先，那么按照二叉搜索树的规则，如果`p,q`两个节点都小于根节点，那么在左子树寻找；如果`p,q`两个节点都大于根节点，那么就在右子树中寻找；如果一个比根节点的值小，一个比根结点的值大，那么肯定就说明这`p,q`两个节点分别在不同的子树中，**当前根节点**就是他们的最近公共祖先。

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val)
            return root;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }
```



### 二叉树的最近公共祖先

左右子树分别寻找`p`, `q`两个节点，找到了就返回当前的节点，没找到就返回`null`；

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == q.val || root.val == p.val) { //
            return root;
        }
        TreeNode leftRes = lowestCommonAncestor(root.left, p, q);
        TreeNode rightRes = lowestCommonAncestor(root.right, p, q);
        if (leftRes != null && rightRes != null) //
            return root;
        if (leftRes == null) return rightRes; //
        if (rightRes == null) return leftRes; //
        else return null; //
    }
```



### 验证二叉搜索树

递归法和迭代法两种写法

递归法：

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;
        if (min != null && min.val >= root.val)
            return false;
        if (max != null && max.val <= root.val)
            return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}
```

迭代法参考中序非递归遍历，中序遍历是升序的！否则返回`false`。

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return iterative(root);
    }

    private boolean iterative(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double preOrder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= preOrder)
                return false;
            preOrder = root.val;
            root = root.right;
        }
        return true;
    }
}
```



### 验证平衡二叉树

递归函数中，就是求左右子树的高度，如果左右子树的高度差的绝对值小于2，那么就返回左右子树的最大高度+ 1（就是正常求树的高度）；如果左右子树的高度差的绝对值大于等于2，就说明这个时候树已经不平衡了，那么递归函数返回-1（标志）。

```java
    public boolean isBalanced(TreeNode root) {
        return treeDepth(root) != -1;
    }

    private int treeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = treeDepth(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = treeDepth(root.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        else return Math.max(leftHeight, rightHeight) + 1;
    }
```



### 完全二叉树的节点个数

思想：分别求出左右最远达到的高度，如果两个高度相等，那么就用公式计算返回节点个数即可，否则调用递归函数，递归求子树的节点个数。

```java
public int countNodes(TreeNode root) {
        int leftNum = 0, rightNum = 0;
        while (left != null) {
            leftNum++;
        }
        while (right != null) {
            rightNum++;
        }
        if (leftNum == rightNum)
            return (int) (Math.pow(2, leftNum) - 1);
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
```



### 判断子树

二叉树 `tree` 的一棵子树包括 `tree` 的**某个节点和这个节点的所有后代节点**。`tree` 也可以看做它自身的一棵子树。

思想：双递归！一个递归用于递归`root`的所有节点的情况，另一个递归用于判断所有递归到的节点和`subRoot`是否是相同的结构

```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null)
        return false;
    return check(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
}

private boolean check(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null)
       return true;
    if (root == null || subRoot == null || root.val != subRoot.val)
       return false;
    return check(root.left, subRoot.left) && check(root.right, subRoot.right);
}
```



### 判断二叉搜索树的后序遍历序列

考察后序遍历的思想，数组最后一个元素是根节点。前边的元素都是其左子树和右子树的节点。现在需要找出一个分界线，将左右子树区分开，左子树的任何一个元素都小于根节点；右子树的任何一个元素都大于根节点。但是！！！！！！！**寻找这个分界线的时候一定要从i=0头开始找，发现比根节点元素打节点时就停止！！！！。** **不能莫名的取最大值，分两半！**

```java
	public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (j <= i) return true;
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int max_index = p;
        while (postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, max_index - 1) && recur(postorder, max_index, j - 1);
    }
```

第二个方法是使用单调栈从后往前遍历数组。
```java
public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1 ; i >= 0 ; i--){
            if (root < postorder[i]) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.push(postorder[i]);
        }
        return true;
    }
```

将二叉搜索树结点的右子树全部放到单调栈中，这也意味着比该节点大的值都放在了单调栈中。所以一遇到比栈顶元素小的节点，也就意味着开始找到了左子树了。那么就找到该左子树的父节点（栈底元素）。那么左子树节点往前的所有节点都应该比根节点的值要小。否则就返回false。



### 二叉树中所有距离为 K 的结点

若将target 当作树的根结点，我们就能从target 出发，使用深度优先搜索去寻找与target 距离为 k 的所有结点，即深度为 k 的所有结点。

由于输入的二叉树没有记录父结点，为此，我们从根结点root 出发，**使用深度优先搜索遍历整棵树，同时用一个哈希表记录每个结点的父结点。**

然后从target 出发，使用深度优先搜索遍历整棵树，除了搜索左右儿子外，还可以顺着父结点向上搜索。

代码实现时，由于每个结点值都是唯一的，哈希表的键可以用结点值代替。此外，为避免在深度优先搜索时重复访问结点，递归时额外传入来源结点 from，在递归前比较目标结点是否与来源结点相同，不同的情况下才进行递归。

```java
    private Map<Integer, TreeNode> map = new HashMap<>();
    private List<Integer> res = new ArrayList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root);
        findDistanceK(target, null, k);
        return res;
    }

    private void findDistanceK(TreeNode target, TreeNode from, int k) {
        if (target == null || k < 0)
            return;
        if (k == 0) {
            res.add(target.val);
            return;
        }
        // 从左子树中找距离为K的节点
        if (target.left != from)
            findDistanceK(target.left, target, k - 1);
        // 从右子树中找距离为K的节点
        if (target.right != from)
            findDistanceK(target.right, target, k - 1);
        // 从父节点中找距离为K的节点
        if (map.get(target.val) != from)
            findDistanceK(map.get(target.val), target, k - 1);
    }

    private void findParent(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            findParent(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            findParent(root.right);
        }
    }
```

BFS版本的好理解

```java
    private Map<Integer, TreeNode> map = new HashMap<>();
    private List<Integer> res = new ArrayList<>();
    private boolean[] visited = new boolean[500];
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int depth = 0;
        while (!queue.isEmpty()) {
            for (int qSize = queue.size(); qSize > 0; qSize--){
                TreeNode node = queue.poll();
                visited[node.val] = true;
                if (depth == k)
                    res.add(node.val);
                if (node.left != null && !visited[node.left.val])
                    queue.offer(node.left);
                if (node.right != null && !visited[node.right.val])
                    queue.offer(node.right);
                if (map.get(node.val) != null && !visited[map.get(node.val).val])
                    queue.offer(map.get(node.val));
            }
            depth++;
        }
        return res;
    }

    private void findParent(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            findParent(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            findParent(root.right);
        }
    }
```



### 恢复二叉搜索树

具体来说，由于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最多两个位置不满足条件，因此在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点 `pred`，然后在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历。

这样我们就可以在中序遍历中直接找到被错误交换的两个节点 `x` 和 `y`，不用显式建立 `nums` 数组。

```java
  public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node1 = null, node2 = null;
        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < preNode.val) {
                node1 = root;
                if (node2 == null) {
                    node2 = preNode;
                } else
                    break;
            }
            preNode = root;
            root = root.right;
        }
        swap(node1, node2);
    }

    private void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
```



### 二叉树剪枝

```java
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        pruneTree(root.left);
        if (!hasOne(root.left))
            root.left = null;
        pruneTree(root.right);
        if (!hasOne(root.right))
            root.right = null;
        if (root.val == 0 && !hasOne(root))
            root = null;
        return root;
    }

    private boolean hasOne(TreeNode root) {
        if (root == null)
            return false;
        return hasOne(root.left) || hasOne(root.right) || root.val == 1;
    }
```



### 求根节点到叶节点数字之和

递归法

```java
    public int sumNumbers(TreeNode root) {
         return getSum(root, 0);
    }

    private int getSum(TreeNode root, int preSum) {
        if (root == null)
            return 0;
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null)
            return sum;
        else
            return getSum(root.left, sum) + getSum(root.right, sum);
    }
```

迭代法

```java
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        int res = 0;
        while (!nodeQueue.isEmpty() && !valQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int val = valQueue.poll();
            if (node.left == null && node.right == null) {
                res += val;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(val * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(val * 10 + node.right.val);
            }
        }
        return res;
    }
```



### 二叉树的最大路径和

思想：计算左右子树给根节点的最大贡献值，如果左子树或右子树给根节点的贡献值为负数，那么就将左右子树的贡献值设置为0。

递归函数的本身是求这个最大贡献值的，那么这个最大路径和是在这其中顺便求出来的。

```java
    private int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    // 计算左右子树的贡献值
    private int myDFS(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = Math.max(dfs(root.left), 0);
        int rightSum = Math.max(dfs(root.right), 0);
        maxPathSum = Math.max(leftSum + root.val + rightSum, maxPathSum);
        return root.val + Math.max(leftSum, rightSum);
    }
```



### 展平二叉搜索树

先建立一个哑节点，然后中序遍历的过程中，将该节点插入到哑节点中。

```java
class Solution {
    private TreeNode resNode;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        buildTreeNode(root);
        return dummyNode.right;
    }

    private void buildTreeNode(TreeNode root) {
        if (root == null)
            return;
        buildTreeNode(root.left);
        resNode.right = root;
        root.left = null;
        resNode = root;
        buildTreeNode(root.right);
    }
}
```





### 二叉树的两数之和

 得借助`O(n)`的空间复杂度`set`或`list`。然后`DFS`或者`BFS`搜索，判断当前节点值是否在辅助空间里，在就返回，不在就将该节点插入到辅助空间里。



### 不同的二叉搜索树

假设 `n` 个节点存在二叉排序树的个数是 `G (n)`，令 `f(i)` 为以 `i` 为根的二叉搜索树的个数，则
$$
G(n) = f(1) + f(2) + f(3) + f(4) + ... + f(n)
$$
当 `i` 为根节点时，其左子树节点个数为 `i-1` 个，右子树节点为 `n-i`，则
$$
f(i) = G(i-1)*G(n-i)
$$
综合两个公式可以得到 卡特兰数 公式
$$
G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
$$

```java
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
```



### 判断完全二叉树

```java
    public static void judge(Node<Integer> node){
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        boolean flag = true;
        while(!queue.isEmpty()){
            //这里没有直接使用queue.poll()的原因是，如果最后那个节点没有左子树，但是有右子树，那么这个循环也会停止，
            //而且那个节点也会从队列中弹出，如果刚好是那个节点就是问题节点，那最后的结果就是有问题的，所以这里使用peek
            node = queue.peek();
            //遇见为空的直接停止
            if (node.left != null){
                queue.add(node.left);
            }else {
                break;
            }
            //遇见为空直接停止
            if (node.right != null){
                queue.add(node.right);
            }else {
                break;
            }
            queue.poll();
        }
        //这里搞这个判断的原因，就是看一下暂停遍历的那个节点是不是有问题，因为上面的循环并没有把这个节点弹出
        if (queue.peek().right == null){
            queue.poll();
        }
        //这里判断队列中剩余的节点是不是都是叶子节点
        while (!queue.isEmpty()){
            node = queue.poll();
            if (node.left != null || node.right != null){
                flag = false;
            }
        }
        System.out.println(flag);
    }
```



# 七. 图

### 深度优先遍历

### 广度优先遍历

### 所有的可能路径

给定一个有 `n` 个节点的有向无环图，用二维数组 `graph` 表示，**请找到所有从 `0` 到 `n-1` 的路径并输出**（不要求按顺序）。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg)

```java
输入：graph = [[1,2],[3],[3],[]]
输出：[[0,1,3],[0,2,3]]
解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
```

注意哈，这道题给限定了条件，只能从`0`点开始遍历到`n-1`号节点。

**`dfs`回溯法：**

```java
    List<List<Integer>> res;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new LinkedList<>();
        dfs(graph, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[][] graph, int u, LinkedList<Integer> path) {
        path.add(u);
        if (u == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int v : graph[u]) {
            dfs(graph, v, path);
            path.removeLast();
        }
    }
```

**`bfs`广度优先搜索**，采用的方法是从中心向周围扩展的方法，周围有几个邻居，就创建几个`List`放在`Queue`里面。

```java
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>(){{add(0);}});
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            int node = list.get(list.size() - 1);
            if (node == graph.length - 1)
                res.add(list);
            else {
                for (int v : graph[node]) {
                    List<Integer> newPath = new ArrayList<>(list);
                    newPath.add(v);
                    queue.add(newPath);
                }
            }
        }
        return res;
    }
```



### 课程表1(拓扑排序)

深度优先算法

```java
class Solution {
    private List<List<Integer>> edges;
    private int[] visited;
    boolean isValid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 图的邻接表存储
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        // 判断节点i是否被访问，0表示未访问，1表示被访问过
        visited = new int[numCourses];
        // 建立图
        for (int[] preRequisite : prerequisites) {
            edges.get(preRequisite[1]).add(preRequisite[0]);
        }
        // 开始深度优先遍历所有节点
        for (int i = 0; i < numCourses && isValid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return isValid;
    }

    private void dfs(int i) {
        // 正在访问节点（包括其子节点）
        visited[i] = 1;
        for (int v : edges.get(i)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!isValid) {
                    return;
                }
            } else if (visited[v] == 1) { // 则存在环，返回false
                isValid = false;
                return;
            }
        }
        visited[i] = 2;
    }
}
```

广度优先算法

```java
class Solution {
    private List<List<Integer>> edges;
    private int[] indeg;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 图的邻接表存储
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            edges.add(new ArrayList<>());
        // 存节点的入度信息，方便从入度为0的节点开始进行广度优先搜索
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }

        // 先将入度为0的节点放入队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) queue.offer(i);
        }

        // 由于不需要输出序列，所以直接计数就行，遍历过的节点技术，不用使用数据结构保存
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int node = queue.poll();
            for (int v : edges.get(node)) {
                indeg[v]--;
                if (indeg[v] == 0)
                    queue.offer(v);
            }
        }
        return res == numCourses;
    }
}
```



### 课程表2

深度优先：

```java
class Solution {
    private int[] res;
    private int[] visited;
    private boolean isValid;
    private int index;
    private Map<Integer, List<Integer>> graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return findOrderDFS(numCourses, prerequisites);
    }

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        res = new int[numCourses];
        visited = new int[numCourses];
        isValid = true;
        index = numCourses - 1;
        graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList());
        }
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
        }
        for (int i = 0; i < numCourses && isValid; i++) {
            if (visited[i] == 0)
                dfs(i);
        }
        if (!isValid)
            return new int[]{};
        return res;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (int v : graph.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!isValid)
                    return;
            } else if (visited[v] == 1) {
                isValid = false;
                return;
            }
        }
        visited[u] = 2;
        res[index--] = u;
    }
}
```

广度优先：

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> resList = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList());
        }
        int[] inDegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0) queue.offer(i);

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            resList.add(course);
            count++;
            for (int adjCourse : graph.get(course)) {
                inDegree[adjCourse]--;
                if (inDegree[adjCourse] == 0)
                    queue.offer(adjCourse);
            }
        }
        if (count != numCourses)
            return new int[]{};
        int[] res = new int[numCourses];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}
```



### 二维数组遍历

把二维数组中的每一个位置看做一个节点，这个节点的上下左右四个位置就是相邻节点。可以使用方向数组来处理上下左右四个方向。

```java
int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
```



### 连通图的个数

dfs邻接矩阵表示的

```java
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        boolean[] visited = new boolean[m];
        int circles = 0;
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                circles++;
            }
        }
        return circles;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }
```

bfs邻接矩阵表示的

```java
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        boolean[] visited = new boolean[m];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < isConnected.length; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }
```

并查集

```java
    private int[] parents;
    private int circles;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        parents = new int[n];
        for (int i = 0; i < parents.length; i++)
            parents[i] = i;
        circles = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1)
                    union(i, j);
            }
        }
        return circles;
    }

    public void union(int node1, int node2) {
        int p = find(node1);
        int q = find(node2);
        if (p != q) {
            parents[p] = q;
            circles--;
        }
    }

    public int find(int node) {
        while (parents[node] != node) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }
```



### 判断二分图

dfs

```java
private boolean isBipartite = true;
    private boolean[] visited;
    private boolean[] color;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        color = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
        return isBipartite;
    }

    private void dfs(int[][] graph, int u) {
        if (!isBipartite)
            return;
        visited[u] = true;
        for (int v : graph[u]) {
            if (!visited[v]) {
                color[v] = !color[u];
                dfs(graph, v);
            } else {
                if (color[u] == color[v])
                    isBipartite = false;
            }
        }
    }
```

bfs

```java
    private boolean isBipartite = true;
    private boolean[] visited;
    private boolean[] color;
    private List<Integer>[] graph;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        graph = buildGraph(n, dislikes);
        visited = new boolean[n + 1];
        color = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }
        return isBipartite;
    }

    private void bfs(List<Integer>[] graph, int u) {
        Queue<Integer> queue = new LinkedList<>();
        visited[u] = true;
        queue.offer(u);
        while (!queue.isEmpty() && isBipartite) {
            int node = queue.poll();
            for (int v : graph[node]) {
                if (!isBipartite)
                    break;
                if (!visited[v]) {
                    color[v] = !color[node];
                    visited[v] = true;
                    queue.offer(v);
                } else {
                    isBipartite = color[v] == color[node] ? false : true;
                }
            }
        }
    }
```



### 01矩阵（矩阵中的1到0的最短距离）

给定一个由 `0` 和 `1` 组成的矩阵 `mat` ，请输出一个大小相同的矩阵，其中每一个格子是 `mat` 中对应位置元素到最近的 `0` 的距离。两个相邻元素间的距离为 `1` 。

**示例 1：**

<img src="https://pic.leetcode-cn.com/1626667201-NCWmuP-image.png" alt="img" style="zoom:50%;" />

```
输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
输出：[[0,0,0],[0,1,0],[0,0,0]]
```

**示例 2：**

<img src="https://pic.leetcode-cn.com/1626667205-xFxIeK-image.png" alt="img" style="zoom:50%;" />

```
输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]
```

**思路：**首先把每个源点 `0` 入队，然后从各个 `0` 同时开始一圈一圈的向 `1` 扩散（每个 `1` 都是被离它最近的 `0` 扩散到的），扩散的时候可以设置 `int[][] dist` 来记录距离（即扩散的层次）并同时标志是否访问过。对于本题是可以直接修改原数组 `int[][] matrix` 来记录距离和标志是否访问的，这里要注意先把 `matrix` 数组中 `1` 的位置设置成 `-1` （设成`Integer.MAX_VALUE`啦，`m * n`啦，`10000`啦都行，只要是个无效的距离值来标志这个位置的 `1` 没有被访问过就行辣~）

```java
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else
                    mat[i][j] = -1;
            }
        }
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < mat.length && newY >= 0 && newY < mat[0].length && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return mat;
    }
```



# 八. 背包问题

### 01背包

```java
/**
 * 0-1's Knapsack Problem
 */
public class knapsack_0_1 {

    /**
     * 0，1背包问题的动态规划解法
     * @param N
     * @param W
     * @param wgt
     * @param val
     * @return
     */
    public int solutionForDP(int N, int W, int[] wgt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wgt[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[N][W];
    }

    public static void main(String[] args) {
        knapsack_0_1 solution = new knapsack_0_1();
        System.out.println(solution.solutionForDP(3, 4, new int[]{2, 1, 3}, new int[]{4, 2, 3}));
    }
}

```



### 完全背包

```c++
#include<iostream>
using namespace std;
const int N = 1010;
int f[N][N];
int v[N],w[N];
int main()
{
    int n,m;
    cin>>n>>m;
    for(int i = 1 ; i <= n ;i ++)
    {
        cin>>v[i]>>w[i];
    }

    for(int i = 1 ; i<=n ;i++)
    for(int j = 0 ; j<=m ;j++)
    {
        for(int k = 0 ; k*v[i]<=j ; k++)
            f[i][j] = max(f[i][j],f[i-1][j-k*v[i]]+k*w[i]);
    }

    cout<<f[n][m]<<endl;
}

```



### 一和零

给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

示例 1：

```
输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
```



```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] zerosAndOnes = getZerosAndOnes(strs[i - 1]);
            int zeros = zerosAndOnes[0], ones = zerosAndOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int[] getZerosAndOnes(String bin) {
        int[] res = new int[2];
        for (int i = 0; i < bin.length(); i++) {
            res[bin.charAt(i) - '0']++;
        }
        return res;
    }
} 
```

空间压缩版本

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] zerosAndOnes = getZerosAndOnes(strs[i - 1]);
            int zeros = zerosAndOnes[0], ones = zerosAndOnes[1];
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] getZerosAndOnes(String bin) {
        int[] res = new int[2];
        for (int i = 0; i < bin.length(); i++) {
            res[bin.charAt(i) - '0']++;
        }
        return res;
    }
}
```



### 分割等和子集

`dp[i][j]`的意义是判断能否从`0-i`个数字中选出和为`j`的组合。所以`dp`的选择就是选还是不选。

```java
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        // 当 j 等于 0 时，即背包容量为空，只要不选择物品就可以，所以 f(i, 0) 为 true。
        for (int i = 1; i <= nums.length; i++)
            dp[i][0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum];
    }
```



# 九. 数学

### 快速幂

```java
int fastPower(int base, int exponent) {
    int sum = 1;
    while (exponent != 0) {
        if ((exponent & 1) != 0) {
            sum *= base;
        }
        exponent = expnonent >> 1;  // 对指数进行移位
        base *= base;               // 让base的次幂以2的倍数增长
    }
    return sum;
}
```



### 矩阵快速幂

```java
public int[][] pow(int[][] a, int n) {
    int[][] ret = {{1, 0}, {0, 1}};
    while (n > 0) {
        if ((n & 1) == 1) {
            ret = multiply(ret, a);
        }
        n >>= 1;
        a = multiply(a, a);
    }
    return ret;
}
```



### 3. 数字 1 的个数

输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

示例 1：

```
输入：n = 12
输出：5
```

示例 2：

```
输入：n = 13
输出：6
```

**解题思路：**

将 1 ~ n 的个位、十位、百位、...的 1 出现次数相加，即为 1 出现的总次数。

**某位中 11 出现次数的计算方法：**

根据当前位**cur** 值的不同，分为以下三种情况：

1. 当 **cur = 0 时：** 此位 1 的出现次数只由高位 high 决定，计算公式为：
   $$
   high×digit
   $$
   > 如下图所示，以 n = 2304 为例，求 digit = 10（即十位）的 1 出现次数。

   <img src="https://pic.leetcode-cn.com/78e60b6c2ada7434ba69643047758e113fa732815f7c53791271c5e0f123687c-Picture1.png" style="zoom:33%;" />

2. 当 **cur = 1**时： 此位 1 的出现次数由高位 high 和低位 low 决定，计算公式为：
   $$
   high×digit+low+1
   $$
   > 如下图所示，以 n = 2314 为例，求 digit = 10 （即十位）的 1 出现次数。

   <img src="https://pic.leetcode-cn.com/58c7e6472155b49923b48daac10bd438b68e9504690cf45d5e739f3a8cb9cee1-Picture2.png" style="zoom:33%;" />

3. 当 **cur = 2, 3,..., 9** 时： 此位 1 的出现次数只由高位 high 决定，计算公式为：
   $$
   (high+1)×digit
   $$

   > 如下图所示，以 n = 2324 为例，求 digit = 10（即十位）的 1 出现次数。

<img src="https://pic.leetcode-cn.com/0e51d37b434ef0ad93882cdcb832f867e18b872833c0c360ad4580eb9ed4aeda-Picture3.png" style="zoom:33%;" />



变量递推公式：
设计按照 “个位、十位、...” 的顺序计算，则 high / cur / low / digit 应初始化为：

```
high = n // 10
cur = n % 10
low = 0
digit = 1 # 个位
```

因此，从个位到最高位的变量递推公式为：

```java
while high != 0 or cur != 0: # 当 high 和 cur 同时为 0 时，说明已经越过最高位，因此跳出
   low += cur * digit # 将 cur 加入 low ，组成下轮 low
   cur = high % 10 # 下轮 cur 是本轮 high 的最低位
   high //= 10 # 将本轮 high 最低位删除，得到下轮 high
   digit *= 10 # 位因子每轮 × 10
```

复杂度分析：
**时间复杂度 O(logn) ：** 循环内的计算操作使用 O(1) 时间；循环次数为数字 n 的位数，即 log10n ，因此循环使用O(logn) 时间。
**空间复杂度 O(1) ：** 几个变量使用常数大小的额外空间。

```java
    public int countDigitOne(int n) {
        int low = 0, digit = 1, res = 0;
        int cur = n % 10, high = n / 10;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low = cur * digit + low;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
```



### 4. 第N位数字

给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。

示例 1：

```
输入：n = 3
输出：3
```

示例 2：

```
输入：n = 11
输出：0
解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
```

<img src="https://pic.leetcode-cn.com/1599888213-CYhLfm-Picture1.png" alt="Picture1.png" style="zoom:40%;" />

1. 首先计算出第n位数字是几位数字的

   比如n=10000，求出第n为的数字是什么？

   > 1位数字一共有9个
   >
   > 2位数字一共有90个
   >
   > 3位数字一共有900个

   所以第n一定是个4位数。
   
2. 然后计算这第n位数字是4位数字的第几个数的第几位？

   进行整除和取模运算即可。

https://leetcode-cn.com/problems/nth-digit/solution/wei-ruan-zhao-pin-ing-400-di-n-wei-shu-z-hb7i/

```java
class Solution {
    public int findNthDigit(int n) {
        int d = 1, count = 9;
        // 开始求是几位数字
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        // 开始求是哪个数字num
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        // 开始求第几位iDigit
        int digitIndex = index % d;
        // 那就是num的第iDigit位置(从高位计算)
        int digit = (num / (int)(Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }
}
```



### 5. 约瑟夫环问题

问题描述：编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3…这样依次报），数到 m 的 士兵会被杀死出列，之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。

**示例 1：**

```
输入: n = 5, m = 3
输出: 3
```

**示例 2：**

```
输入: n = 10, m = 17
输出: 2
```

两种方法解决，一种是模拟链表法，逐渐删除其中第m个节点。

```java
    private Integer getNumLinkedList(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(i);
        int index = 0;
        while (n > 1) {
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }
```

递归法：

其实这道题还可以用递归来解决，递归是思路是**每次我们删除了某一个士兵之后，我们就对这些士兵重新编号，然后我们的难点就是找出删除前和删除后士兵编号的映射关系**。

我们定义递归函数 f(n，m) 的返回结果是存活士兵的编号，显然当 n = 1 时，f(n, m) = 1。假如我们能够找出 f(n，m) 和 f(n-1，m) 之间的关系的话，我们就可以用递归的方式来解决了。我们假设人员数为 n, 报数到 m 的人就自杀。则刚开始的编号为

…
1
…
m - 2

m - 1

m

m + 1

m + 2
…
n
…

进行了一次删除之后，删除了编号为 m 的节点。删除之后，就只剩下 n - 1 个节点了，删除前和删除之后的编号转换关系为：

删除前   ---   删除后

…      ---    …

m - 2   ---   n - 2

m - 1   ---    n - 1

m     ----   无(因为编号被删除了)

m + 1   ---   1(因为下次就从这里报数了)

m + 2   ----   2

…     ----     …

新的环中只有 n - 1 个节点。且删除前编号为 m + 1, m + 2, m + 3 的节点成了删除后编号为 1， 2， 3 的节点。

假设 old 为删除之前的节点编号， new 为删除了一个节点之后的编号，则 old 与 new 之间的关系为 `old = (new + m - 1) % n + 1`。

这样，我们就得出 f(n, m) 与 f(n - 1, m)之间的关系了，而 f(1, m) = 1.所以我们可以采用递归的方式来做。代码如下：

> 注：有些人可能会疑惑为什么不是 old = (new + m ) % n 呢？主要是因为编号是从 1 开始的，而不是从 0 开始的。如果 new + m == n的话，会导致最后的计算结果为 old = 0。所以 old = (new + m - 1) % n + 1.

```java
int f(int n, int m){
    if(n == 1)   return n;
    return (f(n - 1, m) + m - 1) % n + 1;
}
```

对于从0开始编号的，写法会有点不同，如下：

```java
    public int f(int n, int m) {
        return n == 1 ? 0 : (f(n - 1, m) + m) % n;
    }
```

时间复杂度O(n)，空间复杂度也是O(n)。空间优化如下：使用迭代方法，从0开始编号的。

```java
    public int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;
    }
```



### 6. 整数除法

给定两个整数 `a` 和 `b` ，求它们的除法的商 `a/b` ，要求不得使用乘号 `'*'`、除号 `'/'` 以及求余符号 `'%'` 。

```java
class Solution {
    public int divide(int a, int b) {
        if (b == 1) return a;
        if (b == -1 && a == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        if (a < 0 && b < 0) {
            res = getDivide(-(long) a, -(long) b);
        } else if (a > 0 && b > 0) {
            res = getDivide((long) a, (long) b);
        } else {
            res = -getDivide(Math.abs((long) a), Math.abs((long) b));
        }
        return res;
    }

    private int getDivide(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        int count = 1;
        long num = divisor;
        while (num + num <= dividend) {
            num = num + num;
            count = count + count;
        }
        return count + getDivide(dividend - num, divisor);
    }
}
```



### Pow(x,n)

快速幂法加迭代

```java
    public double myPow(double x, int n) {
        if (x == 1)
            return x;
        return n < 0 ? 1.0 / getPow(x, -(long) n) : getPow(x, (long) n);
    }

    private double getPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = getPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
```



### 26进制向字符转换

```
A -> 1
B -> 2
C -> 3 
... 
Z -> 26 
AA -> 27 
AB -> 28
```



```java
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            int n = (columnNumber - 1) % 26 + 1;
            sb.append((char)('A' + n - 1));
            columnNumber = (columnNumber - n) / 26;
        }
        return sb.reverse().toString();
    }
```



### 字符串相乘

给定两个以字符串形式表示的非负整数 `num1` 和 `num2`，返回 `num1` 和 `num2` 的乘积，它们的乘积也表示为字符串形式。

**示例 1:**

```
输入: num1 = "2", num2 = "3"
输出: "6"
```

**示例 2:**

```
输入: num1 = "123", num2 = "456"
输出: "56088"
```

模拟计算：num1和num2。将num2的各个位分解以此和num1相乘，然后结果依次相加。

```java
    public String multiply(String num1, String num2) {
        return num1.length() < num2.length() ? getMultiply(num2, num1) : getMultiply(num1, num2);
    }

    private String getMultiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        String sum = "0";
        for (int j = num2.length() - 1; j >= 0; j--){
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int k = j; k < num2.length() - 1; k++)
                sb.append("0");
            for (int i = num1.length() - 1; i >= 0; i--) {
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                int res = n1 * n2 + carry;
                carry = res / 10;
                sb.append(res % 10);
            }
            if (carry > 0) sb.append(carry);
            sum = getAdd(sum, sb.reverse().toString());
        }
        return sum;
    }

    private String getAdd(String num1, String num2) {
        int carry = 0;
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry != 0) {
            int a = n1 < 0 ? 0 : num1.charAt(n1) - '0';
            int b = n2 < 0 ? 0 : num2.charAt(n2) - '0';
            int sum = a + b + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            n1--;
            n2--;
        }
        return sb.reverse().toString();
    }
```



### 求素数

```java
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
```



### 旋转数字

我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。

如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。

现在我们有一个正整数 `N`, 计算从 `1` 到 `N` 中有多少个数 X 是好数？

**示例：**

```
输入: 10
输出: 4
解释: 
在[1, 10]中有四个好数： 2, 5, 6, 9。
注意 1 和 10 不是好数, 因为他们在旋转之后不变。
```

```java
  public int rotatedDigits(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (isGoodNum(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isGoodNum(Integer num) {
        char[] digits = num.toString().toCharArray();
        int sameCnt = 0;
        for (char ch : digits) {
            if (ch == '0' || ch == '1' || ch == '8') {
                sameCnt++;
            } else if (ch == '2' || ch == '5' || ch == '6' || ch == '9') {
                continue;
            } else {
                return false;
            }
        }
        if (sameCnt == digits.length)
            return false;
        else
            return true;
    }
```



# 十. 动态规划

### 1. 最大连续子数组和

```
输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
```

求最大子数组的和的话,

```java
dp[i] = max(dp[i-1]+nums[i], nums[i]);
res = max(dp[i], res);
```

前缀和思想：

```java
    public int maxSubArray(int[] nums) {
        int res = 0, preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] + preSum < nums[i])
                preSum = nums[i];
            else
                preSum += nums[i];
            res = Math.max(res, preSum);
        }
        return res;
    }
```

如果返回最大子数组的话，确定最大子数组和的同时，保存这个最大和所在元素的索引。然后以此从后往前减掉`nums`中的元素，直到最大和为`0`。这样就确定了最大和数组的起始位置。从而确定了最大子数组。

```java
if (preSum > res) {
    res = preSum;
    maxIndex = i;
}
int endIndex = maxIndex;
while (res > 0) res -= nums[maxIndex--];
// maxIndex + 为子数组起始索引，endIndex为结束的索引
```



### 2. 最长递增子序列

```java
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
```

**动态规划法**：
$$
dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
$$
最后，整个数组的最长上升子序列即所有$ \textit{dp}[i]$ 中的最大值。

$$
\text{LIS}_{\textit{length}}= \max(\textit{dp}[i]), \text{其中} \, 0\leq i < n
$$

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```



**基于二分查找的贪心**：

核心思想就是构造一个最长递增子序列数组，我们想要使子数组最长，就需要子数组的递增速度要慢（递增增幅尽可能小），所以使用二分查找，找到第一个最小的数字，替换掉就行。这样就用最小增幅的数字，构成长度最长的子数组了。

维护一个最长数组 $d[i]$ ，表示长度为 i 的最长上升子序列的末尾元素的最小值，用 $\textit{len}$记录目前最长上升子序列的长度，起始时$ len $为 $1$，$d[1] = \textit{nums}[0]$。



```java
    private List<Integer> list;
    public int lengthOfLIS(int[] nums) {
       list = new ArrayList<>();
       list.add(nums[0]);
       for (int i = 1; i < nums.length; i++) {
           if (nums[i] > list.get(list.size() - 1)) {
               list.add(nums[i]);
           } else {
               int index = binarysearch(list, nums[i]);
               list.set(index, nums[i]);
           }
       }
       return list.size();
    }

    private int binarysearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == num)
                return mid;
            else if (list.get(mid) > num)
                right = mid;
            else left = mid + 1;
        }
        return left;   // 这里注意返回的是left边界
    }
```



### 3. 打家劫舍

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。

给定一个代表每个房屋存放金额的非负整数数组，计算你 **不触动警报装置的情况下** ，一夜之内能够偷窃到的最高金额。

**示例 1：**

```
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

**示例 2：**

```
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```

**思路：**

动态规划，`dp[i]`表示走到第i个房子的最大收益，第`i`个房子这里，可以拿钱，可以不拿钱。如果不拿钱，那么最大收益就是前一个房子`dp[i-1]`的最大收益。如果拿钱的话，比较一下，当前房屋`nums[i]`的收益，和前前一个的最大收益的最大值。然后返回。

**代码：**

```java
public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + nums[i], nums[i]));
        }
        return dp[nums.length - 1];
    }
```



### 4. 打家劫舍3

注意好问题的定义！是父与子的节点不能都选中，兄弟之间是可以都选择的，所以下面的状态不要写错啊

```java
class Solution {
    private Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);
        int rob = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        int noRob = rob(root.left) + rob(root.right);
        int res = Math.max(rob, noRob);
        map.put(root, res);
        return res;
    }
}
```



### 5. 编辑距离

给你两个单词 `word1` 和 `word2`， *请返回将 `word1` 转换成 `word2` 所使用的最少操作数* 。

你可以对一个单词进行如下三种操作：

- 插入一个字符
- 删除一个字符
- 替换一个字符

**示例 1：**

```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

**示例 2：**

```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```

<img src="https://pic.leetcode-cn.com/3241789f2634b72b917d769a92d4f6e38c341833247391fb1b45eb0441fe5cd2-72_fig2.PNG" alt="72_fig2.PNG" style="zoom:20%;" />

**代码：**

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int j = 0; j < len2 + 1; j++)
            dp[0][j] = j;
        for (int i = 0; i < len1 + 1; i++)
            dp[i][0] = i;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
            }
        }
        return dp[len1][len2];
    }
}
```



### 6. 把数字翻译成字符串

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

```
示例 1:
输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
```

**代码：**

```java
private int translateNum(int num) {
    if (num < 10)
        return 1;
    String nums = String.valueOf(num);
    int[] dp = new int[nums.length() + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 1; i < nums.length(); i++) {
        if (nums.charAt(i - 1) != '0' && (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0') < 26) {
            dp[i + 1] = dp[i] + dp[i - 1];
        } else
            dp[i + 1] = dp[i];
    }
    return dp[nums.length()];
}
```



### 7. 丑数

我们把只包含质因子 `2、3` 和 `5` 的数称作丑数`（Ugly Number）`。求按从小到大的顺序的第 `n` 个丑数。

```
示例: 
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 

说明:
1 是丑数。 
n 不超过1690。
```

那就根据`2，3，5`的因子计算出第`n`个丑数，然后返回。第一种方法用小根堆保存生成的数字，第二种使用动态`dp`数组。

**优先队列（小根堆）**

```java
public int nthUglyNumber(int n) {
    int[] factors = new int[]{2, 3, 5};
    PriorityQueue<Long> queue = new PriorityQueue<>();
    queue.offer(1L);
    Set<Long> seen = new HashSet<>();
    int uglyNum = 0;
    for (int i = 0; i < n; i++) {
        long num = queue.poll();
        uglyNum = (int) num;
        for (int factor : factors) {
            if (seen.add(factor * num))
                queue.offer(factor * num);
        }
    }
    return uglyNum;
}
```

**动态规划**

```java
public int nthUglyNumber(int n) {
    int[] dp = new int[n + 1];
    dp[1] = 1;
    int p2 = dp[1], p3 = dp[1], p5 = dp[1];
    for (int i = 2; i <= n; i++) {
        int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
        dp[i] = Math.min(Math.min(num2, num3), num5);
        if (dp[i] == num2)
            p2++;
        if (dp[i] == num3)
            p3++;
        if (dp[i] == num5)
            p5++;
    }
    return dp[n];
}
```



### 完全平方和

给定正整数 *n*，找到若干个完全平方数（比如 `1, 4, 9, 16, ...`）使得它们的和等于 *n*。你需要让组成和的完全平方数的个数最少。

如何定义状态转移方程：

首先，给定一个数`n`，它所能包含的完全平方数一定在`i`属于`[1, sqrt(n)]`之间。换句话说，想要得知`n`的完全平方和的最小个数，那就需要知道`dp[n - i*i]`分别需要多少个，最后`dp[n]`再加上`1`。这个`1`考虑的是平方数`i`的情况。

```java
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
```



### 使用最小花费爬楼梯

给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。请你计算并返回达到楼梯顶部的最低花费。

```
  输入：cost = [10,15,20]
  输出：15
  解释：你将从下标为 1 的台阶开始。支付 15 ，向上爬两个台阶，到达楼梯顶部。总花费为 15 。
```

  直接求出到达第i层的最小费用。然后返回的结果中判断起始状态从0和1台阶的最小费用，也就是`min(dp[cost.length - 1], dp[cost.length - 2])`。

```java
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] , dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
```



### 最长的斐波那契子序列的长度

将斐波那契式的子序列中的两个连续项 `A[i], A[j]` 视为单个结点 `(i, j)`，整个子序列是这些连续结点之间的路径。

例如，对于斐波那契式的子序列 `(A[1] = 2, A[2] = 3, A[4] = 5, A[7] = 8, A[10] = 13)`，结点之间的路径为 `(1, 2) <-> (2, 4) <-> (4, 7) <-> (7, 10)`。

这样做的动机是，只有当 `A[i] + A[j] == A[k]` 时，两结点 `(i, j)` 和 `(j, k)` 才是连通的，我们需要这些信息才能知道这一连通。现在我们得到一个类似于**最长上升子序列**的问题。

设 `dp[i][j]` 是结束在 `[i][j]` 的最长路径。那么 如果 `(i, j)` 和 `(j, k)` 是连续成斐波那契的， `dp[j][k] = dp[i][j] + 1`。

```java
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            index.put(arr[i], i);
        int ans = 0;
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int num = arr[i] + arr[j];
                int k = index.getOrDefault(num, -1);
                if (k > 0 && dp[j][k] == 0) {
                    if (dp[i][j] == 0)
                        dp[i][j] = 2;
                    dp[j][k] = dp[i][j] + 1;
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
        return ans;
    }
```



### 交叉字符串

```java
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())
            return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++)
            dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
        for (int j = 1; j <= n; j++)
            dp[0][j] = s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }
```



### 不同的子序列

**示例 1：**

```
输入：s = "rabbbit", t = "rabbit"
输出：3
解释：
如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
rabbbit
rabbbit
rabbbit
```

`dp[i][j]`代表 `T` 前 `i` 字符串可以由 `S` `j` 字符串组成最多个数。

举个例子,如示例的

<img src="pic\a3a1d30700be05cad2e60666f20ab261e7a04b85ed88b854dd1d8cb484909983-1561970400084.png" alt="a3a1d30700be05cad2e60666f20ab261e7a04b85ed88b854dd1d8cb484909983-1561970400084" style="zoom:67%;" />

对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1

对于第一列, S 为空,这样组成 T 个数当然为 0 了

```java
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= m; j++)
            dp[0][j] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[n][m];
    }
```



### 目标和

递归

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return find(nums, 0, target);
    }

    private int find(int[] nums, int index, int target) {
        if (index == nums.length)
            return target == 0 ? 1 : 0;
        return find(nums, index + 1, target + nums[index]) + find(nums, index + 1, target - nums[index]);
    }
}
```

背包问题

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum < target || (sum & 1) != (target & 1) ? 0 : find(nums, (target + sum) / 2);
    }

    private int find(int[] nums, int target) {
        if (target < 0)
            return 0;
        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0)
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][target];
    }
}
```



### 最少的硬币数目

```java
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int coin: coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
```



### 将字符串翻转到单调递增

如果一个由 `'0'` 和 `'1'` 组成的字符串，是以一些 `'0'`（可能没有 `'0'`）后面跟着一些 `'1'`（也可能没有 `'1'`）的形式组成的，那么该字符串是 **单调递增** 的。

我们给出一个由字符 `'0'` 和 `'1'` 组成的字符串 s，我们可以将任何 `'0'` 翻转为 `'1'` 或者将 `'1'` 翻转为 `'0'`。

返回使 s **单调递增** 的最小翻转次数。

**示例 1：**

```java
输入：s = "00110"
输出：1
解释：我们翻转最后一位得到 00111.
```

**示例 2：**

```java
输入：s = "010110"
输出：2
解释：我们翻转得到 011111，或者是 000111。
```

**示例 3：**

```java
输入：s = "00011000"
输出：2
解释：我们翻转得到 00000000。
```

**难点在于`dp`的设计**

写动态规划看状态转移方程，写状态转移方程看定义状态。

定义`dp[i][0]`, `dp[i][0]`表示前`i`个元素递增且第`i`个元素为`0`的最小翻转次数，

定义`dp[i][1]`， `dp[i][1]`表示前`i`个元素递增且第`i`个元素为`1`的最小翻转次数。

由定义可知，如果前`i`个元素最后以`0`结尾且满足单调递增，那么前`i`个元素必须全部为`0`，由此可得`dp[i][0]`的状态转移如下：

```java
dp[i][0] = dp[i-1][0] + (s.charAt(i)=='0'?0:1);
```

由定义可知， `dp[i][1]`只要满足最后一个元素为`1`就行，那么前`i-1`个元素既可以为`0`，也可以为`1`，因此`dp[i][1]`的状态转移如下：

```java
dp[i][1] = min(dp[i-1][1], dp[i-1][0]) + (s.charAt(i)=='1'?0:1)；
```

最后取`dp[i][0]`,`dp[i][1]`中的较小的即可。

```java
    public int minFlipsMonoIncr(String s) {
        int[][] dp = new int[s.length()][2];
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[0][1] = s.charAt(0) == '1' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] = dp[i - 1][0] + (s.charAt(i) == '0' ? 0 : 1);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (s.charAt(i) == '1' ? 0 : 1);
        }
        return Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]);
    }
```



### 预测赢家（石子游戏）

给你一个整数数组 `nums` 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。

玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 `0` 。每一回合，玩家从数组的任意一端取一个数字（即，`nums[0]` 或 `nums[nums.length - 1]`），取到的数字将会从数组中移除（数组长度减 `1` ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。

如果玩家 1 能成为赢家，返回 `true` 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 `true` 。你可以假设每个玩家的玩法都会使他的分数最大化。

**示例 1：**

```
输入：nums = [1,5,2]
输出：false
解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。 
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
因此，玩家 1 永远不会成为赢家，返回 false 。
```

**示例 2：**

```
输入：nums = [1,5,233,7]
输出：true
解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
```

**解题思路：**

**这里定义 `dp[i][j]` 表示作为先手，在区间 `nums[i..j]` 里进行选择可以获得的净得分**。

甲乙比赛，甲先手面对区间`[i...j]`时，`dp[i][j]`表示甲对乙的净得分。

最终求的就是，甲先手面对区间`[0...n-1]`时，甲对乙的净得分`dp[0][n-1]`是否`>=0`。

甲先手面对区间`[i...j]`时，

- 如果甲拿`nums[i]`，那么变成乙先手面对区间`[i+1...j]`，这段区间内乙对甲的净得分为`dp[i+1][j]`；那么甲对乙的净得分就应该是`nums[i] - dp[i+1][j]`。
- 如果甲拿`nums[j]`，同理可得甲对乙的净得分为是`nums[j] - dp[i][j-1]`。

以上两种情况二者取大即可。

**递归**

```java
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        return dfs(nums, 0, len - 1) >= 0;
    }

    private int dfs(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        int chooseLeft = nums[start] - dfs(nums, start + 1, end);
        int chooseRight = nums[end] - dfs(nums, start, end - 1);
        return Math.max(chooseLeft, chooseRight);
    }
```

**动态规划**

状态定义：`dp[i][j]` 表示作为先手，在区间 `nums[i..j]` 里进行选择可以获得的相对分数。相对分数的意思是：当前自己的选择得分为正，对手的选择得分为负。

从对角线开始填。依次沿着东北方向填

<img src="https://pic.leetcode-cn.com/774b88e4332e5f93ebba7b1dd66aad35dcd2f7cd6c5680c2225f479be0947cfa-image.png" alt="img" style="zoom: 33%;" />

```java
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++)
            dp[i][i] = nums[i];
        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
```



# 十一. 回溯

### 1. 组合

```
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```



```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    if (k > n || k < 0) return res;
    dfs(n, k, 1, res, path);
    return res;
}

private void dfs(int n, int k, int start, List<List<Integer>> res, Deque<Integer> path) {
    if (path.size() == k) {
        res.add(new ArrayList<>(path));
        return;
    }
    for (int i = start; i <= n; i++) {  // 可以剪枝操作 i <= n - (k - path.size()) + 1
        path.offerLast(i);
        dfs(n, k, i + 1, res, path);
        path.removeLast();
    }
}
```



### 2. 组合总和

一个 **无重复元素** 的整数数组 `candidates` 和一个目标整数 `target` ，找出 `candidates` 中可以使数字和为目标数 `target` 的 所有 **不同组合**。`candidates` 中的 **同一个** 数字可以 **无限制重复被选取** 。

**示例 1：**

```
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
```

**示例 2：**

```
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
```

**示例 3：**

```
输入: candidates = [2], target = 1
输出: []
```

**代码：**

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (candidates.length == 0)
            return res;
        dfs(candidates, res, path, target, 0, 0);
        return res;
    }

    private void dfs(int[] candidates, List<List<Integer>> res, Deque<Integer> path, int target, int start, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            path.offerLast(candidates[i]);
            sum += candidates[i];
            dfs(candidates, res, path, target, i, sum);
            sum -= candidates[i];
            path.pollLast();
        }
    }
```



### 3. 组合总和 II

给定集合 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。`candidates` 中的每个数字在每个组合中**只能使用 一次** 。

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
```

**代码：**

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    Arrays.sort(candidates);
    dfs(candidates, res, path, target, 0, 0);
    return res;
}

private void dfs(int[] candidates, List<List<Integer>> res, Deque<Integer> path, int target, int start, int sum) {
    if (sum == target) {
        res.add(new ArrayList<>(path));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        if (sum + candidates[i] > target)//这里注意一下
            break;
        if (i > start && candidates[i] == candidates[i - 1]) //这里注意一下
            continue;
        path.offerLast(candidates[i]);
        sum += candidates[i];
        dfs(candidates, res, path, target, i + 1, sum);
        sum -= candidates[i];
        path.pollLast();
    }
}
```



### 4. 组合总和 III

找出所有相加之和为 `n` 的 `k` 个数的组合，且满足下列条件：

- `k`只使用数字1到9
- 每个数字 **最多使用一次** 

返回 *所有可能的有效组合的列表* 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。

**示例 1:**

```
输入: k = 3, n = 7
输出: [[1,2,4]]
解释:
1 + 2 + 4 = 7
没有其他符合的组合了。
```

**示例 2:**

```
输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
解释:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
没有其他符合的组合了。
```

**示例 3:**

```
输入: k = 4, n = 1
输出: []
解释: 不存在有效的组合。
在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
```

 **代码：**

```java
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    dfs(k, n, res, path, 1, 0);
    return res;
}

private void dfs(int k, int n, List<List<Integer>> res, Deque<Integer> path, int start, int sum) {
    if (sum == n && path.size() == k) {
        res.add(new ArrayList<>(path));
        return;
    }
    for (int i = start; i <= 9; i++) {
        if (sum + i > n)
            break;
        path.offerLast(i);
        dfs(k, n, res, path, i + 1, sum + i);
        path.pollLast();
    }
}
```



### 5. 组合总和 Ⅳ

给你一个由 **不同** 整数组成的数组 `nums` ，和一个目标整数 `target` 。请你从 `nums` 中找出并返回总和为 `target` 的元素组合的个数。

请注意，**顺序不同的序列被视作不同的组合**。

**示例 1：**

```
输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
```

**示例 2：**

```
输入：nums = [9], target = 3
输出：0
```

**代码：**

```java
public int combinationSum4(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i <= target; i++) {
        for (int num : nums) {
            if (num <= i) {
                dp[i] += dp[i - num];
            }
        }
    }
    return dp[target];
}
```



### 组合求和

去重复的方法：

- 使用HashSet；
- 排序，排完序，如果当前元素和前一个元素相同，那么当前元素略过，不选择；因为有序是 **深度优先遍历** 过程中实现「剪枝」的前提。

回溯模板，书写要规范：

```java
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, new LinkedList<Integer>(), 0, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, LinkedList<Integer> path, int index, int sum) {
        if (sum == target) {
            // 满足条件，加入到结果集里面
        }
        for (int i = index; i < candidates.length; i++) {
            // 结束条件都在for循环里判断
            // 大剪枝操作
            if (sum + candidates[i] > target)
                break;
            if (i > index && candidates[i] == candidates[i - 1]) // 去重复的写法
                continue;
            path.addLast(candidates[i]);
            dfs(candidates, target, path, i + 1, sum + candidates[i]);
            path.removeLast();
        }
    }
}
```



### 全排列

分两种情况：

- 数组不重复元素的全排列
- 数组中有重复元素的全排列（涉及重复元素的，都需要**排序**解决去重，比较`nums[i] == nums[i - 1]`），此外由于重复元素的全排列问题，不能单单判断数字是否在path中决定是否添加，要使用下标判断，因为下标是唯一的。

```java
private List<List<Integer>> res;
private boolean[] visited;
private void dfs(int[] nums, LinkedList<Integer> path) {
    if (path.size() == nums.length) {
        res.add(new ArrayList<>(path));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        // visited[i]表示过滤掉当前的元素，避免被重复选中
        // nums[i] == nums[i - 1] && !visited[i - 1] 如果两个数连续一样的话，当从第二个数字开始选的话，那么第一个数组应该就不选了，
        // 并且第一个visited数组应该为false,这样才能表示是从第二个数字开始选的。
        if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]))
            continue;
       visited[i] = true;
       path.add(nums[i]);
       dfs(nums, path);
       path.removeLast();
       visited[i] = false;
    }
}
```



### 排列序列

给定 `n` 和 `k`，返回第 `k` 个`1 ~ n - 1`的排列。

思路：就是正常的递归求解排列，这里让求出第K个，那么就计算当前分支下，剩余的数字有几种组合方式fact，如果`k < fact`，那就说明第K个排列在当前分支下，然后就往下继续进行，选择当前节点，设置当前节点为访问的。如果`k > fact`，那就说明我要在下一个分支下寻找第`k - fact`个排列，然后依次计算，直到`fact = 1`，这样就通过剪枝的方式找到了第K个排列了。

```java
class Solution {
    private StringBuilder stringBuilder;
    private boolean[] visited;
    private int[] factorial;
    public String getPermutation(int n, int k) {
        calculateFactorial(n);
        stringBuilder = new StringBuilder();
        visited = new boolean[n + 1];
        dfs(n, k, 0);
        return stringBuilder.toString();
    }

    private void dfs(int n, int k, int index) {
        if (index == n)
            return;

        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;
            int fact = factorial[n - index - 1];
            if (fact < k) {
                k -= fact;
                continue;
            }
            visited[i] = true;
            stringBuilder.append(i);
            dfs(n, k, index + 1);
            return;
        }
    }

    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i -1];
        }
    }
}
```



### 1. N皇后

列表回溯法

```java
    private List<List<String>> res;
    private Set<Integer> inColumns;
    private Set<Integer> inRL;
    private Set<Integer> inLR;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        inColumns = new HashSet<>();
        inRL = new HashSet<>();
        inLR = new HashSet<>();
        // queens表示皇后的位置queens[i] = j 表示皇后在第i行和第j列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(n, queens, 0);
        return res;
    }

    // 行控制
    private void dfs(int n, int[] queens, int row) {
        if (row == n) {
            res.add(generateBoard(queens, n));
            return;
        }
        // 这里表示列
        for (int col = 0; col < n; col++) {
            if (inColumns.contains(col))
                continue;
            int rl = row - col;
            if (inRL.contains(rl))
                continue;
            int lr = row + col;
            if (inLR.contains(lr))
                continue;
            queens[row] = col;
            inColumns.add(col);
            inRL.add(rl);
            inLR.add(lr);
            dfs(n, queens, row + 1);
            queens[row] = -1;
            inColumns.remove(col);
            inRL.remove(rl);
            inLR.remove(lr);
        }
    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[queens[i]] = 'Q';
            stringList.add(new String(chars));
        }
        return stringList;
    }
```



### 删除无效的括号

1. 先统计出有多少左右括号要删除
2. 一次删除这些括号，查看删除后的是不是有效的括号
3. 如果有效加入到结果集合里面。

```java
    private List<String> res;
    public List<String> removeInvalidParentheses(String s) {
        res = new ArrayList<>();
        int leftRemove = 0, rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                leftRemove++;
            else if (c == ')') {
                if (leftRemove == 0)
                    rightRemove++;
                else
                    leftRemove--;
            }
        }
        dfs(s, 0, leftRemove, rightRemove);
        return res;
    }

    private void dfs(String s, int index, int leftRemove, int rightRemove) {
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValidParentheses(s))
                res.add(s);
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (index != i && s.charAt(i) == s.charAt(i - 1))
                continue;
            if (leftRemove + rightRemove > s.length() - i) {
                return;
            }
            if (s.charAt(i) == '(' && leftRemove > 0)
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRemove - 1, rightRemove);
            if (s.charAt(i) == ')' && rightRemove > 0)
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRemove, rightRemove - 1);
        }
    }

    // 判断括号是否合法
    private boolean isValidParentheses(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            else if (c == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }
```



### *复原IP地址

**输入：**s = "25525511135"

**输出：**["255.255.11.135","255.255.111.35"]

```java
    private List<String> res;
    private int[] segments;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12)
            return res;
        segments = new int[4];
        dfs(s, 0, 0);
        return res;
    }

    private void dfs(String s, int segStart, int segId) {
        if (segId == 4) {
            if (segStart == s.length()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append(segments[i]).append(".");
                }
                res.add(stringBuilder.append(segments[3]).toString());
            }
            return;
        }
        if (segStart == s.length())
            return;
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segStart + 1, segId + 1);
        }
        int ip = 0;
        for (int i = segStart; i < s.length(); i++) {
            ip = ip * 10 + s.charAt(i) - '0';
            segments[segId] = ip;
            if (ip > 0 && ip <= 0xFF) {
                dfs(s, i + 1, segId + 1);
            } else {
                break;
            }
        }
    }
```



### 花括号展开

```
输入：{a,b}c{d,e}f
输出：["acdf", "acef", "bcdf", "bcef"]
```

把输入转化成`{a,b}{c}{d,e}{f}`，分别进行递归：

```java
    private List<String> chars;
    private List<String> res;
    public List<String> expand(String s) {
        chars = new ArrayList<>();
        res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                StringBuilder sb = new StringBuilder();
                while (i + 1 < s.length() && s.charAt(++i) != '}') {
                    if (s.charAt(i) == ',')
                        continue;
                    sb.append(s.charAt(i));
                }
                chars.add(sb.toString());
            } else {
                chars.add(String.valueOf(s.charAt(i)));
            }
        }
        dfs(0, new StringBuilder());
        return res;
    }

    private void dfs(int index, StringBuilder stringBuilder) {
        if (index == chars.size()) {
            res.add(stringBuilder.toString());
            return;
        }
        String curString = chars.get(index);
        for (int i = 0; i < curString.length(); i++) {
            stringBuilder.append(curString.charAt(i));
            dfs(index + 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
```



# 十二. 排序算法

## 【1】基础算法

### 1. 快速排序

```java
class Solution {

    private Random random = new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = random_partition(nums, start, end);
            quickSort(nums, start, mid - 1);
            quickSort(nums, mid + 1, end);
        }
    }

    private int random_partition(int[] nums, int left, int right) {
    	int i = random.nextInt(right - left + 1) + left;
    	swap(nums, left, i);
    	return partition(nums, left, right);
	}
    
    // 第一种partition写法
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }
    
    // 第二种partition写法
    /**
     * i，j，r三个变量分别代表左半部分最后一个元素，未分区的元素的第一个元素，最后一个为中枢的元素
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition(int[] nums, int start, int end) {
        int i = start - 1, j = start, r = nums[end];
        for (; j < end; j++) {
            if (nums[j] <= r) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, j);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

<img src="https://assets.codetop.cc/user/note/24967_zY9rGVtnak62qoFZ.jpg" alt="image" style="zoom: 67%;" />



### 2. 冒泡排序

```java
    public void sort(int[] nums) {
        int len = nums.length;
        for (int k = 1; k < len; k++) {
            for (int i = 0; i < len - k; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
        }
    }
```



### 3. 插入排序

```java
/**
 * 插入排序算法实现
 */
public class InsertionSort {
    public void sort(int[] nums) {
        super.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int j;
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                    nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }
}
```



### 4. 归并排序（递归）

```java
public class MergeSort {

    public void sort(int[] list) {
        super.sort(list);
        mergeSort(list, 0, list.length - 1);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] leftPart = Arrays.copyOfRange(nums, left, mid + 1);
        int[] rightPart = Arrays.copyOfRange(nums, mid + 1, right + 1);
        int numsP = left, leftP = 0, rightP = 0;
        while (leftP < leftPart.length && rightP < rightPart.length) {
            if (leftPart[leftP] < rightPart[rightP])
                nums[numsP++] = leftPart[leftP++];
            else
                nums[numsP++] = rightPart[rightP++];
        }
        while (leftP < leftPart.length)
            nums[numsP++] = leftPart[leftP++];
        while (rightP < rightPart.length)
            nums[numsP++] = rightPart[rightP++];
    }
}
```



### 5. 归并排序（非递归） 

**原数组：4, 7, 5, 1, 6, 3, 2, 8**

**第一次（两个排序）：4, 7, 1, 5, 3, 6, 2, 8**

**第二次（四个排序）：1, 4, 5, 7, 2, 3, 6, 8**

**第三次（八个排序）：1, 2, 3, 4, 5, 6, 7, 8**

```java
public void MergeSortNoneRecur(int[] arr) {
        // 非递归实现，拆分的方式可以理解为：完全二叉树的分层遍历
        // 从 1 开始分割，与递归不同的是，递归由数组长度一分为二最后到1，
        // 而非递归则是从1开始扩大二倍直到数组长度

        int start = 1;
        // 排序前先创建一个和原始数组等长的临时数组，避免在拆分过程中频繁开辟空间
        int[] tempArr = new int[arr.length];
        // 步进从2->4->8直到数组长度，
        while (start < arr.length) {
            for (int i = 0; i + start < arr.length; i += start << 1) {
                int left = i;
                int mid = i + start - 1;
                int right = i + (start << 1) - 1;

                // 防止最后超过数组长度
                if (right > arr.length - 1) {
                    right = arr.length - 1;
                }
                merge(arr, tempArr, left, mid, right);
            }
            start <<= 1;
        }
    }

    private void merge(int[] arr, int[] tempArr, int left, int mid, int right) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int k = left;//临时数组指针

        // 注意： 此处并没有全部放入temp中，当一边达到mid或right时就是退出循环
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                // 降序排序，如果是升序，改为arr[i++]即可
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            tempArr[k++] = arr[i++];
        }

        while (j <= right) {
            tempArr[k++] = arr[j++];
        }

        while (left <= right) {
            arr[left] = tempArr[left++];
        }
    }
```



### 6. 选择排序

```java
/**
 * 简单选择排序算法实现
 */
public class SelectionSort {
    public void sort(int[] nums) {
        super.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int min_index = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[min_index])
                    min_index = j;
            }
            int temp = nums[i];
            nums[i] = nums[min_index];
            nums[min_index] = temp;
        }
    }
}
```



## 【2】排序应用

### 1. 把数组排成最小的数

```
输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

示例 1: 
输入: [10,2]
输出: "102" 

示例 2: 
输入: [3,30,34,5,9]
输出: "3033459" 
```

这道题目实际上是想要找到一种排序规则，根据这种规则排序后的数组满足题目要求。排序规则要从两个元素的比较开始，我们可以定义数字`a`和`b`，假设其可以拼接成`ab`和`ba`两个数字。

我们定义若：

`ab > ba`，则`a > b`；

`ab < ba`，则`a < b`;

`ab==ba`，则`a = b`。

**代码：**

```java
public String minNumber(int[] nums) {
    list = new ArrayList<>();
    for (int num : nums) {
        list.add(String.valueOf(num));
    }
    quicksort(list, 0, list.size() - 1);
    System.out.println(list);
    return list.stream().collect(Collectors.joining());
}

private void quicksort(List<String> list, int start, int end) {
    if (start >= end)
        return;
    int index = partition(list, start, end);
    quicksort(list, start, index - 1);
    quicksort(list, index + 1, end);
}

private int partition(List<String> list, int start, int end) {
    int i = start, j = end;
    String pivot = list.get(i);
    while (i < j) {
        while (i < j && (list.get(j) + pivot).compareTo(pivot + list.get(j)) >= 0) j--; // 重新定义排序规则
        list.set(i, list.get(j));
        while (i < j && (list.get(i) + pivot).compareTo(pivot + list.get(i)) <= 0) i++; // 重新定义排序规则
        list.set(j, list.get(i));
    }
    list.set(j, pivot);
    return j;
}
```



### 2. 缺失数字的数组是否有序

判断数组是否能构成有序序列，0可以充当任何数字，使数组成为有序的。

**示例 1：**

```
输入: places = [0, 6, 9, 0, 7]
输出: True
```

**示例 2：**

```
输入: places = [7, 8, 9, 10, 11]
输出: True
```

**代码：**

排序，找出非零序列的第一个元素和最后一个元素的位置，然后计算差值，看是不是小于`n`，如果小于`n`，那么就说明长度为n的数组能够成有序的。

```java
public boolean isStraight(int[] nums) {
    int unknown = 0;
    Arrays.sort(nums); // 数组排序
    for (int i = 0; i < 4; i++) {
        if (nums[i] == 0) unknown++; // 统计未知朝代数量
        else if (nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
    }
    return nums[4] - nums[unknown] < 5; // 最大编号朝代 - 最小编号朝代 < 5 则连续
}
```



# 十三. 位运算

### 1. 判定字符是否唯一

实现一个算法，确定一个字符串 `s` 的所有字符是否全都不同。

**示例 1：**

```
输入: s = "leetcode"
输出: false 
```

**示例 2：**

```
输入: s = "abc"
输出: true
```

**限制：**

- `0 <= len(s) <= 100 `
- `s[i]`仅包含小写字母

```java
class IsUniqueSolution {
    public static boolean isUnique(String astr) {
        int bitmap = 0;
        for (int i = 0; i < astr.length(); i++) {
            int index = astr.charAt(i) - 'a';
            int bit = 1 << index;
            if ((bitmap & bit) != 0)
                return false;
            bitmap = bitmap | bit;
        }
        return true;
    }
}
```



### 2. 只出现一次的数字I

一个整型数组 `nums` 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。

```
示例 1：
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```

如果是在一个数组找只出现一次的数，就可以将所有的数进行`^`（异或）运算。在数组中寻找两个只出现一次的数，需要对数组进行分类，相同的数进行分类一定在同一个内别里。`&`运算可以用来区某一位数，列如区分奇偶数`num & 1`。难点是如何将只出现一次的两个数分开，**如果两个数不相同那么他们的二进制数至少有一位0和1不相同， 可以用一个标记数来标记那一位将两个数分开，取那个数能取的最小值，然后将`nums`数组用标记数分开进行`^`运算。**

　　　**num1: 101110   110  1111**

　　　**num2:** **111110   001  1001**

**num1^num2: 010000  111  0110** 

**可行的mask:  010000   001  0010**

**代码**：

```java
public int[] singleNumbers(int[] nums) {
    int xorAll = 0;
    int resA = 0, resB = 0;
    for (int num : nums) {
        xorAll ^= num;
    }
    int mask = 1;
    while ((mask & xorAll) == 0)
        mask <<= 1;
    for (int num : nums) {
        if ((num & mask) == 0)
            resA ^= num;
        else
            resB ^= num;
    }
    return new int[]{resA, resB};
}
```



### 3. 只出现一次的数字II

在一个数组 `nums` 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

```
示例 1： 
输入：nums = [3,4,3,3]
输出：4


示例 2： 
输入：nums = [9,1,7,9,7,9,7]
输出：1 
```

因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。

<img src="https://pic.leetcode-cn.com/28f2379be5beccb877c8f1586d8673a256594e0fc45422b03773b8d4c8418825-Picture1.png" alt="Picture1.png" style="zoom:50%;" />

**代码**：

```java
public int singleNumber(int[] nums) {
    int res = 0;
    int[] digits = new int[32];
    for (int num : nums) {
        int mask = 1;
        for (int i = 31; i >= 0; --i) {
            int bit = num & mask;
            if (bit != 0)
                digits[i] += 1;
            mask <<= 1;
        }
    }
    for (int i = 0; i < 32; i++) {
        res <<= 1;
        res += digits[i] % 3 == 0 ? 0 : 1;
    }
    return res;
}
```



### 4. 不用加减乘除实现加法

用**与运算实现进位**，**异或运算实现加法**。直到算到进位为0即可。**无进位和** 与 **异或运算** 规律相同，**进位** 和 **与运算** 规律相同（并需左移一位）

```java
class Solution {
    public int add(int a, int b) {
        int carry = 0;
        while (b != 0) {
            carry = (a & b) << 1;
            a ^= b;
            b = carry;
        
        }
        return a;
    }
}
```



### 5. 格雷码

https://zhuanlan.zhihu.com/p/29254973

属于位运算把，常规二进制转化为格雷码编码的公式为：`n ^ (n >> 1)`

```java
class Solution {
    private List<Integer> res;
    public List<Integer> grayCode(int n) {
        res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++) {
            res.add(getGrayCode(i));
        }
        return res;
    }

    private Integer getGrayCode(int n) {
        return n ^ (n >> 1);
    }
}
```



### 6. 二进制中1的个数

给你一个整数 `n` ，对于 `0 <= i <= n` 中的每个 `i` ，计算其二进制表示中 **`1` 的个数** ，返回一个长度为 `n + 1` 的数组 `ans` 作为答案。

**示例 1：**

```
输入：n = 2
输出：[0,1,1]
解释：
0 --> 0
1 --> 1
2 --> 10
```

**示例 2：**

```
输入：n = 5
输出：[0,1,1,2,1,2]
解释：
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
```

定义正整数 `x` 的「最低设置位」为 `x`的二进制表示中的最低的`1`所在位。例如，`10` 的二进制表示是 
$$
1010_{(2)}
$$
 ，其最低设置位为 `2`，对应的二进制表示是 
$$
10_{(2)}
$$
令 
$$
y=x~\&~(x-1)
$$
，则 `y` 为将 `x` 的最低设置位从 `1`变成 `0`之后的数，显然 
$$
0 \le y<x，\textit{bits}[x]=\textit{bits}[y]+1
$$
因此对任意正整数 `x`，都有
$$
\textit{bits}[x]=\textit{bits}[x~\&~(x-1)]+1
$$
遍历从 `1` 到 `n` 的每个正整数 `i`，计算 `bits` 的值。最终得到的数组 `bits` 即为答案。

**代码：**

```java
public int[] countBits(int n) {
    int[] res = new int[n + 1];
    for (int i = 1; i <= n; i++) {
        res[i] = res[i & (i - 1)] + 1;
    }
    return res;
}
```

