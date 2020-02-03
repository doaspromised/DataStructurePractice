package com.napoleon.cycle;

import com.napoleon.AbstractList;

/**
 * 单向循环链表
 * 
 * 链表增删改查的复杂度都是 O(n)
 * 
 * @author nickdada
 *
 * @param <E>
 */
public class SingleCycleLinkedList<E> extends AbstractList<E> {

	private Node<E> firstNode;

	private static class Node<E> {
		E element;
		Node<E> nextNode;

		public Node(E element, Node<E> nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}
		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("{ element=").append(element);
			if (nextNode != null) {
				s.append(", nextNode=").append(nextNode.element).append("}");
			} else {
				s.append(", nextNode=null");
			}
			return s.toString();
		}

	}

	@Override
	public void clear() {
		size = 0;
		firstNode = null;
	}

	@Override
	public E get(int index) { // O(n)
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
		if (index == 0) {
//			让最后一个节点的next 指定第一个节点
			Node<E> newFirstNode = new Node<>(element, firstNode);
			Node<E> lastNode = size == 0 ? newFirstNode : node(size - 1);
			firstNode = newFirstNode;
			lastNode.nextNode = firstNode;
		} else {
			Node<E> prev = node(index - 1);
			prev.nextNode = new Node<>(element, prev.nextNode);
		}
		size++;
	}

	@Override
	public E remove(int index) { // O(n)
		rangeCheck(index);
		Node<E> removedNode = firstNode; 
		if (index == 0) {		
//			让最后一个节点指定新的头结点
			Node<E> lastNode = node(size - 1);
			if (size == 1) {
				firstNode = null;
			} else {
				firstNode = firstNode.nextNode;
				lastNode.nextNode = firstNode;
			}
		} else {
			Node<E> prevNode = node(index - 1);
			removedNode = prevNode.nextNode;
			prevNode.nextNode = removedNode.nextNode;
		}
		size--;
		return removedNode.element;
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

	private Node<E> node(int index) { // O(n)
		rangeCheck(index);
		Node<E> node = firstNode;
		while (index > 0) {
			node = node.nextNode;
			index--;
		}
		return node;
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
