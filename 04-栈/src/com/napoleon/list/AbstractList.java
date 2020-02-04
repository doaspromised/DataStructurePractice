package com.napoleon.list;
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
		/**
		 * 往数组最后的地方添加元素  
		 * 最好的情况 O(1) 
		 * 最坏的情况是扩容的时候：O(n) 
		 * 平均复杂度 是  O(1)
		 * 绝大部分情况是 O(1)  
		 * 均摊复杂度 O(1)
		 * 
		 */
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
