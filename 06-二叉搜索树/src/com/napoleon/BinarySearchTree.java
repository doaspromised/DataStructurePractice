package com.napoleon;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.napoleon.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
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

	public void remove(E element) {

	}

	public boolean contains(E element) {
		return false;
	}

	/**
	 * 前序遍历
	 */
	public void preorderTranversal() {
		preorderTranversal(root);
	}

	/**
	 * 从给定节点前序遍历
	 * 
	 * @param node
	 */
	private void preorderTranversal(Node<E> node) {
		if (node == null) {
			return;
		}
		System.out.println(node.element);
		preorderTranversal(node.left);
		preorderTranversal(node.right);
	}

	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		inorderTraversal(root);
	}

	/**
	 * 从给定节点中序遍历
	 * 
	 * @param node
	 */
	private void inorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}
	
	/**
	 * 后序遍历
	 */
	public void postorderTranversal() {
		postorderTranversal(root);
	}
	/**
	 * 从给定节点后序遍历
	 * 
	 * @param node
	 */
	private void postorderTranversal(Node<E> node) {
		if (node == null) {
			return;
		}
		postorderTranversal(node.left);
		postorderTranversal(node.right);
		System.out.println(node.element);
	}
	
	/**
	 * 使用队列进行层序遍历
	 */
//	public void levelOrderTraversal() {
//		if (root == null) {
//			return;
//		}
//		Queue<Node<E>> queue = new LinkedList<>();
//		queue.offer(root);
//		while (!queue.isEmpty()) {
//			Node<E> node = queue.poll();
//			System.out.println(node.element);
//			if (node.left != null) {
//				queue.offer(node.left);
//			}
//			if (node.right != null) {
//				queue.offer(node.right);
//			}
//		}
//	}
	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) {
			return;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
//			System.out.println(node.element);
			visitor.visit(node.element);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	
	public int height() {
		if (root == null) {
			return 0;
		}
		int height = 0;
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
//			System.out.println(node.element);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}
	
	/**
	 * 递归获取树的高度
	 * @return
	 */
	public int height2() {
		
		return height(root);
	}
	
	private int height(Node<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
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
	
	public static interface Visitor<E> {
		void visit(E element);
	}
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

	}

	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>) node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>) node).right;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		Node<E> MyNode = (Node<E>) node;
		String parentString = "null";
		if (MyNode.parent != null) {
			parentString = MyNode.parent.element.toString();
		}
		return ((Node<E>) node).element + "_" + parentString;
	}
}
