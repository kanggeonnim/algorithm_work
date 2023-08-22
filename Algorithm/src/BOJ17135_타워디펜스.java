import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17135_타워디펜스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, D, maxKill;
	static int[][] map;
	static boolean[][] visited;
	static int[] archers; // mC3 의 아처를 뽑아서 저장.

	public static void main(String[] args) throws IOException {
		input();
		comb(0, 0);
		System.out.println(maxKill);
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		archers = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	// 3명의 아처 뽑기
	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			// System.out.println(Arrays.toString(archers));
			findArchers();
			return;
		}
		for (int i = start; i < M; i++) {
			archers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void findArchers() {
		// map 복사하기
		int[][] tmp = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			tmp[i] = Arrays.copyOf(map[i], M);
		}
		int kills = 0;

		// 3명의 궁수가 N-1칸부터 0번째 칸까지 전진해감.
		for (int forward = N - 1; forward >= 0; forward--) {
			for (int archer = 0; archer < 3; archer++) { // 0,1,2번 아처에 대해
				int arch_j = archers[archer];
				boolean isKill = false;
				for (int d = 0; d < D && !isKill; d++) { // 사정거리 안에 적을 발견할 때까지 탐색 진행.
					
					// j 값에 대해서 -D시작, i값은 0 부터 시작. j++, i++ 수행후,
					// i == D가 되면, i-- j++수행.

					int r = 0;
					int c = -d;
					// 오른쪽에서 중심으로 적이있나 확인.
					while (r != d && !isKill) {
						int ni = forward - r;
						int nj = arch_j + c;
						if (ni >= 0 && nj >= 0 && ni < N && nj < M && tmp[ni][nj] == 1) {
							visited[ni][nj] = true;
							isKill = true;
						}
						r++;
						c++;
					}
					while (r != -1 && !isKill) {
						int ni = forward - r;
						int nj = arch_j + c;
						if (ni >= 0 && nj >= 0 && ni < N && nj < M && tmp[ni][nj] == 1) {
							visited[ni][nj] = true;
							isKill = true;
						}
						r--;
						c++;
					}

				}
			}

			// archer 3명이 죽인 적은 visited에 저장되어 있음.(중복으로 죽은적 포함)
			// visited가 체크도니 적을 tmp-map에서 지워줌.
			// 이후 아처들을 앞으로 한칸 전진시킴.
			// 만약 죽인적 3명을 찾으면 바로 return
			int killCount = 0;
			for (int i = forward; i >= 0 && killCount != 3; i--) {
				for (int j = 0; j < M && killCount != 3; j++) {
					if (visited[i][j]) {
						tmp[i][j] = 0;
						visited[i][j] = false;
						killCount++;
					}
				}
			}
			kills = kills + killCount;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(tmp[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("---------------" + kills);
		}
		maxKill = Math.max(maxKill, kills);
	}
}
