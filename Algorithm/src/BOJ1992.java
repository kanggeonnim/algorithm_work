import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1992 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] array;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(br.readLine());
		array = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				array[i][j] = input[j] - '0';
			}
		}

		find(0, 0, N);
		if (N == 1) {
			System.out.println(array[0][0]);
		} else {
			System.out.println(sb);
		}
	}

	private static void find(int si, int sj, int size) {
		int half = size / 2;
		int[] di = { 0, 0, half, half };
		int[] dj = { 0, half, 0, half };

		int[] sum = new int[4]; // 0:왼쪽위, 1:오른쪽위, 2:오른쪽아래, 3:왼쪽아래
		for (int s = 0; s < 4; s++) {
			for (int i = si + di[s]; i < si + half + di[s]; i++) {
				for (int j = sj + dj[s]; j < sj + half + dj[s]; j++) {
					sum[s] += array[i][j];
				}
			}
		}

		int allSum = 0;
		for (int s = 0; s < 4; s++) {
			allSum += sum[s];
		}

		// 기저 조건
		if (allSum == 0) {
			sb.append(0);
			return;
		} else if (allSum == size * size) {
			sb.append(1);
			return;
		}

		sb.append("(");
		for (int s = 0; s < 4; s++) {
			if (sum[s] == 0) {
				sb.append(0);
			} else if (sum[s] == half * half) {
				sb.append(1);
			} else {
				find(si + di[s], sj + dj[s], half);
			}
		}
		sb.append(")");
	}
}
