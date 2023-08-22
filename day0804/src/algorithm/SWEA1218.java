package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA1218 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int T = 10, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			Stack stack = new Stack(N);
			boolean isPair = true;
			char[] input = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) {
				// 여는 괄호 만나면 stack에 넣기
				char ch = input[i];
				if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
					stack.push(ch);
				} else if (stack.isEmpty()) {
					isPair = false;
					break;
				} else if (ch == ')') {
					if (stack.pop() == '(')
						continue;
					break;
				} else if (ch == ']') {
					if (stack.pop() == '[')
						continue;
					break;
				} else if (ch == '}') {
					if (stack.pop() == '{')
						continue;
					break;
				} else if (ch == '>') {
					if (stack.pop() == '<')
						continue;
					break;
				}
			}
			
			sb.append("#" + test_case + " ");
			if(isPair && stack.isEmpty()) {
				sb.append(1 + "\n");
			}else {
				sb.append(0 + "\n");
			}
			bw.append(sb);
			bw.flush();
			sb.setLength(0);

		}
		bw.close();
	}

	static class Stack {
		private int idx = 0;
		private char[] stack;

		public Stack(int size) {
			stack = new char[size];
		}

		public boolean isEmpty() {
			if (idx == 0) {
				return true;
			} else {
				return false;
			}
		}

		public void push(char ch) {
			stack[idx++] = ch;
		}

		public char pop() {
			return stack[--idx];
		}

		public char peek() {
			return stack[idx];
		}
	}
}
