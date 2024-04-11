package code.programmers.study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 접근)
 *  1. 구현
 *  2. BFS
 *
 * 제한사항)
 * 0 ≤ dia, iron, stone ≤ 5
 * 5 ≤ minerals의 길이 ≤ 50
 */
class Lesson172927 {

    static String[] mineralList;

    static int solution(int[] picks, String[] minerals) {
        int answer = 25 * 50;
        int totalMinerals = minerals.length;
        int totalPickCount = picks[0] + picks[1] + picks[2];
        int needPickCount = (totalMinerals - 1)/5 + 1;
        mineralList = minerals;
        Queue<Mine> queue = new LinkedList<>();
        queue.add(new Mine(picks[0], picks[1], picks[2], 0));

        for (int i = 0; i < needPickCount; i++) {
            if (totalPickCount-- <= 0) {
                break;
            }
            List<String> list = new ArrayList<>();
            for (int j = i*5; j < i*5+5; j++) {
                if (j >= totalMinerals) {
                    break;
                }
                list.add(minerals[j]);
            }
            int size = queue.size();
            for (int x = 0; x < size; x++) {
                Mine mine = queue.poll();
                if (mine.dia > 0) {
                    queue.add(
                        new Mine(
                            mine.dia - 1,
                            mine.iron,
                            mine.stone,
                            mine.fatigue + pick(0, list)
                        )
                    );
                }
                if (mine.iron > 0) {
                    queue.add(
                        new Mine(
                            mine.dia,
                            mine.iron - 1,
                            mine.stone,
                            mine.fatigue + pick(1, list)
                        )
                    );
                }
                if (mine.stone > 0) {
                    queue.add(
                        new Mine(
                            mine.dia,
                            mine.iron,
                            mine.stone - 1,
                            mine.fatigue + pick(2, list)
                        )
                    );
                }
            }
        }
        for (Mine m : queue) {
            answer = Math.min(m.fatigue, answer);
        }
        return answer;
    }

    private static int pick(int currectPick, List<String> minerals) {
        int sum = 0;
        for (String mineral : minerals) {
            sum += calcFatigue(currectPick, mineral);
        }
        return sum;
    }

    private static int calcFatigue(int pick, String mineral) {
        if (pick == 0) {
            return 1;
        }
        else if (pick == 1) {
            if (mineral.startsWith("d")) {
                return 5;
            }
            else {
                return 1;
            }
        }
        else {
            if (mineral.startsWith("d")) {
                return 25;
            }
            else if (mineral.startsWith("i")) {
                return 5;
            }
            else {
                return 1;
            }
        }
    }

    static class Mine {
        int dia;
        int iron;
        int stone;
        int fatigue;

        Mine(int d, int i, int s, int f) {
            this.dia = d;
            this.iron = i;
            this.stone = s;
            this.fatigue = f;
        }
    }

    public static void main(String[] args) {
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution(picks, minerals));
    }
}
/*
{1, 3, 2}
{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}
12

{0, 1, 1}
{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}
50
 */
