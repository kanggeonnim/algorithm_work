package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2023 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[] result;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		result = new int[N];
		perm(0);
		bw.close();
	}

	// 순열 생성
	private static void perm(int cnt) throws IOException {
		// 왼쪽부터 더한 수가 소수가 아니면 return함.
		if (cnt != 0 && !isPrime(cnt)) {
			return;
		}
		
		// 끝까지 소수이면서 N자리인 순열을 출력
		if (cnt == N) {
			print();
			return;
		}

		// 0, 1은 첫번째 자리에 올 때 소수가 될 수 없으므로 continue;
		for (int i = 0; i < 10; i++) {
			if (cnt == 0 && i <= 1) {
				continue;
			}
			result[cnt] = i;
			perm(cnt + 1);
		}
	}

	// 신비한 소인수 출력하기
	private static void print() throws IOException {
		for (int i = 0; i < N; i++) {
			sb.append(result[i]);
		}
		bw.append(sb + "\n");
		sb.setLength(0);
	}

	// 소인수 판별기
	private static boolean isPrime(int cnt) throws IOException {

		for (int i = 0; i < cnt; i++) {
			sb.append(result[i]);
		}
		
		// 소수판별
		int num = Integer.parseInt(sb.toString());
		int root = (int) Math.sqrt(num);
		sb.setLength(0);
		for (int div = 2; div <= root; div++) {
			if (num % div == 0) {
				return false;
			}
		}
		return true;
	}
}
