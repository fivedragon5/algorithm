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
 *
 * 문제)
 *
 * 풀이)
 *
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

        for (int[] testCase : testCaseList) {
            test(testCase, switchList);
        }

        printArray(switchList);
    }

    private static void test(int[] testCase, int[] switchList) {
        // 남학생 일 경우
        if (testCase[0] == 1) {
            int number = testCase[1];
            while (number < switchList.length) {
                switchList[number - 1] = toggleSwitch(switchList[number - 1]);
                number += number;
            }
        }
        // 여학생 일 경우
        else {
            int number = testCase[1] - 1; // 배열은 0부터 시작하므로 1을 뺌
            int left = number;
            int right = number;

            // 좌우 대칭을 만족하는 가장 큰 구간 찾기
            while (left > 0 && right < switchList.length - 1 && switchList[left - 1] == switchList[right + 1]) {
                left--;
                right++;
            }

            // 대칭 구간의 스위치 상태를 모두 반전
            for (int i = left; i <= right; i++) {
                switchList[i] = toggleSwitch(switchList[i]);
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

        System.out.println(sb.toString());
    }
}
/*
8
0 1 0 1 0 0 0 1
2
1 3
2 3

1 0 0 0 1 1 0 1
 */
