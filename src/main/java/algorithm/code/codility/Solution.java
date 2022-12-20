package algorithm.code.codility;

import java.util.Arrays;

/**
 * Codility Sample template
 */
class Solution {
    static public int solution(int[] A) {

        Arrays.sort(A);

        if (A[A.length-1] < 1) {
            return 1;
        }

        int count = 1;

        for (int number : A) {
            if (number == count) {
                count++;
            }
            else if (number > count) {
                break;
            }
        }
        return count;
    }

    public static void main(String args[]) {
        //int[] a = {1, 3, 6, 4, 1, 2}; // 5 / 1,1,2,3,4,6
        //int[] a = {1, 2, 3}; // 5
        int[] a = {-1, -3}; // 5

        System.out.println(solution(a));
    }
}
