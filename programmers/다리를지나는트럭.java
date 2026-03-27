import java.util.*;

//트럭의 무게와 !!!다리위로 올라간 시간!!!
class Truck{
    int w;
    int time;
    
    public Truck(int w, int time){
        this.w = w;
        this.time = time;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Deque<Truck> q = new ArrayDeque<>(); //대기 큐
        for(int i=0; i<truck_weights.length; i++){
            q.offer(new Truck(truck_weights[i], 0));
        }
        Deque<Truck> bridge = new ArrayDeque<>(); //다리 큐
        
        
        int total = 0; //현재 다리 위 무게
        //대기하는 트럭이 있는 동안 Or 다리 위에 트럭이 있는 동안 반복 -> 둘 다 없어야 멈춤
        while(!q.isEmpty() || !bridge.isEmpty()){
            //다리에서 나갈 수 있는 트럭 확인
            if(!bridge.isEmpty()){
                Truck t = bridge.peek();
                
                //반복문 한번당 시간 초가 가고 있음
                //현재 시간 - 다리에 들어 왔던 시간 = 다리 위에 있던 시간
                //다리 위에 있던 시간이 다리 길이와 같다면 통과
                if(answer - t.time == bridge_length){
                    bridge.poll();
                    total -= t.w;
                }
            }
            
            //다리에 들어올 수 있는 트럭 확인
            if(!q.isEmpty() && total + q.peek().w <= weight){
                Truck t = q.poll();
                t.time = answer; // 들어온 시각(현재 answer)을 기록!!!
                total += t.w;
                bridge.offer(t);
            }
            
            answer++; //반복문 한번당 1초 증가
        }

        return answer;
    }
}
