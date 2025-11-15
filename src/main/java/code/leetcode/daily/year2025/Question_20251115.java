package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/description/?envType=daily-question&envId=2025-11-15
 *
 * 제한)
 *  1 <= s.length <= 4 * 10^4
 *  s consists only of characters '0' and '1'
 *
 * 문제)
 *  1. 이진 문자열 s가 주어진다.
 *  2. 문자열에서 1의 개수가 0의 개수의 제곱보다 크거나 같으면, 그 문자열은 dominant ones를 가진다고 한다.
 *  3. 조건을 만족하는 부문 문자열의 개수를 반환하기
 *   - 예시)
 *    - s = "00011"인 경우, "00011", "0011", "01", "1", "1" 총 5개의 부문 문자열이 조건을 만족한다.
 *
 * 풀이)
 *  단순 O(N^2)으로 모든 부문 문자열을 검사할 경우 시간 초과
 *
 *  ones >= zero^2
 *   - zeros가 많아질수록 오른쪽 값이 급격히 커지기 떄문에,
 *     0의 개수가 큰 substring은 조건을 만족하기 어렵다
 *   - 0이 몇개 없는 subString은 많이 있음 -> BFS
 *   - 0이 많은 subString은 조건을 만족할 수 없음 -> SKIP
 *   - 0의 개수가 k개인 substring에 대해,
 *   1의 개수가 k^2개 이상인 substring을 찾는 문제로 치환 가능
 *   - 0의 개수가 k개인 substring을 찾기 위해, 0의 인덱스를 미리 저장
 *   - 1의 개수를 빠르게 구하기 위해, 1의 누적합 배열 생성
 *   - 0의 개수가 k개인 substring에 대해, 모든 가능한 왼쪽 경계 L에 대해
 *     이분 탐색으로 오른쪽 경계 R을 찾아 조건을 만족하는 substring의 개수를 센다.
 *    - 0의 개수가 k개인 substring에 대해 k를 1부터 sqrt(n)까지 증가시키며 반복
 *    - 0의 개수가 0인 substring (순수 1로만 이루어진 substring)은 별도로 계산
 *    - 전체 시간복잡도 : O(n * sqrt(n) * log(n))
 */

public class Question_20251115 {
    public static void main(String args[]) throws IOException {
        String s = "11";
        System.out.println(numberOfSubstrings(s));

        s = "00011";
        System.out.println(numberOfSubstrings(s));

        s = "101101";
        System.out.println(numberOfSubstrings(s));
    }

    public static int numberOfSubstrings(String s) {
        int n = s.length();
        int result = 0;

        // case 1 : zeros = 0 (순수 1로만 이루어진 substring)
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                int j = i;
                while (j < n && s.charAt(j) == '1') j++;
                long len = j - i;
                result += len * (len + 1) / 2;
                i = j;
            } else {
                i++;
            }
        }

        // case 2 : zeros = k (1 <= k <= maxZeroCount)
        List<Integer> zeroIndices = new ArrayList<>();

        for (int index = 0 ; index < n; index++) {
            if (s.charAt(index) == '0') zeroIndices.add(index);
        }

        int zeroIndicesSize = zeroIndices.size();
        int zeroMaxCount = (int) Math.sqrt(n) + 2;

        // 1의 누적합 배열
        int[] prefix = new int[n];
        prefix[0] = s.charAt(0) == '1' ? 1 : 0;
        for (int index = 1; index < n; index++) {
             prefix[index] = prefix[index - 1] + (s.charAt(index) == '1' ? 1 : 0);
        }

        for (int k = 1; k <= zeroMaxCount && k <= zeroIndicesSize; k++) {

            for (int idx = 0; idx + k - 1 < zeroIndicesSize; idx++) {

                int leftZero = zeroIndices.get(idx);
                int rightZero = zeroIndices.get(idx + k - 1);

                int Lmin = (idx == 0 ? 0 : zeroIndices.get(idx - 1) + 1);
                int Lmax = leftZero;

                int Rmin = rightZero;
                int Rmax = (idx + k == zeroIndicesSize ? n - 1 : zeroIndices.get(idx + k) - 1);

                for (int L = Lmin; L <= Lmax; L++) {

                    // ones needed = k*k
                    int need = k * k;

                    // find smallest R s.t substring [L..R] has >= need ones
                    // binary search on R
                    int lo = Rmin, hi = Rmax, pos = -1;
                    while (lo <= hi) {
                        int mid = (lo + hi) / 2;

                        int ones = prefix[mid] - (L > 0 ? prefix[L - 1] : 0);

                        if (ones >= need) {
                            pos = mid;
                            hi = mid - 1;
                        } else {
                            lo = mid + 1;
                        }
                    }

                    if (pos != -1) {
                        result += (Rmax - pos + 1); // all R >= pos valid
                    }
                }
            }
        }

        return result;
    }
}
