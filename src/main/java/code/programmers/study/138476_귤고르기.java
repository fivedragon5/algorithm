package code.programmers.study;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 풀이)
 * 1. 귤 사이즈 int형 배열(길이:10000001)선언 후 주어진 tangerine을 순회하면서 귤의 사이즈 갯수를 세어준다.
 * 2. 귤 사이즈 int형 배열을 순회하면서 귤의 갯수를 최대 힙에 넣어준다.
 *  - 힙에 넣을때 귤의 갯수가0개인 귤을 넣지 않기위해 1개이상의 귤만 넣을 수 있도록 index를 set에 담아둔다.
 * 3. 힙을 순회하면서 갯수가 가장 많은 사이즈의 귤을 빼면서 k를 넘는지 확인
 *  - 넘을경우 해당 index+1을 return
 *  - 넘지않을경우 넘을때까지 순회
 *
 * 제한)
 * 1 ≤ k ≤ tangerine.lentgh ≤ 100,000
 * 1 ≤ tangerine ≤ 10,000,000
 */
class Lesson138476 {
    static int solution(int k, int[] tangerine) {
        int[] mandarins = new int[10000001];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2- o1);
        Set<Integer> mandarinSizes = new HashSet<>();

        for (int i = 0; i < tangerine.length; i++) {
            int mandarinSize = tangerine[i];
            mandarins[mandarinSize] = mandarins[mandarinSize] + 1;
            mandarinSizes.add(mandarinSize);
        }

        for (int sizes : mandarinSizes) {
            queue.add(mandarins[sizes]);
        }

        int count = 0;
        int answer = 0;

        while (count < k) {
            count += queue.poll();
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
//        int k = 6;
//        int[] tangerine = {1,3,2,5,4,5,2,3};
//        int k = 4;
//        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
//        int k = 2;
//        int[] tangerine = {1, 1, 1, 1, 2, 2, 2, 3};
        int k = 3;
        int[] tangerine = {1,2,2};
        System.out.println("===START===");
        System.out.println(solution(k, tangerine));
        System.out.println("===END===");
    }
}
