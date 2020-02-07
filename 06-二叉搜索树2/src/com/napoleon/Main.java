package com.napoleon;

import com.napoleon.BinaryTree.Visitor;
import com.napoleon.printer.BinaryTrees;

@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
//		bst.add(12);
//		bst.add(1);
//		bst.remove(1);
//		bst.remove(3);
//		bst.remove(12);
//		bst.remove(9);
//		bst.remove(7);
		bst.remove(11);
		BinaryTrees.println(bst);
//		bst.preorderTranversal();
//		bst.inorderTraversal();
//		bst.postorderTranversal();
//		bst.levelOrderTraversal();
//		bst.levelOrder(new Visitor<Integer>() {
//			@Override
//			public void visit(Integer element) {
//				// TODO Auto-generated method stub
//				System.out.println(element);
//			}
//		});
//		System.out.println(bst.height());
		System.out.println(bst.isCompelete());
	}
}