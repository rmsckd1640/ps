import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        //스택을 자신보다 큰 수가 나올 때 까지 대기하는 용도로 사용
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0; i<numbers.length; i++){
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){ //stack에는 인덱스가 담겨있는거 주의
                int temp = stack.pop();
                answer[temp] = numbers[i];
            }
            
            //뒤에 있는 큰 수를 찾으면 자기 자리에 numbers[i]를 넣어야 하기 때문에 값 대신 인덱스 넣기
            stack.push(i);
        }
        
        //스택에 남은 애들은 뒤에 큰 수가 없는 것이니 -1로 채우기
        while(!stack.isEmpty()){
            int temp = stack.pop();
            answer[temp] = -1;
        }
        
        return answer;
    }
}
