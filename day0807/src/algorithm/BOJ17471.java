package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17471 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][] graph;
	static QuickFindUF uf;
	static boolean[] selected;
	static boolean[] connected;
	static int[] weight;
	static int N;
	static int minDiff;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		graph = new boolean[N + 1][N + 1];
		selected = new boolean[N + 1];
		weight = new int[N + 1];
		minDiff = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());

		// 지역별 인구
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		// 그래프 입력 받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= count; j++) {
				int near = Integer.parseInt(st.nextToken());
				graph[i][near] = true;
				graph[near][i] = true;
			}
		}

		// 지역을 true, false가 선택된 지역으로 나눌것이므로
		// 1,2,3,4중에 1,2 지역을 선택하는 경우와 3,4지역을 선택하는 경우가 동일함.
		// 따라서 1은 선택되었다고 가정하고 경우의 수를 구함.
		selected[1] = true;
		findComb(2);

		if (minDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDiff);
		}
	}

	// 구역을 두개 나누는 경우 구하기.
	private static void findComb(int cnt) {
		if (cnt == N + 1) {
			if (checkConnected()) { // 두개로 나눈 구역이 모두 연결되어있는 경우에만 점수 계산.
				findMinDiff();
				return;
			} else {
				return;
			}
		}

		selected[cnt] = true;
		findComb(cnt + 1);
		selected[cnt] = false;
		findComb(cnt + 1);
	}

	// 구역이 연결되었는지 확인.
	private static boolean checkConnected() {
		uf = new QuickFindUF(N + 1);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (selected[i] && selected[j] && graph[i][j]) { // 해당 지역과 연결된 지역을 확인함.
					uf.union(i, j);
				} else if (!selected[i] && !selected[j] && graph[i][j]) { // 해당 지역과 연결된 지역을 확인함.
					uf.union(i, j);
				}
			}
		}

		// 구역이 두개로 나누어지려면 count가 3개가되어야함.
		// => 0번 인덱스는 구역이 무조건 나눠지기 때문에.
		if (uf.count == 3) {
			return true;
		} else
			return false;
	}

	private static void findMinDiff() {
		int trueScore = 0, falseScore = 0;
		for (int i = 1; i <= N; i++) {
			if (selected[i] == true) {
				trueScore += weight[i];
			} else if (selected[i] == false) {
				falseScore += weight[i];
			}
		}

		minDiff = Math.min(minDiff, Math.abs(trueScore - falseScore));
//		System.out.println("-------------check------------");
//		System.out.println(minDiff + " " + trueScore + " " + falseScore);
//		System.out.println(Arrays.toString(selected));
	}

	static class QuickFindUF {
		private int[] id;
		private int count;

		// instantiate N isolated components 0 through N-1
		public QuickFindUF(int N) {
			count = N;
			id = new int[N];
			for (int i = 0; i < N; i++)
				id[i] = i;
		}

		// return number of connected components
		public int count() {
			return count;
		}

		// Return component identifier for component containing p
		public int find(int p) {
			return id[p];
		}

		// are elements p and q in the same component?
		public boolean connected(int p, int q) {
			return id[p] == id[q];
		}

		// merge components containing p and q
		public void union(int p, int q) {
			if (connected(p, q))
				return;
			int lid; // loser
			int cid; // champion
			if (p >= q) {
				lid = id[p]; // loser
				cid = id[q]; // champion
			} else {
				lid = id[q]; // loser
				cid = id[p]; // champion
			}
			for (int i = 0; i < id.length; i++)
				if (id[i] == lid)
					id[i] = cid;
			count--;
		}

		public String toString() {
			return Arrays.toString(id);
		}
	}

}