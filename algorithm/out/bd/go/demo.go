package main

import "fmt"

type Student struct {
	name   string
	age    int64
	isMale bool
}

func main() {
	nums := []int64{2, 4, 6, 8, 9, 10}
	target := int64(1)
	index := binarySearch(nums, target)
	fmt.Println(index)
	student := Student{
		name:   "jianglihao",
		age:    25,
		isMale: true,
	}
	fmt.Println(student)
}

func binarySearch(nums []int64, target int64) int64 {
	left := 0
	right := len(nums)
	for left < right {
		mid := left + (right-left)/2
		if target == nums[mid] {
			return int64(mid)
		} else if target < nums[mid] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return -1
}
