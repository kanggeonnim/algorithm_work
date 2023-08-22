import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ11286 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else if (Math.abs(o1) < Math.abs(o2)) {
					return -1;
				} else {

					return Integer.compare(o1, o2);
				}
			}
		});
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input != 0)
				heap.add(input);
			else if (input == 0) {
				if (heap.isEmpty())
					System.out.println(0);
				else
					System.out.println(heap.poll());
			}
		}
	}
}
