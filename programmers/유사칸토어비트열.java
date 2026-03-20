import java.util.*;

class Solution {
    public static long DFS(int n, long k){
        if(k < 0) return 0;
        if(n == 0) return 1; //n=0일때 1은 1개
        else{
            //지금 단계의 한 묶음 크기
            long size = (long)Math.pow(5, n-1);
            //지금 단계의 한 묶음의 1의개수
            long cnt = (long)Math.pow(4, n-1);
            //k가 속한 구간
            int part = (int)(k/size);
            
            if(part<2){
                return (long)part * cnt + DFS(n-1, k%size); //해당 묶음에서 몇번째인지 계산하고 재귀 호출
            }
            else if(part == 2){ //앞의 두 묶음의 1의개수만 리턴
                return cnt * 2;
            }
            else{
                //0만 있는 구간인 2번째 구간 제외
                return (long)(part-1) * cnt + DFS(n-1, k%size);
            }
        }
        
    }
    
    public int solution(int n, long l, long r) {
        //0번부터 보는거임!!!!!!
        return (int)(DFS(n, r-1) - DFS(n, l-2));
    }
}
/*
r까지의 1의개수 - (l-1)까지의 1의개수 = l~r의 1의개수
*/
