package algorithm.code.programmers;

import java.util.ArrayList;
import java.util.Arrays;

class Lesson133500 {
    /**
     * 리프노드는 불을 안켜도 됌
     *
     * 제한 사항)
     * 등대 : n, 뱃길 : n-1
     * 어느 등대에서 출발해도 다른 모든 등대까지 이동 가능
     * 2 <= n <= 100,000
     *
     */

    static int LIGHT_HOUSE_COUNT;
    static ArrayList<ArrayList<Integer>> course = new ArrayList<>();
    static int answer;

    static int solution(int n, int[][] lighthouse) {

        boolean[] isLightOn = new boolean[n];
        LIGHT_HOUSE_COUNT = n;

        for (int i = 0; i < n ; i++) {
            course.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            course.get(lighthouse[i][0]-1).add(lighthouse[i][1]-1);
            course.get(lighthouse[i][1]-1).add(lighthouse[i][0]-1);
        }

        int leafNodeCount = 0;

        for (ArrayList c : course) {
            if (c.size() > 1) {
                leafNodeCount++;
            }
        }

        for (int i = 1 ; i <= n - leafNodeCount ; i ++) {
            if (turnOnLignt(i, 0, isLightOn)) {
                break;
            }
        }

        return answer;
    }

    static boolean turnOnLignt(int turnOnlightCount, int count, boolean[] isLightOn) {

        if (turnOnlightCount <= count) {
            System.out.println(Arrays.toString(isLightOn));
            if (isPossible(isLightOn)) {
                answer = Math.max(answer, count);
                return true;
            }
        }

        for (int i = 0; i < LIGHT_HOUSE_COUNT; i++) {
            //반복이 많이 돌아가니 다시 짜보자.
            if(!isLightOn[i] && course.get(i).size() > 1) {
                isLightOn[i] = true;
                turnOnLignt(turnOnlightCount, count+1, isLightOn);
                isLightOn[i] = false;
            }
        }

        return false;
    }

    //가능하다면 종료시키자.
    static boolean isPossible(boolean[] isLightOn) {

        for (int i = 0; i < LIGHT_HOUSE_COUNT; i++) {
            boolean flag = false;
            if (!isLightOn[i]) {
                for (int light : course.get(i)) {
                    if (isLightOn[light]) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("===START===");
//        int n = 8;
//        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        int n = 10;
        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
        System.out.println(solution(n, lighthouse));
        System.out.println("===END===");
    }
}
