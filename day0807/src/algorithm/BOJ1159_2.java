package algorithm;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ1159_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		MyLL ll = new MyLL();
		for (int i = 1; i <= N; i++) {
			ll.add(new Node(i, ll.head));
		}

		for (int i = 1; i <= N; i++) {
			if (i == 1) {
				System.out.print("<");
			}
			if (i == N) {
				System.out.println(ll.remove(0) + ">");
			} else {
				int idx = ((i * K + 1) % (ll.size()) - 1);
				System.out.print(ll.remove(idx) + ", ");
			}
		}
	}

	static class Node {
		int data;
		Node next;
		Node prev;

		public Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	static class MyLL extends LinkedList<Node> {
		Node head;
		Node tail;
		Node cur;
		int size;

		public MyLL() {
			head = null;
			tail = null;
			cur = null;
			size = 0;
		}

		public boolean add(int i) {
			Node node = new Node(i);
			if (size == 0) {
				head.next = node;
			}
			cur.prev = cur;
			node;
			cur = node;
			return false;

		}

		@Override
		public Node remove(int index) {
			Node remove = this.get(index);
			remove.next;
			return remove;
		}
	}
}
