package algorithm.code.programmers.study;

import java.util.HashMap;

class Lesson136797 {
    /**
     * 왼손엄지 4, 오른손 엄지 6 시작
     *
     * 가중치)
     * 1: 이동하지 않고 제자리에서 다시 누르는 것
     * 2: 상하좌우로 인접한 숫자로 이동하여 누르는 것
     * 3: 대각선으로 인접한 숫자로 이동하여 누르는 것
     * 같지 않고 인접하지 않은 숫자를 누를 때는 위 규칙에 따라 가중치 합이 최소가 되는 경로를 따릅니다.
     *
     * 조건)
     * 1 ≤ numbers의 길이 ≤ 100,000
     */
    static int solution(String number) {
        int answer = 0;

        //keyboard set
        HashMap<Character, int[]> numberKeyBoard = new HashMap<>();
        numberKeyBoard.put('1', new int[]{0,0});
        numberKeyBoard.put('2', new int[]{0,1});
        numberKeyBoard.put('3', new int[]{0,2});
        numberKeyBoard.put('4', new int[]{1,0});
        numberKeyBoard.put('5', new int[]{1,1});
        numberKeyBoard.put('6', new int[]{1,2});
        numberKeyBoard.put('7', new int[]{2,0});
        numberKeyBoard.put('8', new int[]{2,1});
        numberKeyBoard.put('9', new int[]{2,2});
        numberKeyBoard.put('*', new int[]{3,0});
        numberKeyBoard.put('0', new int[]{3,1});
        numberKeyBoard.put('#', new int[]{3,2});

        int[] left_x = {1,0};
        int[] right_x = {1,2};

        char first = number.charAt(0);

        for (int i = 1; i < number.length(); i++) {
            char n = number.charAt(i);
            //n을 왼손으로 눌렸을경우
            //n을 오른손으로 눌렀을경우
        }

        return answer;
    }

    public static void main(String[] args) {
        String number = "1756"; // 10
        //String number = "5123"; // 8
        System.out.println("===START===");
        System.out.println(solution(number));
        System.out.println("===END===");
    }
}
