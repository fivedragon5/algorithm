package code.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/389481
 *
 * 제한)
 *  1 ≤ n ≤ 10^15
 *  1 ≤ bans의 길이 ≤ 300,000
 *      bans의 원소는 알파벳 소문자로만 이루어진 길이가 1 이상 11 이하인 문자열입니다.
 *      bans의 원소는 중복되지 않습니다.
 *
 * 문제)
 *  1. 알파벳 소문자 11글자 이하로 쓸 수 있는 모든 문자열이 고대의 규칙에 따라 아래와 같이 정렬되어 있다.
 *   - 글자 수가 적은 주문부터 먼저 기록된다.
 *   - 글자 수가 같다면, 사전 순서대로 기록된다.
 *  2. 이 주문서의 악용을 막기 위해 몇몇 주문을 주문서에서 삭제했다.
 *  3. 삭제된 주문서에서 n번째 주문을 찾아야 함
 *  4. 정수 n과 삭제된 주문들을 담은 1차원 배열 bans가 주어질 때 삭제가 완료된 주문서의 n번째 주문을 구하시오.
 *
 * 풀이)
 *  - pow26과 prefix 배열을 초기화하여 문자열의 순위를 계산하는 데 필요한 값을 미리 계산한다.
 *  1. 주어진 bans 배열의 각 문자열을 사전 순으로 정렬된 순위로 변환한다.
 *   - rankOf(String s): 문자열 s의 사전 순서 순위를 계산
 *  2. bans 배열의 각 문자열을 순위로 변환한 후, 오름차순으로 정렬한다.
 *   - bannedRanks 배열에 저장
 *  3. 이진 탐색을 사용하여 삭제된 주문의 순위를 찾는다.
 *   - 이진 탐색을 통해 n번째 주문이 삭제되기 전의 순위를 찾는다.
 *  4. 찾은 순위를 기반으로 n번째 주문을 구한다.
 *   - kthString(long k): k번째 순위에 해당하는 문자열을 생성
 *   - countLessEqual(long[] arr, long x): 배열에서 x 이하의 값의 개수를 세는 함수
 *
 */

class Lesson389481 {

    static long[] pow26 = new long[12];
    static long[] prefix = new long[12];

    public static void main(String[] args) {
//        int n = 30;
//        String[] bans = {"d", "e", "bb", "aa", "ae"}; // ah
//        System.out.println(solution(n, bans));

        int n2 = 7388;
        String[] bans2 = {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"}; // jxk
        System.out.println(solution(n2, bans2));
    }

    public static String solution(long n, String[] bans) {
        init();
        long[] bannedRanks = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            bannedRanks[i] = rankOf(bans[i]);
        }
        Arrays.sort(bannedRanks);

        // 이진 탐색으로 삭제 전 순번 찾기
        long lo = 1, hi = n + bans.length;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            long removed = countLessEqual(bannedRanks, mid);
            if (mid - removed >= n) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return kthString(lo);
    }

    // 1-based 순위 계산
    static long rankOf(String s) {
        long rank = 0;
        int len = s.length();
        rank += prefix[len - 1]; // 더 짧은 문자열 개수
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - 'a';
            rank += c * pow26[len - i - 1];
        }
        return rank + 1;
    }

    // 사전 순 문자열
    static String kthString(long k) {
        int len = 1;
        while (prefix[len] < k) len++;
        k -= prefix[len - 1];
        k--;
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            int c = (int) (k / pow26[len - i - 1]);
            arr[i] = (char) ('a' + c);
            k %= pow26[len - i - 1];
        }
        return new String(arr);
    }

    static long countLessEqual(long[] arr, long x) {
        int idx = Arrays.binarySearch(arr, x);
        if (idx < 0) idx = -idx - 1;
        else {
            while (idx + 1 < arr.length && arr[idx + 1] == x) idx++;
            idx++;
        }
        return idx;
    }

    private static void init() {
        pow26[0] = 1;
        for (int i = 1; i < 12; i++) {
            pow26[i] = pow26[i - 1] * 26;
        }
        prefix[0] = 0;
        for (int i = 1; i < 12; i++) {
            prefix[i] = prefix[i - 1] + pow26[i];
        }
    }
}
