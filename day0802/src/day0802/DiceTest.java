package day0802;

import java.util.Arrays;
import java.util.Scanner;

public class DiceTest {
	static int N, numbers[];
	static boolean[] isSeleted;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		numbers = new int[N];
		switch (M) {
		case 1:	// 중복순열
			dice1(0);
			break;
		case 2:	// 순열
			isSeleted = new boolean[7];
			dice2(0);
			break;
		case 3:	// 중복 조합
			dice3(0,1);
			break;
		case 4: // 조합
			dice4(0,1);
			break;
		}
	} 
	private static void dice1(int cnt) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 1; i <= 6; i++) {
			// 기존 주사위의 눈과 중복되는지 체크
			if(isSeleted[i]) continue;
			numbers[cnt] = i;
			dice1(cnt + 1);
		}
	}
	private static void dice2(int cnt) {	// cnt+1번째 주사위 던지기, cnt: 기존까지 던져진 주사위 횟수
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		// 한번 던질 때 가능한 상황에 대한 시도(1,2,3,4,5,6 가능)
		for(int i = 1; i <= 6; i++) {
			// 기존 주사위의 눈과 중복되는지 체크
			if(isSeleted[i]) continue;
			numbers[cnt] = i;
			//현 주사위의 눈이 i로 결정도니 상태로 다음 주사위 던지러 가기
			isSeleted[i] = true;
			dice2(cnt + 1);
			// 현 주사위의 눈을 다른 선택지로 시도하기 위한 준비
			isSeleted[i] = false;
			
		}
	}
	private static void dice3() {

	}
	private static void dice4() {

	}
}
