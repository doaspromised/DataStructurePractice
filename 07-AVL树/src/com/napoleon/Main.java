package com.napoleon;

import com.napoleon.list.AVLTree;
import com.napoleon.list.BinarySearchTree;
import com.napoleon.list.BinaryTree.Visitor;
import com.napoleon.printer.BinaryTrees;

@SuppressWarnings("unused")
public class Main {
	
	public void testAVLTree() {
		Integer data[] = new Integer[] {
				1, 4, 10, 11, 12, 13, 14, 20, 21, 23, 27, 36, 41, 49, 55, 60, 66, 67, 68, 69, 73, 78, 86, 91, 92, 95
		};
		AVLTree<Integer> bst = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
	}
	
	public static void main(String[] args) {
		Integer data[] = new Integer[] {
				1, 4, 10, 11, 12, 13, 14, 20, 21, 23, 27, 36, 41, 49, 55, 60, 66, 67, 68, 69, 73, 78, 86, 91, 92, 95
		};
		AVLTree<Integer> bst = new AVLTree<>();
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
//		bst.remove(11);
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
//		System.out.println(bst.isCompelete());
	}
}