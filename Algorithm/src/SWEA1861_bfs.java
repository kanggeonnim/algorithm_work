import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1861_bfs {
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

			// input
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// bfs
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {

					Queue<Node> que = new LinkedList<Node>();
					que.add(new Node(i, j, 1, array[i][j]));

					while (!que.isEmpty()) {
						int count = que.size();
						for (int k = 0; k < count; k++) {
							Node cur = que.poll();
							for (int d = 0; d < 4; d++) {
								int ni = cur.i + di[d];
								int nj = cur.j + dj[d];
								if (ni < 0 || ni >= size || nj < 0 || nj >= size)
									continue;
								if (array[ni][nj] - 1 == array[cur.i][cur.j]) {
									que.add(new Node(ni, nj, cur.length + 1, cur.parent));
								}
							}
							findMax(cur.length, cur.parent);

						} 
					}
				}
			}
			System.out.println("#" + test_case + " " + maxNum + " " + maxValue);
		}

	}

	private static void findMax(int count, int parent) {
		if (maxValue < count) {
			maxValue = count;
			maxNum = parent;
		} else if (maxValue == count) {
			if (maxNum > parent) {
				maxNum = parent;
			}
		}
	}

	public static class Node {
		int i;
		int j;
		int length;
		int parent;

		public Node(int i, int j, int length, int parent) {
			this.i = i;
			this.j = j;
			this.length = length;
			this.parent = parent;
		}
	}

}