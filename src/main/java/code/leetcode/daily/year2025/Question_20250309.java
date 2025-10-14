package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/alternating-groups-ii/?envType=daily-question&envId=2025-03-09
 *
 * 제한)
 * 3 <= colors.length <= 10^5
 * 0 <= colors[i] <= 1
 * 3 <= k <= colors.length
 *
 * 문제)
 *  1. 0,1로 이루어진 배열(colors)이 주어짐
 *  2. colors 배열에서 서로 인접한 원소가 다른 그룹 수를 반환
 *   - 서로 인접한 배열의 원소 수 k
 *  3. colors[0], colors[colors.length - 1] 은 서로 인접한걸로 취급
 *
 * 풀이)
 *  1. colors[0] ~ colors[k - 1] 부터 index를 1개씩 늘리면서 조건을 만족하는지 확인한다.
 *   - 조건을 만족할 경우
 *      - colors[index + k], colors[index + k + 1] 만 확인
 *   - 조건을 만족하지 못 할 경우
 *      - index의 위치를 k로 리셋한다음 다음 반복진행
 *  2. index가 colors를 넘길 경우 index 부분을 나머지 연산해서 진행한다.
 *
 *  참고) 배열 병합을 통해 나머지 연산부분 제거 가능
 *
 * 참고)
 *  배열 병합 : System.arraycopy(원본배열, 원본시작인덱스, 대상배열, 대상시작인덱스, 복사할길이);
 */

public class Question_20250309 {
    public static void main(String args[]) throws IOException {

        int[] colors = {0,1,1}; int k = 3;
        System.out.println(numberOfAlternatingGroups(colors, k)); // 3

//        int[] colors = {0,1,0,1,0}; int k = 3;
//        System.out.println(numberOfAlternatingGroups(colors, k)); // 3
//
//        int[] colors2 = {0,1,0,0,1,0,1}; k = 6;
//        System.out.println(numberOfAlternatingGroups(colors2, k)); // 2

    }

    public static int numberOfAlternatingGroups(int[] colors, int k) {
        int answer = 0;
        int colorLength = colors.length;

        int index = 0;
        boolean isBeforeAlternatingGroups = false;

        while (index < colorLength) {
            if (colors[(index + k - 2) % colorLength] == colors[(index + k - 1) % colorLength]) {
                index = index + k - 1;
                isBeforeAlternatingGroups = false;
                continue;
            }
            if (isBeforeAlternatingGroups) {
                answer++;
            } else {
                if (isAlternatingGroups(colors, k, index)) {
                    isBeforeAlternatingGroups = true;
                    answer++;
                } else {
                    isBeforeAlternatingGroups = false;
                }
            }
            index++;
        }

        return answer;
    }

    // startIndex ~ startIndex + k - 1 까지 AlternatingGroups 를 만족하는지 확인
    private static boolean isAlternatingGroups(int[] colors, int k, int startIndex) {
        int colorLength = colors.length;
        for (int i = startIndex; i < startIndex + k - 1; i++) {
            int index = i;
            if (colors[index % colorLength] == colors[(index+1) % colorLength]) {
                return false;
            }
        }
        return true;
    }
}
