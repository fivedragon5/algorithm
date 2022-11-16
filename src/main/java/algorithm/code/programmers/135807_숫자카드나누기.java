package algorithm.code.programmers;

import java.util.Arrays;

class Lesson135807 {

    /**
     *
     1 ≤ arrayA의 길이 = arrayB의 길이 ≤ 500,000
     1 ≤ arrayA의 원소, arrayB의 원소 ≤ 100,000,000
     arrayA와 arrayB에는 중복된 원소가 있을 수 있습니다.
     */
    static int solution(int[] arrayA, int[] arrayB) {
        int maxA = 0;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        for (int i = arrayA[0]; i > 1; i--) {
            boolean flag = true;
            if (checkArray(arrayA, i)) {
                for (int j = 0; j < arrayB.length; j++) {
                    if (arrayB[j]%i == 0) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    System.out.println(i);
                    maxA = i;
                    break;
                }
            }
        }

        for (int i = arrayB[0]; i > 1; i--) {
            boolean flag = true;
            if (checkArray(arrayB, i)) {
                for (int j = 0; j < arrayA.length; j++) {
                    if (arrayA[j]%i == 0) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    System.out.println(i);
                    maxA = Math.max(maxA,i);
                    break;
                }
            }
        }

        return maxA;
    }

    static boolean checkArray(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]%number != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] arrayA = {17, 10};
//        int[] arrayB = {5, 20};
        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};
        System.out.println("===START===");
        System.out.println(solution(arrayA, arrayB));
        System.out.println("===END===");
    }
}
