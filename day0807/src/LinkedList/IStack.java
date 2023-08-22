package LinkedList;

public interface IStack<E> {
	void push(E e);

	Node<E> pop();

	E peek();

	int size();
	
	boolean isEmpty();
}
