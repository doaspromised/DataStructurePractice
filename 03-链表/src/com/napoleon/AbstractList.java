package com.napoleon;
/**
 * 抽象类是不可以去创建的，不对外公开，只负责抽取一些公共代码。
 * abstract 表示自己不必去实现接口 自己的子类去实现接口
 * @author nickdada
 *
 * @param <E>
 */
public abstract class AbstractList<E> implements List<E> {
	
	// 元素数量
	protected int size;
	
	/**
	 * 元素数量
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 是否包含某个元素
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	} 
	/**
	 * 添加元素到尾部
	 * 
	 * @param element
	 */
	public void add(E element) {
		add(size, element);
	}
	
	protected void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outBounds(index);
		}
	}
	protected void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outBounds(index);
		}
	}
	protected void outBounds(int index) {
		throw new IndexOutOfBoundsException("Index = " + index + ", Size =  " + size);
	}
}
