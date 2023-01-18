package algorithm.code.programmers.study;

/**
 * 접근)
 * 포화 이진트리의 노드의 갯수 : 2^높이 - 1
 *  1. 주어진 수를 이진수로 변환
 *  2. 포화 이진트리로 변환
 *  3. 해당 포화 이진트리의 조건에 맞는지 확인
 * 
 * 풀이)
 *  1. 주어진 배열 numbers를 이진수로 변환 toStr()
 *  2. 이진수로 변환 하면서 포화 이진트리가 되도록 0을 앞에 붙여줌
 *  3. 변환된 포화 이진트리를 재귀 함수에 호출 chkeck()
 *   - 루트 노드가 1일경우
 *    - 루트노드의 자식노드들(왼쪽, 오른쪽)을 각각 재귀
 *   - 루트 노드가 0일경우
 *    - 자식중에 하나라도 1이 있으면 이진트리로 변환 불가능하기 떄문에 0 리턴
 *   - 리프노드일 경우 종료
 * 제한)
 * 1 ≤ numbers의 길이 ≤ 10,000
 * 1 ≤ numbers의 원소 ≤ 10^15
 */
class Lesson150367 {
    static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = check(toStr(numbers[i]));
        }
        return answer;
    }

    static int check(String str) {
        int mid = str.length() / 2;
        if (mid == 0) {
            return 1;
        }
        char ch = str.charAt(mid);
        if (ch == '1') {
            if (check(str.substring(0, mid)) == 0 || check(str.substring(mid + 1)) == 0) return 0;
        }
        else {
            if (str.contains("1")) return 0;
        }
        return 1;
    }

    static String toStr(long num) {
        int size = 1;
        int pow = 2;
        String numToBinaryString = Long.toBinaryString(num);
        int numSize = numToBinaryString.length();

        while (numSize > size) {
            size = (int) Math.pow(2, pow++) - 1;
        }

        int addSize = size - numSize;

        StringBuilder sb = new StringBuilder(numToBinaryString);
        for (int i = 0; i < addSize; i++) {
            sb.insert(0,'0');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
//        long[] numbers = {63, 111, 95};
        System.out.println("===START===");
        System.out.println(solution(numbers));
        System.out.println("===END===");
    }
}
