package algorithm.study;

import java.util.Arrays;

/**
 * 최단거리 - 플로이드 와샬 알고리즘
 * 1.출발지에서 도착지로 바로가는 거리
 * 2.출발지에서 도착지 외의 다른곳을 거쳐 도착지로 가는 거리
 * 위 1,2 비교하여 주어진 2차원 배열을 갱신
 * N개의 점(곳)이 주어젔을때 N만큼 for문을 3번 돌리기때문에
 * N^3의 시간 복잡도를 가진다.
 */
public class FloydWarshall {
    public static void main(String args[]) {

        //2차원 배열로 각각의 원소는
        //array[i][j] : i에서 j까지의 거리를 나타냄
        int[][] array = new int[][]{
                {0, 5, 100, 100},
                {7, 0, 9, 100},
                {2, 100, 0, 4},
                {100, 100, 3, 0}
        };
        
        //배열의 길이
        int number = array.length;

        //결과 그래프 초기화
        int[][] d = new int[number][number];
        
        //k:거쳐가는 곳 , i:출발지, j:도착지
        for (int k = 0; k < number; k++) {
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < number; j++) {
                    if (array[i][k] + array[k][j] < array[i][j]) {
                        array[i][j] = array[i][k] + array[k][j];
                    }
                }
            }
        }

        for (int[] a : array) {
            System.out.println(Arrays.toString(a));
        }
    }
}
