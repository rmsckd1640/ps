import java.util.*;

//그룹 안에 어떤 걸 선택해도 그 안의 구성카드가 다 선택됨
class Solution {
    static int[] ch;
    public int solution(int[] cards) {
        int answer = 0;
        
        ch = new int[cards.length+1];
        
        Map<Integer, Integer> map = new LinkedHashMap<>(); //상자, 카드
        //Map 안쓰고 배열 인덱스 1부터 넣어도 됨
        
        //나중에 큰거 두개 뽑을 거임 (최고점수이기때문)
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<cards.length; i++){
            map.put(i+1, cards[i]);
        }
        
        int cnt = 0;
        
        //cur = 현재 상자 key
        for(int cur : map.keySet()){
            if(ch[cur] == 0){//그룹의 시작
                ch[cur] = 1;
                cnt = 1;

                while(true){
                    //next = 다음 상자 key
                    int next = map.get(cur);
                    
                    //1이면 방문 했었음
                    if(ch[next] == 1){
                        break;
                    }
                    
                    //방문을 안했다면
                    else{
                        ch[next] = 1;
                        cnt++;
                        //현재 키로 갱신하여 또 그 다음 상자 보기
                        cur = next;
                    }
                }
                pq.offer(cnt);
            }
        }
        //그룹이 2개 이하일때 예외 처리
        if(pq.size() < 2) return 0;
        else{
            answer = pq.poll() * pq.poll();
        }

        return answer;
    }
}
