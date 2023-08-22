import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17406 {
	static int N, M, S, n, m, K, SX, SY, EX, EY;
	static int[][] array;
	static int[][] tmp;
	static int[][] calculate;
	static boolean[] visited;
	static int[] result;
	static int sumMin;
	static int[] dj = { 0, 1, 0, -1 };
	static int[] di = { 1, 0, -1, 0 }; // 0:right, 1:down, 2:left, 3:up

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sumMin = Integer.MAX_VALUE;
		array = new int[N + 1][M + 1];
		tmp = new int[N + 1][M + 1];
		visited = new boolean[K];
		result = new int[K];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				tmp[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		calculate = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				calculate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(sumMin);
	}

	static void perm(int cnt) {
		if (cnt == K) {
			for (int i = 0; i < N + 1; i++) {
				array[i] = Arrays.copyOfRange(tmp[i], 0, M + 1);
			}
			for (int i = 0; i < K; i++) {
				int r = calculate[result[i]][0];
				int c = calculate[result[i]][1];
				S = calculate[result[i]][2];
				SX = r - S; // 범위 시작 x
				SY = c - S; // 범위 시작 y
				EX = r + S; // 범위 마지막 x
				EY = c + S; // 범위 마지막 y
				n = EX - SX;
				m = EY - SY;
				rotate();
				print();
			}
			findMin();
			return;
		}
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[cnt] = i;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}

	private static void findMin() {
		for (int i = 1; i <= N; i++) {
			int row = 0;
			for (int j = 1; j <= M; j++) {
				row += array[i][j];
			}
			sumMin = Math.min(row, sumMin);
		}
	}

	static void rotate() {
		for (int d = 0; d < S; d++) {
			// depth별 좌표위치
			int tmp = array[SX + d][SY + d];
			int nowi = SX + d, nowj = SY + d;
			int dir = 0;
			while (true) {
				int nexti = nowi + di[dir];
				int nextj = nowj + dj[dir];

				if (nextj > EY - d || nexti > EX - d || nexti < SX + d || nextj < SY + d) { // 현재 레이어 영역 벗어난 옆칸!
					dir++; // 방향 틀어!
					if (dir < 4) {
						continue; // 자 올라가서 옆칸 다시 계산하렴. 바뀐 방향으로
					} else {
						break; // 방향 0,1,2,3 다 했네. 근데 마지막으로 틀었네? 이 레이어 끝!
					}
				}
				array[nowi][nowj] = array[nexti][nextj]; // 옆칸 괜찮네? 숫자 가져오고
				nowi = nexti;
				nowj = nextj;
			}
			array[SX + d][SY + d + 1] = tmp;
		}
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(array[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		System.out.println("--------------------");
	}

}
