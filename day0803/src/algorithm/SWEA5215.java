package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5215 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, L, bestScore;
	static boolean[] visited;
	static int[][] array; // [][0] 맛도리 점수, [][1] 살찌는 점수

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			visited = new boolean[N];
			array = new int[N][2];
			bestScore = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				array[i][0] = Integer.parseInt(st.nextToken());
				array[i][1] = Integer.parseInt(st.nextToken());
			}

			comb(0, 0);
			bw.append("#" + test_case + " " + bestScore + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void comb(int sum, int cnt) {
		// 칼로리를 구해보자,,
		if (sum > L) {
			return;
		}
		if (cnt == N) {
			// 맛도리 조합 찾기
			findBestHamberger();
			System.out.println(Arrays.toString(visited));
			return;

		}

		visited[cnt] = true;
		comb(sum + array[cnt][1], cnt + 1);
		visited[cnt] = false;
		comb(sum, cnt + 1);
	}

	private static void findBestHamberger() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				sum += array[i][0];
			}
		}
		if (bestScore < sum) {
			bestScore = sum;
		}
	}
}
