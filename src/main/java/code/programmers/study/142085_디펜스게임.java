package code.programmers.study;

import java.util.PriorityQueue;

/**
 * 접근)
 * 라운드를 진행시키면서 병사들의 수를 누적시키면서 처음 주어진 병사 수를 넘었을 경우 최대값을 뺴준다.
 * 
 * 풀이)
 * 1. 우선순위 큐를 내림차순으로 선언
 * 2. 라운드를 순회하면서 병사 수를 누적 enemySum
 * 3. enemySum이 n보다 커질경우 무적권 사용
 *  - 무적권 갯수를 확인하여 무적권이 없을경우 게임 종료
 *  - 무적권이 있을경우 우선순위큐에서 poll하여 지나온 라운드중 가장 많은 병사수의 라운드에 무적권 사용
 * 4. 게임이 종료되지 않았을경우 enemy의 길이 return
 *
 * 제한)
 * 1 ≤ n ≤ 1,000,000,000
 * 1 ≤ k ≤ 500,000
 * 1 ≤ enemy의 길이 ≤ 1,000,000
 * 1 ≤ enemy[i] ≤ 1,000,000
 * enemy[i]에는 i + 1 라운드에서 공격해오는 적의 수가 담겨있습니다.
 * 모든 라운드를 막을 수 있는 경우에는 enemy[i]의 길이를 return 해주세요.
 *
 * 설명)
 * n        : 처음가지고 있는 병사 수
 * k        : 무적권
 * enemy[i] : 매 라운드 등장하는 적병사의 수
 * return   : 준호가 몇 라운드까지 막을 수 있는지 (최대)
 */
class Lesson142085 {
    static int solution(int n, int k, int[] enemy) {
        int currentRoundEnemy = 0;
        long enemySum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < enemy.length; i++) {
            currentRoundEnemy = enemy[i];
            enemySum += currentRoundEnemy;
            queue.add(currentRoundEnemy);
            while (enemySum > n) {
                if (k <= 0) {
                    return i;
                }
                else {
                    k--;
                    enemySum -= queue.poll();
                }
            }
        }

        return enemy.length;
    }

    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};

//        int n = 2;
//        int k = 4;
//        int[] enemy = {3, 3, 3, 3};
        System.out.println("===START===");
        System.out.println(solution(n,k,enemy));
        System.out.println("===END===");
    }
}
