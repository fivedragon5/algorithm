package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340212
 *
 * 제한)
 *  1 ≤ diffs의 길이 = times의 길이 = n ≤ 300,000
 *      diffs[i]는 i번째 퍼즐의 난이도, times[i]는 i번째 퍼즐의 소요 시간입니다.
 *      diffs[0] = 1
 *      1 ≤ diffs[i] ≤ 100,000
 *      1 ≤ times[i] ≤ 10,000
 *  1 ≤ limit ≤ 10^15
 *      제한 시간 내에 퍼즐을 모두 해결할 수 있는 경우만 입력으로 주어집니다.
 *
 * 문제)
 *  1. 퍼즐을 푸는데 필요한 숙련도 diff, 퍼즐을 푸는데 걸리는 시간 times가 주어진다.
 *  2. 숙련도를 level이라고 했을때 diff <= level 이라면 한번에 퍼즐을 풀 수 있다.
 *   - diff > level 일 경우, (이전 퍼즐을 푸는데 걸린 시간 + 현재 퍼즐을 푸는데 걸리는 시간) * diff - level + 현재 퍼즐을 푸는데 걸리는 시간
 *  3. 퍼즐을 주어진 limit 시간 안에 풀 경우 가능한 숙련도의 최소값 구하기
 *
 * 풀이)
 *   이분탐색
 *      숙련도를 1부터 하나씩 탐색한다고 가정했을 때, 최악의 경우 diff최대길이 300,000 * 100,000
 *          -> 시간초과 예상
 *      1. 숙련도 최소 1 ~ 최대 100,000 으로 이분탐색 진행
 */
class Lesson340212 {
    public static void main(String[] args) {
        int[] diff = new int[]{1,5,3};
        int[] times = new int[]{2,4,7};
        int limit = 30;
        System.out.println(solution(diff, times, limit));

        int[] diff2 = new int[]{1, 4, 4, 2};
        int[] times2 = new int[]{6, 3, 8, 2};
        int limit2 = 59;
        System.out.println(solution(diff2, times2, limit2));
    }

    public static int solution(int[] diffs, int[] times, long limit) {
        int min = 100000;
        int max = 1;
        int currentLevel = 0;
        while (max <= min) {
            currentLevel = (min + max) / 2;
            if (limit < getTime(diffs, times, currentLevel)) {
                max = currentLevel + 1;
            }
            else {
                min = currentLevel - 1;
            }
        }
        return max;
    }

    private static long getTime(int[] diff, int[] times, int level) {
        long totalTime = times[0];
        if (diff[0] > level) {
            totalTime += (long) (diff[0] - level) * times[0];
        }
        for (int i = 1; i < diff.length; i++) {
            totalTime += times[i];
            if (level < diff[i]) {
                totalTime += (long) (times[i-1] + times[i]) * (diff[i] - level);
            }
        }
        return totalTime;
    }
}
