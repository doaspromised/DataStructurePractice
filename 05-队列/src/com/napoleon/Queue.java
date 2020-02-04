package com.napoleon;

/**
 * 优先使用双向链表，因为队列主要是往头尾操作元素
 * @author nickdada
 *
 * @param <E>
 */
public class Queue<E> {
	private List<E> list = new LinkedList<>();
	public void cealr() {
		list.clear();
	}
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public void enQueue(E element) {
		list.add(element);
	}
	public E deQueue() {
		return list.remove(0);
	}
	
	public E front() {
		return list.get(0);
	}
	
}
