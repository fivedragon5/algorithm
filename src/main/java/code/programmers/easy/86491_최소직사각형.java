package code.programmers.easy;

import java.util.Collections;
import java.util.HashSet;

class Lesson86491 {
    static int solution(int[][] sizes) {
        int width = 0;
        int height = 0;
        for(int[] array : sizes) {
            if(array[0] > array[1]) {
                if(array[0] > width) width = array[0];
                if(array[1] > height) height = array[1];
            }
            else {
                if(array[1] > width) width = array[1];
                if(array[0] > height) height = array[0];
            }
        }
        return width * height;
    }
    static int solution2(int[][] sizes) {
        HashSet<Integer> big = new HashSet<>();
        HashSet<Integer> small = new HashSet<>();
        for(int[] array : sizes) {
            if(array[0] > array[1]) {
                big.add(array[0]);
                small.add(array[1]);
            }
            else {
                big.add(array[1]);
                small.add(array[0]);
            }
        }
        int width = Collections.max(big);
        int height = Collections.max(small);
        return width * height;
    }

    public static void main(String[] args) {
        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}}; // 4000
        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}}; // 120
        int[][] sizes3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}; // 133
        System.out.println("===START===");
        System.out.println(solution(sizes1));
        System.out.println(solution(sizes2));
        System.out.println(solution(sizes3));
        System.out.println("===END===");
    }
}
