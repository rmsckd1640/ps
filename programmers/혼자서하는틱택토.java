import java.util.*;

class Solution {
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int row, col;
    static int[][] arr;
    
    public static boolean isWin(int x, int y, char mark){
        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int cnt = 1;
            while(nx >= 0 && nx < row && ny >= 0 && ny < col && arr[nx][ny] == mark){
                nx += dx[i];
                ny += dy[i];
                cnt++;
                if(cnt == 3) return true;
            }
        }
        return false;
    }
        
    public int solution(String[] board) {
        row = board.length;
        col = board[0].length();
        arr = new int[row][col];
        int ocnt = 0;
        int xcnt = 0;
        int oflag = 0;
        int xflag = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                arr[i][j] = board[i].charAt(j);
                if(arr[i][j] == 'O'){
                    ocnt++;
                    if(isWin(i,j, 'O')) oflag = 1;
                }
                else if(arr[i][j] == 'X'){
                    xcnt++;
                    if(isWin(i,j, 'X')) xflag = 1;
                }
            }
        }
        
        if(xcnt > ocnt) return 0;
        if(ocnt >= xcnt + 2) return 0;
        if(oflag == 1 && xcnt > ocnt-1) return 0;
        if(xflag == 1 && ocnt > xcnt) return 0;

        return 1;
    }
}

/*
X의 개수가 O보다 많은 경우
O가 승리했을때 X의 개수가 O개수-1 보다 많은 경우
반대로 X가 승리했을때도 마찬가지
O의 개수가 X의 개수보다 2개 이상 많은 경우
*/
