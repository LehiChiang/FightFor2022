package out.bd;

import java.util.*;

public class OutBDArray {

    private final int[] nums;
    private List<List<String>> nQueenRes;
    private Set<Integer> inColumns;
    private Set<Integer> inRL;
    private Set<Integer> inLR;

    public OutBDArray(int[] nums) {
        this.nums = nums;
    }

    public int[] getNums() {
        return nums;
    }

    /**
     * 快速选择算法，在给定的Nums中找到第K大的数
     *
     * @param k 第K个
     * @return 返回第K大的数
     */
    public int QuickSelect(int k) {
        // if k is greater than the length of nums, then return -1
        if (k > nums.length || k <= 0)
            return -1;
        return getKthNums(nums.length - k, 0, nums.length - 1);
    }

    private int getKthNums(int k, int start, int end) {
        int pivotPos = partition(start, end);
        if (pivotPos == k)
            return nums[pivotPos];
        else if (pivotPos < k)
            return getKthNums(k, pivotPos + 1, end);
        else
            return getKthNums(k, start, pivotPos - 1);
    }

    private int partition(int left, int right) {
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

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 快速排序
     */
    public void QuickSort() {
        quickSort(0, nums.length - 1);
    }

    private void quickSort(int left, int right) {
        if (left >= right)
            return;
        int pivotPos = partition(left, right);
        quickSort(left, pivotPos - 1);
        quickSort(pivotPos + 1, right);
    }

    /**
     * 和可被K整除的子数组个数
     *
     * @param k
     * @return 子数组个数
     */
    public int SubarraysDivByK(int k) {
        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            int key = Math.floorMod(preSum, k);
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }

    /**
     * 最长递增子序列（动态规划版）
     * Length Of Longest Increasing Subsequence
     *
     * @return 子序列的长度
     */
    public int LIS() {
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

    /**
     * 最长递增子序列（贪心+二分搜索）
     * Length Of Longest Increasing Subsequence With Greedy Algorithm
     *
     * @return 子序列的长度
     */
    public int LISWithGA() {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                int index = ApproximateBinarySearch(nums[i]);
                list.set(index, nums[i]);
            }
        }
        return list.size();
    }

    /**
     * 二分搜索（精确搜索）
     *
     * @param target 被搜索的数字
     * @return 返回target的下标，不存在返回-1
     */
    public int AccurateBinarySearch(int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }

    /**
     * 二分搜索（模糊搜索）
     *
     * @param target 被搜索的数字
     * @return 返回target的下标，不存在返回应该出现target的下标
     */
    public int ApproximateBinarySearch(int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    /**
     * 最长连续不重复子串 LengthOfLongestContinuousNoRepeatSubstring
     *
     * @param s 字符串
     * @return 长度
     */
    public int LCNRS(String s) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        int res = 0, end = -1;
        for (int start = 0; start < length; start++) {
            if (end + 1 >= length)
                break;
            if (start != 0)
                set.remove(s.charAt(start - 1));
            while (end + 1 < length && !set.contains(s.charAt(end + 1))) {
                set.add(s.charAt(end + 1));
                end++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    /**
     * 三数之和
     * 返回结果集中，和为0的三个数字
     *
     * @return 结果列表
     */
    public List<List<Integer>> ThreeSum() {
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

    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在恰好一个解。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,2,1,-4], target = 1
     * <p>
     * 输出：2
     * <p>
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,0], target = 1
     * <p>
     * 输出：0
     *
     * @param target
     * @return
     */
    public int ThreeSumClosest(int target) {
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

    /**
     * 下一个全排列
     *
     * @return
     */
    public void NextPermutation() {
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

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * @return
     */
    public int Rob() {
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

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 示例 2：
     * <p>
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * @param intervals
     * @return
     */
    public int[][] MergeIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(pair -> pair[0]));
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int end = list.get(list.size() - 1)[1];
            if (end >= intervals[i][0])
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], end);
            else
                list.add(intervals[i]);
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
     * <p>
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * <p>
     * 示例 1：
     * <p>
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * <p>
     * 示例 2：
     * <p>
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * <p>
     * 示例 3：
     * <p>
     * 输入：intervals = [], newInterval = [5,7]
     * 输出：[[5,7]]
     * <p>
     * 示例 4：
     * <p>
     * 输入：intervals = [[1,5]], newInterval = [2,3]
     * 输出：[[1,5]]
     * <p>
     * 示例 5：
     * <p>
     * 输入：intervals = [[1,5]], newInterval = [2,7]
     * 输出：[[1,7]]
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] InsertIntervals(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        boolean placed = false;
        int left = newInterval[0], right = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[1] < left)
                list.add(interval);
            else if (interval[0] > right) {
                if (!placed) {
                    list.add(new int[]{left, right});
                    placed = true;
                }
                list.add(interval);
            } else {
                left = Math.min(interval[0], left);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
     * <p>
     * 返回这 两个区间列表的交集 。
     * <p>
     * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
     * <p>
     * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <a href="https://assets.leetcode.com/uploads/2019/01/30/interval1.png">示例图</a>
     * <p>
     * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * <p>
     * 示例 2：
     * <p>
     * 输入：firstList = [[1,3],[5,9]], secondList = []
     * 输出：[]
     * <p>
     * 示例 3：
     * <p>
     * 输入：firstList = [], secondList = [[4,8],[10,12]]
     * 输出：[]
     * <p>
     * 示例 4：
     * <p>
     * 输入：firstList = [[1,7]], secondList = [[3,10]]
     * 输出：[[3,7]]
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] IntervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0)
            return new int[][]{};
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int firstStart = firstList[i][0], firstEnd = firstList[i][1];
            int secondStart = secondList[j][0], secondEnd = secondList[i][1];
            if (firstEnd >= secondStart && firstStart <= secondEnd) {
                list.add(new int[]{Math.max(firstStart, secondStart), Math.min(firstEnd, secondEnd)});
            } else if (firstEnd < secondStart)
                i++;
            else j++;
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 荷兰国旗问题
     *
     * @param nums
     */
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

    /**
     * 搜索旋转数组中的值
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * @param nums
     * @param target
     * @return
     */
    public int SearchInRotatedSortedArray(int[] nums, int target) {
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

    /**
     * 寻找旋转排序数组中的最小值
     * 示例 1：
     * <p>
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
     * <p>
     * 示例 3：
     * <p>
     * 输入：nums = [11,13,15,17]
     * 输出：11
     * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     *
     * @param nums
     * @return
     */
    public int FindMinimumInRotatedSortedArray(int[] nums) {
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

    /**
     * N皇后问题，输入N返回所有可能结果
     *
     * @param n
     * @return
     */
    public List<List<String>> NQueens(int n) {
        nQueenRes = new ArrayList<>();
        inColumns = new HashSet<>();
        inRL = new HashSet<>();
        inLR = new HashSet<>();
        // queens表示皇后的位置queens[i] = j 表示皇后在第i行和第j列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(n, queens, 0);
        return nQueenRes;
    }

    // 行控制
    private void dfs(int n, int[] queens, int row) {
        if (row == n) {
            nQueenRes.add(generateBoard(queens, n));
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

    /**
     * 最短无序连续子数组
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：0
     * <p>
     * 示例 3：
     * <p>
     * 输入：nums = [1]
     * 输出：0
     *
     * @param nums
     * @return
     */
    public int ShortestUnsortedContinuousSubarray(int[] nums) {
        int left = 0, right = nums.length - 1;
        int max = nums[left], min = nums[right];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max)
                max = nums[i];
            else
                right = i;
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] <= min)
                min = nums[j];
            else
                left = j;
        }
        if (left == 0 && right == nums.length - 1)
            return 0;
        return right - left + 1;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * <p>
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * @param heights
     * @return
     */
    public int LargestRectangleArea(int[] heights) {
        int area = heights[0], len = heights.length;
        int[] leftMin = new int[len];
        int[] rightMin = new int[len];
        Arrays.fill(rightMin, len);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                rightMin[stack.peek()] = i;
                stack.pop();
            }
            leftMin[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            area = Math.max(area, (rightMin[i] - leftMin[i] - 1) * heights[i]);
        }
        return area;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * @param height
     * @return
     */
    public int Trap(int[] height) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIndex = stack.pop();
                int left = stack.isEmpty() ? curIndex : stack.peek();
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]) - height[curIndex];
                res += curHeight * curWidth;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 归并排序（非递归实现）
     * 时间复杂度O(nlogn)
     * 空间复杂度O(n)
     */
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
}
