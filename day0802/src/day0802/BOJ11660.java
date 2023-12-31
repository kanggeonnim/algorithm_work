package day0802;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] sum = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i-1][j-1] + Integer.parseInt(st.nextToken());
			}
		}

//		System.out.println();
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.print(sum[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int sumAll = 0;
			sumAll += sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
			bw.write(sumAll + "\n");
			bw.flush();

		}
		bw.close();
	}
}
