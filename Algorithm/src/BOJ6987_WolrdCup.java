import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6987_WolrdCup {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][][] score; // 4개 조, 6개 국가, 3개의 (승, 무, 패)
	static int[] result;
	static int[] count = { 2, 2, 1 }; // 승, 무, 패
	static int lines, passCount;
	static boolean isPass;

	public static void main(String[] args) throws IOException {
		score = new int[4][6][3];
		result = new int[5];
		// count = new int[3]; // 승 무 패가 몇개 필요한지.

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					score[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 6; j++) {
//				for (int k = 0; k < 3; k++) {
//					System.out.print(score[i][j][k] + " ");
//				}
//			}
//			System.out.println();
//		}

		//for (int i = 0; i < 4; i++) {
			isPass = false;
			passCount = 0;
			comb(score[lines][5], 5, 0);
			System.out.println("Pass Count is " + passCount);
			lines++;
//			if (isPass) {
//				System.out.print(1 + " ");
//			} else {
//				System.out.print(0 + " ");
//			}
		//}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.print(score[i][j][k] + " ");
				}
			}
			System.out.println();
		}
	}

	// r개의 팀중에서 경우의 수 구하기 승리:-1, 무승부:0, 패배:1
	private static void comb(int[] array, int r, int cnt) {
		int[] count = Arrays.copyOf(array, array.length);
		if (cnt == r) {
//			for (int i = 0; i < r; i++) {
//				System.out.print(result[i] + " ");
//			}
//			System.out.println();

			for (int i = 0; i < r; i++) {
				if (isValiable(result[i], i, r)) {
					int res = result[i];
					comb(score[lines][r - 1], r - 1, 0);
					score[lines][i][-1 * res + 1]++;
					score[lines][r][res + 1]++;
				}
			}
			//System.out.println(Arrays.toString(result));
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					for (int k = 0; k < 3; k++) {
						System.out.print(score[i][j][k] + " ");
					}
				}
				System.out.println();
			}
			System.out.println("----------------------------");
			return;
		}
		for (int i = 0; i <= 2; i++) {
			if (count[i] != 0) {
				result[cnt] = i - 1;
				count[i]--;
				comb(count, r, cnt + 1);
				count[i]++;
			}

		}
	}

	
	// r개 국가에 대해 한번에 검사해야함. 
	private static boolean isValiable(int result, int country, int r) {
		// 가지치기
		if (--score[lines][country][-1 * result + 1] < 0 | --score[lines][r][result + 1] < 0) {
			score[lines][country][-1 * result + 1]++;
			score[lines][r][result + 1]++;
			//System.out.println("score[" + lines + "][" + r + "][" + (result + 1) + "]: " + score[lines][r][result + 1]);
			return false;
		}
		if (r == 1) {
			isPass = true;
			passCount++;
		}
		return true;
	}

}