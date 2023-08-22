package LinkedList;

import java.util.EmptyStackException;

public class LinkedListStack<E> implements IStack<E> {

	private Node<E> top = null;

	@Override
	public void push(E e) { // 첫 노드로 삽입
		top = new Node<>(e, top);
	}

	@Override
	public Node<E> pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Node<E> popNode = top;
		top = popNode.getLink();
		popNode.setLink(null);
		return popNode;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return top.getData();
	}

	@Override
	public int size() {
		int size = 0;
		for (Node<E> temp = top; temp != null; temp = temp.getLink()) {
			size++;
		}

		return size;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

}
