package 链表;

/**
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/submissions/
 * @author nickdada
 *
 */
public class _206_ReverseLinkedList {
//	迭代
	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode current = head;
		while (current != null) { // 迭代方法
			ListNode temListNode = current.next;
			current.next = prev;
			prev = current;
			current = temListNode;
		}
		return prev;
	}
//	递归：关键是搞清楚这个方法的作用  充分利用它的作用做事情。
	public ListNode reverseList2(ListNode head) {
//		递归边界条件
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList2(head.next);
		head.next.next = newHead;
		head.next = null;
		return newHead;
	}
}
