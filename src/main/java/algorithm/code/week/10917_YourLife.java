package algorithm.code.week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문제)
 * 당신이 꿈을 이루는 과정 중에 일어날 수 있는 수많은 상황들의 관계를 그래프로 나타내어 보겠다. 많은 상황을 압축해서 N개의 상황이 일어날 수 있다고 하고
 * 1번에서 N까지의 번호를 붙였다.
 *
 * 당신은 현재 1번 상황에 있다. 그리고 N번 상황은 당신이 이루고자 하는 유일한 꿈이다.
 * 상황은 당신의 선택에 따라 변화할 수 있다. 당신이 선택할 수 있는 변화는 총 M개 있으며 x, y의 형태로 주어진다.
 * 이는 당신이 상태 x에 있는 경우 상태 y로 가는 선택을 할 수 있다는 것을 의미하며, x < y임이 보장된다.
 *
 * 당신은 꿈을 이룰 수 있을까? 이룰 수 있다면 당신의 상황이 변화하는 횟수를 최소한으로 줄이면 몇 번 만에 꿈을 이룰 수 있을까?
 *
 * 제한)
 * 0 ≤ M ≤ 200,000
 * 1 ≤ x < y ≤ N
 * 1 ≤ N ≤ 100,000
 */
class Problem10917 {

    static int[] check;
    static Map<Integer, HashSet<Integer>> map = new HashMap();
    static int destinationDream;
    static int min = 100001;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 목표 꿈
        int M = Integer.parseInt(st.nextToken()); // 상황의 수
        destinationDream = N;
        check = new int[N + 1];
        visited = new boolean[N + 1];

        Arrays.fill(check, 100001);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x > N) continue;
            HashSet set = map.getOrDefault(x, new HashSet());
            set.add(y);
            map.put(x, set);
        }

        dfs(1, 0);

        System.out.println(min == 100001 ? -1 : min);
    }

    static void dfs(int currentDream, int count) {
        if (currentDream == destinationDream) {
            visited[currentDream] = false;
            min = Math.min(min, count);
            return;
        }

        HashSet<Integer> set = map.getOrDefault(currentDream, new HashSet());
        if (set.size() == 0) {
            visited[currentDream] = true;
            return;
        }
        for (int nextDream : set) {
            visited[currentDream] = true;
            if (!visited[nextDream] && check[nextDream] >= count + 1) {
                check[nextDream] = count + 1;
                visited[nextDream] = true;
                dfs(nextDream, count + 1);
                visited[nextDream] = false;
            }
            visited[currentDream] = false;
        }

    }
}

/*
4 4
1 2
2 3
3 4
2 4
 */

