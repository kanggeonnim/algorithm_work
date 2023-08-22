import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2463_색종이 {
	static final int PAPER = 100, COLOR_PAPER = 10;
	static int N, area = 0;
	static boolean[][] array = new boolean[PAPER][PAPER];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		for (int count = 0; count < N; count++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < COLOR_PAPER; i++) {
				for (int j = 0; j < COLOR_PAPER; j++) {
					array[i + y][j + x] = true;
				}
			}
		}
		for (int i = 0; i < PAPER; i++) {
			for (int j = 0; j < PAPER; j++) {
				if (array[i][j] == true)
					area++;
			}
		}
		System.out.println(area);
	}
}
