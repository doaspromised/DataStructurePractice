package com.napoleon.set;

import java.util.Comparator;

import com.napoleon.tree.BinaryTree;
import com.napoleon.tree.RBTree;

public class TreeSet<E> implements Set<E> {

	private RBTree<E> tree = new RBTree<>();
	
	public TreeSet() {
		this(null);
	}
	public TreeSet(Comparator<E> comparator) {
		
	}
	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		tree.clear();
	}

	@Override
	public boolean contains(E element) {
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		// 红黑树默认就是去重的
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
//		中序遍历是从小到大的顺序，对集合来说比较有意义
		tree.inorder(new BinaryTree.Visitor<E>() {

			@Override
			public boolean visit(E element) {
				// TODO Auto-generated method stub
				return visitor.visit(element);
			}
		});
	}

}
