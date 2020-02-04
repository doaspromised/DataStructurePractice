package 栈;

import java.util.HashMap;
import java.util.Stack;

public class _20_有效的括号 {
	public boolean isValid(String s) {
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		Stack<Character> leftChars = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			Character character = s.charAt(i);
			if (map.containsKey(character)) {
				leftChars.push(character);
			} else {
				if (leftChars.isEmpty()) {
					return false;
				} 
				Character leftChar = leftChars.pop();
				if (map.get(leftChar) != character) {
					return false;
				}
			}
		}
		return leftChars.isEmpty();
	}
}
