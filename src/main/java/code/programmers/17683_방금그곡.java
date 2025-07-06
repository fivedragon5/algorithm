package code.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17683
 *
 * 제한)
 * 입력으로 네오가 기억한 멜로디를 담은 문자열 m과 방송된 곡의 정보를 담고 있는 배열 musicinfos가 주어진다.
 *  m은 음 1개 이상 1439개 이하로 구성되어 있다.
 *  musicinfos는 100개 이하의 곡 정보를 담고 있는 배열로, 각각의 곡 정보는 음악이 시작한 시각, 끝난 시각, 음악 제목, 악보 정보가 ','로 구분된 문자열이다.
 *  음악의 시작 시각과 끝난 시각은 24시간 HH:MM 형식이다.
 *  음악 제목은 ',' 이외의 출력 가능한 문자로 표현된 길이 1 이상 64 이하의 문자열이다.
 *  악보 정보는 음 1개 이상 1439개 이하로 구성되어 있다.
 *
 * 문제)
 *  네오가 찾으려는 음악의 제목을 구하기 위해 다음과 같은 조건을 만족하는 음악 제목을 반환한다.
 *  1. 방금그곡 서비스에서는 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공한다.
 *  2. 네오가 기억한 멜로디와 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개이다.
 *  3. 각 음은 1분에 1개씩 재생된다. 음악은 반드시 처음부터 재생되며 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복해서 재생된다.
 *     음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생된다.
 *  4. 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
 *  5. 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다.
 *     재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
 *  6. 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환한다.
 *
 * 풀이)
 *  1. 주어진 musicinfos 배열을 순회하며 각 곡의 title, melody를 추출한다.
 *   - melody를 추출할 때, 재생 시간만큼 반복 재생된 멜로디를 저장한다.
 *   - melody에서 '#'이 있는 음을 소문자로 변환하여 저장한다.
 *  2. 주어진 m에서 '#'이 있는 음을 소문자로 변환하여 저장한다.
 *  3. 각 곡의 melody가 m을 포함하는지 확인한다.
 *   - 포함할 경우 해당 곡의 title과 melody 길이를 비교하여 가장 긴 곡을 찾는다.
 *   - **길이가 같을 경우 먼저 입력된 곡의 title을 반환.**
 *      - 순서대로 탐색하기에 길이가 같을 경우 먼저 입력된 곡이 우선적으로 선택된다.
 *  4. 조건에 맞는 곡이 없을 경우 "(None)"을 반환한다.
 *
 */
class Lesson17683 {
    public static void main(String[] args) {
        String m3 = "ABC#D";
        String[] musicinfos3 = {
                "04:00,04:03,NAME,ABC#D"
        };
        System.out.println(solution(m3, musicinfos3)); //

        String m4 = "ABC#";
        String[] musicinfos4 = {
                "04:00,04:03,NAME,ABC#D"
        };
        System.out.println(solution(m4, musicinfos4)); //

        String m = "ABCDEFG";
        String[] musicinfos = {
            "12:00,12:14,HELLO,CDEFGAB",
            "13:00,13:05,WORLD,ABCDEF"
        };
        System.out.println(solution(m, musicinfos)); // "HELLO"

        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {
                "03:00,03:30,FOO,CC#B",
                "04:00,04:08,BAR,CC#BCC#BCC#B"
        };
        System.out.println(solution(m2, musicinfos2)); // "HELLO"
    }

    public static String solution(String m, String[] musicinfos) {
        int len = musicinfos.length;
        String[] titles = new String[len];
        String[] melodies = new String[len];
        for (int i = 0; i < len; i++) {
            String[] split = musicinfos[i].split(",");
            String start = split[0];
            String end = split[1];
            titles[i] = split[2];
            melodies[i] = getMelody(replaceSharp(split[3]), getTimeInMinutes(end) - getTimeInMinutes(start));
        }
        m = replaceSharp(m);
        String answer = "(None)";
        int answerMelodyLength = 0;
        for (int i = 0; i < len; i++) {
            if (melodies[i].contains(m)) {
                if (answerMelodyLength < melodies[i].length()) {
                    answerMelodyLength = melodies[i].length();
                    answer = titles[i];
                }
            }
        }
        return answer;
    }

    private static int getTimeInMinutes(String time) {
        String[] split = time.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);
        return hours * 60 + minutes;
    }

    private static String getMelody(String melody, int playTime) {
        StringBuilder sb = new StringBuilder();
        int melodyLength = melody.length();
        for (int i = 0; i < playTime; i++) {
            sb.append(melody.charAt(i % melodyLength));
        }
        return sb.toString();
    }

    private static String replaceSharp(String melody) {
        return melody.replace("C#", "c")
                     .replace("D#", "d")
                     .replace("F#", "f")
                     .replace("G#", "g")
                     .replace("A#", "a")
                     .replace("B#", "b");
    }
}
