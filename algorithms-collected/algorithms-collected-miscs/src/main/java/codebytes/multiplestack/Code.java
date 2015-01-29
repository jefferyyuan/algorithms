package codebytes.multiplestack;

import java.util.ArrayList;

/**
 * http://www.codebytes.in/2014/12/manage-multiple-stacks-using-arraylist.html
 */
class Stacks<T> {
	int s;
	ArrayList<Stack<T>> stackList;

	Stacks(int s) {
		this.s = s;
		stackList = new ArrayList<>();
	}

	public void push(T value) {
		if (stackList.isEmpty()) {
			stackList.add(new Stack<>(s));
		}
		if (stackList.get(stackList.size() - 1).top == s - 1) {
			stackList.add(new Stack<>(s));
		}
		stackList.get(stackList.size() - 1).push(value);
	}

	public T pop() {
		if (stackList.isEmpty())
			return null;
		T val = stackList.get(stackList.size() - 1).pop();
		if (stackList.get(stackList.size() - 1).isEmpty()) {
			stackList.remove(stackList.size() - 1);
		}
		return val;
	}

	public boolean isEmpty() {
		return stackList.isEmpty();
	}

	// FOLLOW UP
	T popAt(int index) {
		if (index < 0 || index >= stackList.size() || stackList.isEmpty())
			return null;
		Stack<T> st = stackList.get(index);

		T val = st.pop();

		if (index != stackList.size() - 1) {
			st.push(stackList.get(index + 1).elements[0]);
			int i = index + 1;
			for (; i + 1 < stackList.size(); ++i) {
				st = stackList.get(i);
				// Remove the bottom most element
				for (int j = 0; j < st.elements.length - 1; ++j)
					st.elements[j] = st.elements[j + 1];
				st.top--;
				st.push(stackList.get(i + 1).elements[0]);
			}
			// Fix last stack
			st = stackList.get(i);
			if (st.top == 0)
				stackList.remove(stackList.size() - 1);
			else {
				for (int j = 0; j < st.top; ++j)
					st.elements[j] = st.elements[j + 1];
				st.top = st.top - 1;
			}
		}
		return val;
	}
}

/**
 * @author administrator
 *
 */
/**
 * @author administrator
 *
 */
public class Code {
	public static void main(String[] args) {
		Stacks<Integer> stacks = new Stacks<>(3);
		for (int i = 1; i <= 9; ++i)
			stacks.push(i);

		for (int i = 0; i < 10; ++i)
			System.out.println(stacks.popAt(0));
	}
}
