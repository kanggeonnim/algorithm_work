package day0808;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex = 0;
	private final int SIZE;

	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new Object[size + 1];
	}

	public boolean isEmpty() {
		return lastIndex == 0;
	}

	public boolean isFull() {
		return lastIndex == SIZE;
	}

	public boolean add(T data) {
		if (isFull())
			return false;
		nodes[++lastIndex] = data;
		return true;
	}

	public void bfs() {
		if (isEmpty())
			return;
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(1);

		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println(nodes[current]);
			if (current * 2 <= lastIndex)
				queue.offer(current * 2);
			if (current * 2 + 1 <= lastIndex)
				queue.offer(current * 2 + 1);
		}
	}
}
