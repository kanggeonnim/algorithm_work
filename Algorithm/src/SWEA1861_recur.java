import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1861_recur {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, size, maxValue, maxNum;
	static int[][] array;
	static int[] di = { 0, -1, 0, 1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			size = Integer.parseInt(br.readLine());
			array = new int[size][size];
			maxValue = 0;
			maxNum = 0;
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					find(i, j, 1, 0);
				}
			}
			System.out.println("#" + test_case + " " + maxNum + " " + maxValue);
		}
	}

	private static void find(int i, int j, int count, int parent) {
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni < 0 || ni >= size || nj < 0 || nj >= size)
				continue;
			// 다음에 확인할 값들이 나보다 작으면 거기서 시작하는게 더 김.
			if (d < 2 && array[ni][nj] + 1 == array[i][j]) {
				return;
			}
			if (array[ni][nj] - 1 == array[i][j]) {
				if (count == 1)
					find(ni, nj, count + 1, array[i][j]);
				else
					find(ni, nj, count + 1, parent);
			} else {
				continue;
			}
		}
		findMax(count, parent);
		//System.out.println(count + " " + parent);
		return;
	}

	private static void findMax(int count, int parent) {
		if (maxValue < count) {
			maxValue = count;
			maxNum = parent;
		} else if (maxValue == count) {
			if(maxNum > parent) {
				maxNum = parent;
			}
		}
	}
}
