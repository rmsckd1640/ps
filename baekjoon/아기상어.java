import java.util.*;

class Point{
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main{
	//북동남서
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int n;
	static int[][] arr;
	static int[][] ch; //방문배열
	static int[][] dis; //거리배열
	static int size = 2; //초기 아기상어 크기 2
	static int cnt = 0; //먹은 먹이 수 --> 크기와 같게 먹으면 크기 +1 커짐
	static List<Point> list = new ArrayList<>(); //먹을 수 있는 가장 가까운 먹이 리스트
	static int answer = 0;
	
	static void BFS(int x, int y) {
		Deque<Point> q = new ArrayDeque<>();
		ch[x][y] = 1;
		q.offer(new Point(x, y));
		int min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && ch[nx][ny] == 0) {
					if(arr[nx][ny] == 0 || arr[nx][ny] == size) {
						ch[nx][ny] = 1;
						dis[nx][ny] = dis[cur.x][cur.y] + 1; 
						q.offer(new Point(nx, ny));
					}
					//먹이를 찾았을 때
					else if(arr[nx][ny] > 0 && arr[nx][ny] < size) {
						//최단 거리를 구해야 하기 떄문에 비교 필수 (다른 먹이 위치볻 작거나 같아야 리스트에 추가)
						if(min >=  dis[cur.x][cur.y]+ 1) {
							min =  dis[cur.x][cur.y]+ 1;
							ch[nx][ny] = 1;
							dis[nx][ny] = dis[cur.x][cur.y] + 1;
							list.add(new Point(nx, ny));
						}
						//이 이상으론 탐색하지 않아도 되기 때문에 큐에 넣지 않음
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		ch = new int[n][n];
		dis = new int[n][n];

		int startX=0, startY=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 9) {
					startX = i;
					startY = j;
				}
			}
		}
		
		while(true) {
			//탐색 시작
			BFS(startX, startY);
			if(list.isEmpty()) break; //먹을 수 있는 먹이가 없다면 끝
			//가장 가까운 먹이가 여러개이면 가장 위쪽, 가장 왼쪽 꺼내기
			Point temp = list.get(0);
			for(Point p : list) {
				if(p.x < temp.x) {
					temp = p;
				}
				else if(p.x == temp.x) {
					if(p.y < temp.y) {
						temp = p;
					}
				}
			}
			//시작점 갱신
			arr[startX][startY] = 0; //위치 옮겼으니 0으로 갱신
			startX = temp.x;
			startY = temp.y;
			arr[startX][startY] = 0; //먹이 먹었으니 0으로 갱신
			answer += dis[startX][startY]; //먹은 곳으로 간 만큼의 거리 더해주기
			cnt++; //먹이 먹은 횟수
			if(cnt == size) { //아기 상어 크기만큼 먹었다면
				size++; //크기 증가
				cnt=0; //먹은 횟수 초기화
			}
			//지금 갱신한 위치부터 다시 탐색 할거여서 다 초기화
			list.clear(); //먹을 수 있는 가장 가까운 먹이 리스트 초기화
			//방문, 거리 배열 초기화
			for (int i = 0; i < n; i++) {
			    Arrays.fill(ch[i], 0);
			    Arrays.fill(dis[i], 0);
			}
		}
		
		System.out.println(answer);
	}
}
