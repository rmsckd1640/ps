import java.util.*;

class Solution{
    public int solution(String s){
        int answer = 1; //초기값 설정. 한 글자 자체도 팰린드롬이므로 최소 길이는 1
        char[] arr = s.toCharArray();
        
        //중심이 홀수개, 짝수개 나눠서 중심으로부터 확장하며 체크하자
        
        //양 끝단 예외 처리하기. 반복문 범위에서 벗어나는 짝수 팰린드롬 체크
        //왼쪽 끝 중심 짝수 (ex: "aa...")
        if(arr.length > 1 && arr[0] == arr[1]){
            answer = 2;
        }
        //오른쪽 끝 중심 짝수 (ex: "...aa")
        if(arr.length > 1 && arr[arr.length-1] == arr[arr.length-2]){
            answer = 2;
        }
        //중심을 이동하며 확장 검사
        for(int i=1; i<arr.length-1; i++){
            //중심이 홀수일 때
            int lt=i-1, rt=i+1;
            int len = rt - lt + 1;
            while(rt>=0 && rt<arr.length && lt >=0 && lt<arr.length){
                if(arr[lt] == arr[rt]){
                    answer = Math.max(answer, len); //답 갱신하고
                    //길이 늘려서 또 확인
                    lt--;
                    rt++;
                    len = rt - lt + 1;
                }
                else break;
            }
            
            //중심이 짝수일때
            if(arr[i] == arr[i+1]){
                lt = i-1;
                rt = i+2;
                len = rt - lt + 1;
                while(rt>=0 && rt<arr.length && lt >=0 && lt<arr.length){
                    if(arr[lt] == arr[rt]){
                        answer = Math.max(answer, len);
                        lt--;
                        rt++;
                        len = rt - lt + 1;
                    }
                    else break;
                }
            }
        }

        return answer;
    }
}
