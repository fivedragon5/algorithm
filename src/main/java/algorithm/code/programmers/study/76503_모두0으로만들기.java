package algorithm.code.programmers.study;

import java.util.ArrayList;

class Lesson76503 {

    static int answer = 0;
    static boolean[] vistied;
    static ArrayList<Integer>[] list;
    static long longA[];

    static int solution(int[] a, int[][] edges) {
        int sum = 0;
        vistied = new boolean[a.length];
        list = new ArrayList[a.length];
        longA = new long[a.length];

        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            list[i] = new ArrayList<Integer>();
            longA[i] = a[i];
        }

        if(sum != 0) {
            return -1;
        }

        for (int i = 0; i < edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);
            list[edges[i][1]].add(edges[i][0]);
        }

        dfs(0);

        return answer;
    }

    static long dfs(int index) {
        vistied[index] = true;

        for (int i = 0; i < list[index].size(); i++) {
            int next = list[index].get(i);
            if (!vistied[next]) longA[index] += dfs(next);
        }

        answer += Math.abs(longA[index]);

        return longA[index];
    }

    public static void main(String[] args) {
        int[] a = {-5,0,2,1,2};
        int[][] edges = {{0,1}, {3,4}, {2,3}, {0,3}};
        System.out.println("===START===");
        System.out.println(solution(a, edges));
        System.out.println("===END===");
    }
}
