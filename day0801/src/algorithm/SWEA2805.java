package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA2805 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] array;
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.parseInt(br.readLine());
			array = new int[size][size];

			for (int i = 0; i < size; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < size; j++) {
					array[i][j] = input[j] - '0';
				}
			}
			int y = 0;
			int sum = 0;
			// 윗부분의 합
			for (int i = 0; i < size; i += 2, y++) { // 읽어들이는 갯수
				for (int j = (size - i) / 2; j <= (size + i) / 2; j++) { // 시작지점부터 읽어드리기
					sum += array[y][j];
					//System.out.println(y + " " + j);
				}
			}
			
			// 아랫부분의 합
			int y2 = size - 1;
			
			for (int i = 0; y2 >= y; i += 2, y2--) { // 읽어들이는 갯수
				for (int j = (size - i) / 2; j <= (size + i) / 2; j++) { // 시작지점부터 읽어드리기
					sum += array[y2][j];
					//System.out.println(y2 + " " + j);
				}
			}
			
			System.out.println("#" + test_case + " " + sum);
		}
	}

}
