import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	static StringTokenizer st;
	// static boolean[][] adj;
	static LinkedList<Integer>[] adjList;
	static boolean[] visited;
	static int N, M, V;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		// adj = new boolean[N + 1][N + 1];
		adjList = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());

			adjList[to].add(from);
			adjList[from].add(to);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}
		
		sb = new StringBuilder();
		visited = new boolean[N + 1];
		dfs(V);
		System.out.println(sb);

		sb = new StringBuilder();
		visited = new boolean[N + 1];
		bfs(V);
		System.out.println(sb);

	}

	private static void dfs(int vertex) {
		visited[vertex] = true;
		sb.append(vertex + " ");
		for (int i : (LinkedList<Integer>) adjList[vertex]) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		return;
	}

	private static void bfs(int vertex) {
		Queue<Integer> que = new LinkedList<>();
		visited[vertex] = true;
		que.add(vertex);
		while (!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur + " ");
			for (int i : (LinkedList<Integer>) adjList[cur]) {
				if (!visited[i]) {
					visited[i] = true;
					que.add(i);
				}
			}
		}
	}
}
