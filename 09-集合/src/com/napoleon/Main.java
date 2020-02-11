package com.napoleon;

import com.napoleon.Times.Task;
import com.napoleon.file.FileInfo;
import com.napoleon.file.Files;
import com.napoleon.set.ListSet;
import com.napoleon.set.Set;
import com.napoleon.set.Set.Visitor;
import com.napoleon.set.TreeSet;

public class Main {

	public static void testListSet() {
		Set<Integer> listSet = new ListSet<Integer>();
		listSet.add(12);
		listSet.add(10);
		listSet.add(11);
		listSet.add(11);
		listSet.add(12);
		listSet.add(120);
		listSet.add(10);
		listSet.traversal(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}

	public static void testTreeSet() {
		Set<Integer> listSet = new TreeSet<Integer>();
		listSet.add(12);
		listSet.add(10);
		listSet.add(11);
		listSet.add(11);
		listSet.add(120);
		listSet.add(10);
		listSet.add(12);
		listSet.traversal(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}

	/**
	 * listSet 和 TreeSet 性能比较
	 * 
	 * 备注：TreeSet 局限性：元素必须具有可比较性
	 */
	static void test3() {
		FileInfo fileInfo = Files.read("/Users/nickdada/Desktop/恋上数据结构第一季/资料/Visualization",
				new String[] { "html", "js" });
		System.out.println("文件数量： " + fileInfo.getFiles());
		System.out.println("代码行数： " + fileInfo.getLines());
		System.out.println("单词数量： " + fileInfo.words().length);
		String[] words = fileInfo.words();
		Set<String> listSet = new ListSet<>();
		Set<String> treeSet = new TreeSet<>();
		Times.test("ListSet", new Task() {

			@Override
			public void execute() {
				test4(listSet, words);
			}
		});
		Times.test("ListSet", new Task() {

			@Override
			public void execute() {
				test4(treeSet, words);
			}
		});
		

	}

	static void test4(Set<String> set, String[] words) {
		for (int i = 0; i < words.length; i++) {
			set.add(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.contains(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.remove(words[i]);
		}
	}

	public static void main(String[] args) {
//		testListSet();
		test3();

	}
}
