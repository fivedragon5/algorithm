package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1339
 * 제한)
 * 1 <= N <= 10
 * 1 <= 알파벳 수 <= 10
 * 1 <= 알파벳 길이 <= 8
 * 서로 다른 문자는 서로 다른 숫자를 나타낸다 0 ~ 9
 *
 * 문제)
 * 1. 주어진 단어의 합의 최댓값 을 출력
 *
 * 풀이)
 *
 */
class Problem1339 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 단어의 갯수

        String[] wordList = new String[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            wordList[i] = st.nextToken();
        }

        // 최대 합 계산
        int result = calculateMaxSum(wordList);
        System.out.println(result);
    }

    public static int calculateMaxSum(String[] words) {
        // 알파벳 별 중요도 를 저장할 Map
        Map<Character, Integer> alphabetWeights = new HashMap<>();

        // 단어를 분석해 각 알파벳의 자리수 중요도 계산
        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char ch = word.charAt(i);
                int power = length - i - 1; // 자릿수 계산
                alphabetWeights.put(ch, alphabetWeights.getOrDefault(ch, 0) + (int) Math.pow(10, power));
            }
        }

        // 기여도가 큰 순서로 정렬
        List<Map.Entry<Character, Integer>> sortedAlphabet = new ArrayList<>(alphabetWeights.entrySet());
        sortedAlphabet.sort((a, b) -> b.getValue() - a.getValue());

        // 높은 숫자(9부터 시작)를 기여도가 높은 알파벳에 할당
        Map<Character, Integer> numberAssignment = new HashMap<>();
        int currentNumber = 9;
        for (Map.Entry<Character, Integer> entry : sortedAlphabet) {
            numberAssignment.put(entry.getKey(), currentNumber);
            currentNumber--;
        }

        // 단어를 숫자로 변환 후 합산
        int totalSum = 0;
        for (String word : words) {
            int num = 0;
            for (char ch : word.toCharArray()) {
                num = num * 10 + numberAssignment.get(ch);
            }
            totalSum += num;
        }

        return totalSum;
    }
}
/*
5 6
1 5
2 2
3 2
4 3
5 1
5 3

5 6
1 1
1 2
2 2
2 3
2 4
2 5
 */
