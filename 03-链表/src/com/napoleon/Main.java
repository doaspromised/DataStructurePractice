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
	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<>();
//		List<Integer> list2 = new CycleLinkedList<>();
//		list2.add(20);
//		list2.add(0,10);
//		list2.add(30);
//		list2.add(1, 40);
//		list2.remove(2);
//		list2.remove(list2.size() - 1);
//		System.out.println(list2);
		josephus();
	}
}
