package com.napoleon;

/**
 * 双端队列
 * 
 * @author nickdada
 *
 */
public class Deque<E> {

	private List<E> list = new LinkedList<>();
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public void enQueueRear(E element) {
		list.add(element);
	}
	public void enQueueFont(E element) {
		list.add(0, element);
	}
	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}
	public E deQueueFront() {
		return list.remove(0);
	}
	
	public E front() {
		return list.get(0);
	}
	
	public E Rear() {
		return list.get(list.size() - 1);
	}
}
