package algorithm.code.study;

import java.util.LinkedList;
import java.util.List;

/**
 * 사다리꼴 넓이 (아랫변 + 윗변) x 높이 / 2
 *
 * 1-1. 입력된 수가 짝수라면 2로 나눕니다.
 * 1-2. 입력된 수가 홀수라면 3을 곱하고 1을 더합니다.
 * 2.결과로 나온 수가 1보다 크다면 1번 작업을 반복합니다.
 *
 * 2 ≤ k ≤ 10,000
 * 1 ≤ ranges의 길이 ≤ 10,000
 * ranges의 원소는 [a, b] 형식이며 0 ≤ a < 200, -200 < b ≤ 0 입니다.
 * 주어진 모든 입력에 대해 정적분의 결과는 227 을 넘지 않습니다.
 * 본 문제는 정답에 실수형이 포함되는 문제입니다. 입출력 예의 소수 부분 .0이 코드 실행 버튼 클릭 후 나타나는 결괏값, 기댓값 표시와 다를 수 있습니다.
 */

class Lesson134239 {
    static double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Double> list = new LinkedList<>();
        double start = (double) k;
        list.add(start);

        while (start > 1) {
            if (start%2 == 0) start = start / 2;
            else start = start * 3 + 1;
            list.add(start);
        }

        int collatzLength = list.size();

        double[] areas = new double[collatzLength-1];

        for (int i = 0; i < collatzLength - 1; i++) {
            areas[i] = (list.get(i) + list.get(i+1)) / 2;
        }

        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = collatzLength + ranges[i][1] - 1;
            answer[i] = calc(a, b, areas);
        }

        return answer;
    }

    static double calc(int start, int end, double[] areas) {
        if (start > end) {
            return -1.0;
        }
        double sum = 0;

        for (int i = start; i < end; i++) {
            sum += areas[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
        //[33.0,31.5,0.0,-1.0]
        System.out.println("===START===");
        System.out.println(solution(k, ranges));
        System.out.println("===END===");
    }
}
