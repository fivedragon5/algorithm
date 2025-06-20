package code.programmers.study;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/176962
 *
 * 제한)
 *  3 ≤ plans의 길이 ≤ 1,000
 *      plans의 원소는 [name, start, playtime]의 구조로 이루어져 있습니다.
 *          name : 과제의 이름을 의미합니다.
 *          2 ≤ name의 길이 ≤ 10
 *          name은 알파벳 소문자로만 이루어져 있습니다.
 *          name이 중복되는 원소는 없습니다.
 *      start : 과제의 시작 시각을 나타냅니다.
 *          "hh:mm"의 형태로 "00:00" ~ "23:59" 사이의 시간값만 들어가 있습니다.
 *          모든 과제의 시작 시각은 달라서 겹칠 일이 없습니다.
 *          과제는 "00:00" ... "23:59" 순으로 시작하면 됩니다. 즉, 시와 분의 값이 작을수록 더 빨리 시작한 과제입니다.
 *      playtime : 과제를 마치는데 걸리는 시간을 의미하며, 단위는 분입니다.
 *          1 ≤ playtime ≤ 100
 *          playtime은 0으로 시작하지 않습니다.
 *      배열은 시간순으로 정렬되어 있지 않을 수 있습니다.
 *  진행중이던 과제가 끝나는 시각과 새로운 과제를 시작해야하는 시각이 같은 경우 진행중이던 과제는 끝난 것으로 판단합니다.
 *
 * 문제)
 *  1. 과제명, 과제의 시작시간, 과제를 완료하는데에 걸리는 시간 이 주어집니다.
 *  2. 과제를 진행 하던 중 다른 과제의 시작시간이 왔을 경우 새로운 과제를 진행합니다.
 *  3. 과제를 완료했을 경우 멈춰둔 과제를 시작합니다.(멈춰둔 과제가 여러개일 경우 가장 최근에 멈춘 과제를 먼저 시작)
 *  4. 과제가 끝난 순서대로 이름을 배열에 담아 반환하기
 *
 * 풀이)
 *  구현
 *  1. 과제 Class를 생성하여 과제의 이름, 시작시간, 남은시간을 저장
 *  2. 과제의 시작시간을 기준으로 오름차순 정렬
 *  3. 과제를 순회하며 과제를 진행
 *  4. 과제를 진행하며 남은 과제가 있는 경우 남은 과제를 확인하여 다음 과제를 진행
 *  5. 과제를 진행하며 남은 과제가 없는 경우 다음 과제를 진행
 *
 */
class Lesson176962 {
    public static void main(String[] args) {
        String[][] plans4 = {
                {"A", "12:00", "30"}, {"B", "12:10", "20"}, {"C", "15:00", "40"}, {"D", "15:10", "30"}
        };
        System.out.println(solution(plans4));

        String[][] a =  {{"aaa", "00:00", "1"}, {"bbb", "00:01", "2"}, {"ccc", "00:02", "2"}};
        System.out.println(solution(a));

        String[][] plans6 = { { "A", "00:00", "60" } , { "B", "00:10", "60" } , { "C", "00:20", "60" } , { "D", "02:20", "60" } , { "E", "03:20", "10" } , { "F", "03:40", "20" } , { "G", "04:40", "60" } };
        System.out.println(solution(plans6));

        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        System.out.println(solution(plans));

        String[][] plans2 = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        System.out.println(solution(plans2));

        String[][] plans3 = {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
        System.out.println(solution(plans3));
    }

    public static String[] solution(String[][] plans) {
        List<String> completedSubjectNames = new ArrayList<>();
        int N = plans.length;
        List<Subject> subjectList = new ArrayList<>();
        for (String[] plan : plans) {
            String[] startTime = plan[1].split(":");
            int hour = Integer.parseInt(startTime[0]);
            int minute = Integer.parseInt(startTime[1]);
            subjectList.add(new Subject(plan[0], hour * 60 + minute, Integer.parseInt(plan[2])));
        }

        // subjectList의 startMinute을 기준으로 오름차순 정렬
        subjectList.sort(Comparator.comparingInt(s -> s.startMinute));
        Stack<Subject> remainSubjects = new Stack<>();
        Subject currentSubject = subjectList.get(0);
        int nextSubjectIndex = 1;
        int nowMinute = currentSubject.startMinute;

        while (completedSubjectNames.size() < N && nextSubjectIndex < N) {
            Subject nexstSubject = subjectList.get(nextSubjectIndex);
            // 현재 과제를 다음 과제 시작 전까지 완료할 수 있는 경우
            if (nowMinute + currentSubject.remainingMinute <= nexstSubject.startMinute) {
                nowMinute += currentSubject.remainingMinute;
                completedSubjectNames.add(currentSubject.name);
                if (!remainSubjects.isEmpty()) {
                    // 남아있는 과제가 있는경우 다음 과제 시간과 남아있는 과제를 확인해야한다.
                    while (!remainSubjects.isEmpty()) {
                        Subject remainSubject = remainSubjects.pop();
                        if (nowMinute + remainSubject.remainingMinute <= nexstSubject.startMinute) {
                            nowMinute += remainSubject.remainingMinute;
                            completedSubjectNames.add(remainSubject.name);
                        }
                        else {
                            remainSubject.remainingMinute -= nexstSubject.startMinute - nowMinute;
                            remainSubjects.add(remainSubject);
                            break;
                        }
                    }
                }
            }
            // 현재 과제를 다음 과제 시작 전까지 완료 할 수 없는 경우
            else {
                currentSubject.remainingMinute -= nexstSubject.startMinute - nowMinute;
                remainSubjects.add(currentSubject);
            }
            nowMinute = nexstSubject.startMinute;
            currentSubject = nexstSubject;
            nextSubjectIndex++;
        }

        completedSubjectNames.add(currentSubject.name);
        while (!remainSubjects.isEmpty()) {
            completedSubjectNames.add(remainSubjects.pop().name);
        }
        String[] answer = new String[plans.length];
        for (int i = 0; i < completedSubjectNames.size(); i++) {
            answer[i] = completedSubjectNames.get(i);
        }
        return answer;
    }

    static class Subject {
        private final String name;
        private final int startMinute;
        private int remainingMinute;

        Subject(String name, int startMinute, int remainingMinute) {
            this.name = name;
            this.startMinute = startMinute;
            this.remainingMinute = remainingMinute;
        }
    }
}
