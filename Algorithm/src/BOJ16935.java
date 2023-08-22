import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, R, max;
	static int[] cal;
	static int[][] array;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		cal = new int[R];
		array = new int[N][M];

		// 배열 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 연산 종류 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int r = Integer.parseInt(st.nextToken());
			
			cal[i] = r;
		}

		// 배열을 돌.려.보.자.
		for (int i = 0; i < R; i++) {
			switch (cal[i]) {
			case 1:
				rot1();
				break;
			case 2:
				rot2();
				break;
			case 3:
				rot3();
				break;
			case 4:
				rot4();
				break;
			case 5:
				rot5();
				break;
			case 6:
				rot6();
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void rot1() {
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				int temp = array[i][j];
				array[i][j] = array[N - 1 - i][j];
				array[N - 1 - i][j] = temp;
			}
		}
	}

	private static void rot2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				int temp = array[i][j];
				array[i][j] = array[i][M - 1 - j];
				array[i][M - 1 - j] = temp;
			}
		}
	}

	private static void rot3() {
		// 90도 돌린 배열저장할 공간
		int[][] temp = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[j][N - 1 - i] = array[i][j];
			}
		}
		// N, M 바꾸기
		array = temp;
		int tmp = M;
		M = N;
		N = tmp;
	}

	private static void rot4() {
		// 90도 돌린 배열저장할 공간
		int[][] temp = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[M - 1 - j][i] = array[i][j];
			}
		}
		// N, M 바꾸기
		array = temp;
		int tmp = M;
		M = N;
		N = tmp;
	}

	private static void rot5() {
		int[][] array1 = new int[N / 2][M / 2];
		int[][] array2 = new int[N / 2][M / 2];
		int[][] array3 = new int[N / 2][M / 2];
		int[][] array4 = new int[N / 2][M / 2];

		// 왼쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array1[y][x] = array[i][j];
			}
		}
		// 오른쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array2[y][x] = array[i][j];
			}
		}
		// 왼쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array3[y][x] = array[i][j];
			}
		}
		// 오른쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array4[y][x] = array[i][j];
			}
		}

		// 삽입
		// 왼쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array[i][j] = array3[y][x];
			}
		}
		// 오른쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array[i][j] = array1[y][x];
			}
		}
		// 왼쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array[i][j] = array4[y][x];
			}
		}
		// 오른쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array[i][j] = array2[y][x];
			}
		}
	}

	private static void rot6() {
		int[][] array1 = new int[N / 2][M / 2];
		int[][] array2 = new int[N / 2][M / 2];
		int[][] array3 = new int[N / 2][M / 2];
		int[][] array4 = new int[N / 2][M / 2];

		// 왼쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array1[y][x] = array[i][j];
			}
		}
		// 오른쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array2[y][x] = array[i][j];
			}
		}
		// 왼쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array3[y][x] = array[i][j];
			}
		}
		// 오른쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array4[y][x] = array[i][j];
			}
		}

		// 삽입
		// 왼쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array[i][j] = array2[y][x];
			}
		}
		// 오른쪽 위
		for (int i = 0, y = 0; i < N / 2; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array[i][j] = array4[y][x];
			}
		}
		// 왼쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = 0, x = 0; j < M / 2; j++, x++) {
				array[i][j] = array1[y][x];
			}
		}
		// 오른쪽 아래
		for (int i = N / 2, y = 0; i < N; i++, y++) {
			for (int j = M / 2, x = 0; j < M; j++, x++) {
				array[i][j] = array3[y][x];
			}
		}
	}
}
