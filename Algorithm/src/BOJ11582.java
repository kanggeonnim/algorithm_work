import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11582 {
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> house;
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N, count, houseCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		count = 0;
		house = new ArrayList<>();
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = input[j] - '0';
			}
		}
		// bfs();
		dfs();
		System.out.println(count);
		Collections.sort(house);
		for (int i : house) {
			System.out.println(i);
		}

	}

	private static void bfs() {
		Queue<Location> que = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (map[i][j] == 1 && !visited[i][j]) {
					que.add(new Location(i, j));
					visited[i][j] = true;
					count++;

					while (!que.isEmpty()) {
						Location cur = que.poll();

						for (int d = 0; d < 8; d++) {
							int ni = cur.i + di[d];
							int nj = cur.j + dj[d];

							if (ni >= 0 && nj >= 0 && ni < N && nj < N && visited[ni][nj] == false
									&& map[ni][nj] == 1) {
								que.add(new Location(ni, nj));
								visited[ni][nj] = true;
							}
						}
					}
				}
			}
		}
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					houseCount = 1;
					dfs(new Location(i, j));
					house.add(houseCount);
					count++;
				}
			}
		}
	}

	private static void dfs(Location cur) {
		for (int d = 0; d < 4; d++) {
			int ni = cur.i + di[d];
			int nj = cur.j + dj[d];

			if (ni >= 0 && nj >= 0 && ni < N && nj < N && visited[ni][nj] == false && map[ni][nj] == 1) {
				visited[ni][nj] = true;
				houseCount++;
				dfs(new Location(ni, nj));
			} else {
				continue;
			}
		}
		return;
	}

	public static class Location {
		int i;
		int j;

		public Location(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}