package main

import (
	"container/list"
	"fmt"
)

type MyHashSetInterface interface {
	add(key int64)
	remove(key int64)
	contains(key int64) bool
	hash(key int64) int64
}

type MyHashSet struct {
	BASE   int64       // hash取模
	bucket []list.List // hash桶
}

func (h MyHashSet) add(key int64) {
	if !h.contains(key) {
		hashKey := h.hash(key)
		h.bucket[hashKey].PushBack(key)
	}
}

func (h MyHashSet) remove(key int64) {
	hashKey := h.hash(key)
	if h.contains(key) {
		for e := h.bucket[hashKey].Front(); e != nil; e = e.Next() {
			if e.Value.(int64) == key {
				h.bucket[hashKey].Remove(e)
			}
		}
	}
}

func (h MyHashSet) contains(key int64) bool {
	hashKey := h.hash(key)
	for e := h.bucket[hashKey].Front(); e != nil; e = e.Next() {
		if e.Value.(int64) == key {
			return true
		}
	}
	return false
}

func (h MyHashSet) hash(key int64) int64 {
	return key % h.BASE
}

func main() {
	hashSet := MyHashSet{BASE: 13}
	hashSet.bucket = make([]list.List, hashSet.BASE)
	hashSet.add(3)
	hashSet.add(7)
	hashSet.add(9)
	hashSet.remove(3)
	fmt.Println(hashSet.contains(3))
	fmt.Println(hashSet.contains(9))
}
