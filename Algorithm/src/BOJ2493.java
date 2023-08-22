import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Tower> stack = new Stack<>();

		for (int i = 1; i <= N; i++) {
			Tower input = new Tower(i, Integer.parseInt(st.nextToken()));
			while (!stack.isEmpty()) {
				Tower top = stack.peek();
				if (top.height > input.height) {
					stack.add(input);
					System.out.print(top.num + " ");
					break;
				} else if (top.height < input.height) {
					stack.pop();
				}
			}
			if (stack.isEmpty()) {
				stack.push(input);
				System.out.print("0 ");
			}
		}
	}

	static class Tower {
		int num;
		int height;

		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
}
