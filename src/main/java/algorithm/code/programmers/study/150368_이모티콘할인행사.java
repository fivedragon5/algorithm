package algorithm.code.programmers.study;

import java.util.Arrays;

/**
 * 접근)
 * 1. 구현 문제
 *  - combination, dfs
 *
 * 풀이)
 * 1. 각각의 이모티콘을 할인한 가격들의 정보를 담는 2차원 배열을 계산
 * 2. 이모티콘이 할인률이 나올 수 있는 경우의 수를 구함
 *  - combination
 * 3. 조합이 완성 될때마다 플러스 유저 가입자, 판매가격을 갱신
 *
 * 제한)
 * 1 ≤ n명 ≤ 100
 * 100 ≤ 가격 ≤ 1,000,000
 * 가격 % 100 = 0
 * 1 ≤ m ≤ 7
 * 100 ≤ emoticons의 원소 ≤ 1,000,000
 * emoticons의 원소는 100의 배수입니다
 * 할인률 : 10%, 20%, 30%, 40%
 *  4^7 = 16384
 */

class Lesson150368 {

    static int size;
    static int[][] userList;
    static int[][] emoticonsDiscount;
    static int[] discount = {10, 20, 30, 40};
    static int maxUser = 0;
    static int maxSales = 0;

    static int[] solution(int[][] users, int[] emoticons) {
        size = emoticons.length;

        emoticonsDiscount = new int[size][4];
        userList = users;

        for (int j = 0; j < size; j++) {
            int price = emoticons[j];
            for (int i = 0; i < 4; i++) {
                emoticonsDiscount[j][i] = price - (price / 100 * discount[i]);
            }
        }

        for (int[] eds : emoticonsDiscount) {
            System.out.println(Arrays.toString(eds));
        }

        int[] list = new int[size];
        combination(list, 0);

        return new int[]{maxUser, maxSales};
    }

    static void combination(int[] list, int n) {
        if (n == size) {
            calc(list);
            return;
        }

        for (int i = 0; i < 4; i++) {
            list[n] = i;
            combination(list, n + 1);
        }
    }

    static void calc(int[] combinationList) {
        int plusUser = 0;
        int sales = 0;
        for (int i = 0; i < userList.length; i++) {
            int price = 0;
            for (int j = 0; j < size; j++) {
                if (discount[combinationList[j]] >= userList[i][0]) {
                    price += emoticonsDiscount[j][combinationList[j]];
                }
            }
            if (price >= userList[i][1]) plusUser++;
            else sales += price;
        }
        if (plusUser > maxUser) {
            maxUser = plusUser;
            maxSales = sales;
        }
        else if (plusUser == maxUser) {
            maxSales = Math.max(sales, maxSales);
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        System.out.println("===START===");
        System.out.println(Arrays.toString(solution(users, emoticons)));
        System.out.println("===END===");
    }
}
