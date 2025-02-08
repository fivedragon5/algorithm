package code.leetcode.daily;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/design-a-number-container-system/description/?envType=daily-question&envId=2025-02-08
 *
 * 제한)
 *  1 <= index, number <= 109
 *  At most 105 calls will be made in total to change and find.
 *
 * 문제)

 *
 * 풀이)
 */

public class Question_20250208 {
    public static void main(String args[]) throws IOException {

        String[] a = {"NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"};
        int[][] b = {{}, {10}, {2, 10}, {1, 10},{3, 10},{5, 10},{10},{1, 20},{10}};

        NumberContainers numberContainers = new NumberContainers();
        for (int i = 1; i < a.length; i++) {

        }
    }

    public static class NumberContainers {
        private HashMap<Integer, Integer> map;
        private HashMap<Integer, PriorityQueue<Integer>> numberAndIndexQueueMap;
        public NumberContainers() {
            this.map = new HashMap<>();
        }
        public void change(int index, int number) {
            int current = map.getOrDefault(index, 0);
            if (current == 0) {

            }
            this.map.put(index, number);
            PriorityQueue<Integer> queue = numberAndIndexQueueMap.getOrDefault(number, null);
            if (queue == null) {
                queue = new PriorityQueue<>();
                queue.add(index);
                numberAndIndexQueueMap.put(number, queue);
            } else {

            }
        }
        public int find(int number) {
            PriorityQueue<Integer> queue = numberAndIndexQueueMap.getOrDefault(number, null);
            if (queue == null) {
                return -1;
            } else {
                return queue.peek();
            }
        }
    }
}
