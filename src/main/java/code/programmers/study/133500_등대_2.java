package code.programmers.study;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Lesson133500_2 {
    /**
     * 1. 풀이1)
     *  - 등대에 불을 키는 수를 1 ~ n/2까지 증가시킴
     *  - 각각의 등대에 불을 켜보고 조건을 만족 하는순간 return
     *
     * 2.풀이2)
     *  - 리프노드와 연결된 리프노드의 부모노드중 부모노드 등대에 불을 키는게 리프노드 등대에 불을 키는것보다 무조건 효율이 좋음
     *  - 풀이1)을 적용하기전 킬수있는 등대에 미리 불을 켜두고 범위를 좁혀 풀이1 시작
     *
     * 3. 풀이3)
     * - 1.리프노드 탐색
     * - 2.리프노드의 부모노드를 Set에 담아둠 (담을때마다 불을 켜줌) 
     * - 3.리프노드의 부모노드 Set을 순회하면서 set에있는 부모노드와 연관된 리프노드들을 그래프에서 지움
     * - 더이상 리프노드가 없을때까지 반복
     *
     * - { 그래프 순회 - 리프노드 찾기 리프노드의 부모노드불 켜주고 (리프노드,부모노드) 삭제 } 반복
     *
     * 제한 사항)
     * 등대 : n, 뱃길 : n-1 : 순회 불가능
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

            //리프노드의 카운트를 셈
            count = leafNodeCheck(course);

            answer += count;

            //불을 킨 부모노드가 없을경우 종료
            if (count == 0) {
                break;
            }
            /** 출력용 Start **/
                int index = 0;
                System.out.println("====");
                for (LinkedList a : course) {
                    System.out.println(index++ + ":" + a.toString());
                }
            /** 출력용 End **/

            //그래프를 다시 그려줌
            reDrawCourse();
        }

        return answer;
    }

    static int leafNodeCheck(ArrayList<LinkedList<Integer>> nodeList) {
        int count = 0;

        for (int i = 0; i < LIGHT_HOUSE_COUNT; i++) {
            if (nodeList.get(i).size() == 1 && !isLightOn[i]) {
                int parentNode = nodeList.get(i).get(0);

                //이때 이미 불이 켜저있는 부모노드일경우 카운트X
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
        
        //불을 킨 부모노드와 관련된 자식노드들을 순회하며 지워줌
        for (int node : turnOnLightHouse) {
            for (int childeNode : course.get(node)) {
                int index = course.get(childeNode).indexOf(node);
                course.get(childeNode).remove(index);
            }
            course.get(node).clear();
        }
        //부모노드 Set을 비워줌
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
