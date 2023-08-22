import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {
	static int[][] map;
	static int[][] distance; // object[] 치킨집 과 집들의 거리
	static ArrayList<Location> locChicken;
	static ArrayList<Location> locHouse;
	static int N, M, houseCount, chickenCount, ans;
	static int[] combResult;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		map = new int[N][N];
		combResult = new int[M];
		locChicken = new ArrayList<>(); // 최대 치킨집, 집만큼 배열 생성.
		locHouse = new ArrayList<>();

		// input array, count house & chicken zip
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				map[i][j] = cur;
				if (cur == 1) { // zip
					locHouse.add(new Location(1, i, j));
					houseCount++;
				} else if (cur == 2) { // chick
					locChicken.add(new Location(2, i, j));
					chickenCount++;
				}
			}
		}
		// 치킨집과 집사이의 거리구하기.
		distance = new int[chickenCount][houseCount];
		for (int i = 0; i < chickenCount; i++) {
			Location curC = locChicken.get(i);
			for (int j = 0; j < houseCount; j++) {
				Location curH = locHouse.get(j);
				distance[i][j] = Math.abs(curC.i - curH.i) + Math.abs(curC.j - curH.j);
			}
		}

		visited = new boolean[chickenCount];
		comb(0);
		System.out.println(ans);

	}

	// 치킨 집 선별
	private static void comb(int cnt) {
		if (cnt == M) {
			calculateDistance();
			return;
		}
		for (int i = 0; i < chickenCount; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combResult[cnt] = i;
				comb(cnt + 1);
				visited[i] = false;
			}
		}
	}

	private static void calculateDistance() {
		int sum = 0;
		int[] min = new int[houseCount];
		Arrays.fill(min, Integer.MAX_VALUE);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < houseCount; j++) {
				int curDistance = distance[combResult[i]][j];
				if (min[j] > curDistance) {
					min[j] = curDistance;
				}
			}
		}
		for (int i = 0; i < houseCount; i++) {
			sum += min[i];
		}
		ans = Math.min(sum, ans);
	}

	public static class Location {
		int type; // 1: house, 2: chicken
		int i;
		int j;

		public Location(int type, int i, int j) {
			this.i = i;
			this.j = j;
			this.type = type;
		}
	}
}
