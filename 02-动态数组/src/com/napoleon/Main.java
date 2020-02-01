package com.napoleon;

public class Main {
	public static void main(String[] args) {
//		java.util.ArrayList<E>
		ArrayList<Object> persons = new ArrayList<>(10);
//		list.add(11);
//		list.add(22);
//		list.add(33);
//		list.add(44);
//		list.set(1, 99);
		for (int i = 0; i < 10; i++) {
			persons.add(i);
		}
//		list.remove(9);
//		list.add(list.size(), 100);
		persons.add(new Person(10, "jack"));
		persons.add(new Person(11, "hiii"));
		persons.add(new Person(12, "jiiis"));
		System.out.println(persons);
	}
}
