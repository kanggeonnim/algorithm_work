
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] op; //
	static String[] operator = { "+", "-", "*", "/" };;
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
		for (int i = 0; i < N - 1; i++) {
			sb.append(nums[i]);
			sb.append(operator[result[i]]);
		}
		sb.append(nums[N - 1]);
		System.out.println(sb);
		sb.setLength(0);
	}
}
