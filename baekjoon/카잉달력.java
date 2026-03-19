import java.util.*;
public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] answer = new int[T];
		for(int i=0; i<T; i++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int res = -1; //구하지 못할경우 대비 -1
			for(int j = x; j <= M * N; j += M) {
				//M과 N이 같은 경우 대비 (그냥 나누면 동일할때에는 0이 되어버림 --> -1 해서 나머지 구한 후 +1)
				if((j-1) % N +1 == y) {
					res = j;
					break;
				}
			}
			answer[i] = res;
		}

		for(int i : answer) {
			System.out.println(i);
		}
	}
}

/*

x를 고정하고 M만큼 껑충 뛰어 넘는걸 이용
y는 x+M만큼을 N씩 따라갔을때의 나머지임!

M=10이면 --> 3이든 13이든 23이든 전부 3임
y를 구할때는 x가 껑충뛸때 따라서 N만큼 갔을 때의 나머지임

*/
