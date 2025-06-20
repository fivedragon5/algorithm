package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 *
 * 제한)
 *  arr의 행의 개수는 1 이상 1024 이하이며, 2의 거듭 제곱수 형태를 하고 있습니다.
 *  즉, arr의 행의 개수는 1, 2, 4, 8, ..., 1024 중 하나입니다.
 *      arr의 각 행의 길이는 arr의 행의 개수와 같습니다. 즉, arr은 정사각형 배열입니다.
 *      arr의 각 행에 있는 모든 값은 0 또는 1 입니다.
 *
 * 문제)
 *  1. 0과 1로 이루어진 2^n * 2^n 크기의 2차원 배열 arr이 있습니다.
 *  2. 이 배열을 쿼드 트리와 같은 방식으로 압축하기
 *  3. 압축방법
 *   - 압축 하고자 하는 특정 영역이 S 일 경우
 *   - S 영역의 모든 값이 같은 값일 경우, S를 해당 수 하나로 압축
 *   - 그렇지 않다면, S를 정확히 4개의 균일한 정사각형 영역으로 쪼갠 뒤, 각 정사각형 영역에 대해 같은 방식의 압축을 시도
 *  4. 최종적으로 남는 0의 개수와 1의 개수를 배열에 담아 반환
 *
 * 풀이)
 *  1. compress 메서드를 재귀적으로 호출하여 배열을 압축
 *  2. isUniform 메서드를 사용하여 현재 영역이 균일한지 확인
 *   - 현재 영역이 값이 같을 경우
 *      - count 배열의 해당 인덱스(0 또는 1)를 증가
 *   - 현재 영역이 값이 다를 경우
 *      - 현재 영역을 4개의 균일한 정사각형 영역으로 나누고, 각 영역에 대해 compress 메서드를 재귀적으로 호출
 */

class Lesson68936 {
    public static void main(String[] args) {
        int[][] arr = {
                {1,1,0,0},
                {1,0,0,0},
                {1,0,0,1},
                {1,1,1,1}
        };
        System.out.println(solution(arr)); // [4,9]

        int[][] arr2 = {
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        };
        System.out.println(solution(arr2)); // [10, 15]
    }

    public static int[] solution(int[][] arr) {
        int[] count = new int[2];
        compress(arr, 0, 0, arr.length, count);
        return count;
    }

    private static void compress(int[][] arr, int startRow, int startCol, int size, int[] count) {
        if (isUniform(arr, startRow, startCol, size)) {
            count[arr[startRow][startCol]]++;
            return;
        }

        int half = size / 2;
        compress(arr, startRow, startCol, half, count); // 1사분면
        compress(arr, startRow, startCol + half, half, count); // 2사분면
        compress(arr, startRow + half, startCol, half, count); // 3사분면
        compress(arr, startRow + half, startCol + half, half, count); // 4사분면
    }

    private static boolean isUniform(int[][] arr, int startRow, int startCol, int size) {
        int firstValue = arr[startRow][startCol];
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if (arr[i][j] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }
}
