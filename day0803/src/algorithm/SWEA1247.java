package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1247 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, minWay;
	static int[][] map;
	static boolean[] visited;
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 2][2];
			visited = new boolean[N];
			result = new int[N];
			minWay = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N + 2; i++) {
				map[i][1] = Integer.parseInt(st.nextToken());
				map[i][0] = Integer.parseInt(st.nextToken());
			}
			perm(0);
			bw.append("#" + test_case + " " + +minWay + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			findShortWay();
			//System.out.println(Arrays.toString(result));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[cnt] = i;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}

	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................
	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................
	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................
	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................
	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................
	// 메모리 아끼려고 어려운코드를 만들지 말자..........................................

	private static void findShortWay() {
		int sum = 0;
		for (int i = 0; i < N - 1; i++) {
			int idx = result[i] + 2;
			int nextIdx = result[i + 1] + 2;
			sum += Math.abs(map[idx][0] - map[nextIdx][0]);
			sum += Math.abs(map[idx][1] - map[nextIdx][1]);
		}
		sum += Math.abs(map[0][0] - map[result[0] + 2][0]);
		sum += Math.abs(map[0][1] - map[result[0] + 2][1]);

		sum += Math.abs(map[1][0] - map[result[N - 1] + 2][0]);
		sum += Math.abs(map[1][1] - map[result[N - 1] + 2][1]);

		if (minWay > sum) {
			minWay = sum;
		}
	}
}
