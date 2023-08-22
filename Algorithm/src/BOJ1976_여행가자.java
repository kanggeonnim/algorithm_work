import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_여행가자 {
	static int N, M;
	static int[] parent;
	static int[] travel;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		travel = new int[M + 1];

		makeSet();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			travel[i] = Integer.parseInt(st.nextToken());
		}

		boolean isTravel = true;
		for (int i = 1; i <= M - 1; i++) {
			if (find(travel[i]) != find(travel[i + 1])) {
				isTravel = false;
				break;
			}
		}

		if (isTravel) {
			System.err.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	private static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	private static int find(int c) {
		if (parent[c] == c)
			return c;
		return parent[c] = find(parent[c]);
	}

	private static void union(int c, int p) {
		if (c < p) {
			int tmp = p;
			p = c;
			c = tmp;
		}
		parent[find(c)] = find(p);
	}
}
