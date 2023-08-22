package day0810;

import java.util.Arrays;
import java.util.Scanner;

public class PermutationBitTest {
	static int N, R, input[], numbers[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		permutation(0, 0);
	}

	private static void permutation(int cnt, int flag) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			// 0이 아니면으로 체크하지않으면 flag의 값이 1,2,3,4등의 값을 가질 수 있으므로 어떻게 될지 모름.
			if ((flag & i << i) != 0)
				continue;
			numbers[cnt] = input[i];
			permutation(cnt + 1, flag | 1 << i);

		}
	}
}
