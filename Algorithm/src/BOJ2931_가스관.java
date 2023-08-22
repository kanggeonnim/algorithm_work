import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2931_가스관 {
	static int T, R, C, remove_i, remove_j; // 테스트케이스, 행, 열, 지워진 좌표
	static char[][] map; // 도면
	static char[] pipes = { '|', '-', '1', '2', '3', '4' }; // 연산이 복잡한 '+' 를 제외한 나머지를 delta를 사용해서 방향 탐색
	static int[] di = { 0, 1, 0, -1 }; // 4방탐색
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		// 도면 정보 입력받기.
		for (int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];
			}
		}

		// 지도를 돌면서 파이프를 발견하며 지워진 부분을 탐색.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 땅을 찾거나 범위 벗어난경우 패스
				char curMap = map[i][j];
				if (i < 0 || i >= R || j < 0 || j >= C || curMap == '.')
					continue;

				// 파이프 확인시 오른쪽방향과 아래쪽 방향만 탐색하면서 내려감.
				// 오른쪽아래방향으로 map을 확인하면서 지나오기때문에
				if (curMap == '|') { // '|' 파이프 발견시
					// 파이프의 상하부분이 비어있는지 확인하기.
					if (map[i - 1][j] == '.') {
						remove_i = i - 1;
						remove_j = j;
						break;
					} else {
						continue;
					}
				} else if (curMap == '-') {
					if (map[i][j + 1] == '.') {
						remove_i = i;
						remove_j = j + 1;
						break;
					} else {
						continue;
					}
				} else if (curMap == '+') {
					for (int d = 0; d < 4; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (map[ni][nj] == '.') {
							remove_i = ni;
							remove_j = nj;
							break;
						}
					}
					continue;
				} else if (curMap == '1') {
					for (int d = 0; d < 2; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (map[ni][nj] == '.') {
							remove_i = ni;
							remove_j = nj;
							break;
						}
					}
					continue;
				} else if (curMap == '2') {
					for (int d = 0; d < 4; d = d + 3) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (map[ni][nj] == '.') {
							remove_i = ni;
							remove_j = nj;
							break;
						}
					}
					continue;
				} else if (curMap == '3') {
					for (int d = 2; d < 4; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (map[ni][nj] == '.') {
							remove_i = ni;
							remove_j = nj;
							break;
						}
					}
					continue;
				} else if (curMap == '4') {
					for (int d = 1; d < 3; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if (map[ni][nj] == '.') {
							remove_i = ni;
							remove_j = nj;
							break;
						}
					}
					continue;
				}
			}
		}

		char result = ' ';
		boolean up = false, down = false, left = false, right = false;
		for (int d = 0; d < 4; d++) {
			int ni = remove_i + di[d];
			int nj = remove_j + dj[d];

			if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == '.')
				continue;
			if (map[ni][nj] == '-') {
				if (d == 0) {
					right = true;
				} else if (d == 2) {
					left = true;
				}
			} else if (map[ni][nj] == '|') {
				if (d == 1) {
					down = true;
				} else if (d == 3) {
					up = true;
				}
			} else if (map[ni][nj] == '+') {
				if (d == 0) {
					right = true;
				} else if (d == 1) {
					down = true;
				} else if (d == 2) {
					left = true;
				} else if (d == 3) {
					up = true;
				}
			} else if (map[ni][nj] == '1') {
				if (d == 2) {
					left = true;
				} else if (d == 3) {
					up = true;
				}
			} else if (map[ni][nj] == '2') {
				if (d == 1) {
					down = true;
				} else if (d == 2) {
					left = true;
				}
			} else if (map[ni][nj] == '3') {
				if (d == 0) {
					right = true;
				} else if (d == 1) {
					down = true;
				}
			} else if (map[ni][nj] == '4') {
				if (d == 0) {
					right = true;
				} else if (d == 3) {
					up = true;
				}
			}

		}
		if (up && down && left && right) {
			result = '+';
		} else if (up && down) {
			result = '|';
		} else if (left && right) {
			result = '-';
		} else if (down && left) {
			result = '4';
		} else if (up && right) {
			result = '2';
		} else if (up && left) {
			result = '3';
		} else if (down && right) {
			result = '1';
		}
		System.out.println((remove_i + 1) + " " + (remove_j + 1) + " " + result);
	}
}
