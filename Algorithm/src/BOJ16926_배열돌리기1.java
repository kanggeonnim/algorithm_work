

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16926_배열돌리기1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); // M+N-4 바퀴만큼 돌면 제자리

		// input
		int[][] array = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 배열이 몇겹인지
		int min = Math.min(M, N);
		int count = min / 2;

		for (int i = 1; i <= count; i++) {
			Queue<Integer> que = new LinkedList<Integer>();
			// 돌리려는 배열의 왼쪽 맨위 좌표와 오른쪽 맨아래 좌표
			int[] start = { i, i };
			int[] end = { M + (1 - i), N + (1 - i) };

			// 배열 맨 윗줄 때서 큐에 넣기
			for (int j = 0; j <= end[1] - start[1]; j++) {
				que.add(array[start[0]][start[1] + j]);
			}
			// 배열 오른쪽 줄 때서 큐에 넣기
			for (int j = 1; j < end[0] - start[0]; j++) {
				que.add(array[start[0] + j][end[1]]);
			}
			// 배열 맨 아랫 때서 큐에 넣기
			for (int j = 0; j <= end[1] - start[1]; j++) {
				que.add(array[end[0]][end[1] - j]);
			}
			// 배열 왼쪽 줄 때서 큐에 넣기
			for (int j = 1; j < end[0] - start[0]; j++) {
				que.add(array[end[0] - j][start[1]]);
			}

			int rotation = R % (((end[0] - start[0] + end[1] - start[1]) * 2));
			// 배열 돌리기
			for (int j = 0; j < rotation; j++) {
				que.add(que.poll());
			}

			// 큐에서 배열에 넣기
			// 윗줄
			for (int j = 0; j <= end[1] - start[1]; j++) {
				// que.add(array[start[0]][start[1] + j]);
				array[start[0]][start[1] + j] = que.poll();
			}

			// 오른쪽
			for (int j = 1; j < end[0] - start[0]; j++) {
				// que.add(array[start[0] + j][end[1]]);
				array[start[0] + j][end[1]] = que.poll();
			}

			// 아래쪽
			for (int j = 0; j <= end[1] - start[1]; j++) {
				// que.add(array[end[0]][end[1] - j]);
				array[end[0]][end[1] - j] = que.poll();
			}

			// 왼쪽
			for (int j = 1; j < end[0] - start[0]; j++) {
				array[end[0] - j][start[1]] = que.poll();
			}
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == N)
					sb.append(array[i][j]);
				else
					sb.append(array[i][j] + " ");
			}
			sb.append(" \n");
		}

		bw.append(sb);
		bw.flush();
		bw.close();
	}
}
