package com.napoleon.set;

import com.napoleon.list.LinkedList;
import com.napoleon.list.List;

public class ListSet<E> implements Set<E> {

	private List<E> list = new LinkedList<>();
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		if (list.contains(element)) {
			list.set(list.indexOf(element), element);
		} else {
			list.add(element);
		}
	}

	@Override
	public void remove(E element) {
		if (list.contains(element)) {
			list.remove(list.indexOf(element));
		}
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (visitor.visit(list.get(i))) {
				return;
			}
		}
	}

}
