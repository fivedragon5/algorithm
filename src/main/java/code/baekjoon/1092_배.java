package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1092
 * 1 <= N(크레인 수) <= 50
 * 1 <= 크레인 무게 제한, 박스 무게 <= 1,000,000
 * 1 <= M(박스의 수) <= 10,000
 *
 * 1. 모든 박스를 배로 옮기는데 드는 최소 시간
 * 2. 만약 모든 박스를 배로 옮길 수 없으면 -1
 *
 * 풀이)
 * 1. 크레인 무게제한 내림차순으로 정렬
 * 2. 박스 무게 오름차순으로 정렬
 *  - 박스배열을 지울때 편하게 하기 위해 오름차순으로 정렬하고 뒤에서부터 지우기
 * 3. 크레인이 박스를 옮길 수 있는 최대 무게제한 보다 박스의 무게가 더 클 경우 빠른 탈출
 * 4. 크레인이 박스를 옮길때마다 box배열을 erase 해줘서 시간을 줄이기
 *  - 최초 제출시 temp를 사용해서 복사를 했는데 메모리, 시간초과에 걸림
 */
class Problem1092 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> boxes = new ArrayList<>();
        List<Integer> cranes = new ArrayList<>();
        int craneMaxWeight = 0;
        int boxMaxWeight = 0;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int crane = Integer.parseInt(st.nextToken());
            cranes.add(crane);
            craneMaxWeight = Math.max(craneMaxWeight, crane);
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int box = Integer.parseInt(st.nextToken());
            boxes.add(box);
            boxMaxWeight = Math.max(boxMaxWeight, box);
        }

        if (boxMaxWeight > craneMaxWeight) {
            // 크레인이 최대로 들 수 있는 무게보다 박스 무게가 클 경우
            System.out.println(-1);
            return;
        }

        // 크레인 내림차순 정렬
        Collections.sort(cranes, Collections.reverseOrder());
        // 박스 오름차순 정렬
        Collections.sort(boxes);

        int second = 0;

        while (boxes.size() > 0) {
            for (int crane : cranes) {
                int startBoxIndex = boxes.size() - 1;
                boolean isMove = false;
                for (int i = startBoxIndex; i >= 0; i--) {
                    if (crane >= boxes.get(i)) {
                        isMove = true;
                        startBoxIndex = i - 1;
                        boxes.remove(i);
                        break;
                    }
                }
                if (!isMove) break; // 시간 초과, 메모리 초과
            }
            second++;
        }
        System.out.println(second);
    }
}
/*
3
6 8 9
5
2 5 2 4 7

1
1
1
2

3
10 6 5
11
6 8 9 6 8 6 9 6 8 6 9
 */
