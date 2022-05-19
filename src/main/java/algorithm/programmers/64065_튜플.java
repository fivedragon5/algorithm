package algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Lesson64065 {
    static int[] solution(String s) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, String> tupleList = new HashMap<>();
        int left = 1;
        int right = 1;

        for(int i = 1 ; i < s.length() - 1 ; i ++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                left = i;
            }
            else if (ch == '}') {
                //수정 필요 length X
                right = i;
                String tuple = s.substring(left+1, right);
                tupleList.put((tuple.length()/2)+1, tuple);
            }
        }

        System.out.println(tupleList.toString());

        int[] answer = new int[tupleList.size()];

        for(int i = 1 ; i <= tupleList.size() ; i ++) {
            answer[i-1] = Integer.parseInt(tupleList.get(i).replaceAll(",",""));
            for(int j = i+1 ; j <= tupleList.size() ; j ++) {
                String a = tupleList.get(j);
                String b = a.replace(tupleList.get(i), "");
                tupleList.put(j, b);
            }
        }

        System.out.println(Arrays.toString(answer));

        return answer;

    }

    public static void main(String[] args) {
        //2, 3, 1, 2
        //String s = "{{1,3,2},{2},{2,1,3,2},{3,2}}";
        String s = "{20,111},{111}}";
        System.out.println("===START===");
        System.out.println(solution(s));
        System.out.println("===END===");
    }
}
