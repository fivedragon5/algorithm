package algorithm.code.programmers.study;

import java.util.Stack;

/**
 * 풀이)
 * 1. 보조 컨베이어 벨트 Stack를 만든다. (subConveyorBelt)
 * 2. 주문 순서를 확인을 위한 변수 point를 만든다
 *  - order index 위치 확인용
 * 3. 택배상자를 순서대로 받으면서 order[point]를 확인해준다
 *  - order[point] == i 일때 다음 주문순서로 이동 point++
 *      - 동시에 현재까지 저장해둔 보조 컨베이어 벨트를 확인.
 *  - order[point] != i 일때 보조 컨베이어 벨트에 현재 상자 i를 add 해준다.
 * 4. for문 종료시 현재 가리키고 있는 point를 반환
 *
 * 제한)
 * 1 ≤ order의 길이 ≤ 1,000,000
 * order는 1이상 order의 길이 이하의 모든 정수가 한번씩 등장합니다.
 * order[i]는 기존의 컨테이너 벨트에 order[i]번째 상자를 i+1번째로 트럭에 실어야 함을 의미합니다.
 */
class Lesson131704 {

    static Stack<Integer> subConveyorBelt;

    static int solution(int[] order) {
        subConveyorBelt = new Stack<>();
        int point = 0;

        for (int i = 1; i <= order.length; i++) {
            if (order[point] == i) {
                point++;
                if (!subConveyorBelt.isEmpty() && subConveyorBelt.peek() == order[point]) {
                    point = checkSubConveyorBelt(point, order);
                }
            }
            else {
                subConveyorBelt.add(i);
            }
        }

        return point;
    }

    static int checkSubConveyorBelt(int point, int[] order) {
        while (!subConveyorBelt.isEmpty()) {
            if (order[point] == subConveyorBelt.peek()) {
                point++;
                subConveyorBelt.pop();
            }
            else {
                break;
            }
        }
        return point;
    }

    public static void main(String[] args) {
        int[] order = {4, 3, 1, 2, 5}; //2
        int[] order2 = {5, 4, 3, 2, 1}; //5
        System.out.println("===START===");
        System.out.println(solution(order2));
        System.out.println("===END===");
    }
}
