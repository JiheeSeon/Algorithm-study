package Implementation.Programmers.lv1;

class Main67256 {
    public String solution(int[] numbers, String hand) {
        StringBuilder stb = new StringBuilder();

        int leftPos = 10;
        int rightPos = 12;

        int leftDist, rightDist;

        String defaultString = hand.equals("left") ? "L" : "R";

        int leftAdjust, rightAdjust;

        for(int i : numbers){
            if (i == 0) i = 11;
            if (i % 3 == 1){
                leftPos = i;
                stb.append("L");
            }
            else if (i % 3 == 0){
                rightPos = i;
                stb.append("R");
            }
            else{
                leftAdjust = i % 3 > leftPos % 3 ? 1 : -1;
                rightAdjust = i % 3 > rightPos % 3 ? -1 : 1;

                leftDist = (i % 3 == leftPos % 3)
                        ? Math.abs(i - leftPos) / 3
                        : 1 + Math.abs(i - (leftPos + leftAdjust)) / 3;

                rightDist = (i % 3 == rightPos % 3)
                        ? Math.abs(i - rightPos) / 3
                        : 1 + Math.abs(i - (rightPos + rightAdjust)) / 3;

                if(leftDist > rightDist){
                    stb.append("R");
                    rightPos = i;
                } else if (leftDist < rightDist){
                    stb.append("L");
                    leftPos = i;
                } else{
                    stb.append(defaultString);
                    if (defaultString.equals("L"))
                        leftPos = i;
                    else
                        rightPos = i;
                }
            }
        }
        return stb.toString();
    }
}