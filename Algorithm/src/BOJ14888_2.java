
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14888_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] op; // { "+", "-", "*", "/" }
	static int[] result;
	static int[] nums;
	static int N, min, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		op = new int[4];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		// operator의 중복순열이 담기는 배열
		result = new int[N - 1];
		find(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void find(int cnt) {
		if (cnt == N - 1) {
			calculator();
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (op[i] != 0) {
				op[i]--;
				result[cnt] = i;
				find(cnt + 1);
				op[i]++;
			}
		}
	}

	private static void calculator() {

		int res = Integer.valueOf(nums[0]);
		for (int i = 1; i < N; i++) {
			switch (result[i - 1]) {
			case 0:
				res = res + nums[i];
				break;
			case 1:
				res = res - nums[i];
				break;
			case 2:
				res = res * nums[i];
				break;
			case 3:
				res = res / nums[i];
				break;
			default:
				break;
			}
		}

		min = Math.min(min, res);
		max = Math.max(max, res);
	}
}
