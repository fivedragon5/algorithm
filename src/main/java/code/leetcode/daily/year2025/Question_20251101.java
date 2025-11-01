package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/description/?envType=daily-question&envId=2025-11-01
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^5
 *  모든 원소는 유니크하다
 *  모든 Node의 수는 [1, 10^5] 범위이다.
 *  1 <= Node.val <= 10^5
 *  입력은 연결 리스트에 적어도 하나 이상의 노드가 있으며, 그 노드의 값은 nums에 존재하지 않도록 생성된다.
 *
 * 문제)
 *  1. 정수배열 nums와 연결 리스트의 head가 주어진다.
 *  2. 연결 리스트에서 nums에 있는 모든 노드를 삭제한 후 연결 리스트의 head를 반환
 *
 * 풀이)
 *  1. nums 배열의 원소들을 HashSet에 담아 빠른 탐색이 가능하도록 한다.
 *  2. 연결 리스트를 순회하며 현재 노드의 값이 HashSet 에 존재하는지 확인한다.
 *  3. 존재한다면 해당 노드를 건너뛰고, 존재하지 않는다면 그대로 연결 리스트에 남긴다.
 *  4. 최종적으로 수정된 연결 리스트의 head를 반환한다.
 *
 */

public class Question_20251101 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[] {1,2,3};
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(modifiedList(nums, head));
    }

    private static ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        while (head != null && set.contains(head.val)) {
            head = head.next;
        }

        ListNode current = head;
        while (current != null && current.next != null) {
            while (current.next != null && set.contains(current.next.val)) {
                current.next = current.next.next;
            }
            current = current.next;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
