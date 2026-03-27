import java.util.*;

class Solution {
    static int[] unf;
    
    public static void Union(int a, int b){
        int fa = Find(a);
        int fb = Find(b);
        if(fa != fb) unf[fa] = fb;
    }
    
    public static int Find(int v){
        if(v == unf[v]) return v;
        else return unf[v] = Find(unf[v]);
    }
    
    
    public int solution(int[] cards) {
        unf = new int[cards.length+1];
        
        //!!!초기화!!!
        for(int i=0; i<cards.length+1; i++){
            unf[i] = i;
        }
        
        for(int i=0; i<cards.length; i++){
            int a = i+1;
            int b = cards[i];
            Union(a,b);
        }
        
        //대표를 통해 각 집합에 몇개 있는지 볼거임
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=1; i<cards.length+1; i++){ //노드를 순회 하면서 대표 찾고 넣기 (1번부터)
            int temp = Find(i); //i의 대표 찾기
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        
        //최대힙 우선순위 큐 사용해서 큰 거 두개 뽑기
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int key : map.keySet()){
            pq.offer(map.get(key));
        }
        
        if(pq.size() < 2) return 0;
        else return pq.poll() * pq.poll();
    }
}
