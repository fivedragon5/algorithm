package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 구현
 *  
 * 풀이)
 *  1. 봄,여름,가을,겨울 주어진 순서대로 구현은 쉽게 할 수 있다.
 *  2. 단 제한시간에 맞추기 위해서 신경쓴 부분
 *      1. 나무 삭제시 iterator에서 삭제하면 O(1)의 시간복잡도로 지움
 *      2. 나무를 여러개 삭제, 또는 추가 시 한번에 모아서 하는게 시간을 줄일 수 있음
 *          - addAll시 원하는 위치에서부터 추가 가능
 *  
 * 주의사항)
 *  1. 처음에는 모든 땅에 양분이 5로 시작
 */
class Problem16235 {

    static int N;
    static List<Tree> trees = new LinkedList<>();
    static int[][] add;
    static int[][] map;
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        add = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x =  Integer.parseInt(st.nextToken()) - 1;
            int y =  Integer.parseInt(st.nextToken()) - 1;
            int age =  Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        trees.sort(Comparator.comparingInt(o -> o.age));

        for (int i = 0; i < K; i++) {
            startSummer(startSpring());
            startFall();
            startWinter();
        }

        System.out.println(trees.size());
    }

    static Queue<Tree> startSpring() {
        Queue<Tree> deadTrees = new LinkedList<>();
        Iterator<Tree> iterator = trees.iterator();
        while (iterator.hasNext()) {
            Tree tree = iterator.next();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if (map[x][y] < age) {
                deadTrees.offer(tree);
                iterator.remove();
            } else {
                map[x][y] -= age;
                tree.age += 1;
            }
        }
        return deadTrees;
    }

    static void startSummer(Queue<Tree> deadTrees) {
        for (Tree tree : deadTrees) {
            map[tree.x][tree.y] += tree.age / 2;
        }
    }

    static void startFall() {
        LinkedList<Tree> newTrees = new LinkedList<>();
        for (Tree tree : trees) {
            if (tree.age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int x = tree.x + dx[j];
                    int y = tree.y + dy[j];
                    if (!(x < 0 || y < 0 || x>= N || y >= N)) {
                        newTrees.add(new Tree(x, y, 1));
                    }
                }
            }
        }
        trees.addAll(0, newTrees);
    }

    static void startWinter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += add[i][j];
            }
        }
    }

    static class Tree {
        int x;
        int y;
        int age;

        Tree(int x , int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
/*
5 2 7
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 1 2
3 2 1

71
 */
