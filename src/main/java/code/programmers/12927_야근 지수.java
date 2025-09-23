package code.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12927
 *
 * 제한)
 *  1 <= works.length <= 20,000
 *  1 <= works[i] <= 50,000
 *  1 <= n <= 1,000,000
 *
 * 문제)
 *  1. 야근 피로도 = 남은 일의 제곱의 합
 *  2. n시간 동안 일을 하여 야근 피로도를 최소화
 *  3. 남은 일의 양이 담긴 배열 works와 n이 매개변수로 주어질 때, 야근 피로도를 최소화한 값을 return
 *
 * 풀이)
 *  1. 총 일의 양이 n보다 작거나 같으면 0 을 return
 *  2. works 배열을 오름차순 정렬
 *  3. 가장 큰 값부터 n을 줄여나감
 *   - 한번에 줄일 수 있는 경우: 다음 값과 같아질 때까지 줄임
 *   - 한번에 줄일 수 없는 경우: n을 남은 일의 개수로 나눈 몫만큼 줄이고, 나머지는 하나씩 분배해서 줄임
 *  4. 만약 다음 값과 같아지면 그 다음 값과 비교하며 줄여나감
 *  5. n이 모두 소진되면 남은 일의 제곱의 합을 구해 return
 *
 */

class Lesson12927 {
    public static void main(String[] args) {
        int n = 4;
        int[] works = {4, 3, 3};
        System.out.println(solution(n, works)); // 12

        n = 1;
        works = new int[]{2, 1, 2};
        System.out.println(solution(n, works)); // 6

        n = 3;
        works = new int[]{1, 1};
        System.out.println(solution(n, works)); // 0
    }

    public static long solution(int n, int[] works) {
        long totalWork = 0;
        for (int work : works) {
            totalWork += work;
        }
        if (totalWork <= n) {
            return 0;
        }

        Arrays.sort(works);
        int len = works.length;
        int idx = len - 1;

        while (n > 0) {
            int currentWork = works[idx];
            int nextWork = (idx > 0) ? works[idx - 1] : 0;
            int diff = currentWork - nextWork;
            int count = len - idx;

            // 한번에 줄일 수 있는 경우
            if (diff * count <= n) {
                for (int i = idx; i < len; i++) {
                    works[i] = nextWork;
                }
                n -= diff * count;
                idx--;
            }
            // 한번에 줄일 수 없는 경우 -> 분배해서 줄임
            else {
                int reduce = n / count;
                int remainder = n % count;
                for (int i = idx; i < len; i++) {
                    works[i] -= reduce;
                    if (remainder > 0) {
                        works[i]--;
                        remainder--;
                    }
                }
                n = 0;
            }
        }

        long answer = 0;
        for (int work : works) {
            answer += (long) work * work;
        }
        return answer;
    }
}
