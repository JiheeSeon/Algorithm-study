package Implementation.Programmers.lv2;

class 문자열압축_60057 {
    public int solution(String s) {
        int result = s.length();
        String prev, curr;
        StringBuilder made;
        int repeatN;
        int toInsert;
        int cIdx;

        for(int i = 1; i <= s.length() / 2; i++){
            prev = s.substring(0, i);
            made = new StringBuilder(prev);
            repeatN = 0;
            toInsert = 0;

            for(cIdx = i; cIdx < s.length(); cIdx += i){
                curr = (cIdx + i >= s.length())
                        ? s.substring(cIdx, s.length()): s.substring(cIdx, cIdx + i);

                if(prev.equals(curr)){
                    repeatN++;
                    if(cIdx + i >= s.length() && repeatN != 0)
                        made.insert(toInsert, repeatN + 1);
                }else{
                    if(repeatN != 0) made.insert(toInsert, repeatN + 1);
                    repeatN = 0;
                    toInsert = made.length();
                    made.append(curr);
                    prev = curr;
                }
            }
            if(result > made.length()) result = made.length();
        }
        return result;
    }
}
class Main60057{
    public static void main(String[] args) {
        System.out.println(new 문자열압축_60057().solution("aabbaccc"));
        System.out.println(new 문자열압축_60057().solution("ababcdcdababcdcd"));
        System.out.println(new 문자열압축_60057().solution("abcabcdede"));
        System.out.println(new 문자열압축_60057().solution("abcabcabcabcdededededede"));
    }
}