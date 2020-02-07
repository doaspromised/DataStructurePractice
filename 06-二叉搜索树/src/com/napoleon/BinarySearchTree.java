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
	
	public void clear() {
		root = null;
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
	 * 是否包含某元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return node(element) != null;
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
	
	/**
	 * 判断是否是完全二叉树
	 * @return
	 */
	public boolean isCompelete() {
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (node.hasTwoChildren()) {
				queue.offer(node.left);
				queue.offer(node.right);
			} else if (node.left == null && node.right != null) {
				return false;
			} else { // 后续节点必须是叶子节点
				return node.isLeaf();
			}
		}
		return true;
	}
	
	/**
	 * 利用层序遍历计算高度
	 * @return
	 */
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
	/**
	 * 寻找指定节点的前驱节点（中序遍历时的前一个节点）
	 * 
	 * @param node 
	 * @return
	 */
	private Node<E> predecessor(Node<E> node) {
		if (node == null) {
			return null;
		}
//		从前驱节点的左子树当中寻找（left.right.right...）
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
//		从父节点祖父节点中寻找
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
//		node.parent == null
//		node == node.parent.right
		return node.parent;
	}
	/**
	 * 寻找指定节点的后继节点(中序遍历时的后一个节点)
	 * @param node
	 * @return
	 */
	private Node<E> successor(Node<E> node)	{
		if (node == null) {
			return null;
		}
//		从右子树中寻找（right.left.left..）
		Node<E> s = node.right;
		if (s != null) {
			while (s.left != null) {
				s = s.left;
			}
			return s;
		}
//		寻找父节点，并当前节点是父节点的左子树
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		return node.parent;
		
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
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		public boolean hasTwoChildren() {
			return left != null && right != null;
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
