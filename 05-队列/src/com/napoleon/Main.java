package com.napoleon;

public class Main {

	public static void main(String[] args) {
		Deque<Integer> queue = new Deque<>();
		queue.enQueueFont(11);
		queue.enQueueFont(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
		
	}
	
}
