package day0817;

import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {
	static class Node {
		int vertext;
		Node next;

		public Node(int vertext, Node next) {
			super();
			this.vertext = vertext;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertext=" + vertext + ", next=" + next + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		// int[][] adjMatrix = new int[V][V];
		Node adjList[] = new Node[V];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);
			//adjList[to] = new Node(from, adjList[to]);
		}

		for (Node node : adjList) {
			System.out.println(node);
		}
	}
}
