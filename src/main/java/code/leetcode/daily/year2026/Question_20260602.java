package code.leetcode.daily.year2026;

import java.io.IOException;

/**
 * https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/?envType=daily-question&envId=2026-06-02
 *
 * 제한)
 *
 * 문제)
 *
 * 풀이)
 *
 */

public class Question_20260602 {
    public static void main(String args[]) throws IOException {
        int[] landStartTime1 = {2, 8};
        int[] landDuration1 = {4, 1};
        int[] waterStartTime1 = {6};
        int[] waterDuration1 = {3};
        System.out.println(earliestFinishTime(landStartTime1, landDuration1, waterStartTime1, waterDuration1));

        int[] landStartTime2 = {5};
        int[] landDuration2 = {3};
        int[] waterStartTime2 = {1};
        int[] waterDuration2 = {10};
        System.out.println(earliestFinishTime(landStartTime2, landDuration2, waterStartTime2, waterDuration2));
    }

    public static int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;

        // 모든 지상 놀이기구 i와 수상 놀이기구 j의 조합을 순회
        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {

                // 1. 지상(Land)을 먼저 타고 수상(Water)을 타는 경우
                int landEndTime = landStartTime[i] + landDuration[i];
                int startWaterTime = Math.max(landEndTime, waterStartTime[j]);
                int finalWaterEndTime = startWaterTime + waterDuration[j];

                // 최솟값 갱신
                minFinishTime = Math.min(minFinishTime, finalWaterEndTime);

                // 2. 수상(Water)을 먼저 타고 지상(Land)을 타는 경우
                int waterEndTime = waterStartTime[j] + waterDuration[j];
                int startLandTime = Math.max(waterEndTime, landStartTime[i]);
                int finalLandEndTime = startLandTime + landDuration[i];

                // 최솟값 갱신
                minFinishTime = Math.min(minFinishTime, finalLandEndTime);
            }
        }

        return minFinishTime;
    }
}
