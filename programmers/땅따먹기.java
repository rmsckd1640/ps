import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = -1;
        
        int n = land.length;
        int[][] dp = new int[n][4];
        
        //첫째행은 미리 채워놓기
        dp[0] = land[0].clone();
        
        /*
        첫째행의 결과를 이용해서 둘째행부터 채워 나갈거임
        i행의 j번째 열이 i-1행의 어떤 열하고 합쳤을 때 최대인걸 기록하면됨 (같은 열 제외)
        */
        
        for(int i=1; i<n; i++){
            for(int j=0; j<4; j++){ //둘째행 이상의 열들을 볼거임
                int max = -1;
                for(int k=0; k<4; k++){ //본인의 앞 행의 열들을 가져올거임
                    if(k == j) continue; //같은 열은 제외
                    max = Math.max(max, dp[i-1][k]);
                }
                dp[i][j] = land[i][j] + max;
            }
        }
        
        for(int i=0; i<4; i++){
            answer = Math.max(answer, dp[n-1][i]);
        }

        return answer;
    }
}
