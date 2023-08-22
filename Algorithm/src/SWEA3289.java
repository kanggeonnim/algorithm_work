import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3289 {
	static int T, N, M;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder("#" + test_case + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N + 1];
			makeSet();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.parseInt(st.nextToken());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());

				switch (t) {
				case 0:
					union(v1, v2);
					break;
				case 1:
					sb.append(isUnion(v1, v2));
					break;
				}
			}
			System.out.println(sb);
		}
	}

	private static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	private static int findParent(int c) {
		if (c == parent[c])
			return c;

		// 경로 압축
		return parent[c] = findParent(parent[c]);
	}

	private static int isUnion(int c, int p) {
		if (findParent(c) == findParent(p))
			return 1;
		else
			return 0;
	}

	private static void union(int c, int p) {
		if (p < c) {
			int temp = p;
			p = c;
			c = temp;
		}
		parent[findParent(p)] = findParent(c);
	}

}
