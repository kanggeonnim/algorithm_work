package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.InputMap;

public class BOJ15649 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int result[];
	static boolean visited[];
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		result = new int[m];
		visited = new boolean[n + 1];
		perm(0);
	}

	static public void perm(int r) {
		if (r == m) {
			print();
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				result[r] = i;
				perm(r + 1);
				visited[i] = false;
			}
		}
	}

	static public void print() {
		for (int i = 0; i < m; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}
}
