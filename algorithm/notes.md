## 数组

### 二分查找的边界

```java
left = 0, right = nums.length;
while(left < right){
    left = mid + 1;
    right = mid;
    return left;
}
```



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
```



### 堆

两个操作，（一）建堆`buildHeap` （二）维护堆`heapify`

（一）从元素一般的位置上，从后往前建堆；

（二）维护堆的意思就是确保**堆中的所有节点**，都是父节点大于/小于子节点的；

```java
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = 2 * i + 1, right = 2 * i + 2, maxIndex = i;
        if (left < heapSize && nums[left] > nums[maxIndex])
            maxIndex = left;
        if (right < heapSize && nums[right] > nums[maxIndex])
            maxIndex = right;
        if (maxIndex != i) {
            swap(nums, maxIndex, i);
            maxHeapify(nums, maxIndex, heapSize);
        }
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; --i) {
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



### 排列组合问题

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



## 字符串

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



## 设计

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

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        System.out.println(lruCache);
        lruCache.put(2,2);
        System.out.println(lruCache);
        lruCache.put(3,3);
        System.out.println(lruCache);
        lruCache.get(1);
        System.out.println(lruCache);
        lruCache.put(2, 5);
        System.out.println(lruCache);
        lruCache.put(4,4);
        System.out.println(lruCache);
    }
}
```



## 栈

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



## 树

### 非递归遍历（未完成）

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



