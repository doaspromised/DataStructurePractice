package com.napoleon;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private int front = 0;
	private int size;
	private E[] elements;
	public CircleQueue() {
		elements = (E[]) new Object[10];
	}
	public void cealr() {
		for (int i = 0; i < size; i++) {
			elements[index(i)] = null;
		}
		size = 0;
		front = 0;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void enQueue(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}
	public E deQueue() {
		E frontElementE = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return frontElementE;
	}
	
	public E front() {
		return elements[front];
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		s.append("capacity=").append(elements.length)
		.append(", Size=").append(size)
		.append(", Front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == null) {
				s.append("null,");
			} else {
				s.append(elements[i]).append(",");
			}
		}
		s.append("]");
		return s.toString();
	}
	
	/**
	 * 获取真正的index
	 * @param index
	 * @return
	 */
	private int index(int index) {
//		return (front + index) % elements.length;
		// 模运算优化: 
		// 尽量避免使用乘，除， 模，浮点数运算，效率低下。
		// 已知：m > 0, n < 0
		// n%m 等价于 n - (n >=m ? m : 0), 前提条件是： n < 2m
		int capacity = elements.length;
		index += front;
		return index - (index >= capacity ? capacity : 0);
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity < capacity) {
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			E[] newElements = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[index(i)];
			}
			elements = newElements;
//			重置front
			front = 0;
			System.out.println("扩容 = " + newElements.length);
		}
	}
}
