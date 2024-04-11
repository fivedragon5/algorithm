package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem16116 {

    static ArrayList<Set<Long>> lineStation = new ArrayList<>();
    static ArrayList<Set<Integer>> lineNodes = new ArrayList<>();
    static HashSet<Integer> destionLines = new HashSet<>();
    static boolean[] vistied;
    static int min = 11;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        lineStation = new ArrayList<>();
        lineNodes = new ArrayList<>();
        vistied = new boolean[N];
        HashSet<Integer> startLines = new HashSet<>();
        HashSet<Integer> destionLines = new HashSet<>();
        Long destionStation;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int stationCount = Integer.parseInt(st.nextToken());
            lineStation.add(new HashSet());
            lineNodes.add(new HashSet());
            for (int j = 0; j < stationCount; j++) {
                Long station = Long.parseLong(st.nextToken());
                lineStation.get(i).add(station);
                if (station == 0) {
                    startLines.add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        destionStation = Long.parseLong(st.nextToken());

        connectLine(destionStation);

        for (int start : startLines) {
            dfs(start, 0);
        }

        if (min == 11) {
            System.out.println(-1);
        }
        else {
            System.out.println(min);
        }
    }

    static void connectLine(Long destionStation) {
        Map<Long, HashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < lineStation.size(); i++) {
            for (long station : lineStation.get(i)) {
                if(station == destionStation) destionLines.add(i);
                HashSet<Integer> set = map.getOrDefault(station, new HashSet<Integer>());
                if (set.size() == 0) {
                    set.add(i);
                    map.put(station, set);
                }
                else {
                    for (int line : set) {
                        if (i != line) {
                            lineNodes.get(i).add(line);
                            lineNodes.get(line).add(i);
                        }
                    }
                    set.add(i);
                    map.put(station, set);
                }
            }
        }
    }

    static void dfs(int line, int count) {
        if (destionLines.contains(line)) {
            min = Math.min(min, count);
            vistied[line] = false;
            return;
        }

        for (int next : lineNodes.get(line)) {
            vistied[line] = true;
            if (!vistied[next]) {
                vistied[next] = true;
                dfs(next, count + 1);
                vistied[next] = false;
            }
            vistied[line] = false;
        }
    }
}

