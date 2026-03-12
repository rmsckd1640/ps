import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        //k가 라운드 수 보다 많은 경우를 예상 못했음 --> 밑에 반복문에서 런타임 에러 발생
        //무적권 개수가 라운드 개수보다 많다면 라운드 개수 만큼 무조건 방어 가능
        if (k >= enemy.length) {
            return enemy.length;
        }
        
        int answer = k;
        
        //무적권 스킬을 쓰는 적의 수를 담는 바구니
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        //일단 k번은 막아낼 수 있으니 처음 k라운드를 넣어놓고
        //그 뒤로는 비교해가며 하기
        //큐에 있는 것 보다 큰 지 봐야하니깐 최소힙으로 사용
        for(int i=0; i<k; i++){
            pq.offer(enemy[i]);
        }
        
        for(int i=k; i<enemy.length; i++){
            int temp = pq.peek();
            if(enemy[i] > temp){
                pq.poll();
                pq.offer(enemy[i]);
                n -= temp;
            }
            else{
                n -= enemy[i];
            }
            
            if(n < 0) return answer;
            
            answer++;
        }
        
        return answer;
    }
}
