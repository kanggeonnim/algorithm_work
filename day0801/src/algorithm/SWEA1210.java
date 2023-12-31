package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1210 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int dx[] = { 1, -1, 0 };
	static int dy[] = { 0, 0, -1 };

	static int[][] board = null;
	static int start = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		for (int tc = 1; tc <= 10; tc++) {

			board = new int[100][100];
			int t = Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 시작지점 찾기
			for (int i = 0; i < 100; i++) {
				if (board[99][i] == 2) {
					start = i;
				}
			}
			int x = start;
			int y = 99;
			int direction = 2;
			while (true) {
				if (y == 0) {
					System.out.println("#" + tc + " " + x);
					break;
				}
				// 위로 올라온 상황이라면
				if (direction == 2) {
					for (int d = 0; d < 2; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (nx >= 0 && ny >= 0 && nx < 100 && ny < 100 && board[ny][nx] == 1) {
							x = nx;
							y = ny;
							direction = d;
							break;
						}
					}
					if (direction == 2) {
						y = y - 1;
					}

				} else {
					int nx = x + dx[direction];
					int ny = y + dy[direction];
					if (nx >= 0 && ny >= 0 && nx < 100 && ny < 100 && board[ny][nx] == 1) {
						x = nx;
						y = ny;
					} else {
						y = y - 1;
						direction = 2;
					}

				}
			}
		}
	}
}