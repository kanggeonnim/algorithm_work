package day0814;

import java.util.Scanner;

public class SquareNumberTest {
	static int X, N;
	static int c1, c2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		N = sc.nextInt();

		System.out.println(exp1(X, N) + " " + c1);
		System.out.println(exp2(X, N) + " " + c2);
	}

	// 분할X, 재귀 호출
	// X^n = X*X^n-1
	// X^n-1 = X*X^n-2
	private static long exp1(long x, int n) {
		c1++;
		if (n == 1) {
			return X;
		}
		return x * exp1(x, n - 1);
	}

	// 분할 정복 적용
	// n:짝수 -> X^n = X^n/2*X^n/2
	// n:홀수 -> X^n = X^n-1/2*X^n-1/2*X
	// n:홀수 X^n/2 => X^n-1/2와 같음.
	private static long exp2(long x, int n) {
		c2++;
		if (n == 1) {
			return X;
		}
		long y = exp2(x, n / 2);
		return (n % 2 == 0) ? y * y : y * y * x;
	}
}