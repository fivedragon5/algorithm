package code.programmers.study;

/**
 * 접근)
 *  1. 거리가 가장 먼 곳부터 우선으로 배달, 수거
 *  2. 배달과 수거 둘중 먼 곳으로 가야하는 곳을 우선으로 거리를 구해준다
 *
 * 풀이)
 *  1. 배달할 재활용 택배 상자를 최대로 싣고 마지막 집부터 확인
 *   1.1 집에 배달할 택배 박스가 있다면 짐을 내리고 그만큼 배열에서 빼줌
 *      - 짐이 0개되는순간 탈출
 *   1.2 집에 수거할 택배 박스가 있다면 수거후 그만큼 배열에서 빼줌
 *      - 수거할 자리가 다 차면 탈출
 *  2. 1번 을 통해 짐을 싣거나 짐을 내리는 활동을 하는 순간 거리를 기억해둔뒤
 *     해당 거리를 이동한 거리에 더해줌(누적)
 *  3. deliveries, pickups 배열들의 모든 원소가 0이 될때까지 반복
 *
 * 제한)
 * 1 ≤ cap ≤ 50
 * 1 ≤ n ≤ 100,000
 * deliveries.length = pickups.length
 * deliveries[i]는 i+1번째 집에 배달할 재활용 택배 상자의 개수를 나타냅니다.
 * pickups[i]는 i+1번째 집에서 수거할 빈 재활용 택배 상자의 개수를 나타냅니다.
 * 0 ≤ deliveries[i] ≤ 50
 * 0 ≤ pickups[i] ≤ 50
 */
class Lesson150369 {
    static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int point = n-1;

        while (point != -1) {
            int box = cap;
            int pickupBox = cap;
            int dis = -1;

            for (int i = point; i >= 0; i--) {
                if (deliveries[i] > box) {
                    deliveries[i] = deliveries[i] - box;
                    box = 0;
                }
                else {
                    box -= deliveries[i];
                    deliveries[i] = 0;
                }
                if (box != cap) dis = Math.max(dis, point);
                if (box == 0) break;
            }

            for (int i = point; i >= 0; i--) {
                if (pickups[i] > pickupBox) {
                    pickups[i] = pickups[i] - pickupBox;
                    pickupBox = 0;
                }
                else {
                    pickupBox -= pickups[i];
                    pickups[i] = 0;
                }
                if (pickupBox != cap) dis = Math.max(dis, point);
                if (pickupBox == 0) break;
            }

            if (dis != -1) {
                answer += (dis + 1) * 2;
            }

            for (int i = point; i >= 0; i--) {
                if (deliveries[i] == 0 && pickups[i] == 0) point--;
                else break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int cap = 2;
        int n = 2;
        int[] deliveries = {0,6};
        int[] pickups = {0,0};
//        int cap = 4;
//        int n = 5;
//        int[] deliveries = {1, 0, 3, 1, 2};
//        int[] pickups = {0, 3, 0, 4, 0};
//        int cap = 2;
//        int n = 7;
//        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
//        int[] pickups = {0, 2, 0, 1, 0, 2, 0};
        System.out.println("===START==="); //
        System.out.println(solution(cap, n, deliveries, pickups));
        System.out.println("===END===");
    }
}
