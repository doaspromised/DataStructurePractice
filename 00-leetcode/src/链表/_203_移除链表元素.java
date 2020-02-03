package 链表;
/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author nickdada
 *
 */
public class _203_移除链表元素 {
	/**
	 * 方法：哨兵节点
	 * 如果删除的是中间节点，问题似乎变得非常的简单：
	 * 1. 选择要删除的节点的前一个节点prev；
	 * 2.将prev的next设置为要删除的节点的next
	 * 如果删除的是头部节点，可以生成一个伪头，是链表标准化。
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElements(ListNode head, int val) {
		ListNode sentineListNode = new ListNode(0);
		sentineListNode.next = head;
		ListNode prevListNode = sentineListNode, currListNode = head;
		while (currListNode != null) {
			if (currListNode.val == val) {
				prevListNode.next = currListNode.next;
			} else {
				prevListNode = currListNode;
			}
			currListNode = currListNode.next;
		}
		return sentineListNode.next;
		
	}
	public ListNode removeElements2(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		// 非头结点
        ListNode prev = head, curr = head.next;
        while(curr != null) {
            if(curr.val == val) {
                 prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
		return head;
		
	}
}
