package codebytes;

import java.lang.reflect.Array;

public class MinStack<T extends Comparable<T>> {
	class Element {
		T value;
		int nMI;

		Element(T v, int mI) {
			value = v;
			nMI = mI;
		}
	}

	// mi stores the index, not the value
	int top = -1, mI = 0;
	Element[] elements;

	MinStack(int size) {
		elements = (Element[]) Array.newInstance(Element.class, size);
	}

	public void push(T value) {
		if (top + 1 == elements.length)
			return;
		Element el = new Element(value, mI);
		elements[++top] = el;
		if (top > 0) {
			if (elements[top].value.compareTo(elements[mI].value) <= 0)
				mI = top;
		}
	}

	public T pop() {
		if (top == -1)
			return null;
		Element el = elements[top];
		if (mI == top)
			mI = elements[top].nMI;
		top--;
		if (top == -1)
			mI = -1;
		return el.value;
	}

	public T min() {
		if (mI == -1)
			return null;
		return elements[mI].value;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	/*
	 * //For testing
	 * 
	 * @Override public String toString(){ String s = ""; for(int
	 * i=0;i<=top;++i){ s+=elements[i].nMI+" "; } return s; }
	 */

	public static void main(String[] args) {
		Integer[] input = { 11, 3, 7, 4, 1, 3, -1, 2 };
		Stack<Integer> s = new Stack<>(input.length + 4);
		for (int i : input)
			s.push(i);
		// System.out.println(s);

		System.out.println("Popping " + s.pop());
		System.out.println("Min = " + s.min() + "\n\n");
		s.pop();
		s.push(44);
		s.push(-4);
		s.push(23);
		s.push(33);
		// System.out.println(s);
		while (!s.isEmpty()) {
			System.out.println("Min: " + s.min());
			System.out.println("Popping: " + s.pop());
		}
	}
}
