package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2493 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int maxheigt = 0;
		int preidx = 0;
		int prevHeight = 0;
		int topidx = 0;
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (height > prevHeight) {
				if (maxheigt < height) {
					sb.append(0);
					maxheigt = height;
				} else {
					sb.append(topidx);
				}
				preidx = i;
				prevHeight = height;

			} else if (height < prevHeight) {
				sb.append(preidx);
				preidx = i;
				topidx = i - 1;
				prevHeight = height;
			}
			if (i != N)
				sb.append(" ");
		}

		System.out.println(sb);
	}
}
