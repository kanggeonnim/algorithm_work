package day0731;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static int[] dx = { 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1 };
	static int win = 0;
	static int winX = 0;
	static int winY = 0;

	public static void main(String[] args) throws Exception {
		//////////////////////////////////////////////////////////////
		// 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
		//////////////////////////////////////////////////////////////
		// System.setIn(new FileInputStream("Test5.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 오목 탐색시
		// o--
		// /|\
		// 위의 4방향에 대해서 탐색하면 됨.

		board = new int[19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 16칸 까지만 검사함.
		// 이후로는 5개가 연속해서 올수 없음.
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				// 검은색 돌을 발견하면 오목이 되는지 확인함.
				if (board[i][j] == 1) {
					for (int d = 0; d < 4; d++) {
						back(1, d, 1, i, j);
					}
					// 흰돌을 발견하면 오목이 되는지 확인함.
				} else if (board[i][j] == 2) {
					for (int d = 0; d < 4; d++) {
						back(2, d, 1, i, j);
					}
				}
				// 오목판을 탐색하며 delta를 이용해 4방향에 대해 오목의 가능성을 확인함.

			}
		}
		if (win == 0) {
			System.out.println(win);
		} else {
			System.out.println(win);
			System.out.println(winX + " " + winY);
		}
	}

	public static void back(int color, int direction, int len, int i, int j) {
		// 방향에 맞는 좌표 구하기
		int ni = i + dy[direction];
		int nj = j + dx[direction];

		// 바둑알이 5개 이상일 때 6개가 되었는지 확인함.
		if (len == 5) {
			if ((ni >= 0 && nj >= 0 && ni < 19 && nj < 19 && board[ni][nj] != color && board[ni - (dy[direction] * 5)][nj - (dy[direction] * 5)] != color) || ni < 0 || nj < 0 || ni >= 19
					|| nj >= 19) {
				int back_i = ni - (dy[direction] * 5);
				int back_j = nj - (dy[direction] * 5);
				if (back_i >= 0 && back_j >= 0 && back_i < 19 && back_j < 19 && board[ni - (dy[direction] * 5)][nj - (dy[direction] * 5)] != color) {
					win = color;
					if (direction == 3) {
						winX = (i + 1);
						winY = (j + 1);
					} else {
						winX = (i + 1) - (dy[direction] * 4);
						winY = (j + 1) - (dx[direction] * 4);
					}
				}else {
					win = color;
					if (direction == 3) {
						winX = (i + 1);
						winY = (j + 1);
					} else {
						winX = (i + 1) - (dy[direction] * 4);
						winY = (j + 1) - (dx[direction] * 4);
					}
				}
			}
			return;
		}

		// 만약 해당 방향의 다음 돌또한 같은 색상이라면
		if (ni >= 0 && nj >= 0 && ni < 19 && nj < 19 && color == board[ni][nj]) {
			back(color, direction, len + 1, ni, nj);
		}
	}
}
