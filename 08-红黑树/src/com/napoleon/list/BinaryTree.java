package com.napoleon.list;

import java.util.LinkedList;

import java.util.Queue;

import javax.xml.soap.Node;

import com.napoleon.printer.BinaryTreeInfo;
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
	}
	
	/**
	 * 判断是否是完全二叉树
	 * @return
	 */
	public boolean isCompelete() {
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) {
				return false;
			}
			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				return false;
			}
			if (node.right != null) {
				queue.offer(node.right);
			} else {
				leaf = true;
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
	public static interface Visitor<E> {
		void visit(E element);
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
	 * 寻找指定节点的前驱节点（中序遍历时的前一个节点）
	 * 
	 * @param node 
	 * @return
	 */
	protected Node<E> predecessor(Node<E> node) {
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
	protected Node<E> successor(Node<E> node)	{
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
	/**
	 * 创建二叉树节点
	 * @param element
	 * @param parent
	 * @return
	 */
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	protected static class Node<E> {
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
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
//		返回兄弟节点
		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			if (isRightChild()) {
				return parent.left;
			}
			return null;
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
