package main

import (
	"fmt"
	"strconv"
	"time"
)

func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
		time.Sleep(100 * time.Millisecond)
	}
	c <- sum // 把 sum 发送到通道 c
}

func main() {
	s := []int{7, 2, 8, -9, 4, 0}

	c := make(chan int)
	go sum(s[:len(s)/2], c)
	go sum(s[len(s)/2:], c)
	x, y := <-c, <-c // 从通道 c 中接收

	fmt.Println(x, y, x+y)
}

func testConverter() {
	name := "2024"
	num, _ := strconv.ParseInt(name, 10, 64)
	println(num)
	newString := strconv.FormatInt(num, 10)
	println(newString)
}

func testPointer() {
	var a = 4
	var ptr *int

	ptr = &a
	fmt.Println(ptr)  // ptr所指变量的地址（0xc00001e0a8）
	fmt.Println(*ptr) // ptr所指变量（4）
	fmt.Println(&a)   // ptr所指变量的地址（0xc00001e0a8）
	fmt.Println(&ptr) // ptr的地址（0xc00000a028）
}

func testSwitch() {
	switch 2 {
	case 1:
		fmt.Println(1)
	case 2:
		fmt.Println(2)
	case 3:
		fmt.Println(3)
	default:
		fmt.Println("default")
	}
}

func testGoroutine() {
	go func(str string) {
		for i := 0; i < 5; i++ {
			time.Sleep(100 * time.Millisecond)
			fmt.Println(str)
		}
	}("A")

	go func(str string) {
		for i := 0; i < 5; i++ {
			time.Sleep(100 * time.Millisecond)
			fmt.Println(str)
		}
	}("B")
	time.Sleep(40000 * time.Millisecond)
}
