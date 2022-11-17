package algorithm.code.study;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Lesson133500_2 {
    /**
     * 리프노드는 불을 안켜도 됌
     * { 그래프 순회 - 리프노드 찾기 리프노드의 부모노드불 켜주고 (리프노드,부모노드) 삭제 } 반복
     *
     * 제한 사항)
     * 등대 : n, 뱃길 : n-1
     * 어느 등대에서 출발해도 다른 모든 등대까지 이동 가능
     * 2 <= n <= 100,000
     *
     */

    static ArrayList<LinkedList<Integer>> course = new ArrayList<>();
    static Set<Integer> turnOnLightHouse = new HashSet<>();
    static boolean[] isLightOn;
    static int LIGHT_HOUSE_COUNT;

    //풀이1)
    static int solution(int n, int[][] lighthouse) {

        int answer = 0;

        LIGHT_HOUSE_COUNT = n;
        isLightOn = new boolean[n];

        for (int i = 0; i < n ; i++) {
            course.add(new LinkedList<>());
        }

        for (int i = 0; i < n-1; i++) {
            course.get(lighthouse[i][0]-1).add(lighthouse[i][1]-1);
            course.get(lighthouse[i][1]-1).add(lighthouse[i][0]-1);
        }

        while (true) {
            int count = 0;
            count = leafNodeCheck(course);

            answer += count;
            if (count == 0) {
                break;
            }
            reDrawCourse();
        }

        return answer;
    }

    static int leafNodeCheck(ArrayList<LinkedList<Integer>> nodeList) {
        int count = 0;

        for (int i = 0; i < LIGHT_HOUSE_COUNT; i++) {
            if (nodeList.get(i).size() == 1 && !isLightOn[i]) {
                int parentNode = nodeList.get(i).get(0);
                if (!isLightOn[parentNode]) {
                    isLightOn[parentNode] = true;
                    turnOnLightHouse.add(parentNode);
                    count++;
                }
            }
        }
        return count;
    }

    static void reDrawCourse() {

        for (int node : turnOnLightHouse) {
            for (int childeNode : course.get(node)) {
                int index = course.get(childeNode).indexOf(node);
                course.get(childeNode).remove(index);
            }
            course.get(node).clear();
        }
        turnOnLightHouse.clear();
    }

    public static void main(String[] args) {
        System.out.println("===START===");
//        int n = 2;
//        int[][] lighthouse = {{1,2}};
//        int n = 8;
//        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
//        int n = 10;
//        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
        int n = 16;
        int[][] lighthouse = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}};
        System.out.println(solution(n, lighthouse));
        System.out.println("===END===");
    }
}
