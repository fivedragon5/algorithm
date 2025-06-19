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
        int row = arr.length;

        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (arr[i][j] == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
        }

        int powerOfTwo = getPowerOfTwo(row);

        for (int i = powerOfTwo; i >= 0; i--) {
            int size = (int) Math.pow(2, i - 1);

            if (!isFirst) {
                if (checkUniformity(arr, 0, 0, size)) {
                    isFirst = true;
                    int number = arr[0][0];
                    if (number == 0) {
                        zeroCount = zeroCount - (size * size) + 1;
                    } else {
                        oneCount = oneCount - (size * size) + 1;
                    }
                }
            }

            if (!isSecond) {
                if (checkUniformity(arr, 0, size, size)) {
                    isSecond = true;
                    int number = arr[0][size];
                    if (number == 0) {
                        zeroCount = zeroCount - (size * size) + 1;
                    } else {
                        oneCount = oneCount - (size * size) + 1;
                    }
                }
            }

            if (!isThird) {
                if (checkUniformity(arr, size, 0, size)) {
                    isThird = true;
                    int number = arr[size][0];
                    if (number == 0) {
                        zeroCount = zeroCount - (size * size) + 1;
                    } else {
                        oneCount = oneCount - (size * size) + 1;
                    }
                }
            }

            if (!isFourth) {
                if (checkUniformity(arr, size, size, size)) {
                    isFourth = true;
                    int number = arr[size][size];
                    if (number == 0) {
                        zeroCount = zeroCount - (size * size) + 1;
                    } else {
                        oneCount = oneCount - (size * size) + 1;
                    }
                }
            }
        }

        return new int[]{zeroCount, oneCount};
    }

    private static int getPowerOfTwo(int number) {
        return (int)(Math.log(number) / Math.log(2));
    }

    private static boolean checkUniformity(int[][] arr, int startRow, int startCol, int size) {
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
