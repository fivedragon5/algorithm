package algorithm.code.programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * 제한)
 * 3 ≤ elements의 길이 ≤ 1,000
 * 1 ≤ elements의 원소 ≤ 1,000
 */
class Lesson131701 {
    static int solution(int[] elements) {
        int size = elements.length;
        int[] arrays = new int[(2 * size) - 1];

        arrays[size-1] = elements[size-1];

        for (int i = 0; i < size - 1; i++) {
            arrays[i] = elements[i];
            arrays[size + i] = elements[i];
        }

        Set<Integer> set = new HashSet<>();

        for (int count = 1; count <= size; count++) {
            int index = 0;
            int stack = 0;
            int sum = 0;
            while (index < size + count - 1) {
                if (stack != count) {
                    sum += arrays[index];
                    index++;
                    stack++;
                }
                if (count == stack) {
                    System.out.println(count + " "+ sum);
                    set.add(sum);
                    sum -= arrays[index - count];
                    stack--;
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] elements = {7,9,1,1,4};
//        int[] elements = {1000,1,2,3,4,56,6};
        System.out.println("===START===");
        System.out.println(solution(elements));
        System.out.println("===END===");
    }
}
