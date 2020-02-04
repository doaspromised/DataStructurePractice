package com.napoleon;

import com.napoleon.cycle.CycleLinkedList;
import com.napoleon.cycle.SingleCycleLinkedList;


public class Main {
	/**
	 * 约瑟夫问题
	 */
	static void josephus() {
		CycleLinkedList<Integer> list = new CycleLinkedList<>();
		for (int i = 1; i < 9; i++) {
			list.add(i);
		}
		// 指向头结点
		list.reset();
		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}
	
	/**
	 * 静态链表
	 * 
	 * 没有指针的情况下，如何实现链表？？？
	 * 
	 * 可以通过数组来模拟链表，成为静态链表；
	 * 数组的每个元素存放两个数据，值和下个元素的索引。
	 * 
	 * 数组的元素可以存放两个数据？？？  可以  比如C语言的结构体；
	 * 
	 * 如果不能存放，可以使用两个数组，一个存放元素，一个存放索引，了解即可。
	 *  
	 * 数组0存放的是头结点信息。
	 *
	 */
	
	
	
	
	
	public static void main(String[] args) {
		List<Integer> list2 = new ArrayList<>();
//		List<Integer> list2 = new CycleLinkedList<>();
		list2.add(20);
		list2.add(0,10);
		list2.add(30);
		list2.add(1, 40); // [10, 40, 20, 30];
		list2.remove(0);
		System.out.println(list2);
//		josephus();
	}
}
