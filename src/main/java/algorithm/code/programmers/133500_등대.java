package algorithm.code.programmers;

import java.util.LinkedList;

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
    static LinkedList<LinkedList<Integer>> course = new LinkedList<>();
    static int answer;
    static boolean[] isLightOn;

    static int solution(int n, int[][] lighthouse) {

        answer = n/2;

        isLightOn = new boolean[n];
        LIGHT_HOUSE_COUNT = n;

        for (int i = 0; i < n ; i++) {
            course.add(new LinkedList<>());
        }

        for (int i = 0; i < n-1; i++) {
            course.get(lighthouse[i][0]-1).add(lighthouse[i][1]-1);
            course.get(lighthouse[i][1]-1).add(lighthouse[i][0]-1);
        }

        //최소 등의 갯수 1 <= onLightHouse <= 등대 수 - leafNode
        for (int i = 1; i <= answer; i ++) {
            if (turnOnLignt(i, 0, 0)) {
                break;
            }
        }

        return answer;
    }

    static boolean turnOnLignt(int turnOnlightCount, int count, int currentLightHouse) {

        if (turnOnlightCount == count) {
            //System.out.println(count + "/" + Arrays.toString(isLightOn));
            if (isPossible()) {
                answer = count;
                return true;
            }
            return false;
        }

        for (int i = currentLightHouse; i < LIGHT_HOUSE_COUNT; i++) {
            if(!isLightOn[i] && course.get(i).size() > 1) {
                isLightOn[i] = true;
                if (turnOnLignt(turnOnlightCount, count+1, i + 1)) {
                    return true;
                }
                isLightOn[i] = false;
            }
        }
        return false;
    }

    //가능하다면 종료시키자.
    static boolean isPossible() {

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
//        int n = 2;
//        int[][] lighthouse = {{1,2}};
//        int n = 8;
//        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        int n = 10;
        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
//        int n = 16;
//        int[][] lighthouse = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}};
        System.out.println(solution(n, lighthouse));
        System.out.println("===END===");
    }
}
