package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/rabbits-in-forest/description/?envType=daily-question&envId=2025-04-20
 *
 * 제한)
 *  1 <= answers.length <= 1000
 *  0 <= answers[i] < 1000
 *
 * 문제)
 *  1. 숲속에 몇 마리의 토끼가 있는지 숲 속에 있을 수 있는 최소 토끼 수 반환하기
 *   - n마리 토끼에게 너와 같은 색깔의 토끼는 몇 마리니? 라고 물었을 때 에 대한 대답이 answer[]에 주어진다
 *
 * 풀이)
 *  1. Map<Integer, Integer>에 <토끼 수 카운트, 토끼 수> 로 저장한다
 *   - answer[] 를 순회
 *  2. 최소 토끼 수 를 구해야 하기에 최대한 같은 색깔이라고 가정하고 계산
 *   example) n이라고 대답한 토끼가 n마리 발견됬을 경우 그 토끼를 같은 묶음으로 계산후 초기화
 *  3. 마지막에 map을 확인해서 남은 토끼들을 전부 더하기
 */

public class Question_20250420 {
    public static void main(String args[]) throws IOException {
        int[] nums4 = new int[]{0,0,1,1,1};
        System.out.println(numRabbits(nums4));

        int[] nums3 = new int[]{1,0,1,0,0};
        System.out.println(numRabbits(nums3));

        int[] nums = new int[]{1,1,2};
        System.out.println(numRabbits(nums));

        int[] nums2 = new int[]{10,10,10};
        System.out.println(numRabbits(nums2));
    }

    public static int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (int answer : answers) {
            int count = map.getOrDefault(answer, 0);
            if (count == answer) {
                map.remove(answer);
                total += count + 1;
            } else {
                map.put(answer, count + 1);
            }
        }

        for (int key : map.keySet()) {
            total += key + 1;
        }

        return total;
    }
}
