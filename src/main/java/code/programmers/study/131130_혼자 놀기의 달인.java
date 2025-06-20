package code.programmers.study;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/131130
 *
 * 제한)
 *  2 ≤ cards의 길이 ≤ 100
 *  cards의 원소는 cards의 길이 이하인 임의의 자연수입니다.
 *  cards에는 중복되는 원소가 존재하지 않습니다.
 *  cards[i]는 i + 1번 상자에 담긴 카드에 적힌 숫자를 의미합니다.
 *
 * 문제)
 *  1. 2 ~ 100 사이의 자연수를 하나 정해 그 수보다 작거나 같은 숫자 카드를 준비한다.
 *  2. 준비한 카드들을 각각 상자에 1장씩 넣는다.
 *  3. 상자를 무작위로 섞고 일렬로 나열한다
 *  4. 임의의 상자를 하나 선택해 선택한 상자의 적힌 숫자를 확인한다
 *  5. 확인한 카드에 적힌 번호의 상자를 선택해서 확인한다.
 *  6. 열어야 하는 상자가 이미 열려있을 때 까지 반복한다
 *  7. 이렇게 연 상자를 1번 상자 그룹으로 분류하고 나머지 상자를 2번 그룹으로 분류한다.
 *  8. 상자안에 들어있는 카드 번호가 순서대로 담긴 배열 cards가 주어질 때, 이 게임에서 얻을 수 있는 최고 점수를 구하기
 *   - 점수 : 1번 그룹의 상자 수 * 2번 그룹의 상자 수
 *
 * 풀이)
 *  구현
 *  1. 0번째 상자부터 순서대로 상자를 열어 방문 여부를 체크한다.
 *  2. 이미 열려있는 상자를 만나면 해당 상자까지 그룹을 지어 준다.
 *  3. 각 그룹의 크기를 우선순위 큐에 저장한다.
 *  4. 우선순위 큐에서 가장 큰 두 그룹의 크기를 꺼내 곱하여 점수를 계산한다.
 *   - 만약 상자의 그룹 수가 2보다 작을 경우 0을 반환한다.
 *
 */
class Lesson131130 {
    public static void main(String[] args) {
        int[] cards = new int[]{8,6,3,7,2,5,1,4};
        System.out.println(solution(cards)); // 12

        int[] cars1 = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(solution(cars1)); // 1

        int[] cars2 = new int[]{4, 3, 5, 8, 6, 7, 2, 10, 11, 1, 9, 13, 15, 14, 12};
        System.out.println(solution(cars2)); // 20

        int[] cars3 = new int[]{2, 3, 4, 1};
        System.out.println(solution(cars3)); // 0

    }

    public static int solution(int[] cards) {
        int cardsCount = cards.length;
        PriorityQueue<Integer> groupSizes = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 내림차순 정렬을 위한 우선순위 큐
        boolean[] visited = new boolean[cardsCount];
        for (int i = 0; i < cardsCount; i++) {
            if (visited[i]) {
                continue; // 이미 방문한 상자는 건너뛴다
            }
            int current = i;
            Set<Integer> group = new HashSet<>();
            while (!visited[current] && cardsCount > current) {
                visited[current] = true;
                group.add(cards[current]);
                current = cards[current] - 1; // 다음 상자로 이동
            }
            if (!group.isEmpty()) {
                groupSizes.offer(group.size()); // 그룹의 크기를 우선순위 큐에 추가
            }
        }
        if (groupSizes.size() < 2) {
            return 0; // 그룹이 2개 미만이면 점수는 0
        }
        return groupSizes.poll() * groupSizes.poll(); // 가장 큰 두 그룹의 크기를 곱하여 점수 계산
    }
}
