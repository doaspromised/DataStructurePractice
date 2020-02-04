package com.napoleon;

import com.napoleon.list.ArrayList;
import com.napoleon.list.List;

/**
 * 可以使用继承或者组合来实现，从设计的角度讲，组合模式更好一些
 * 
 * @author nickdada
 *
 */
public class Stack<E> {
	private List<E> list = new ArrayList<>();
	public void clear() {
		list.clear();
	}
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public void push(E element) {
		list.add(element);
	}
	public E pop() {
		return list.remove(list.size() - 1);
	}
	public E top() {
		return list.get(list.size() - 1);
	}
	
}
