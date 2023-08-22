import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12891_DNA비밀번호 {
	static int S, P, count;
	static int[] DNA; // 부분 문자열의 포함 갯수 0:A, 1:C, 2:G, 3:T
	static int[] limit; // 최소 필요 문자열 갯수
	static char[] str;
	static boolean isPass;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		str = br.readLine().toCharArray();
		DNA = new int[4];
		limit = new int[4];
		count = 0;
		isPass = true;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			limit[i] = Integer.parseInt(st.nextToken());
		}
		// 아래 for문 실행전에 맨앞부분 한번 확인하기.
		for (int i = 0; i < P; i++) {
			if (str[i] == 'A') {
				DNA[0]++;
			} else if (str[i] == 'C') {
				DNA[1]++;
			} else if (str[i] == 'G') {
				DNA[2]++;
			} else if (str[i] == 'T') {
				DNA[3]++;
			}
		}
		// System.out.println(Arrays.toString(DNA));
		countingP();
		// ACGT 문자열의 P개(부분문자열)에서 ACGT각각의 갯수를 세고, 맨 앞과 맨끝의 갯수만큼 뺴고 더해서
		// 조건을 만족하는지 확인하기.
		for (int i = P; i < S; i++) {
			if (str[i - P] == 'A') {
				DNA[0]--;
			} else if (str[i - P] == 'C') {
				DNA[1]--;
			} else if (str[i - P] == 'G') {
				DNA[2]--;
			} else if (str[i - P] == 'T') {
				DNA[3]--;
			}

			if (str[i] == 'A') {
				DNA[0]++;
			} else if (str[i] == 'C') {
				DNA[1]++;
			} else if (str[i] == 'G') {
				DNA[2]++;
			} else if (str[i] == 'T') {
				DNA[3]++;
			}
			countingP();
//			for (int j = i; j <P; j++) {
//				System.out.print(str[j]);
//			}
		}

		System.out.println(count);
	}

	private static void countingP() {
		for (int i = 0; i < 4; i++) {
			if (limit[i] > DNA[i]) {
				isPass = false;
			}
		}
		if (isPass) {
			count++;
			// System.out.println(Arrays.toString(DNA));
		} else {
			isPass = true;
		}
	}
}
