package String.Programmers.lv1;

class Main17682 {
    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T")); // 1S 2D* 3T
        System.out.println(solution("1S*2T*3S"));
    }

    static int solution(String s) {
        int i = 0;
        int [] score = new int[]{0, 0, 0};

        String[] ss = s.split("(?<=((S|D|T)(#|(\\*))))");

        for (String sss : ss) {
            System.out.println(sss);
        }

        boolean flag1 = false; boolean flag2 = false;

        for (String sss : ss) {
            System.out.println(sss);
            if(sss.contains("#"))  flag1 = true;
            if(sss.contains("*"))  flag2 = true;

            sss = sss.replaceAll("S|D|T|#|(\\*)", "");

            score[i] = (int)Math.pow(Integer.parseInt(sss), i+1);

            if(flag1) score[i] = -score[i];
            if(flag2) score[i] = score[i] * 2;

            i++;

            flag1 = false; flag2 = false;
        }

        for(int scor : score)
            System.out.println(scor);

        return score[0] +score[1]+ score[2];
    }
}