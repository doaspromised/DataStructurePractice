package com.napoleon.cycle;

import javax.xml.soap.Node;

import com.napoleon.AbstractList;

/**
 * 双向循环链表
 * 
 * 相比于 单向链表，空间增多了，但是时间缩短了一半。
 * 
 * 链表增删改查的复杂度都是 O(n)
 * 
 * @author nickdada
 *
 * @param <E>
 */
public class CycleLinkedList<E> extends AbstractList<E> {

	private Node<E> firstNode;
	private Node<E> lastNode;
	private Node<E> currentNode;
	
	private static class Node<E> {
		E element;
		Node<E> prevNode;
		Node<E> nextNode;

		public Node( Node<E> prevNode, E element, Node<E> nextNode) {
			this.element = element;
			this.prevNode = prevNode;
			this.nextNode = nextNode;
		}
		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			if (prevNode != null) {
				s.append("{ prevNode=").append(prevNode.element);
			} else {
				s.append("{ prevNode=null");
			}
			s.append(", element=").append(element);
			if (nextNode != null) {
				s.append(", nextNode=").append(nextNode.element).append("}");
			} else {
				s.append(", nextNode=null");
			}
			return s.toString();
		}

	}

	/**
	 * 重置currentNode位置
	 */
	public void reset() {
		currentNode = firstNode;
	}
	
	/**
	 * 移动 currentNode 到下一个节点
	 * @return
	 */
	public E next() {
		if (currentNode == null) {
			return null;
		}
		currentNode = currentNode.nextNode;
		return currentNode.element;
	}
	
	/**
	 * 移除 currentNode 节点，并把currentNode移动到下一个节点
	 * @return
	 */
	public E remove() {
		if (currentNode == null) {
			return null;
		}
		Node<E> next = currentNode.nextNode;
 		E element = remove(currentNode);
 		if (size == 0) {
			currentNode = null;
		} else {
			currentNode = next;
		}
 		return element;
	}
	@Override
	public void clear() {
		size = 0;
		firstNode = null;
		lastNode = null;
		// java 中不需要遍历去除每个节点的引用，因为 java中如果对象没有没 gc root 引用，就会死掉。
		// gc root 对象包括：
		// 1. 被栈指针指向的对象
	}

	@Override
	public E get(int index) { 
		return node(index).element;
	}

	@Override
	public E set(int index, E element) { // O(n)
		Node<E> node = node(index);
		E oldElement = node.element;
		node.element = element;
		return oldElement;
	}

	@Override
	public void add(int index, E element) {// O(n)
		rangeCheckForAdd(index);
		if (index == size) { // 添加到最后面
			Node<E> oldLastNode = lastNode;
			lastNode = new Node<>(oldLastNode, element, firstNode);
			if (oldLastNode== null) {
				firstNode = lastNode;
				firstNode.nextNode = firstNode;
				firstNode.prevNode = firstNode;
			} else {
				oldLastNode.nextNode = lastNode;
				firstNode.prevNode = lastNode;
			}
		} else {
			Node<E> nexNode = node(index);
			Node<E> prevNode = nexNode.prevNode;
			Node<E> node = new Node<>(prevNode, element, nexNode);
			nexNode.prevNode = node; 
			prevNode.nextNode = node;
			if (index == 0) {
				firstNode = node;
			}
		}
		size++;
	}

	@Override
	public E remove(int index) { 
		rangeCheck(index);
		return remove(node(index));
	}
	
	private E remove(Node<E> node) {
		if (size == 1) {
			firstNode = null;
			lastNode = null;
		} else {
			Node<E> prev = node.prevNode;
			Node<E> next = node.nextNode;
			prev.nextNode = next;
			next.prevNode = prev;
			
			if (node == firstNode) { // index == 0
				firstNode = next;
			}
			
			if (node == lastNode) { // index == size - 1
				lastNode = prev;
			}
		}
		
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = firstNode;
			for (int i = 0; i < size; i++) {
				if (node.element == null) {
					return i;
				}
				node = node.nextNode;
			}
		} else {
			Node<E> node = firstNode;
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) {
					return i;
				}
				node = node.nextNode;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	private Node<E> node(int index) {
		rangeCheck(index);
		if (index < (size >> 1)) {
			Node<E> node = firstNode;
			while (index > 0) {
				node = node.nextNode;
				index--;
			}
			return node;
		} else {
			Node<E> node = lastNode;
			while (index < size - 1) {
				node = node.prevNode;
			 	index++;
			}
			return node;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("size=").append(size).append(", [");
		Node<E> node = firstNode;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				s.append(",").append(node);
			} else {
				s.append(node);
			}
			node = node.nextNode;
		}
		s.append("]");
		return s.toString();
	}
	
}
