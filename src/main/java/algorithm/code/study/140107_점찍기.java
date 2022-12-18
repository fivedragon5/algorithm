package algorithm.code.study;

import java.util.ArrayList;

/**
 * 접근)
 * 1. 만족하는 점들을 탐색할때 시간을 줄여야 하는 문제
 *  - 선형 탐색 : 시간초과 solution()
 *  - 이분탐색 사용 solution2()
 * 2. 제곱값 처리를 위한 자료형 확인
 *
 * 풀이)
 * 1. 좌표로 가능한 수들을 List에 저장 : numberList
 *  - 0부터 d까지 k의 배수
 * 2. numberList를 순회 하면서 좌표값중 최대 거리를 넘지않는 좌표의 최대값인 index를 탐색
 * 3. 탐색한 index + 1 만큼 정답에 더해준다.
 *
 * 제한)
 * 1 ≤ k ≤ 1,000,000
 * 1 ≤ d ≤ 1,000,000
 */
class Lesson140107 {
    static long solution2(int k, int d) {
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i = 0; i <= d; i += k) {
            numberList.add(i);
        }
        long maxLength = (long) d * d;
        long count = 0;
        int size = numberList.size();

        for (int i = 0; i < numberList.size(); i++) {
            long pow1 = (long) Math.pow(numberList.get(i),2);
            int low = 0;
            int high = size - 1;
            int mid = 0;
            while (low <= high) {
                mid = (low + high) / 2;
                int number = numberList.get(mid);
                long pow2 = (long) number * number;
                if (maxLength == pow1 + pow2) {
                    break;
                }
                else if (maxLength > pow1 + pow2) {
                    low = mid + 1;
                }
                else if (maxLength < pow1 + pow2) {
                    high = mid - 1;
                }
            }
            count += (low + high) / 2 + 1;
        }
        return count;
    }

    static long solution(int k, int d) {
        ArrayList<Integer> numberList = new ArrayList<>();

        for (int i = 0; i <= d; i += k) {
            numberList.add(i);
        }
        long maxLength = (long) d * d;

        long count = 0;

        for (int i = 0; i < numberList.size(); i++) {
            long pow1 = (long) Math.pow(numberList.get(i),2);
            for (int j = i; j < numberList.size(); j++) {
                long pow2 = (long) Math.pow(numberList.get(j),2);
                if (maxLength >= pow1 + pow2) {
                    if (pow1 == pow2) count++;
                    else count += 2;
                }
                else {
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
//        int k = 2;
//        int d = 4;
        int k = 1;
        int d = 5;
        System.out.println("===START===");
        System.out.println(solution2(k, d));
        System.out.println("===END===");
    }
}
