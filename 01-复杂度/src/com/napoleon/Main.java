package com.napoleon;

/**
 * 求斐波那契
 * 0 1 2 3 4 5 6
 * 0 1 1 2 3 5 8... 
 * @author nickdada
 *
 */
public class Main {

	/**
	 * 递归方法
	 * 缺点： 复杂度太高 O(2^n)
	 * @param n
	 * @return
	 */
	public static int fib1(int n) {
		if (n <= 1) {
			return n;
		}
		return fib1(n - 1) + fib1(n - 2);
	}
	
	/**
	 * 复杂度 O(n)
	 * @param n
	 * @return
	 */
	public static int fib2(int n) {
		if (n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			int fibNum = first + second;	
			first = second;
			second = fibNum;
		}
		return second;
	}
	public static void main(String[] args) {
		System.out.println(fib1(200));
	}
}
