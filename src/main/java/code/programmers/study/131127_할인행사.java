package code.programmers.study;

import java.util.HashMap;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/131127
 *
 * 제한)
 *  1 ≤ want의 길이 = number의 길이 ≤ 10
 *      1 ≤ number의 원소 ≤ 10
 *      number[i]는 want[i]의 수량을 의미하며, number의 원소의 합은 10입니다.
 *  10 ≤ discount의 길이 ≤ 100,000
 *  want와 discount의 원소들은 알파벳 소문자로 이루어진 문자열입니다.
 *      1 ≤ want의 원소의 길이, discount의 원소의 길이 ≤ 12
 *
 * 문제)
 *  1. 정현이가 원하는 물건 배열 want와 그 물건의 대한 갯수 배열 number가 주어진다.
 *  2. 마트에선 하루에 1개 물건에 대해 할인 행사를 진행하며, 할인하는 제품에 대해 하루에 하나씩만 구매할 수 있습니다.
 *  3. 마트에서 회원자격은 10일 동안 유지되며 ,회원자격이 유지되는 동안 할인하는 물건을 구매할 수 있다.
 *  4. 정현이가 원하는 물건을 모두 구매할 수 있는 회원등록 날짜의 총 일수를 반환하기
 *
 * 풀이)
 *  1. 원하는 물건을 Map<String, Intger> 형태로 저장하여 원하는 물건과 그 갯수를 관리한다.
 *  2. 마트에서 1일 부터 10일 까지 할인하는 물건을 Map<String, Integer> 형태로 저장하여 할인하는 물건과 그 갯수를 관리한다.
 *  3. 10일 동안 할인하는 물건을 구매할 수 있는지 확인하고, 구매할 수 있다면 카운트한다.
 *  4. 10일 이후부터는 할인하는 물건을 하나씩 제거하고, 새로운 할인하는 물건을 추가하면서 구매할 수 있는지 확인한다.
 *
 */
class Lesson131127 {
    public static void main(String[] args) {
        String[] want = new String[]{"banana", "apple", "rice", "pork", "pot"};
        int[] number = new int[]{3, 2, 2, 2, 1};
        String[] discount = new String[]{
                "chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"
        };
        System.out.println(solution(want, number, discount));

        String[] want2 = new String[]{"apple"};
        int[] number2 = new int[]{10};
        String[] discount2 = new String[]{
                "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"
        };
        System.out.println(solution(want2, number2, discount2));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        Map<String ,Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        Map<String, Integer> discountMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String item = discount[i];
            discountMap.put(item, discountMap.getOrDefault(item, 0) + 1);
        }

        int wantSum = 0;
        if(canBuy(wantMap, discountMap)) {
            wantSum++;
        }

        for (int i = 10; i < discount.length; i++) {
            String itemToRemove = discount[i - 10];
            String itemToAdd = discount[i];

            discountMap.put(itemToRemove, discountMap.get(itemToRemove) - 1);
            if (discountMap.get(itemToRemove) == 0) {
                discountMap.remove(itemToRemove);
            }

            discountMap.put(itemToAdd, discountMap.getOrDefault(itemToAdd, 0) + 1);
            if (canBuy(wantMap, discountMap)) {
                wantSum++;
            }
        }

        return wantSum;
    }
    private static boolean canBuy(Map<String, Integer> wantMap, Map<String, Integer> discountMap) {
        for (String key : wantMap.keySet()) {
            if (wantMap.get(key) > discountMap.getOrDefault(key, 0)) return false;
        }
        return true;
    }
}
