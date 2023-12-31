package day0802;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] array;
	static int n;
	static int direction;
	static int length;
	static int number;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());

			array = new int[n][n];
			length = n;
			number = 1;
			snail(1, 0, 0);
			print();
		}

		bw.close();
	}

	/*
	 * direction: 달팽이가 이동하는 방향 count: 같은 방향으로 두번씩 이동함을 카운트 length: 달팽이가 얼마나 이동해야 하는지
	 */
	public static void snail(int count, int x, int y, int len) {
		if (count == 0 && length == 1) {
			return;
		}
		
		array[x][y] = number++;
		int nx = x + dx[direction];
		int ny = y + dy[direction];

		if (length == 0) {
			
			// 벽에 부딫히면(특정 길이만큼 이동했으면 방향을 꺽어줘야함.
			direction = (direction + 1) % 4;
			nx = x + dx[direction];
			ny = y + dy[direction];

			// 벽에 두번 부딫힌거면 길이를 한칸 줄여주기도 해야함.
			if (count == 0) {
				snail(count + 2, nx, ny);
			} else {
			// 한번 부딫힌거면 방향만 돌려주기.
				snail(count - 1, nx, ny);
			}
		}
	}

	public static void print() throws IOException {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				bw.append(array[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
	}
}
