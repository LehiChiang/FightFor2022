package main

import "fmt"

func main() {
	fmt.Println()
	rotate([]int{1, 2, 3, 4, 5, 6, 7}, 3)
}

func removeDuplicates(nums []int) int {
	if len(nums) <= 2 {
		return len(nums)
	}
	slow, fast := 2, 2
	for fast < len(nums) {
		if nums[fast] != nums[slow-2] {
			nums[slow] = nums[fast]
			slow++
		}
		fast++
	}
	return slow
}

func rotate(nums []int, k int) {
	n := len(nums)
	k = k % n
	p, q := n-k, n-k
	res := make([]int, 0)
	for i := 0; i < n; i++ {
		if p < n && p >= q {
			res = append(res, nums[p])
			p++
		} else {
			res = append(res, nums[q+i-p])
		}
	}
	for i := 0; i < n; i++ {
		nums[i] = res[i]
	}
}
