import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ16637_괄호_추가하기2 {
    static boolean[] isSelected;
    static boolean[] isCal;
    static String[] expression;
    static String[] Input;
    static int N;
    static long ans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        isSelected = new boolean[(N / 2) + 1];
        Input = br.readLine().split("");
        ans = Integer.MIN_VALUE;
        comb(1);
        System.out.println(ans);
    }

    private static void comb(int cnt) {
        if (cnt == (N / 2) + 1) {

//            System.out.println(Arrays.toString(isSelected));
            calculate();
//            System.out.println();
            return;
        }
        if (!isSelected[cnt] && !isSelected[cnt - 1]) {
            isSelected[cnt] = true;
            comb(cnt + 1);
        }
        isSelected[cnt] = false;
        comb(cnt + 1);
    }

    private static void calculate() {
        expression = Arrays.copyOf(Input, N);
        isCal = new boolean[N];
        long res = 0;
        String cal = expression[0];
        // 괄호 쳐진거 먼저 계산 해주기
        for (int i = 0; i < N / 2; i++) {
            if (isSelected[i + 1]) {
                long l = Long.parseLong(expression[i * 2]);
                long r = Long.parseLong(expression[i * 2 + 2]);

                if (expression[i * 2 + 1].equals("+")) {
                    cal = String.valueOf(l + r);
                    expression[i * 2] = cal;
                    isCal[i * 2 + 2] = true;
                } else if (expression[i * 2 + 1].equals("-")) {
                    cal = String.valueOf(l - r);
                    expression[i * 2] = cal;
                    isCal[i * 2 + 2] = true;
                } else if (expression[i * 2 + 1].equals("*")) {
                    cal = String.valueOf(l * r);
                    expression[i * 2] = cal;
                    isCal[i * 2 + 2] = true;
                }
            }
        }
//        System.out.println(Arrays.toString(isSelected));
//        System.out.println("괄호계산후");
//        System.out.println(Arrays.toString(isCal));
//        System.out.println(Arrays.toString(expression));
        // 괄호가 안쳐진 부분 계산
        for (int i = 0; i < N / 2; i++) {
            if (!isSelected[i + 1]) {
                int lIdx = i * 2;
                int rIdx = i * 2 + 2;
                while (isCal[lIdx]) {
                    lIdx -= 2;
                }
                //System.out.println(lIdx + " " + i);
                //System.out.println(lIdx + " " + rIdx);
                long l = Long.parseLong(expression[lIdx]);
                long r = Long.parseLong(expression[rIdx]);

                if (expression[i * 2 + 1].equals("*")) {
                    cal = String.valueOf(l * r);
                    expression[lIdx] = cal;
                    isCal[rIdx] = true;
                }
            }
        }
        
        for (int i = 0; i < N / 2; i++) {
            if (!isSelected[i + 1]) {
                int lIdx = i * 2;
                int rIdx = i * 2 + 2;
                while (isCal[lIdx]) {
                    lIdx -= 2;
                }
                //System.out.println(lIdx + " " + i);
                //System.out.println(lIdx + " " + rIdx);
                long l = Long.parseLong(expression[lIdx]);
                long r = Long.parseLong(expression[rIdx]);

                if (expression[i * 2 + 1].equals("+")) {
                    cal = String.valueOf(l + r);
                    expression[lIdx] = cal;
                    isCal[rIdx] = true;
                } else if (expression[i * 2 + 1].equals("-")) {
                    cal = String.valueOf(l - r);
                    expression[lIdx] = cal;
                    isCal[rIdx] = true;
                }
            }
        }
//        System.out.println("괄호X계산후");
//        System.out.println(Arrays.toString(expression));
        res = Long.parseLong(cal);
        ans = Math.max(ans, res);

    }
}