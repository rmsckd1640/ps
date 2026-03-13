import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Deque<Character> stack = new ArrayDeque<>();
		int answer = 0;
		int temp = 1; //배수를 중첩 시키는 변수
		
		//큰 괄호끼리 풀어서 계산 (분배법칙)
		for(int i=0; i<str.length(); i++) {
			char cur = str.charAt(i);
			
			if(cur == '(') { //2배 확정
				stack.push(cur);
				temp *= 2;
			}
			
			else if(cur == '[') { //3배 확정
				stack.push(cur);
				temp *= 3;
			}
			
			else if(cur == ')') {
				//예외처리: 스택이 비어있거나 짝이 맞지 않는 경우
				if(stack.isEmpty() || stack.peek() != '(') {
					answer = 0;
					break;
				}
				
				//처음으로 닫히는 알맹이인지 확인
				//확인안하면 temp에 이미 중첩이 다 되어있는건데
				//중복으로 더하게 됨
				if(str.charAt(i-1) == '(') {
					answer += temp;
				}
				
				//예외처리 덕분에 무조건 짝이 있음. 스택에서 제거
				stack.pop();
				// 해당 괄호가 가졌던 배수 영향력이 끝났으므로 원래대로 복구
				temp /= 2;
			}
			
			else if(cur == ']') {
				if(stack.isEmpty() || stack.peek() != '[') {
					answer = 0;
					break;
				}
				
				if(str.charAt(i-1) == '[') {
					answer += temp;
				}
				
				stack.pop();
				
				temp /= 3;
			}
		}
		
		//스택이 비어있지 않으면 짝 안맞음
		if(!stack.isEmpty()) answer = 0;
		
		System.out.println(answer);
	}
}
