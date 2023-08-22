package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2164 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue<Integer> que = new LinkedList<Integer>();
		for(int i = 1; i <= n; i++) {
			que.add(i);
		}
		int cur = 0;
		for(int i = 0;!que.isEmpty();i++) {
			cur = que.poll();
			if(i % 2 == 0) {
				continue;
			}else {
				que.add(cur);
			}
		}
		
		System.out.println(cur);
		
	}
}