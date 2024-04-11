package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제)
 * 입력된 MVP 등급을 달성하기 위한 최대 누적 과금액을 만원 단위로 출력한다.
 *
 *  풀이)
 *  1. 상민이가 게임을 플레이한 개월 수 N만큼 for문을 돈다.
 *  2. 게임을 시작한 달의 과금액은 전월의 과금액이 없기 때문에 해당개월의 등급중 최대로 과금할 수 있는 금액을 넣어준다. (cash[0])
 *  3. 해당 개월의 등급을 확인하여 과금액(전월 과금액 + 해당월 과금액)이 해당개월의 등급보다 한단계 높은 등급을 달성하기 위해 필요한 과금액보다
 *     1만원 적은 금액이 되도록 계산해 준다. (최대 과금액을 계산 하기 위해)
 *  4. 마지막으로 cash를 순회하면서 과금액들을 더해준다
 *
 *  ※ 'D'등급에 대한 과금액은 최대 다이아몬드 등급 기준액 까지만 과금 할 수 있음
 *
 * 제한)
 * 1 ≤ N ≤ 36
 * 0 < s < g < p < d ≤ 500
 * 한달에 최대 다이아몬드 등금 기준액 까지만 과금 할 수 있음
 * 달성한 등급 아래로는 떨어지지 않음
 *  - 최대 과금액이기때문에 사실 따로 조건을 추가할 필요는 없어보임.
 */
class Problem20413 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] gradeTable = new int[4];

        for (int i = 0; i < 4; i++) {
            gradeTable[i] = Integer.parseInt(st.nextToken(" "));
        }

        st = new StringTokenizer(br.readLine());
        String grades = st.nextToken();

        int[] cash = new int[N];

        char grade = grades.charAt(0);

        if (grade == 'B') {
            cash[0] = gradeTable[0] - 1;
        }
        else if (grade == 'S') {
            cash[0] = gradeTable[1] - 1;
        }
        else if (grade == 'G') {
            cash[0] = gradeTable[2] - 1;
        }
        else if (grade == 'P') {
            cash[0] = gradeTable[3] - 1;
        }
        else if (grade == 'D') {
            cash[0] = gradeTable[3];
        }
        for (int i = 1; i < N; i++) {
            grade = grades.charAt(i);

            if (grade == 'B') {
                cash[i] = gradeTable[0] - cash[i-1] - 1;
            }
            else if (grade == 'S') {
                cash[i] = gradeTable[1] - cash[i-1] - 1;
            }
            else if (grade == 'G') {
                cash[i] = gradeTable[2] - cash[i-1] - 1;
            }
            else if (grade == 'P') {
                cash[i] = gradeTable[3] - cash[i-1] - 1;
            }
            else if (grade == 'D') {
                cash[i] = gradeTable[3];
            }
        }

        int total = 0;

        for (int money : cash) {
            total += money;
        }

        System.out.println(total);
    }
}
/*
3
30 60 90 150
BSG

118

10
257 269 367 500
BSGGGGPPDD

2499
 */

