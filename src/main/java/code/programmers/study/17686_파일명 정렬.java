package code.programmers.study;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17686
 *
 * 제한)
 *  files는 1000 개 이하의 파일명을 포함하는 문자열 배열이다.
 *  각 파일명은 100 글자 이하 길이로, 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")만으로 이루어져 있다.
 *      파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
 *  중복된 파일명은 없으나, 대소문자나 숫자 앞부분의 0 차이가 있는 경우는 함께 주어질 수 있다.
 *      (muzi1.txt, MUZI1.txt, muzi001.txt, muzi1.TXT는 함께 입력으로 주어질 수 있다.)
 *
 * 문제)
 *  1. 파일명은 HEAD, NUMBER, TAIL로 구성되어 있다.
 *      - HEAD : 숫자가 아닌 문자로 이루어져 있으며, 최소한 1글자 이상이다.
 *      - NUMBER : 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며, 앞쪽에 0이 올 수 있다.
 *                 0 ~ 99999 사이의 숫자로, 00000 이나 0101 등도 가능
 *      - TAIL : HEAD와 NUMBER 다음에 오는 나머지 문자로, 여기에는 숫자가 다시 나타라 수 도 있으며, 아무 글자도 없을 수 있다.
 *  2. 파일명을 세 부분으로 나눈 후, 다음 기준에 따라 파일명을 정렬한다.
 *      - HEAD를 기준으로 사전순으로 정렬한다. (대소문자 구분하지 않음)
 *      - 파일명의 HEAD가 같을 경우, NUMBER를 기준으로 오름차순 정렬한다.
 *          - ex) 9 < 10 < 0011 < 012 < 13 < 014 | 숫자 앞에 0은 무시
 *      - HEAD, NUMBER가 모두 같을 경우, 원래 입력에 주어진 수선를 유지한다.
 *
 * 풀이)
 *  1. 주어진 files 변수를 String parts[][]로 변환
 *   - parts[i][0] = HEAD, parts[i][1] = NUMBER, parts[i][2] = TAIL
 *  2. 문제의 주어진 조건대로 정렬 진행
 *   - HEAD를 기준으로 사전순 정렬 (대소문자 구분하지 않음 CompareToIgnoreCase 사용)
 *   - HEAD가 같을 경우 NUMBER를 기준으로 오름차순 정렬
 *      - NUMBER는 Integer.parseInt()를 사용해 숫자로 변환 후 비교
 *      - ex) 01 -> 1로 변환, 0011 -> 11로 변환
 *  3. 정렬된 parts를 다시 원래 파일명으로 변환하여 해서 반환
 */
class Lesson17686 {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(solution(files));

        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(solution(files2));
    }

    public static String[] solution(String[] files) {
        int len = files.length;
        String[][] parts = new String[len][3];
        for (int i = 0; i < len; i++) {
            String file = files[i];
            String[] parsed = parseFileName(file);
            parts[i][0] = parsed[0]; // HEAD
            parts[i][1] = parsed[1]; // NUMBER
            parts[i][2] = parsed[2]; // TAIL
        }

        // 정렬
        Arrays.sort(parts, (a, b) -> {
            // 1. HEAD 비교
            int headCompare = a[0].compareToIgnoreCase(b[0]);
            if (headCompare != 0) {
                return headCompare;
            }
            // 2. NUMBER 비교
            int numberA = Integer.parseInt(a[1]);
            int numberB = Integer.parseInt(b[1]);
            return Integer.compare(numberA, numberB);
        });

        // 정렬된 결과를 원래 파일명으로 변환
        String[] answer = new String[len];
        for (int i = 0; i < len; i++) {
            answer[i] = parts[i][0] + parts[i][1] + parts[i][2];
        }
        return answer;
    }

    private static String[] parseFileName(String file) {
        int len = file.length();
        String head = "";
        String number = "";
        String tail = "";

        int index = 0;
        while (index < len && !Character.isDigit(file.charAt(index))) {
            head += file.charAt(index);
            index++;
        }

        while (index < len && Character.isDigit(file.charAt(index))) {
            number += file.charAt(index);
            index++;
        }

        if (index < len) {
            tail = file.substring(index);
        }

        return new String[]{head, number, tail};
    }
}
