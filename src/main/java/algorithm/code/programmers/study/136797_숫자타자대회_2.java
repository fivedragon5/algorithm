package algorithm.code.programmers.study;

import java.util.HashMap;

/**
 * 접근)
 * 1. 일반적인 BFS로는 후반부에 있는 테스트 케이스 시간초과
 * 2. 중복되는 값을 줄이면서 BFS를 돌리는것이 키포인트
 *  - 손가락의 좌,우 는 중요하지 않다. 현재 위치하고 있는 2개의 좌표만 있으면 된다.
 *      - ex) (R L : 가중치), (L R : 가중치) 를 같은 값으로 둔다.
 *      -     (Btn1 Btn2 : 가중치) = (Btn2 Btn1 : 가중치)를 같게하여 중복을 제거해준다.
 *  - 버튼의 위치는 같고 가중치가 다른경우 최소값을 구하는 것이기 때문에 가중치가 높은것은 제거해 준다.
 *      - ex) (Btn1 Btn2 : 10), (Btn1 Btn2 : 17) 가중치가 17인 정보는 지워준다.
 * 3. 단순한 구현문제지만 효율성을 고려하니 까다로운 문제였다.
 * 4. 버튼간의 이동거리를 DP로 구현하면 효율성이 더 올라갈꺼 같다. OR 미리 구해서 가중치 2차원 배열로 만드는것도 방법
 *
 * 문제)
 * 숫자로 이루어진 문자열 numbers가 주어졌을 때 최소한의 시간으로 타이핑을 하는
 * 경우의 가중치 합을 return 하도록 solution 함수를 완성해주세요.
 *
 * 가중치)
 * 1: 이동하지 않고 제자리에서 다시 누르는 것
 * 2: 상하좌우로 인접한 숫자로 이동하여 누르는 것
 * 3: 대각선으로 인접한 숫자로 이동하여 누르는 것
 * 같지 않고 인접하지 않은 숫자를 누를 때는 위 규칙에 따라 가중치 합이 최소가 되는 경로를 따릅니다.
 *
 * 조건)
 * 왼손엄지 4, 오른손 엄지 6 시작
 * 1 ≤ numbers의 길이 ≤ 100,000
 * 어떤 숫자를 눌러야 할 차례에 그 숫자 위에 올려져 있는 손가락이 있다면 반드시 그 손가락으로 눌러야 합니다.
 */

class Lesson136797_2 {
    static HashMap<Character, int[]> numberKeyBoard = new HashMap<>();

    static int solution(String numbers) {

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


        HashMap<String, Integer> tempMap = new HashMap<>();
        HashMap<String, Integer> saveMap = new HashMap<>();

        saveMap.put("46", 0);

        int power = 0;
        int tempPower = 0;
        int movePower = 0;

        for (int i = 0; i < numbers.length(); i++) {

            tempMap.clear();

            char n = numbers.charAt(i);

            for (String key : saveMap.keySet()) {
                char start1 = key.charAt(0);
                char start2 = key.charAt(1);
                power = saveMap.get(key);

                if (!isThumbMovePossible(n, start1, start2)) {
                    tempPower = tempMap.getOrDefault(buttonToStr(start1, start2), 100000000);
                    if (tempPower > power + 1) {
                        tempMap.put(buttonToStr(start1, start2), power + 1);
                    }
                }
                else {
                    movePower = calcMovePower(start1, n);
                    tempPower = tempMap.getOrDefault(buttonToStr(start2, n), 100000000);
                    if (tempPower > power + movePower) {
                        tempMap.put(buttonToStr(start2, n), power + movePower);
                    }
                    movePower = calcMovePower(start2, n);
                    tempPower = tempMap.getOrDefault(buttonToStr(start1, n), 100000000);
                    if (tempPower > power + movePower) {
                        tempMap.put(buttonToStr(start1, n), power + movePower);
                    }
                }
            }

            saveMap.clear();

            for (String tempKey : tempMap.keySet()) {
                saveMap.put(tempKey, tempMap.get(tempKey));
            }

        }
        int min = 100000000;

        for(String key : saveMap.keySet()) {
            min = Math.min(min, saveMap.get(key));
        }

        return min;
    }

    static boolean isThumbMovePossible(Character n, Character button1, Character button2) {
        if (n == button1 || n == button2) {
            return false;
        }
        return true;
    }

    static int calcMovePower(Character startButton, Character endButton) {
        int movePower = 0;

        int startRow = numberKeyBoard.get(startButton)[0];
        int startCol = numberKeyBoard.get(startButton)[1];

        int endRow = numberKeyBoard.get(endButton)[0];
        int endCol = numberKeyBoard.get(endButton)[1];

        int rowGap = Math.abs(startRow - endRow);
        int colGap = Math.abs(startCol - endCol);

        while (rowGap != 0 || colGap != 0) {
            if (rowGap > 0 && colGap > 0) {
                rowGap--;
                colGap--;
                movePower += 3;
            }
            else if (rowGap > 0) {
                rowGap--;
                movePower += 2;
            }
            else if (colGap > 0) {
                colGap--;
                movePower += 2;
            }
        }
        return movePower;
    }

    public static String buttonToStr(Character c1, Character c2) {
        if (c1 > c2) {
            return c2 + "" + c1;
        }
        return c1 + "" + c2;
    }

    public static void main(String[] args) {
        //String number = "1756"; // 10
        String number = "5123"; // 8

        System.out.println("===START===");
        System.out.println(solution(number));
        System.out.println("===END===");
    }
}
