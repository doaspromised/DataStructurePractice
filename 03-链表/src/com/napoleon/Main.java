package com.napoleon;

public class Main {
	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<>();
		List<Integer> list2 = new LinkedList<>();
		list2.add(20);
		list2.add(0,10);
		list2.add(30);
		list2.add(list2.size(), 40);
		list2.remove(2);
		list2.remove(list2.size() - 1);
		System.out.println(list2);
	}
}
