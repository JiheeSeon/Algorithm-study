package String.Programmers.lv1;

class Main12931{
    static int solution(int n){
        String s = Integer.toString(n);
        int result = 0;

        for(int i = 0; i < s.length(); i++){
            result += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return result;
    }

    static int otherSolution(int n){
        // 타입변환 없이 가능
        int answer = 0;

        while(true){
            answer+=n%10;
            if(n<10)
                break;

            n=n/10;
        }
        return answer;
    }
}