package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 문제)
 * 1.먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다.
 *   만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정한다.
 * 2.만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
 * 3.어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.
 * 4.위의 규칙을 첫 번째 옵션부터 N번째 옵션까지 차례대로 적용한다.
 * 
 * 접근)
 * 구현 문제
 * 
 * 풀이)
 * 1. 단축키 지정을 map에 저장
 * 2. 옵션1확인후 옵션2 확인
 * 
 * 제한)
 * 1 ≤ N ≤ 30
 * 하나의 옵션 5개 이하의 단어
 * 각 단어 10개 이하의 알파벳
 */
class Problem1283 {

    static HashMap<Character, Boolean> map = new HashMap<>();
    static LinkedList<String> answerList = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());

        String[] array = new String[N];

        for (int i = 0; i < N; i++) {
            array[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            String[] strArray = array[i].split(" ");
            if (option(strArray)) {
            }
            else {
                option2(strArray);
            }
        }

        for (String answer : answerList) {
            System.out.println(answer);
        }
    }

    static boolean option(String[] strArray) {

        for (int i = 0; i < strArray.length; i++) {
            Character c = strArray[i].toLowerCase().charAt(0);
            if(!map.getOrDefault(c, false)) {
                map.put(c, true);
                StringBuilder sbb = new StringBuilder();
                for (int p = 0; p < strArray.length; p++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(strArray[p] + " ");
                    if (i == p) {
                        sb.insert(0, "[");
                        sb.insert(2, "]");
                    }
                    sbb.append(sb);
                }
                sbb.delete(sbb.length()-1, sbb.length());
                answerList.add(sbb.toString());
                return true;
            }
        }
        return false;
    }

    static void option2(String[] strArray) {
        boolean isflag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            if (isflag) {
                sb.append(strArray[i] + " ");
                continue;
            }
            for (int j = 0; j < strArray[i].length(); j++) {
                Character c = strArray[i].charAt(j);
                if(!isflag && !map.getOrDefault(Character.toLowerCase(c), false)) {
                    isflag = true;
                    map.put(Character.toLowerCase(c), true);
                    sb.append("[");
                    sb.append(c);
                    sb.append("]");
                }
                else {
                    sb.append(c);
                }
            }
            sb.append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        answerList.add(sb.toString());
    }
}


/*
5
New
Open
Save
Save As
Save All


[N]ew
[O]pen
[S]ave
Save [A]s
Sa[v]e All

2
a b bcd
a a acd
 */
