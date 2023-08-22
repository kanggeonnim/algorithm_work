package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1225 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = 10;
		for (int test_case = 1; test_case <= 10; test_case++) {
			int t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Queue<Integer> que = new LinkedList<Integer>();
			for (int i = 0; i < 8; i++) {
				que.add(Integer.parseInt(st.nextToken()));
			}
			int cur;
			boolean isZero = false;
			while (!isZero) {
				for (int i = 1; i <= 5; i++) {
					cur = que.poll();
					int num = cur - i;
					if (num <= 0) {
						isZero = true;
						que.add(0);
						break;
					}
					que.add(num);
				}
			}

			sb.append("#" + test_case + " ");
			for (int i = 0; i < 8; i++) {
				sb.append(que.poll() + " ");
			}
			sb.append("\n");
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}