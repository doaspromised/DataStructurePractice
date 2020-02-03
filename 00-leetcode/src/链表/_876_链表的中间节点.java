package 链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 
 * @author nickdada
 *
 */
public class _876_链表的中间节点 {
	public ListNode middleNode(ListNode head) {
		if (head.next == null) {
			return head;
		}
		ListNode slowListNode = head, fastListNode = head;
		while (fastListNode != null && fastListNode.next != null) {
			slowListNode = slowListNode.next;
			fastListNode = fastListNode.next.next;
		}
		return slowListNode;
	}
}
