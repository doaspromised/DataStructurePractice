package com.napoleon.list;

import java.util.Comparator;

import com.napoleon.list.BinaryTree.Node;

public class RBTree<E> extends BinarySearchTree<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		super.afterAdd(node);
		
	}
	@Override
	protected void afterRemove(Node<E> node) {
		super.afterRemove(node);
		
	}
	
	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}
	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node);
	}
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	/**
	 * 节点染色
	 * @param node
	 * @param color
	 * @return
	 */
	private Node<E> color(Node<E> node, boolean color) {	
		if (node != null) {
			((RBNode<E>)node).color = color;
		}
		return node;
		
	}
	
	private class RBNode<E> extends Node<E> {
		boolean color = RED; // 默认是红色，能尽快的满足红黑树的性质~
		public RBNode(E element, Node<E> parent) {
			super(element, parent);

		}
	}
}
