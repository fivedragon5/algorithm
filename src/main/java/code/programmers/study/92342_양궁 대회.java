package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/92342
 *
 * 제한)
 *  1 ≤ n ≤ 10
 *  info의 길이 = 11
 *      0 ≤ info의 원소 ≤ n
 *      info의 원소 총합 = n
 *      info의 i번째 원소는 과녁의 10 - i 점을 맞힌 화살 개수입니다. ( i는 0~10 사이의 정수입니다.)
 *      라이언이 우승할 방법이 있는 경우, return 할 정수 배열의 길이는 11입니다.
 *  0 ≤ return할 정수 배열의 원소 ≤ n
 *      return할 정수 배열의 원소 총합 = n (꼭 n발을 다 쏴야 합니다.)
 *      return할 정수 배열의 i번째 원소는 과녁의 10 - i 점을 맞힌 화살 개수입니다. ( i는 0~10 사이의 정수입니다.)
 *      라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
 *          가장 낮은 점수를 맞힌 개수가 같을 경우 계속해서 그다음으로 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
 *          예를 들어, [2,3,1,0,0,0,0,1,3,0,0]과 [2,1,0,2,0,0,0,2,3,0,0]를 비교하면 [2,1,0,2,0,0,0,2,3,0,0]를 return 해야 합니다.
 *          다른 예로, [0,0,2,3,4,1,0,0,0,0,0]과 [9,0,0,0,0,0,0,0,1,0,0]를 비교하면[9,0,0,0,0,0,0,0,1,0,0]를 return 해야 합니다.
 *  라이언이 우승할 방법이 없는 경우, return 할 정수 배열의 길이는 1입니다.
 *      라이언이 어떻게 화살을 쏘든 라이언의 점수가 어피치의 점수보다 낮거나 같으면 [-1]을 return 해야 합니다.
 *
 * 문제)
 *  1. 라이언과 어피치가 과녁을 쏘는 게임을 진행 한다.
 *  2. 어피치가 맞힌 과녁의 점수는 info 배열로 주어진다.
 *  3. 라이언은 n발의 화살을 쏘며, 각 화살은 과녁의 0~10점 중 하나를 맞힌다.
 *  4. 라이언이 어피치보다 높은 점수 차이로 우승할 수 있는 방법을 찾아야 한다.
 *  점수 계산
 *   - K점의 과녁을 어피치가 1발 라이언이 1발 맞혔을 때, 어피치의 점수는 K점, 라이언의 점수는 0점
 *   - K점의 과녁을 어피치가 0발 라이언이 0발 맞혔을 때, 어피치, 라이언 모두 0점
 *   - K점의 과녁을 어피치가 0발 라이언이 1발 맞혔을 때, 어피치의 점수는 0점, 라이언의 점수는 K점
 *  라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우
 *   - 가장 낮은 점수를 더 많이 맞힌 경우를 우선으로 고려
 *
 * 풀이)
 *  1. 10졈 0점까지 순회 하면서 각각 2가지 경우로 나누어 재귀적으로 탐색 진행
 *   - 라이언이 해당 점수를 획득 하는 경우
 *      - 어피치가 해당 점수에 쏜 화살 개수 + 1개를 라이언이 쏜다.
 *   - 라이언이 해당 점수를 획득하지 않는 경우
 *      - 라이언이 해당 점수에 0발을 쏜다.
 *  2. n발을 모두 쏜 경우 OR 0점까지 모두 순회한 경우
 *   - 라이언 획득 점수 - 어피치 획득 점수 -> 최대값 갱신
 *      - 최대 값이 여러가지 경우의 수가 나오는 경우 가장 낮은 점수를 더 많이 맞힌 경우로 갱신 처리 추가
 */

class Lesson92342 {
    public static void main(String[] args) {
//        int n = 5;
//        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
//        System.out.println(solution(n, info)); // 0,2,2,0,1,0,0,0,0,0,0

        int n2 = 1;
        int[] info2 = {1,0,0,0,0,0,0,0,0,0,0};
        System.out.println(solution(n2, info2)); // -1

        int n3 = 9;
        int[] info3 = {0,0,1,2,0,1,1,1,1,1,1};
        System.out.println(solution(n3, info3)); // [1,1,2,0,1,2,2,0,0,0,0]
    }

    private static int[] answer = new int[11]; // 라이언이 맞힌 과녁 점수 배열
    private static int MAX_SCORE = Integer.MIN_VALUE; // 최대 점수 차이

    public static int[] solution(int n, int[] info) {
        back(n, 0, info, new int[11], 0);
        return MAX_SCORE > 0 ? answer : new int[]{-1};
    }

    private static void back(int n, int depth, int[] info, int[] lionScore, int start) {
        if (n == depth || start == 11) {
            // 남은 화살이 있다면 0점에 몰아주기
            if (depth < n) lionScore[10] += (n - depth);
            int diffScore = calculateScore(lionScore, info);
            if (diffScore > MAX_SCORE) {
                MAX_SCORE = diffScore;
                System.arraycopy(lionScore, 0, answer, 0, 11);
            } else if (diffScore == MAX_SCORE && diffScore > 0) {
                // 낮은 점수(오른쪽)부터 비교해서 더 많이 쏜 쪽으로 lion 갱신
                for (int i = 10; i >= 0; i--) {
                    if (lionScore[i] > answer[i]) {
                        System.arraycopy(lionScore, 0, answer, 0, 11);
                        break;
                    } else if (lionScore[i] < answer[i]) {
                        break;
                    }
                }
            }
            if (depth < n) lionScore[10] -= (n - depth); // 다시 원상복구
            return;
        }
        // i점에 화살 쏘기 (어피치보다 1개 더)
        if (n - depth > info[start]) {
            lionScore[start] = info[start] + 1;
            back(n, depth + info[start] + 1, info, lionScore, start + 1);
            lionScore[start] = 0;
        }
        // i점에 화살 안 쏘기
        back(n, depth, info, lionScore, start + 1);
    }

    private static int calculateScore(int[] lionScore, int[] info) {
        int lionTotal = 0;
        int appachTotal = 0;

        for (int i = 0; i < 11; i++) {
            if (lionScore[i] > info[i]) {
                lionTotal += (10 - i);
            } else if (info[i] > 0) {
                appachTotal += (10 - i);
            }
        }
        return lionTotal - appachTotal;
    }
}
