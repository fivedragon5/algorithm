package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1244
 * 제한)
 * 1 <= 스위치 수 <= 100
 * 1 <= 학생 수 <= 100
 *
 * 문제)
 *  스위치 상태
 *   - 1 : ON
 *   - 2 : OFF
 *   1. 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다
 *   2. 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서,
 *   그 구간에 속한 스위치의 상태를 모두 바꾼다. 이때 구간에 속한 스위치 개수는 항상 홀수가 된다.
 *
 * 풀이)
 *  - 구현
 *  1. 성별에 따라 분기
 *  2. 여학생일 경우, ArrayIndexOutOfBounds 주의
 *   - 여학생의 위치 기준으로 양 옆의 스위치를 변경해준다.
 */
class Problem1244 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int switchCount = Integer.parseInt(st.nextToken()); // 스위치 수

        int[] switchList = new int[switchCount];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switchCount; i++) {
            switchList[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int caseCount = Integer.parseInt(st.nextToken());

        int[][] testCaseList = new int[caseCount][2];

        for (int i = 0; i < caseCount; i++) {
            st = new StringTokenizer(br.readLine());
            testCaseList[i][0] = Integer.parseInt(st.nextToken());
            testCaseList[i][1] = Integer.parseInt(st.nextToken());
        }

        // 스위치 토글
        for (int[] testCase : testCaseList) {
            test(testCase, switchList);
        }

        printArray(switchList);
    }

    private static void test(int[] testCase, int[] switchList) {
        int position = testCase[1];
        // 남학생
        if (testCase[0] == 1) {
            for (int i = position; i <= switchList.length; i += position) {
                switchList[i - 1] = toggleSwitch(switchList[i - 1]);
            }
        }
        // 여학생
        else {
            switchList[position - 1] = toggleSwitch(switchList[position - 1]);
            for (int i = 1; i <= switchList.length / 2; i++) {
                if (position - 1 - i < 0 || position - 1 + i > switchList.length - 1) {
                    break;
                }
                if (switchList[position - 1 - i] == switchList[position - 1 + i]) {
                    switchList[position - 1 - i] = toggleSwitch(switchList[position - 1 - i]);
                    switchList[position - 1 + i] = toggleSwitch(switchList[position - 1 + i]);
                } else {
                    break;
                }
            }
        }
    }

    private static int toggleSwitch(int number) {
        if (number == 1) return 0;
        return 1;
    }

    private static void printArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        int lineLimit = 20; // 한 줄에 20자 제한
        int currentLineLength = 0;

        for (int i = 0; i < array.length; i++) {
            String numberStr = String.valueOf(array[i]);

            // 한 칸 띄우기 포함하여 현재 줄의 길이를 확인
            if (currentLineLength + numberStr.length() + 1 > lineLimit) {
                sb.append("\n"); // 줄 바꾸기
                currentLineLength = 0; // 현재 줄 길이 초기화
            }

            if (currentLineLength > 0) {
                sb.append(" "); // 줄 시작이 아니면 공백 추가
                currentLineLength++;
            }

            sb.append(numberStr);
            currentLineLength += numberStr.length(); // 현재 줄 길이에 추가
        }

        System.out.println(sb);
    }
}
/*
8
0 1 0 1 0 0 0 1
2
1 3
2 3

1 0 0 0 1 1 0 1

4
1 0 0 1
3
1 3
2 2
1 1

1
1
2
1 1
2 1

8
0 1 0 1 0 0 0 1
5
1 3
2 3
2 5
1 6
1 7
 */
