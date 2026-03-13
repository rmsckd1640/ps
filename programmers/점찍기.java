import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        /*
        데이터 타입이 long이더라도, 계산 과정에서 피연산자들이 int이면,
        결과가 먼저 int범위 내에서 잘린 뒤(오버플로우) 대입 됨.
        따라서 연산 시점에 최소 하나 이상의 변수를 long으로 형변환해야
        정확한 값을 얻을 수 있다.
        */
        long dlen = (long)d*d;

        //k씩 증가
        for(long x=0; x<=d; x+=k){
            long y = (long)Math.sqrt(dlen - x*x); //자동내림
            answer += y/k + 1; //0포함
        }

        return answer;
    }
}
