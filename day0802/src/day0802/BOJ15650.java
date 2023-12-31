package day0802;

import java.util.Scanner;

public class BOJ15650 {
	static int[] result;
	static int n;
	static int m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		result = new int[m];

		comb(1, 0);
	}

	public static void comb(int start, int r) {
		if (r == m) {
			print();
			return;
		}
		for (int i = start; i <= n; i++) {
			result[r] = i;
			comb(i + 1, r + 1);
		}
	}

	public static void print() {
		for (int i = 0; i < m; i++) {
			System.out.printf(result[i] + " ");
		}
		System.out.println();
	}
}
