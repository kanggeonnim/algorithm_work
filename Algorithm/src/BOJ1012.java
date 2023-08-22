import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
	static int[][] map;
	static boolean[][] visited;
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int T, M, N, K, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			count = 0;

			map = new int[N][M];
			visited = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1;
			}
			
			// bfs();
			dfs();
			System.out.println(count);
		}
	}

	private static void bfs() {
		Queue<Location> que = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == 1 && !visited[i][j]) {
					que.add(new Location(i, j));
					visited[i][j] = true;
					count++;

					while (!que.isEmpty()) {
						Location cur = que.poll();

						for (int d = 0; d < 8; d++) {
							int ni = cur.i + di[d];
							int nj = cur.j + dj[d];

							if (ni >= 0 && nj >= 0 && ni < N && nj < M && visited[ni][nj] == false
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
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					dfs(new Location(i, j));
					count++;
				}
			}
		}
	}

	private static void dfs(Location cur) {
		for (int d = 0; d < 4; d++) {
			int ni = cur.i + di[d];
			int nj = cur.j + dj[d];

			if (ni >= 0 && nj >= 0 && ni < N && nj < M && visited[ni][nj] == false && map[ni][nj] == 1) {
				visited[ni][nj] = true;
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