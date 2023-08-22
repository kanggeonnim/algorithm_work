package day0803;

import java.util.Scanner;

public class PowerSetTest {
	static int N;
	static boolean[] isSelected;
	static int[] input;
	static int TARGET;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		TARGET = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		// generateSubset(0);
		generateSubset2(0, 0, 0);
	}

	private static void generateSubset2(int cnt, int sum, int selectedCount) {
		if (cnt == N) {
			if (selectedCount > 0 && sum == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.printf(input[i] + "\t");
					}
				}
				System.out.println();
			}
			return;
		}

		isSelected[cnt] = true;
		generateSubset2(cnt + 1, sum + input[cnt], selectedCount + 1);
		isSelected[cnt] = false;
		generateSubset2(cnt + 1, sum, selectedCount);
	}

	private static void generateSubset(int cnt) {
		if (cnt == N) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					temp += input[i];
				}
			}
			if (temp == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.println(input[i] + "\t");
					}
				}
				System.out.println();
				return;
			}
		}

		isSelected[cnt] = true;
		generateSubset(cnt + 1);
		isSelected[cnt] = false;
		generateSubset(cnt + 1);
	}
}
