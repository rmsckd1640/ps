import java.util.*;
public class Main{
	static int n;
	static int k;
	static int answer = 0;
	static int cnt = 0;
	
	static int[] ch = new int[100001]; //같은 층 체크 배열
	
	public static void BFS(int L){
		Deque<Integer> q = new ArrayDeque<>();
		
		ch[n] = 1;
		q.offer(n);
		boolean found = false;
		
		while(!q.isEmpty()) {
			
			int size = q.size(); //해당 레벨의 노드들을 훑을거임.
			
			for(int i=0; i<size; i++) {
				int cur = q.poll();
				if(cur == k) {
					answer = L;
					cnt++;
					found = true;
				}
				
				//모든 경우의 수를 따져야 하기 때문에 중간에 중복된 숫자가 나왔다 해도 무조건 막으면 안됨
				//예를 들어 k로 향하는 길에 같은 숫자로 향할 수 있는 데 막는 거임
				//그래서 생각해 낸것은 중복된 숫자가 같은 층에 한해서 넣으면 됨
				//그럼 k로 가는 경우의 수 전체가 나올 것이고 다음 층에 k도 여러개 존재할 거임
				if(cur-1 >= 0 && (ch[cur-1] == 0 || ch[cur-1] == L+1)) {
					ch[cur-1] = L+1;
					q.offer(cur-1);
				}
				if(cur+1 < 100_001 && (ch[cur+1] == 0 || ch[cur+1] == L+1)) {
					ch[cur+1] = L+1;
					q.offer(cur+1);
				}
				if(cur*2 < 100_001 && (ch[cur*2] == 0 || ch[cur*2] == L+1)) {
					ch[cur*2] = L+1;
					q.offer(cur*2);
				}
			}
			if(found) return; //해당 층에서 k를 발견하고 카운팅했으면 종료
			L++; //하나의 레벨이 끝나면 다음 레벨로
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		BFS(0);
		
		System.out.println(answer);
		System.out.println(cnt);
	}
}
