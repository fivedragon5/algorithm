package algorithm.code.programmers.study;

import java.util.Arrays;

/**
 * 풀이)
 * 1.제한사항 e의 범위를 참고하여 억억단에 등장횟수를 저장하는 배열 선언 int[5000001]후
 *   이중 for문으로 등장횟수 입력
 *  - e를 넘지 않기 때문에 e를 최대 범위, 길이로 잡아도 무방
 *  - 매번 등장횟수를 체크 할 수 없기때문에 미리 map을 생성
 * 2. starts의 범위가 1 ~ e이기 때문에 범위를 줄이기 위해 1부터 e까지의 2차원 int형 배열 선언 원소{등장횟수, 숫자}
 * 3. 등장횟수를 기준으로 내림차순 정렬 및 등장횟수가 동일할 경우(낮은숫자 출력 조건) 숫자 오름차순으로 정렬
 * 4. 주어진 starts를 순회하며 각 케이스마다 정렬한 범위를 탐색하며 조건에 부합하는 숫자를 정답 배열에 입력
 * 
 * 제한)
 * 1 ≤ e ≤ 5,000,000
 * 1 ≤ starts.length ≤ min {e,100,000}
 * 1 ≤ starts의 원소 ≤ e
 * starts에는 중복되는 원소가 존재하지 않습니다.
 */
class Lesson138475 {

    static int MAX;

    static int[] solution(int e, int[] starts) {
        MAX = e;
        int[] answer = new int[starts.length];

        int[] map = new int[MAX + 1];
        map[1] = -1;
        int number = 0;

        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= MAX; j++) {
                number = i*j;
                if (number > MAX) break;
                map[number]++;
            }
        }

        int[][] list = new int[e+1][2];

        for (int i = 1; i <= e; i++) {
            list[i][0] = map[i];
            list[i][1] = i;
        }

        Arrays.sort(list, (list1, list2) -> {
            if (list1[0] == list2[0]) {
                return list1[1] - list2[1];
            }
            return list2[0] - list1[0];
        });

        int index = 0;
        for (int start : starts) {
            if (start == e) {
                answer[index++] = start;
                continue;
            }
            for (int i = 0; i < e; i++) {
                if (list[i][1] >= start && list[i][1] <= e) {
                    answer[index++] = list[i][1];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(answer)); //출력용
        return answer;
    }

    public static void main(String[] args) {
        int e = 8;
        int[] starts = {1,3,7};
        System.out.println("===START===");
        System.out.println(solution(e, starts));
        System.out.println("===END===");
    }
}
