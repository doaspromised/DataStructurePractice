package 队列;

import java.util.LinkedList;
import java.util.Queue;

/** 
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 
 * @author nickdada
 *
 */
public class _225_用队列实现栈 {
	Queue<Integer> queue;
	   /** Initialize your data structure here. */
    public _225_用队列实现栈() {
    	queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	Queue<Integer> tempQueue = new LinkedList<>();
    	while (queue.size() > 1) {
			tempQueue.offer(queue.poll());
		}
    	int element = queue.poll();
    	queue = tempQueue;
    	return element;
    }
    
    /** Get the top element. */
    public int top() {
    	Queue<Integer> tempQueue = new LinkedList<>();
    	while (queue.size() > 1) {
			tempQueue.offer(queue.poll());
		}
    	int element = queue.peek();
    	tempQueue.offer(queue.poll());
    	queue = tempQueue;
    	return element;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	StringBuilder s = new StringBuilder();
    	s.append("[");
    	int size = queue.size();
    	for (int i = 0; i < size; i++) {
			s.append(queue.poll()).append(", ");
		}
    	s.append("]");
    	return s.toString();
    }
}

