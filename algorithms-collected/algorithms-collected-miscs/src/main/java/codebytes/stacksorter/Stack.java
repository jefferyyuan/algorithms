package codebytes.stacksorter;

public class Stack<T> {
	int top = -1;
	T[] elements;

	public Stack(int size) {
		elements = (T[]) new Object[size];
	}

	public int size() {
		return top + 1;
	}

	public void push(T element) {
		if (top + 1 == elements.length) {
			System.out.println("Overflow.");
			return;
		}
		elements[++top] = element;
	}

	public T pop() {
		if (top < 0) {
			System.out.println("Underflow.");
			return null;
		}
		return elements[top--];
	}

	public T peek() {
		if (top < 0) {
			System.out.println("Stack is empty!");
			return null;
		}
		return elements[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}
}