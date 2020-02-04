package com.napoleon.list;

/**
 * 动态数组
 * 
 * 增删的复杂度是 O(n)
 * 改查的复杂度是 O(1)
 * 
 * @author nickdada
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
	/**
	 * 所有元素
	 */
	private E[] elements;
	/**
	 * 数组开始的索引
	 */
	private int firstIndex = 0;

	/**
	 * 默认容量
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * 可指定容量的构造函数
	 * 
	 * @param capacity
	 */
	public ArrayList(int capacity) {
		// 对capacity做安全性校验
		capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 清楚所有元素
	 */
	public void clear() {
		// 释放 elements 中存储的元素对象。
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		// 不能 直接设置 elements = null， 因为我们还要循环利用elements，避免重复的开辟销毁内存空间。
		size = 0;
		// 缩容
		trim();
	}

	/**
	 * 在指定位置添加元素
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {// O(n)
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}

	/**
	 * 获取index位置的元素
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {// O(1)
		rangeCheck(index);
		index = getIndex(index);
		return elements[index];
	}

	/**
	 * 在index位置处插入元素
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public E set(int index, E element) {// O(1)
		index = getIndex(index);
		E oldElement = elements[index];
		elements[index] = element;
		return oldElement;
	}

	/**
	 * 删除index位置的元素
	 * 
	 * @param index
	 * @return
	 */
	public E remove(int index) { // O(n)
		rangeCheck(index);
		index = getIndex(index);
		E removedElement = elements[index];
		if (index == 0) {
			firstIndex = 1;
		} else {
			for (int i = index; i < size - 1; i++) {
				elements[i] = elements[i + 1];
			}
		}
		elements[--size] = null;
//		缩容
		trim();
		return removedElement;
	}
	
	public void remove(E element) {
	    remove(indexOf(element));
	}

	/**
	 * 查看元素索引
	 * 
	 * @param element
	 * @return
	 */
	public int indexOf(E element) {
		// null 值处理
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	/**
	 * 获取真正的index
	 * @param index
	 * @return
	 */
	private int getIndex(int index) {
		int capacity = elements.length;
		index = firstIndex + index;
		if (index > capacity) {
			index = index % capacity;
		}
		return index;
	}
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity < capacity) {
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			E[] newElements = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
			System.out.println("扩容 = " + newElements.length);
		}
	}
	
	/**
	 * 缩容
	 * 
	 * 如果扩容和缩容的设计时机不恰当（扩容因子 * 缩容因子 = 1）  会造成复杂度震荡。
	 */
	private void trim() {
//		剩余空间占总容量一半 就进行缩容 这个条件可以自己制定
		int capacity = elements.length;
		if (size >= (capacity >> 1) || capacity <= DEFAULT_CAPACITY) {
			return;
		}
		int newCapacity = capacity >> 1;
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		s.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			int index = getIndex(i);
			if (index != firstIndex) {
				s.append(",").append(elements[i]);
			} else {
				s.append(elements[i]);
			}
		}
		s.append("]");
		return s.toString();
	}
}
