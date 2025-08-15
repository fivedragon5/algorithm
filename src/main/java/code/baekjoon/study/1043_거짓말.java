package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1043
 *
 * 제한)
 *  1 <= N, M <= 50
 *  0 <= 진실을 아는 사람의 수 <= 50
 *  1 <= 각 파티마다 오는 사람의 수 <= 50
 *
 * 문제)
 *  1. 사람의 수 N, 파티의 수 M이 주어진다.
 *  2. 지민이가 모든 파티에 참가한다고 했을 때, 지민이가 거짓말쟁이로 알려지지 않으면, 과장된 이야기를 할 수 있는 파티의 최대 수 구하기
 *
 * 풀이)
 *  UnionFind
 *  1. UnionFind 알고리즘을 사용해서 각 사람들의 그룹을 만들어 준다.
 *   - 파티별 참석하는 인원들을 바탕으로 그룹 생성
 *  2. 각 파티들을 순회하면서 참석한 인원중 한명을 뽑고(가장 첫번째 인원) 해당 그룹에서 거짓말 할 수 있는지 없는지 판단
 *   - 거짓에 대한 판단은 Union에 대한 Find로 그룹을 찾고 그 중 진실을 알고 있는 사람이 있을 경우 거짓말을 할 수 없는 것으로 판단
 *  3. 거짓말을 할 수 없는 경우 다음 파티로 continue
 *   - 거짓말을 할 수 있는 경우 count 추가
 *
 */
class Problem1043 {

    private static int N, M;
    private static List<Integer> TRUTH_KNOWERS = new ArrayList<>();
    private static List<Integer>[] PARTYS;
    private static int[] UNION;
    private static boolean[] TRUTH_PEOPLE;

    public static void main(String args[]) throws IOException {
        input();

        int count = 0;

        for (List<Integer> party : PARTYS) {
            boolean isLie = true;
            int firstPeopleNumber = party.get(0);
            for (int number : TRUTH_KNOWERS) {
                if (find(number) == find(firstPeopleNumber)) {
                    isLie = false;
                    break;
                }
            }
            if (isLie) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static int find(int a) {
        if (UNION[a] == a) {
            return a;
        }
        return UNION[a] = find(UNION[a]);
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA != findB) {
            if (TRUTH_PEOPLE[findA]) {
                UNION[findB] = findA;
            } else {
                UNION[findA] = findB;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        UNION = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            UNION[i] = i;
        }
        TRUTH_PEOPLE = new boolean[N + 1];
        // 진실을 아는 사람의 수
        int truthKnownCount = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthKnownCount; i++) {
            int number = Integer.parseInt(st.nextToken());
            TRUTH_PEOPLE[number] = true;
            TRUTH_KNOWERS.add(number);
        }
        PARTYS = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            PARTYS[i] = new ArrayList<>();
            int countOfPeople = Integer.parseInt(st.nextToken());
            int prePeopleNumber = Integer.parseInt(st.nextToken());
            PARTYS[i].add(prePeopleNumber);
            for (int k = 1; k < countOfPeople; k++) {
                int number = Integer.parseInt(st.nextToken());
                PARTYS[i].add(number);
                union(prePeopleNumber, number);
                prePeopleNumber = number;
            }
        }
        br.close();
    }
}
/*
4 3
0
2 1 2
1 3
3 2 3 4

 */
