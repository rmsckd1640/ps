import java.util.*;
import java.io.*;

public class Main{
	public static long bisearch(long l, long h, long[] arr, long m) {
		long answer = 0;
		while(l <= h) {
			long mid = (l+h) / 2;
			long sum = 0;
			for(int i=0; i<arr.length; i++) {
				if(arr[i] - mid > 0) {
					sum += arr[i] - mid;
				}
			}
			if(sum == m) return mid;
			else if(sum > m) {
				//적어도 m은 가져가는 경우 (딱 맞는 경우가 없어서) 저장해두기
				answer = mid;
				l = mid + 1;
			}
			else h = mid - 1;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());
		long[] arr = new long[n];
		long l = 0L;
		long h = Long.MIN_VALUE;
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			h = Math.max(h, arr[i]);
		}
		
		System.out.println(bisearch(l, h, arr, m));
	}
}
