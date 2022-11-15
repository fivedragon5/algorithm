package algorithm.code.programmers.study;

class Lesson67256 {
    static String solution(int[] numbers, String hand) {
        StringBuffer touch = new StringBuffer();

        int lpoint = 10;
        int rpoint = 12;

        String tempStr = "";

        for (int number : numbers) {
            if(number == 1 || number == 4 || number == 7) tempStr = "L";
            else if(number == 3 || number == 6 || number == 9) tempStr = "R";
            else {
                int temp = Math.abs(lpoint-number) - Math.abs(rpoint-number);
                if (temp == 0) {
                    if ("left".equals(hand)) tempStr = "L";
                    else tempStr = "R";
                }
                else if (temp > 0) tempStr = "R";
                else tempStr = "L";
            }
            System.out.println("number : " + number + "/ left : " + lpoint + "/ right : " + rpoint + "///touch : " + tempStr + "temp");
            if("L".equals(tempStr)) lpoint = number;
            else rpoint = number;
            touch.append(tempStr);


        }
        return touch.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        System.out.println("===START===");
        System.out.println(solution(numbers, hand));
        System.out.println("LRLLLRLLRRL");
        System.out.println("===END===");
    }
}
