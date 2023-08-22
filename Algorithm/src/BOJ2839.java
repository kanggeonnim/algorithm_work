import java.util.Scanner;

public class BOJ2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long count = 0;
		while(N % 3 != 0 && N > 4) {
			N = N - 5;
			count++;
			//System.out.println(N + " count:" + count);
		}
		if(N % 3 == 0) {
			count += N/3;
			System.out.println(count);
		}else if(N < 5) {
			System.out.println(-1);
		}
	}
}
