import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1873 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;
	static int T, H, W, N, cur_i, cur_j;
	static char[][] map;
	static char[] order;
	static char[] arrow = { '^', 'v', '<', '>' };
	static char[] direction = { 'U', 'D', 'L', 'R' };
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// input
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = input[j];
					// 전차 위치 찾기
					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {
						cur_i = i;
						cur_j = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			order = br.readLine().toCharArray();

			// 전차 이동 계산
			for (int i = 0; i < N; i++) {
				char curOrder = order[i];
				for (int d = 0; d < 4; d++) {
					if (curOrder == direction[d]) {
						map[cur_i][cur_j] = arrow[d];
						move(cur_i + di[d], cur_j + dj[d]);
					} else if (curOrder == 'S') {
						shoot();
						break;
					}
				}
			}
			
			// 화면에 출력
			sb.append("#" + test_case + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}

			System.out.print(sb);
		}
	}

	// 이동
	private static void move(int ni, int nj) {
		if (ni >= 0 && ni < H && nj >= 0 && nj < W && map[ni][nj] == '.') {
			char tank = map[cur_i][cur_j];
			map[cur_i][cur_j] = '.';
			map[ni][nj] = tank;

			cur_i = ni;
			cur_j = nj;
		}
	}

	// 전차 방향탐색후 포탄장전
	private static void shoot() {
		char tankDir = map[cur_i][cur_j];
		for (int d = 0; d < 4; d++) {
			if (tankDir == arrow[d]) {
				boom(di[d], dj[d]);
			}
		}
	}

	// 발사
	private static void boom(int di, int dj) {
		int ni = cur_i + di;
		int nj = cur_j + dj;
		while (true) {
			if (ni >= 0 && ni < H && nj >= 0 && nj < W) {
				if (map[ni][nj] == '*') {
					map[ni][nj] = '.';
					break;
				} else if (map[ni][nj] == '#') {
					break;
				} else {
					ni = ni + di;
					nj = nj + dj;
				}
			} else
				break;
		}
	}
}
