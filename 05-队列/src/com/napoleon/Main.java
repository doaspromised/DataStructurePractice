package com.napoleon;

public class Main {

	static void test() {
		Deque<Integer> queue = new Deque<>();
		queue.enQueueFont(11);
		queue.enQueueFont(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
	static void testCirclrQueue() {
		CircleQueue<Integer> queue = new CircleQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		for (int i = 15; i < 23; i++) {
			queue.enQueue(i);
		}
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
	
	public static void main(String[] args) {
		
		CircleDeque<Integer> queue = new CircleDeque<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueueFont(i + 1);
			queue.enQueueRear(i + 100);
		}
		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}
		queue.enQueueFont(11);
		queue.enQueueFont(12);
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
}
