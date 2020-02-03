package 链表;

// 237. 删除链表中的节点
/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author nickdada
 *
 */
public class _237_DeleteNodeInLinkedList {
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;	
	}
}
