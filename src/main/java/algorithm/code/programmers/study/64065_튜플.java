package algorithm.code.programmers.study;

import java.util.HashMap;

class Lesson64065 {
    static int[] solution(String s) {
        HashMap<Integer, String> tupleList = new HashMap<>();
        int left = 1;
        int right = 1;

        for(int i = 1 ; i < s.length() - 1 ; i ++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                left = i;
            }
            else if (ch == '}') {
                right = i;
                String tuple = s.substring(left+1, right);
                tupleList.put((tuple.split(",").length), tuple);
            }
        }

        int[] answer = new int[tupleList.size()];

        for(int i = 1 ; i <= tupleList.size() ; i ++) {
            String temp = tupleList.get(i).replaceAll(",","");
            answer[i-1] = Integer.parseInt(temp);
            for(int j = i+1 ; j <= tupleList.size() ; j ++) {
                //tupleList.put(j, tupleList.get(j).replaceFirst(Integer.toString(answer[i-1]), ""));
                tupleList.put(j, tupleList.get(j).replaceFirst(temp, ""));
            }
        }
        return answer;
    }

    



    public static void main(String[] args) {
        //2, 3, 1, 2
        //String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        String s = "{20,111},{111}}";
        System.out.println("===START===");
        System.out.println(solution(s));
        System.out.println("===END===");
    }
}
