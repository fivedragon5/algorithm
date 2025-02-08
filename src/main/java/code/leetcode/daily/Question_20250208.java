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
 * 1. change(int index, int number)
 *  - index 위치에 number 저장
 *  - 해당 index에 이미 숫자가 존재할 경우 number로 대체
 * 2. find(int number)
 *  - number가 저장된 가장 작은 인덱스 반환
 *  - number가 저장된 index가 없을 경우 -1 반환
 *
 * 풀이)
 *  1. HashMap<Integer, Integer> map
 *   - <index, number> index : number
 *  2. HashMap<Integer, PriorityQueue<Integer>> numberAndIndexQueueMap
 *   - number : List<index> ( 오름차순 )
 *
 *   change(int index, int number)
 *    - index:number로 갱신
 *    - numberAndIndexQueueMap 갱신
 *     - 기존에 있던 number의 index 삭제
 *     - 새로운 number에 index 추가
 *   find(int number)
 *    - numberAndIndexQueueMap의 number 를 peek 해서 반환
 */

public class Question_20250208 {
    public static void main(String args[]) throws IOException {
        String[] a = {"NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"};
        int[][] b = {{}, {10}, {2, 10}, {1, 10},{3, 10},{5, 10},{10},{1, 20},{10}};
        NumberContainers numberContainers = new NumberContainers();
    }

    public static class NumberContainers {
        private HashMap<Integer, Integer> map;
        private HashMap<Integer, PriorityQueue<Integer>> numberAndIndexQueueMap;
        public NumberContainers() {
            this.map = new HashMap<>();
            this.numberAndIndexQueueMap = new HashMap<>();
        }
        public void change(int index, int number) {
            if (map.containsKey(index)) {
                int current = map.get(index);
                if (current == number) return;
                numberAndIndexQueueMap.get(current).remove(index);
            }
            this.map.put(index, number);
            PriorityQueue<Integer> queue = numberAndIndexQueueMap.getOrDefault(number, new PriorityQueue<>());
            queue.add(index);
            numberAndIndexQueueMap.put(number, queue);
        }
        public int find(int number) {
            PriorityQueue<Integer> queue = numberAndIndexQueueMap.getOrDefault(number, new PriorityQueue<>());
            return queue.peek() == null ? -1 : queue.peek();
        }
    }
}
