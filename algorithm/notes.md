## 一. 数组

### 二分查找的边界

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
left = 0, right = nums.length;
while(left <= right){
    return mid;
    left = mid + 1;
    right = mid - 1;
    return left;
}
```

！！！**注意**：如果是二分查找不返回mid的话，那么一般`right = nums.length - 1`，比如**山脉数组，数字峰值**等，其他的与第一种写法一样。



### 快排搜索边界

```java
quicksort(list, 0, list.length - 1);

int index = partition(nums, start, end);
quicksort(nums, start, index - 1);
quicksort(nums, index + 1, end);

private int random_partition(int[] nums, int left, int right) {
    int i = random.nextInt(right - left + 1) + left;
    swap(nums, left, i);
    return partition(nums, left, right);
}

private int partition(int[] nums, int start, int end) {
    while (start < end) {
        while (start < end && nums[end] >= pivot) end--;
        nums[start] = nums[end];
        while (start < end && nums[start] <= pivot) start++;
        nums[end] = nums[start];
    }
    nums[end] = pivot;
    return end;
}

/**
 * i，j，r三个变量分别代表左半部分最后一个元素，未分区的元素的第一个元素，最后一个最为中枢的元素
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
```

<img src="https://assets.codetop.cc/user/note/24967_zY9rGVtnak62qoFZ.jpg" alt="image" style="zoom: 67%;" />

### 堆

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
        int left = 2 * i + 1, right = 2 * i + 2, maxIndex = i;
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



### 最大子数组和

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



### 环形子数组的最大和

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



### 最大子矩阵和

返回一个数组 `[r1, c1, r2, c2]`，其中 `r1, c1` 分别代表子矩阵左上角的行号和列号，`r2, c2` 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

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



### 和为K的子数组

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



### 和可被K整除的子数组

`preSum % k`作为判断条件

```java
    public int subarraysDivByK(int[] nums, int k) {
        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = (preSum % k + k) % k;
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }
```



### 连续的子数组和

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



### 统计优美子数组

前缀和思想：

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

### 最长递增子序列

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
**基于二分查找的动态规划**：

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
        return left;
    }
```



### 最长连续序列

```java
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
```

我们的做法可以是从当前元素`num`开始，一次查找`num+1, num+2, ... num+y`是否在数字中，如果都在那么个最后的答案就是`y`。那么这个其实不是最优的，因为如果当前元素是`num+2`的话，那么找到`num+y`，这算一次了。但是如果走到`num`，还要找一遍`num+1, num+2, ... num+y`。那么前面走的那一遍就白走了。所以我们就直接找的最小的元素`num`，从`num`开始`+1`的找。既然`num`是数组中的最小值，那么数组中肯定不存在`num-1`，所以`num-1`就是我们判断最小值的条件。如果`num-1`在数组里，`num`就不是最小值，跳过。如果`num-1`不在数组中，那么`num`就是最小值，这个时候开始往上找。



### 最长连续不重复子序列(下标连续)

```java
输入: nums= [1,2,2,3,5]
输出格式: 3
解释：最长下标连续不重复的子序列是[2,3,5]。它的长度是3
```

双指针算法的模板一般都可以写成下面的形式(模板)：

```java
for (int i = 0, j = 0; i < n; i++)
{
    while (j < i && check(i, j)) j++;
    // 每道题目的具体逻辑
}
```

因为双指针算法是一种优化时间复杂度的方法，所以我们可以首先写出最朴素的**两层循环**的写法。然后考虑题目中是否具有**单调性**。即当其中一个指针 向后移动时，在希望得到答案的情况下，另一个指针 是不是只能向着一个方向移动。

这道题我们只枚举`i`， `j`的话是每次看一下要不要往后走。如果有重复元素的话，就`j++`。一直移动到`j`和`i`之间没有重复元素为止，所以最多是`i`走`n`步，`j`走`n`步，一共走`2n`步。

```java
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
```



### 最长不含重复字符的子字符串

和上一题一样的思想！（也是滑动窗口）



### 三数之和


```java
public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int third = n - 1;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < third && nums[i] + nums[j] + nums[third] > 0)
                    third--;
                if (j == third)
                    break;
                if (nums[i] + nums[j] + nums[third] == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
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

### N数之和

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



### 搜索旋转排序数组

查看当前 `mid` 为分割位置分割出来的两个部分 `[l, mid]` 和 `[mid + 1, r]` 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 `target` 在不在这个部分：

- 如果 `[l, mid - 1]` 是有序数组，且 target 的大小满足 `[nums[l],nums[mid])`，则我们应该将搜索范围缩小至 `[l, mid - 1]`，否则在 `[mid + 1, r]` 中寻找。
- 如果 `[mid, r]` 是有序数组，且 target 的大小满足 `(nums[mid+1],nums[r]]`，则我们应该将搜索范围缩小至 `[mid + 1, r]`，否则在 `[l, mid - 1]` 中寻找。

```java
while (low < high) {
    int mid = (low + high) / 2;
    if (nums[mid] == target)
        return mid;
    if (nums[0] < nums[mid]) {
        if (target >= nums[0] && target < nums[mid])
            high = mid;
        else
            low = mid + 1;
    }
    else {
        if (target > nums[mid] && target <= nums[high - 1])
            low = mid + 1;
        else
            high = mid;
    }
}
```



### 寻找旋转排序数组中的最小值 II

和I一样，只拿`mid`的元素和`right`的元素比较 ，来判断。这里多了重复元素的情况，所以，如果`mid`元素和`right`元素相同，那么`right--`。因为总会有`mid`能兜住最小值。

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



### 乘积最大子数组

由于数字中有正负，所以不光要记录最大的乘积，也要记录最小的乘积。对于当前数字，不管正负，都要分别×最大乘积和最小乘积，然后选最大的当结果。



### 两个有序数组第k小的数

https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/

我们一次遍历就相当于去掉不可能是中位数的一个值，也就是一个一个排除。由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数。看下边一个例子。

假设我们要找第 7 小的数字。

我们比较两个数组的第 k/2 个数字，如果 k 是奇数，向下取整。也就是比较第 33 个数字，上边数组中的 44 和下边数组中的 33，如果哪个小，就表明该数组的前 k/2 个数字都不是第 k 小数字，所以可以排除。也就是 11，22，33 这三个数字不可能是第 77 小的数字，我们可以把它排除掉。将 13491349 和 4567891045678910 两个数组作为新的数组进行比较。

更一般的情况 A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ... ，如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字。

A 数组中比 A[k/2] 小的数有 k/2-1 个，B 数组中，B[k/2] 比 A[k/2] 小，假设 B[k/2] 前边的数字都比 A[k/2] 小，也只有 k/2-1 个，所以比 A[k/2] 小的数字最多有 k/1-1+k/2-1=k-2个，所以 A[k/2] 最多是第 k-1 小的数。而比 A[k/2] 小的数更不可能是第 k 小的数了，所以可以把它们排除。

橙色的部分表示已经去掉的数字。



由于我们已经排除掉了 3 个数字，就是这 3 个数字一定在最前边，所以在两个新数组中，我们只需要找第 7 - 3 = 4 小的数字就可以了，也就是 k = 4。此时两个数组，比较第 2 个数字，3 < 5，所以我们可以把小的那个数组中的 1 ，3 排除掉了。



我们又排除掉 2 个数字，所以现在找第 4 - 2 = 2 小的数字就可以了。此时比较两个数组中的第 k / 2 = 1 个数，4 == 4，怎么办呢？由于两个数相等，所以我们无论去掉哪个数组中的都行，因为去掉 1 个总会保留 1 个的，所以没有影响。为了统一，我们就假设 4 > 4 吧，所以此时将下边的 4 去掉。



由于又去掉 1 个数字，此时我们要找第 1 小的数字，所以只需判断两个数组中第一个数字哪个小就可以了，也就是 4。

所以第 7 小的数字是 4。

我们每次都是取 k/2 的数进行比较，有时候可能会遇到数组长度小于 k/2的时候。



此时 k / 2 等于 3，而上边的数组长度是 2，我们此时将箭头指向它的末尾就可以了。这样的话，由于 2 < 3，所以就会导致上边的数组 1，2 都被排除。造成下边的情况。



由于 2 个元素被排除，所以此时 k = 5，又由于上边的数组已经空了，我们只需要返回下边的数组的第 5 个数字就可以了。

从上边可以看到，无论是找第奇数个还是第偶数个数字，对我们的算法并没有影响，而且在算法进行中，k 的值都有可能从奇数变为偶数，最终都会变为 1 或者由于一个数组空了，直接返回结果。

所以我们采用递归的思路，为了防止数组长度小于 k/2，所以每次比较 min(k/2，len(数组) 对应的数字，把小的那个对应的数组的数字排除，将两个新数组进入递归，并且 k 要减去排除的数字的个数。递归出口就是当 k=1 或者其中一个数字长度是 0 了。

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



### Knuth洗牌算法

**共有 n 个不同的数，根据每个位置能够选择什么数，共有n! 种组合。**

**题目要求每次调用 shuffle 时等概率返回某个方案，或者说每个元素都够等概率出现在每个位置中。**

我们可以使用 Knuth 洗牌算法，在O(n) 复杂度内等概率返回某个方案。

具体的，我们从前往后尝试填充 [0,n−1] 该填入什么数时，通过随机当前下标与（剩余的）哪个下标进行值交换来实现。

对于下标x而言，我们从[x,n−1] 中随机出一个位置与 x 进行值交换，当所有位置都进行这样的处理后，我们便得到了一个公平的洗牌方案。

对于下标为0位置，从 [0,n−1] 随机一个位置进行交换，共有 n种选择；下标为 1的位置，从[1,n−1] 随机一个位置进行交换，共有n−1 种选择

```java
Random rnd = new Random();
for (int i = length - 1 ; i > 0 ; i--) {
    int j = rnd.nextInt(i + 1);
    swap(nums, i, j);
}
```



### 下一个排列

注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：

- 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。

- 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。

<img src="https://assets.leetcode-cn.com/solution-static/31/31.gif" alt="fig1" style="zoom:67%;" />

```java
public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1])
        i--;
    if (i >= 0) {
        int j = nums.length - 1;
        while (j > i && nums[j] <= nums[i])
            j--;
        swap(nums, i, j);
    }
    reverse(nums, i + 1);
}
```



### 打家劫舍3

注意好问题的定义！是父与子的节点不能都选中，兄弟之间是可以都选择的，所以下面的状态不要写错啊

```java
int rob = root.val 
    + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) // 两个递归是相加的关系
    + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
int noRob = rob(root.left) + rob(root.right);
int res = Math.max(rob, noRob);
```



### 合并区间

先把初始区间放到结果集里面，然后遍历每个其他的区间，找边界，更改还是新添加区间！



### 荷兰国旗

和快排的`partition`思想一样，正常的状态下，两个指针`i,j`分别指向`1，2`序列的第一个元素。如果`1，2`没都出现的时候，那么`i, j`两个指针是指在一起的。初始状态下`i = 0, j = 0`。

```java
for (int k = 0; k < nums.length; k++) {
    if (nums[k] == 0) {
        swap(nums, i, k);
        if (i < j)
            swap(nums, j, k);
        i++;
        j++;
    } else if (nums[k] == 1) {
        swap(nums, j++, k);
    }
}
```



### 摆动排序

给你一个整数数组 `nums`，将它重新排列成 `nums[0] < nums[1] > nums[2] < nums[3]...` 的顺序。

方法：

- 使用快速选择，选择好中位数
- 使用3-way-partition将小于中位数的数字都放在中位数的左边，大于中位数的数字放在中位数的右边
- 逆序分别选择两半数字，进行插入组成摆动的排序



### 区间列表的交集

双指针，根据当前指针对应区间的相交情况，计算相交段计入结果并不断移动指针**（注意，每次选择A或B中的一个指针移动）**

判断两个区间是否相交使用的是`&&`条件判断。



### N皇后

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



### 单调栈

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



### 柱状图中最大的矩形

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



### 求区间最小数乘区间和的最大值

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



### 数组中的逆序对

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



### 缺失的第一个正数

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



## 二. 链表

### 复制带随机指针的链表

先用一个循环把新旧链表对应的两个结点捆绑在一个`HashMap`二元组里，然后再用一个循环完成对新链表每个结点的`next`域和`random`域的赋值，学习到了！

```java
for (; node != null; node = node.next) {
    map.put(node, new Node(node.val));
}
for (; node != null; node = node.next) {
    map.get(node).next = map.get(node.next);
    map.get(node).random = map.get(node.random);
}
return map.get(head);
```

这种空间复杂度和时间复杂度都是`O(n)`。

方法二的做法也很常见，构建+拆分链表。我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表 $A \rightarrow B \rightarrow C$，我们可以将其拆分为 $A→A'→B→B'→C→C'$。对于任意一个原节点 $S$，其拷贝节点$ S'$即为其后继节点。这样，我们可以直接找到每一个拷贝节点 $S'$的随机指针应当指向的节点，即为其原节点 $S$ 的随机指针指向的节点 $T$ 的后继节点$ T'$。需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。

```java
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
    node.next = node.next.next;
    newNode.next = (newNode.next != null) ? newNode.next.next : null;
}
return newhead;
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

就一个cur指针指向头结点，然后判断cur.next和cur.next.next的值是否相等。

```java
while (cur.next != null && cur.next.next != null) {
    if (cur.next.val != cur.next.next.val) {
        // cur指向下一个
    } else {
        int num = cur.next.val; // 记录重复的节点值
        while (cur.next != null && cur.next.val == num) {
            cur.next = cur.next.next;
        } // 开始一个个删掉
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



## 三. 排列组合

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
```



## 四. 字符串

### 滑动窗口框架

```java
int left = 0, right = -1;

while (right < s.size()) {
    // 增大窗口
    window.add(s[right]);
    // 右移窗口
    right++;
    // 进行窗口内数据的一系列更新
    ...

    /*** debug 输出的位置 ***/
    printf("window: [%d, %d)\n", left, right);
    /********************/

    // 判断左侧窗口是否要收缩
    while (window needs shrink) {
        // 缩小窗口
        window.remove(s[left]);
        left++;
    }
}
```



### 无重复字符的最长子串

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

这个题是带有查询的回溯

一。 回溯法

```java
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



### 字符串中的变位词

```java
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] win = new int[26];
        for (int i = 0; i < m; i++)
            --win[s1.charAt(i) - 'a'];
        int left = 0;
        for (int right = 0; right < n; right++) {
            ++win[s2.charAt(right) - 'a'];
            while (win[s2.charAt(right) - 'a'] > 0) {
                --win[s2.charAt(left) - 'a'];
                left++;
            }
            if (right - left + 1 == m)
                return true;
        }
        return false;
    }
```



### 字符串中的所有变位词

```java
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] sWin = new int[26];
        int[] pwin = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pwin[p.charAt(i) - 'a']++;
        }
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            sWin[s.charAt(end) - 'a']++;
            while (sWin[s.charAt(end) - 'a'] > pwin[s.charAt(end) - 'a']) {
                sWin[s.charAt(start) - 'a']--;
                start++;
            }
            if (end - start + 1 == p.length())
                list.add(start);
        }
        return list;
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

中心扩张发：以`s[i]`和`s[i, i + 1]`作为核心，分别向两段扩张，如果两段的字符相等，那么这就是一个回文子串。

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



## 五. 设计

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



### 设计一个HashSet

为了实现哈希集合这一数据结构，有以下几个关键问题需要解决：

- 哈希函数：能够将集合中任意可能的元素映射到一个固定范围的整数值，并将该元素存储到整数值对应的地址上。

- 冲突处理：由于不同元素可能映射到相同的整数值，因此需要在整数值出现「冲突」时，需要进行冲突处理。总的来说，有以下几种策略解决冲突：

  - 链地址法：为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中。

  - 开放地址法：当发现哈希值 h 处产生冲突时，根据某种策略，从 h 出发找到下一个不冲突的位置。

  - 再哈希法：当发现哈希冲突后，使用另一个哈希函数产生一个新的地址。

- 扩容：当哈希表元素过多时，冲突的概率将越来越大，而在哈希表中查询一个元素的效率也会越来越低。因此，需要开辟一块更大的空间，来缓解哈希表中发生的冲突。

使用链地址法解决：

```java
private static final int BASE = 769;
private LinkedList[] data;
```



### 设计一个HashSet

```java
class MyHashMap {
    private class Pair {
        private int key;
        private int value;
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
    }
    
    public int get(int key) {
        int hashKey = hash(key);
    }
    
    public void remove(int key) {
        int hashKey = hash(key);
    }

    public int hash(int key) {
        return key % BASE;
    }
}
```



### 格雷码

https://zhuanlan.zhihu.com/p/29254973

属于位运算把，常规二进制转化为格雷码编码的公式为：`n ^ (n >> 1)`



### 并查集

```java
class UnionFind {
    int[] parents;

    public UnionFind(int totalNodes) {
        parents = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
        }
    }
		// 合并连通区域是通过find来操作的, 即看这两个节点是不是在一个连通区域内.
    void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
        }
    }

    int find(int node) {
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parents[node] = parents[parents[node]];
            node = parents[node];
        }

        return node;
    }

    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
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



## 六. 栈

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
private void dfs(int n, StringBuilder path, int leftNum, int rightNum) {
    // 1. 条件满足的判断，左右括号计数等于n
    // 2. 开始回溯生成（
    if (leftNum < n) {
        path.append('(');
        dfs(n, path, leftNum + 1, rightNum);
        path.deleteCharAt(path.length() - 1);
    }
    // 3. 开始回溯生成符合数量的右括号），条件是右括号要小于左括号的时候才生成
    if (rightNum < leftNum) {}
}
```



## 七. 树

### 非递归遍历

前序：

```java
while(!stack.isEmpty() || root != null) {
    while (root != null) {
        // 输出节点值
        stack.push(root);
        root = root.left;
    }
    root = stack.pop();
    root = root.right;
}
```

中序：

```java
while (!stack.isEmpty() || root != null) {
    while (root != null) {
        stack.push(root);
        root = root.left;
    }
    root = stack.pop();
    // 输出节点值
    root = root.right;
}
```

后序：

```java
TreeNode prev = null; // 指向当前节点的前一个节点，判断右子树遍历完后，遍历根节点的情况。
while (!stack.isEmpty() || root != null) {
    while (root != null) {
        stack.push(root);
        root = root.left;
    }
    root = stack.pop();
    if (root.right == null || root.right == prev) {
        // 输出节点值    
        prev = root;
        root = null;
    } else {
        stack.push(root); // 将右子树压入栈中
        root = root.right;
    }
}
```



### 序列化二叉树

层序遍历序列化和深度优先遍历序列化

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

```java
if (root == null)
    return true;
if (min != null && min.val >= root.val) //
    return false;
if (max != null && max.val <= root.val) //
    return false;
return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
```

迭代法参考中序非递归遍历，中序遍历是升序的！否则返回`false`。



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



## 八. 图

### 拓扑排序

#### 深度优先算法

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 判断节点i是否被访问，0表示未访问，1表示被访问过
        visited = new int[numCourses];
        // 2. 建立图的邻接表存储edges
        // 3. 开始深度优先遍历所有节点
        for (int i = 0; i < numCourses && isValid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        // 4. 返回是否有效即可，不需定义数据结构存储顺序
        return isValid;
    }

    private void dfs(int i) {
        // 正在访问节点（包括其子节点）
        visited[i] = 1;
        for (int v : edges.get(i)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!isValid)  return;
            } else if (visited[v] == 1) { // 则存在环，返回false
                isValid = false;
                return;
            }
        }
        // 访问完毕，置2
        visited[i] = 2;
    }
}
```

#### 广度优先算法

```java
private boolean bfdCanFinish(int numCourses, int[][] prerequisites) {
    // 1. 图的邻接表存储edges
    edges = new ArrayList<>();
    // 2. 存节点的入度信息，方便从入度为0的节点开始进行广度优先搜索
    indeg = new int[numCourses];
    // 3. 先将入度为0的节点放入队列中
    Queue<Integer> queue = new LinkedList<>();
    // 4. 由于不需要输出序列，所以直接计数就行，遍历过的节点技术，不用使用数据结构保存
    int res = 0;
    while (!queue.isEmpty()) {
        res++;
        int node = queue.poll();
        // 5. 弹出一个节点，边数－1，即相邻节点的入度-1
        // 6. 将新的0入度点加入到队列中
    }
    return res == numCourses;
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



## 九. 背包问题

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

```java
public class complete_knapsack {

    /**
     * 完全背包问题
     * @param W
     * @param weights
     * @return
     */
    public int solutionForDP(int W, int[] weights) {
        int[][] dp = new int[weights.length + 1][W + 1];
        for (int i = 0; i <= weights.length; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     * dp[i][j - weights[i - 1]]这个一定要注意，由于i的选取不止一次，所以
                     * i的情况可分为（1）一次都不选（2）选一次的情况
                     */
                    dp[i][j] = dp[i - 1][j] + dp[i][j - weights[i - 1]];
                }
            }
        }
        return dp[weights.length][W];
    }

    public static void main(String[] args) {
        complete_knapsack solution = new complete_knapsack();
        System.out.println(solution.solutionForDP(5, new int[]{1, 2, 5}));
    }
}
```



### 一和零

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



## 十. 数学

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



### 数字 1 的个数

https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/

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



### 第N位数字

https://leetcode-cn.com/problems/nth-digit/solution/wei-ruan-zhao-pin-ing-400-di-n-wei-shu-z-hb7i/

```java
    public int findNthDigit(int n) {
        int digit = 1;
        int numDigit = 9;
        // 开始求是几位数字
        while (n > digit * numDigit) {
            n -= numDigit * digit++;
            numDigit *= 10;
            if(Integer.MAX_VALUE / numDigit < digit){
                break;
            }
        }
        // 开始求是哪个数字num
        int num = (int) (Math.pow(10, digit - 1)) + (n - 1) / digit;
        // 开始求第几位iDigit
        int iDigit = (n - 1) % digit;
        // 那就是num的第iDigit位置(从高位计算)
        return num / (int) (Math.pow(10, digit - iDigit - 1)) % 10;
    }
```



### 约瑟夫环问题

```java
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }
```



### 不用加减乘除实现加法

用与运算实现进位，异或运算实现加法。知道算到进位为0即可。

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



### 整数除法

```java
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
```

