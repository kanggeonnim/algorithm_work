import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1987 {
	static int[][] board;
	static boolean[] visited;
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int T, R, C, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		count = 0;

		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				board[i][j] = input[j] - 'A';
			}
		}
		dfs();
		System.out.println(count);
	}

	private static void dfs() {
		visited = new boolean[26];
		visited[board[0][0]] = true;

		dfs(0, 0);

		// System.out.println(Arrays.toString(visited));

	}

	private static void dfs(int i, int j) {
		boolean isEnd = true;
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];

			if (ni >= 0 && nj >= 0 && ni < R && nj < C && visited[board[ni][nj]] == false) {
				visited[board[ni][nj]] = true;
				// System.out.println((char) (board[ni][nj] + 'A'));
				dfs(ni, nj);
				visited[board[ni][nj]] = false;
				isEnd = false;
			} else {
				continue;
			}
		}
		if (isEnd) {
			int len = 0;
			for (int k = 0; k < 26; k++) {
				if (visited[k]) {
					len++;
				}
			}
			count = Math.max(len, count);
		}
	}
}