import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15683 {
	static int N, M, minBlindSpot;
	static int[][] map; // 7: 감시영역
	static ArrayList<Location> cctv;
	static ArrayList<Location> c5; // 5번 cctv의 경우 별도로 저장.
	static int[] di = { 0, -1, 0, 1 };
	static int[] dj = { 1, 0, -1, 0 };

	static int[] result; // cctv 방향 조합리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minBlindSpot = Integer.MAX_VALUE;
		map = new int[N][M];
		cctv = new ArrayList<>();
		c5 = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if (input == 5) {
					c5.add(new Location(i, j, 5));
				} else if (input != 0 && input != 6) {
					cctv.add(new Location(i, j, input));
				}
			}
		}

		// 5번 cctv만큼 영역지우기.
		for (int k = 0; k < c5.size(); k++) {
			Location cur = c5.get(k);
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				while (true) {
					if (ni < 0 || nj < 0 || ni >= N || nj >= M || map[ni][nj] == 6)
						break;
					if (map[ni][nj] == 0) {
						map[ni][nj] = 7;
					}
					ni += di[d];
					nj += dj[d];
				}
			}
		}

		// 5번 cctv를 제외한 나머지 cctv를 통해서 조합만들기
		result = new int[cctv.size()];
		comb(0);

		System.out.println(minBlindSpot);
	}

	private static void makeBlindSpot(int[][] map) {
		for (int k = 0; k < cctv.size(); k++) {
			Location cur = cctv.get(k); // cctv 종류 및 위치
			int cctvForward = result[k]; // cctv가 바라보는 방향
			boolean[] directions = new boolean[4]; // cctv별로 볼 수 있는 시야각.

			if (cur.cctv == 1) {
				directions[(0 + cctvForward) % 4] = true;
			} else if (cur.cctv == 2) {
				directions[(0 + cctvForward) % 4] = true;
				directions[(2 + cctvForward) % 4] = true;
			} else if (cur.cctv == 3) {
				directions[(0 + cctvForward) % 4] = true;
				directions[(1 + cctvForward) % 4] = true;
			} else if (cur.cctv == 4) {
				directions[(0 + cctvForward) % 4] = true;
				directions[(1 + cctvForward) % 4] = true;
				directions[(2 + cctvForward) % 4] = true;
			}

			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				while (true) {
					if (!directions[d] || ni < 0 || nj < 0 || ni >= N || nj >= M || map[ni][nj] == 6)
						break;
					if (map[ni][nj] == 0) {
						map[ni][nj] = 7;
					}
					ni += di[d];
					nj += dj[d];
				}
			}
		}
		minBlindSpot = Math.min(getCount(map), minBlindSpot);
	}

	private static void comb(int cnt) {
		if (cnt == cctv.size()) {
			makeBlindSpot(deepCopy(map));
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (cctv.get(cnt).cctv == 2 && i > 1) {
				continue;
			}
			result[cnt] = i;
			comb(cnt + 1);
		}
	}

	public static class Location {
		int i;
		int j;
		int cctv;

		public Location(int i, int j, int cctv) {
			this.i = i;
			this.j = j;
			this.cctv = cctv;
		}

	}

	private static int getCount(int[][] map) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static int[][] deepCopy(int[][] map) {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			tmp[i] = Arrays.copyOf(map[i], M);
		}
		return tmp;
	}
}
