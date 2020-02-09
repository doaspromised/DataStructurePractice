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
	 * 删除节点后的操作
	 * 
	 * 删除节点后可能会导致祖父节点或祖先节点失衡（只有一个节点会失衡）
	 * 只让父节点平衡后，可能会导致更高层的祖父节点失衡（最多只需要O(logn)次调整)
	 */
	@Override
	protected void afterRemove(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalance(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				// 删除节点后，调整后，可能会导致更远的祖先节点失衡。所以恢复平衡要检查所有的祖先节点是否失衡
				rebalance(node);
			}
		}
	}
	/**
	 * 添加节点后的操作
	 * 
	 * 添加节点之后，可能会导致所有的祖父节点失衡
	 * 只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡（仅需要O(1)的调整)
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
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			} else { // LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			} else { // RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
		}
	}
	
	private void rotate(
			Node<E> r, // 子树根节点
			Node<E> a, Node<E> b, Node<E> c, 
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
//		a-b-c
		b.left = a;
		b.right = c;
		if (a != null) a.parent = b;
		if (c != null) c.parent = b;
		updateHeight(b);
//		e-f-g
		f.left = e;
		f.right = g;
		if (e != null) e.parent = f;
		if (g != null) g.parent = f;
		updateHeight(f);
//		b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
	}
	/**
	 * 恢复平衡
	 * 
	 * @param node
	 */
	private void rebalance2(Node<E> grand) {
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
