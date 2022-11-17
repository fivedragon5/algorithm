package algorithm.code.programmers.soon;

import java.util.ArrayList;
import java.util.Arrays;
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
    static ArrayList<LinkedList<Integer>> course = new ArrayList<>();
    static int answer;
    static boolean[] isLightOn;

//    static int solution2(int n, int[][] lighthouse) {
//
//        answer = n/2;
//
//        isLightOn = new boolean[n];
//        LIGHT_HOUSE_COUNT = n;
//
//        for (int i = 0; i < n ; i++) {
//            course.add(new LinkedList<>());
//        }
//
//        for (int i = 0; i < n-1; i++) {
//            course.get(lighthouse[i][0]-1).add(lighthouse[i][1]-1);
//            course.get(lighthouse[i][1]-1).add(lighthouse[i][0]-1);
//        }
//
//        int minLightOnCount = 0;
//
//        for (int i = 0; i < course.size(); i++) {
//            LinkedList<Integer> nodeList = course.get(i);
//            if (nodeList.size() == 1) {
//                int parentNode = nodeList.get(0);
//                if (!isLightOn[parentNode]) {
//                    isLightOn[parentNode] = true;
//                    minLightOnCount++;
//                }
//            }
//        }
//
//        answer = minLightOnCount;
//
//        checkPossible(0);
//
//        System.out.println(minLightOnCount + Arrays.toString(isLightOn));
//
//        return minLightOnCount;
//    }

    //풀이 2)
//    static boolean checkPossible(int start) {
//
//        for (int i = start; i < LIGHT_HOUSE_COUNT; i++) {
//            int falseNodeIndex = i;
//            if (!isLightOn[i]) {
//                for (int light : course.get(i)) {
//                    if (isLightOn[light]) {
//                        falseNodeIndex = -1;
//                        break;
//                    }
//                }
//                if(falseNodeIndex > -1) {
//                    System.out.println("node check : " + falseNodeIndex);
//                    isLightOn[falseNodeIndex] = true;
//                    answer++;
//                    if (checkPossible(falseNodeIndex)) {
//                        System.out.println(Arrays.toString(isLightOn));
//                        return true;
//                    }
//                    answer--;
//                    isLightOn[falseNodeIndex] = false;
//                    for (int nextNode : course.get(falseNodeIndex)) {
//                        isLightOn[nextNode] = true;
//                        answer++;
//                        if (checkPossible(nextNode)) {
//                            System.out.println(Arrays.toString(isLightOn));
//                            return true;
//                        }
//                        answer--;
//                        isLightOn[nextNode] = false;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }

    //풀이1)
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

        int minLightOnCount = 0;

        for (int i = 0; i < n; i++) {
            LinkedList<Integer> nodeList = course.get(i);
            if (nodeList.size() == 1) {
                int parentNode = nodeList.get(0);
                if (!isLightOn[parentNode]) {
                    isLightOn[parentNode] = true;
                    minLightOnCount++;
                }
            }
        }

        for (int i = minLightOnCount; i <= answer; i ++) {
            if (turnOnLignt(i, minLightOnCount, 0)) {
                break;
            }
        }

        return answer;
    }

    //풀이1)
    static boolean turnOnLignt(int turnOnlightCount, int count, int currentLightHouse) {

        if (turnOnlightCount == count) {
            System.out.println(count + "/" + Arrays.toString(isLightOn));
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

    //풀이 1)
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
        int n = 2;
        int[][] lighthouse = {{1,2}};
//        int n = 8;
//        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
//        int n = 10;
//        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
//        int n = 16;
//        int[][] lighthouse = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}};
        System.out.println(solution(n, lighthouse));
        //System.out.println(solution2(n, lighthouse));
        System.out.println("===END===");
    }
}
