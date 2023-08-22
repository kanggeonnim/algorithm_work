import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		LinkedList<Integer>[] list = new LinkedList[N + 1];
		int[] indegree = new int[N + 1];
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			list[left].add(right);
			indegree[right] += 1;
		}

		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				visited[i] = true;
				que.add(i);
			}
		}
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur + " ");
			for (int node : list[cur]) {
				if (!visited[node]) {
					if (--indegree[node] == 0) {
						visited[node] = true;
						que.add(node);

					}
				}
			}
		}

		System.out.println(sb);
	}
}
