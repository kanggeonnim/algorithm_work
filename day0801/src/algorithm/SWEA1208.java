package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1208 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] array;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		for (int t = 1; t <= 10; t++) {
			int dump = Integer.parseInt(br.readLine());
			int max;
			int min;
			int maxIdx = 99;
			int minIdx = 0;
			st = new StringTokenizer(br.readLine());
			array = new int[100];
			
			for (int i = 0; i < 100; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(array);
			max = array[maxIdx];
			min = array[minIdx];
			
			for(int i = 0; i < dump; i++) {
				if((max - min) <2) {
					break;
				}
				array[maxIdx]--;
				array[minIdx]++;
				boolean findMax = false;
				for(int j = maxIdx - 1; j >= 0; j--) {
					if(array[maxIdx] < array[j]) {
						maxIdx = j;
						max = array[j];
						findMax = true;
					}
				}
				if(!findMax) {
					maxIdx = 99;
					max = array[maxIdx];
				}
				
				boolean findMin = false;
				for(int j = minIdx + 1; j < 100; j++) {
					if(array[minIdx] > array[j]) {
						minIdx = j;
						min = array[j];
						findMin = true;
					}
				}
				if(!findMin) {
					minIdx = 0;
					min = array[minIdx];
				}
				//System.out.printf("max:%d min:%d maxIdx:%d minIdx:%d\n", max, min, maxIdx, minIdx);
			}
			System.out.println("#" + t + " " + (max - min));
			
		}
	}

}
