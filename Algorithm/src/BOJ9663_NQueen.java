import java.util.Scanner;

public class BOJ9663_NQueen {
	static int N, col[], ans;
	static boolean visitC[], visitCR[], visitCL[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N + 1];
		ans = 0;
		setQueen(1);
		System.out.println(ans);
	}

	private static void setQueen(int row) {
		// 기저조건
		if (row > N) {
			ans++;
			return;
		}
		// 유도파트
		for (int c = 1; c <= N; c++) {
			col[row] = c;
			if (isAvailable(row))
				setQueen(row + 1);
		}
	}

	private static boolean isAvailable(int row) {
		for (int r = 1; r < row; r++) {
			if (col[r] == col[row] || row - r == Math.abs(col[row] - col[r])) {
				return false;
			}
		}
		return true;
	}
}
