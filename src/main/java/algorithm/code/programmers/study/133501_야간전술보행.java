package algorithm.code.programmers.study;

/**
 * 풀이)
 * 첫번째 방법) scope[n][i] 순으로 오름차순, scope 정렬할때 같이 오름차순 후 비교
 * 두번째 방법) scope를 순회하며 해당구간의 감시만 확인 후 감시에 걸릴경우 해당 값의 최소값으로 갱신
 * 
 * 설명)
 * 두번째 방법 채택
 * 감시 확인 조건 : (근무시간 + 휴식시간) = totalWork
 * 화랑이가 이동한 거리와 시간이 같음
 * 따라서 화랑이가 이동한 거리(d) (d % totalWork) 의 값이 근무시간(time[n][0])보다 작고 0이 아닐경우 감시에 걸린것
 * 감시에 걸릴때마다 Math.min(answer, d)로 갱신
 * 최초 answer값은 문제의 최다값인 paramter distance로 잡고 시작
 *
 * 10 ≤ distance ≤ 10,000,000
 * 1 ≤ scope의 길이, times의 길이 ≤ 1,000
 * scope[i]는 i+1번째 경비병이 감시하는 구간입니다.
 * scope[i]를 [a, b]라고 했을 때, (a ≠ b)입니다.
 * ** scope[i]는 정렬되어 있지 않을 수 있습니다(예시 2번 참조). ** * 중요
 * 경비병의 감시구간은 서로 겹치지 않습니다.
 * 1 ≤ scope의 원소 ≤ distance
 * 1 ≤ times의 원소 ≤ 10
 * times[i]는 i+1번째 경비병의 [근무 시간, 휴식 시간]입니다.
 */

class Lesson133501 {
    static int solution(int distance, int[][] scope, int[][] times) {
        int answer = distance;

        for (int i = 0; i < scope.length; i++) {
            int workTime = times[i][0];
            int totalWork = workTime + times[i][1];
            int start = Math.min(scope[i][0], scope[i][1]);
            int end = Math.max(scope[i][0], scope[i][1]);

            for (int j = start; j <= end; j++) {
                int check = j % totalWork;
                if (check != 0 && check <= workTime) {
                    answer = Math.min(answer, j);
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
//        int distance = 10;
//        int[][] scope = {{3, 4}, {5, 8}};
//        int[][] times = {{2, 5}, {4, 3}};

//        int distance = 12;
//        int[][] scope = {{7, 8}, {4, 6}, {11, 10}};
//        int[][] times = {{2, 2}, {2, 4}, {3, 3}};

//        int distance = 14;
//        int[][] scope = {{7, 10}};
//        int[][] times = {{2, 2}};

        int distance = 14;
        int[][] scope = {{11,10},{3,4},{12,13}};
        int[][] times = {{2, 2},{2, 2},{2, 2}};

        System.out.println("===START===");
        System.out.println(solution(distance, scope, times));
        System.out.println("===END===");
    }
}
