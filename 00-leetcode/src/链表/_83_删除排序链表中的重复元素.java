package 链表;
/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * @author nickdada
 *
 */
public class _83_删除排序链表中的重复元素 {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = head, current = head.next;
		while (current != null) {
			if (current.val == prev.val) {
				prev.next = current.next;
			} else {
				prev = current;
			}
			current = current.next;
		}
		return head;
	}
}
