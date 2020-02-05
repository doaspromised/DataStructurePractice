package com.napoleon;

/**
 * 循环双端队列
 * @author nickdada
 *
 */
@SuppressWarnings("unchecked")
public class CircleDeque<E> {
	private int front = 0;
	private int size;
	private E[] elements;
	
	public CircleDeque() {
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
	public void enQueueRear(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}
	/**
	 * 从头部入队
	 * @param element
	 */
	public void enQueueFont(E element) {
		ensureCapacity(size + 1);
		front = index(-1);
		elements[front] = element;
		size++;
	}
	/**
	 * 从尾部离队
	 * @return
	 */
	public E deQueueRear() {
		int realIndex = index(size - 1);
		E element = elements[realIndex];
		elements[realIndex] = null;
		size--;
		return element;
	}
	
	public E deQueueFront() {
		E element = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return element;
	}
	
	public E front() {
		return elements[front];
	}
	
	public E Rear() {
		return elements[index(size - 1)];
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
		int capacity = elements.length;
		index += front;
		if (index < 0) {
			return index + capacity;
		}
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
