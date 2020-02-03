package com.napoleon;

public class LinkedList<E> extends AbstractList<E> {

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
			return "{element=" + element + ", nextNode=" + "" + "}";
		}

	}

	@Override
	public void clear() {
		size = 0;
		firstNode = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E oldElement = node.element;
		node.element = element;
		return oldElement;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		if (index == 0) {
			firstNode = new Node<>(element, firstNode);
		} else {
			Node<E> prev = node(index - 1);
			prev.nextNode = new Node<>(element, prev.nextNode);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		Node<E> removedNode = firstNode; 
		if (index == 0) {		
			firstNode = firstNode.nextNode;
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

	private Node<E> node(int index) {
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
