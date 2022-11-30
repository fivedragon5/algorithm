package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 접근)
 * 입력받은 선분의 시작, 끝 정보를 {점의 위치, 시작 지점 | 끝나는 지점}으로 변환시켜 정렬한뒤
 *  변환시킨 정보들을 순회하면서 시작지점을 만났을 경우에는 증가, 끝나는 지점을 만났을 경우에는 감소 시켜
 *  증가된 경우에는 최대값을 확인하여 갱신시켜준다.
 * 단 점의 위치가 동일한 경우 끝나는 지점을 먼저 오도록 배치하여 감소가 먼저 되도록 한다.
 *
 * 풀이)
 * N : 선분의 갯수
 * lines[][] : 선분을 점 좌표로 변환 시킨 2차원 int형 배열 / 길이는 [2N][2];
 * lines[2N] : 선분을 점 좌표로 변환시킨 값으로 각선분당 점의 갯수는 시작,끝 점으로 선분당 점이2개씩 나온다
 *             따라서 길이를 2N으로 선언
 * lines[2N][2] : {점의 좌표, 점의 타입} (점의 타입: 0|1) 1:시작점, 0:끝점
 * answer : 겹치는 지점의 최대값
 * count : 현재 위치의 지점에서 겹치는 선분의 갯수
 * 
 * 조건)
 * 1 <= N <= 1000000 선분의 갯수
 * -100,000,000 <= s < e <= 100,000,000
 * s < e
 *
 * 선분의 끝 점에서 겹치는 것은 겹치는 것으로 세지 않는다.
 */

class Problem1689 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] lines = new int[2*N][2];

        int answer = 0;
        
        //시작점:[i], 끝점:[i+N]에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = 1; //시작점일 경우 1로 저장
            lines[i+N][0] = Integer.parseInt(st.nextToken());
        }
        
        //입력한 점의 좌표들을 정렬
        Arrays.sort(lines, (line1, line2) -> {
            //시작점이 동일할 경우 끝점이 먼저 순회 되도록 정렬 오름차순 0 < 1
            if (line1[0] == line2[0]) {
                return line1[1] - line2[1];
            }
            //시작점 정렬
            return line1[0] -line2[0];
        });

        int count = 0;

        for (int[] a : lines) {
            //시작점일 경우 증가 및 최대값 갱신
            if (a[1] == 1) {
                count++;
                answer = Math.max(answer, count);
            }
            //끝점일 경우 감소
            else {
                count--;
            }
        }

        System.out.println(answer);
    }
}
/*
TEST CASE
11
1 2
3 6
7 8
10 11
13 16
0 5
5 6
2 5
6 10
9 14
12 15

3

1
-1 1

 */

