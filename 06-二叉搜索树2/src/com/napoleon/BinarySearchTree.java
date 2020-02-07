package com.napoleon;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.napoleon.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {

	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) {
			root = new Node<>(element, null);
			size++;
			return;
		}

//		找到父节点
		Node<E> parent = root;
		int cmp = 0;
		Node<E> node = root;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等
//				覆盖原来的值	
				node.element = element;
				return;
			}
		}
//		看看插入到父节点的哪个位置
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
	}
	
	/**
	 * 是否包含某元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return node(element) != null;
	}

	public void remove(E element) {
		  remove(node(element));
	}
	
	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
//		删除度为2的节点
		if (node.hasTwoChildren()) {
			Node<E> s = successor(node);
			// 把后继节点的值赋值给被删除的节点
			node.element = s.element;
			// 删除后继节点（度为2的节点的后继节点的度必定为1或者0）
			node = s;
		}
//		删除度为1或者0的节点
		if (node.isLeaf()) {
//			如果是叶子节点，度为0
			if (node.parent == null) {
				root = null;
			} else if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		} else {
			Node<E> replacement  = node.left != null ? node.left : node.right;
			replacement.parent = node.parent;
//			如果不是叶子节点，度为1
			if (node.parent == null) {// node 是根节点并且是度为1的几点
				node = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		}
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}

	/**
	 * 元素比较逻辑
	 * 
	 * @param e1
	 * @param e2
	 * @return 0: e2 = e2, 1: e1> e2, 2: e2 > e1
	 */
	private int compare(E e1, E e2) {
//		设计相关的问题： 如果外界想个性化定制比较逻辑，就传一个比较器；否则，就实现Comparable接口。
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>) e1).compareTo(e2);
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
