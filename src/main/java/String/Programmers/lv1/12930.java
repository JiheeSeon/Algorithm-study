package String.Programmers.lv1;

class Main12930 {
    static String solution(String s) {
        StringBuilder stb = new StringBuilder();
        String[] strings = s.split("(?<= )");
        for (String ss : strings) {
            for (int i = 0; i < ss.length(); i++) {
                stb.append(i % 2 == 0 ? Character.toUpperCase(ss.charAt(i)) : Character.toLowerCase(ss.charAt(i)));
            }
        }
        return stb.toString();
    }

    static String otherSolution(String s) {

        String answer = "";
        int cnt = 0;
        String[] array = s.split("");

        for (String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer += cnt % 2 == 0 ? ss.toLowerCase() : ss.toUpperCase();
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] lookAhead = " S B C ".split("(?<= )"); //앞의 단어에 뒤쪽에 붙여준다
        String[] lookBehind = " S B C ".split("(?= )"); //뒤의 단어에 앞쪽에 붙여준다

        for (int i = 0; i < Math.min(lookAhead.length, lookBehind.length); i++)
            System.out.println("lookAhead = " + lookAhead[i] + ", lookBehind = " + lookBehind[i]);

    }
}