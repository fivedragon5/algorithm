package algorithm.code.programmers.soon;

import java.util.LinkedList;

/**
 * 제한)
 * 1 ≤ commands의 길이 ≤ 1,000
 * 1 <= value.length <= 10
 * 1 <= r1, c1 <= 50
 */
class Lesson150366 {

    static LinkedList<String> answerList = new LinkedList<>();
    static String[][] table = new String[51][51];

    static String[] solution(String[] commands) {

        for (int i = 0; i < commands.length; i++) {
            processCommand(commands[i].split(""));
        }

        String[] answer = answerList.toArray(new String[answerList.size()]);

        return answer;
    }

    static void processCommand(String[] command) {

        if (command[0].equals("UPDATE")) {
            //셀 값 변경
            if (command.length == 3) {
                for (int i = 1; i < 51; i++) {
                    for (int j = 1; j < 51; j++) {
                        if (command[1].equals(table[i][j])) {
                            table[i][j] = command[2];
                        }
                    }
                }
            }
            //셀 값 입력
            //병합 되어 있으면 전부 변경 해야함.
            else {
                table[Integer.parseInt(command[1])][Integer.parseInt(command[2])] = command[3];
            }
        }
        //셀 합치기
        else if (command[0].equals("MERGE")) {

        }
        //셀 분리
        else if (command[0].equals("UNMERGE")) {

        }
        //문자 출력
        else {
            answerList.add(table[Integer.parseInt(command[1])][Integer.parseInt(command[2])]);
        }

    }

    public static void main(String[] args) {
        String[] commands = {
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",
                "MERGE 1 2 1 3",
                "MERGE 1 3 1 4",
                "UPDATE korean hansik",
                "UPDATE 1 3 group",
                "UNMERGE 1 4",
                "PRINT 1 3",
                "PRINT 1 4"
        };
        System.out.println("===START===");
        System.out.println(solution(commands));
        System.out.println("===END===");

        String[] commands2 = {
                "UPDATE 1 1 a",
                "UPDATE 1 2 b",
                "UPDATE 2 1 c",
                "UPDATE 2 2 d",
                "MERGE 1 1 1 2",
                "MERGE 2 2 2 1",
                "MERGE 2 1 1 1",
                "PRINT 1 1",
                "UNMERGE 2 2",
                "PRINT 1 1"
        };
    }
}
