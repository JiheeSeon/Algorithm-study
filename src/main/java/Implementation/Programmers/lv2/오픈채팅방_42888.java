package Implementation.Programmers.lv2;

import java.util.*;

class 오픈채팅방_42888 {
    public String[] solution(String[] record) {
        Map<String, String> pInfoMap = new HashMap<String, String>(); // id-> nickname

        String[] tmp;
        int idx = 0;
        String id, nickname;

        ArrayList<ActionInfo> a = new ArrayList<>();

        for(String s : record){
            tmp = s.split(" ");
            id = tmp[1];
            switch (tmp[0]) {
                case "Enter" -> {
                    nickname = tmp[2];
                    pInfoMap.put(id, nickname);
                    a.add(new ActionInfo(id, tmp[0]));
                    idx++;
                }
                case "Leave" -> {
                    a.add(new ActionInfo(id, tmp[0]));
                    idx++;
                }
                case "Change" -> {
                    nickname = tmp[2];
                    pInfoMap.put(id, nickname);
                }
            }
        }

        String[] answer = new String[idx];
        StringBuilder stb;
        ActionInfo aInfo;
        for(int i = 0; i < answer.length; i++){
            aInfo = a.get(i);
            answer[i] = new StringBuilder(pInfoMap.get(aInfo.id)).append(aInfo.action).toString();
        }

        return answer;
    }
    private class ActionInfo{
        String id;
        String action;

        public ActionInfo(String id, String action){
            this.id = id;
            if(action.equals("Enter"))
                this.action = "님이 들어왔습니다.";
            else if(action.equals("Leave"))
                this.action = "님이 나갔습니다.";
        }
    }
}
class Main42888{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 오픈채팅방_42888().solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
    }
}
