package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4012 {
	// nC(n/2) 조합이 경우의 수.
	// 모든 경우의 수 계산해서 음식맛 차이의 최솟값 구하기.
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] array;
	static boolean[] visited; // 선택 된 재료들
	static int N, T, foodA, foodB, minDiff;

	public static void main(String[] args) throws NumberFormatException, IOException {

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			visited = new boolean[N];
			minDiff = Integer.MAX_VALUE;

			// input
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 1은 고정시키고 나머지요소들로만 조합을 구성함
			// -> 음식을 고르는 경우는 반절을 고르면 나머지 반절도 정해지기 때문에
			// 4C2에서 (1,2) 와 (3,4)는 중복된 경우임. 따라서 1이 포함된 것만 출력하면 그안에 3이 선택된 케이스도 포함.
			visited[0] = true;
			combFood(1, 1);
			System.out.println("#" + test_case + " " + minDiff);
		}
	}

	private static void combFood(int cnt, int selectedCount) {
		// N개의 재료를 N/2개로 나눠서 재료 선택하기.
		if (selectedCount == N / 2) {
			makeFood();
			return;
		}
		if (cnt == visited.length)
			return;

		visited[cnt] = true;
		combFood(cnt + 1, selectedCount + 1);
		visited[cnt] = false;
		combFood(cnt + 1, selectedCount);
	}

	private static void makeFood() {
		foodA = 0;
		foodB = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) { // true인 재료들 모아서 요리하기
				for (int j = 0; j < N; j++) {
					if (visited[j]) {
						foodA += array[i][j];
					}
				}
			}
			if (!visited[i]) { // false인 재료들 모아서 요리하기
				for (int j = 0; j < N; j++) {
					if (!visited[j]) {
						foodB += array[i][j];
					}
				}
			}
		}
		if (Math.abs(foodA - foodB) < minDiff) {
			minDiff = Math.abs(foodA - foodB);
		}

	}
}
