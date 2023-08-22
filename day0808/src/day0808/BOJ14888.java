package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] op;
	static int[] result;
	static int[] nums;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		// operator의 중복순열이 담기는 배열
		result = new int[N-1];
		calculate(0);
	}

	private static void calculate(int cnt) {
		if (cnt == N) {
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = 1; i <= 4; i++) {
			result[cnt] = i;
			calculate(cnt + 1);
		}
	}
}
