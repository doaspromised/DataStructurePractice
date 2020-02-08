package com.napoleon.list;

import java.awt.Frame;
import java.util.Comparator;

import com.napoleon.list.BinaryTree.Node;

@SuppressWarnings("unchecked")
public class AVLTree<E> extends BinarySearchTree<E> {

	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * 添加节点后的操作
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalance(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				// 整个树就恢复平衡
				break;
			}
		}
	}

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode(element, parent);
	}

	/**
	 * 恢复平衡
	 * 
	 * @param node
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotateLeft(grand);
				rotateRight(parent);
			} else { // RR
				rotateLeft(grand);
			}
		}
	}
	
	/**
	 * 左旋转
	 * @param node
	 */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> chidlNode = parent.left;
		grand.right = chidlNode;
		parent.left = grand;
		afterRotate(grand, parent, chidlNode);
		
	}
	/**
	 * 右旋转
	 * @param node
	 */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> chidlNode = parent.right;
		grand.left = chidlNode;
		parent.right = grand;
		afterRotate(grand, parent, chidlNode);
	}

	/**
	 * 更新 grand parent chidlNode 的parent 以及 grand 和 parent的高度
	 * @param grand
	 * @param parent
	 * @param chidlNode
	 */
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> chidlNode) {
//		让parent 成为 子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}
//		更新child 的 parent 
		if (chidlNode != null) {
			chidlNode.parent = grand;
		}
//		更新grand 的 parent
		grand.parent = parent;
//		更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	/**
	 * 更新节点高度
	 * 
	 * @param node
	 */
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}

	/**
	 * 判断是否平衡
	 * 
	 * @param node
	 * @return
	 */
	private boolean isBalance(Node<E> node) {
		return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
	}

	/**
	 * AVL 节点类
	 * 
	 * @author nickdada
	 * @param <E>
	 */
	private class AVLNode<E> extends Node<E> {

		int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);

		}

		// 平衡因子
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			return leftHeight - rightHeight;
		}

		// 更新高度
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			this.height = 1 + Math.max(leftHeight, rightHeight);
		}

		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			if (isLeftChild()) {
				return left;
			} else {
				return right;
			}
		}
	}
}
