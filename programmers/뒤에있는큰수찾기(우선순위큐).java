import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        //큐에는 인덱스를 넣음 (answer배열 인덱스자리에 값을 채워넣기 위함)
        //하지만 정렬은 실제 숫자 값(numbers[])으로 해야하기 때문에 Comparator 설정
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> numbers[a] - numbers[b]);
        
        for(int i=0; i<numbers.length; i++){
            //현재 숫자(numbers[i])가 큐에 있는 수(앞에 수들)보다 큰 수 인지 확인
            while(!pq.isEmpty() && numbers[pq.peek()] < numbers[i]){
                //맞다면 해당 숫자의 뒷 큰수 저장하기
                answer[pq.poll()] = numbers[i];
            }
            pq.offer(i); //현재 숫자도 큐에 넣기
        }
        
        //큐에 남아 있으면 뒷 큰수가 없는 것 --> -1로 채우기
        while(!pq.isEmpty()){
            answer[pq.poll()] = -1;
        }
        
        return answer;
    }
}
