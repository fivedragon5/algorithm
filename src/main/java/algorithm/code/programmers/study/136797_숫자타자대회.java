package algorithm.code.programmers.study;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO : 다시 풀어보기 BFS + DP ?
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

class Lesson136797 {
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

        Queue<int[]> queue = new LinkedList<>();

        HashMap<String, Integer> map = new HashMap<>();

        //{0:왼엄지row, 1:왼엄지col, 2:오른엄지row, 3:오른엄지col, 4:step, 5:가중치}
        queue.add(new int[]{1, 0, 1, 2, 0 ,0});
        int[] temp;
        int power = 0;

        for (int i = 0; i < numbers.length(); i++) {

            map.clear();

            char n = numbers.charAt(i);

            while(!queue.isEmpty()) {

                temp = queue.poll();

                if (temp[4] > i) {
                    queue.add(temp);
                    break;
                }

                if (isThumbMovePossible(n, temp)) {
                    //1.가중치가 낮은것으로 교체하는 로직을 만들어보자...
                    //2.사실 왼손, 오른손은 중요하지 않다.
                    power = calcMovePower(temp[0], temp[1], n);
                    queue.add(new int[]{numberKeyBoard.get(n)[0], numberKeyBoard.get(n)[1], temp[2], temp[3], i+1, temp[5] + power});

                    power = calcMovePower(temp[2], temp[3], n);
                    queue.add(new int[]{temp[0], temp[1], numberKeyBoard.get(n)[0], numberKeyBoard.get(n)[1], i+1, temp[5] + power});
                }
                else {
                    temp[4] = i + 1;
                    temp[5] = temp[5] + 1;
                    queue.add(temp);
                }
            }

            int index = 0;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] tempArray = queue.poll();
            }
        }
        int min = 1000000;

        for(int[] a : queue) {
            min = Math.min(min, a[5]);
        }

        return min;
    }

    //0:해당X, 1:왼손엄지, 2:오른손엄지
    static boolean isThumbMovePossible(Character n, int[] thumbPostion) {
        int numberRow = numberKeyBoard.get(n)[0];
        int numberCol = numberKeyBoard.get(n)[1];

        if (numberRow == thumbPostion[0] && numberCol == thumbPostion[1]) {
            return false;
        }
        else if (numberRow == thumbPostion[2] && numberCol == thumbPostion[3]) {
            return false;
        }
        else {
            return true;
        }
    }

    static int calcMovePower(int startRow, int startCol, Character n) {
        int movePower = 0;

        int endRow = numberKeyBoard.get(n)[0];
        int endCol = numberKeyBoard.get(n)[1];

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

    public static void main(String[] args) {
        //String number = "1756"; // 10
        String number = "5123"; // 8
        System.out.println("===START===");
        System.out.println(solution(number));
        System.out.println("===END===");
    }
}
