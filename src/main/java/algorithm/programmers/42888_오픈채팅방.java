package algorithm.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Lesson42888 {
    static String[] solution(String[] record) {
        List<String> answerList = new ArrayList<>();
        List<String[]> resultList = new ArrayList<>();
        HashMap<String, String> memberChangeTable = new HashMap<>();

        for(String row : record) {
            String[] splitStr = row.split(" ");

            if(splitStr[0].equals("Change")) memberChangeTable.put(splitStr[1], splitStr[2]);

            else {
                resultList.add(new String[]{splitStr[0], splitStr[1]});
                if(splitStr[0].equals("Enter")) {
                    memberChangeTable.put(splitStr[1], splitStr[2]);
                }
            }
        }

        for(String[] arr : resultList) {
            String str = "";
            if(arr[0].equals("Enter")) {
                str = memberChangeTable.get(arr[1]) + "님이 들어왔습니다.";
            }
            else if(arr[0].equals("Leave")) {
                str = memberChangeTable.get(arr[1]) + "님이 나갔습니다.";
            }
            answerList.add(str);
        }

        String[] answer = new String[answerList.size()];
        for(int i = 0 ; i < answer.length ; i ++)
            answer[i] = answerList.get(i);

        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println("===START===");
        System.out.println(solution(record) + "??");
        System.out.println("===END===");
    }
}
