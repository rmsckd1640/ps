import java.util.*;

class Solution {
    public long[] solution(long begin, long end) {
        long[] answer = new long[(int)(end-begin+1)];
        
        //본인 제외 제일 큰 약수 넣으면 됨
        int size = 0;
        for(long i=begin; i<=end; i++){
            if(i==1){ //1인 경우는 예외처리
                answer[size] = 0;
                size++;
            }
            else{
                long temp = 1; //기본적으로 제일 큰 약수는 1
                for(long j=2; j<=Math.sqrt(i); j++){
                    if(i%j == 0){
                        //처음 나오는 몫이 약수중 제일 큼 근데 문제 제한 조건에 맞춤
                        if(i/j <= 10_000_000){
                            temp = i/j;
                            break;
                        }
                        //i/j가 블록 범위를 초과하면, 현재 약수인 j라도 후보로 저장해두고 더 큰 약수를 찾으러 감
                        temp = j;
                    }
                }
                answer[size] = temp;
                size++;
            }
        }
        
        return answer;
    }
}
