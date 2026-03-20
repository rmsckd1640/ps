import java.util.*;

class Point{
    int x;
    int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static int[][] ch;
    static int x1, y1, row, col;
    static int answer = 0;
    
    public static int BFS(int x, int y){
        Deque<Point> q = new ArrayDeque<>();
        ch[x][y] = 1;
        q.offer(new Point(x, y));
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                //범위안에 있는 동안 한 방향으로 계속 가보기
                while(nx>=0 && nx < row && ny>=0 && ny < col && arr[nx][ny] != 'D'){
                    nx += dx[i];
                    ny += dy[i];
                }
                //범위 넘어가서 멈춘거니 한칸 되돌리기
                nx -= dx[i];
                ny -= dy[i];
                
                //무한 루프 방지 역할도 함
                if(ch[nx][ny] == 0){
                    ch[nx][ny] = ch[cur.x][cur.y] + 1; //전 칸에서 한번 쭉 이동해 왔으니 +1
                    //근데 골인이면 움직인 이동수 반환
                    if(arr[nx][ny] == 'G') return ch[nx][ny]-1; //처음에 시작을 1부터 했으니 -1 꼭 해주기
                    //이제 여기 칸 주변 탐색
                    q.offer(new Point(nx, ny));
                }
            }
        }
        return -1;
    }
    
    public int solution(String[] board) {
        row = board.length;
        col = board[0].length();
        arr = new int[row][col];
        ch = new int[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                arr[i][j] = board[i].charAt(j);
                if(arr[i][j] == 'R'){
                    x1 = i;
                    y1 = j;
                }
            }
        }
        
        return BFS(x1, y1);
    }
}
