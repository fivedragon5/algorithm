package algorithm.code.programmers;

import java.util.Stack;

class Lesson76502 {
    static int solution(String s) {
        int answer = 0;
        s = s.replaceAll("\"","");
        int sLength = s.length();
        Stack<Character> stack = new Stack<>();
        boolean isFlag = true;
        char ch;
        for(int i = 0; i < sLength; i++) {
            isFlag = true;
            for(int j = 0; j < sLength; j++) {
                ch = s.charAt((i+j)%sLength);
                if ('{' == ch || '[' == ch || '(' == ch) {
                    stack.add(ch);
                }
                else if(stack.size() <= 0) {
                    isFlag = false;
                    break;
                }
                else if ('}' == ch) {
                    if(stack.pop() != '{') isFlag = false;
                }
                else if (']' == ch){
                    if(stack.pop() != '[') isFlag = false;
                }
                else if (')' == ch){
                    if(stack.pop() != '(') isFlag = false;
                }
                if (!isFlag) {
                    isFlag = false;
                    break;
                }
            }
            if(isFlag && stack.size() == 0) answer++;
            stack.clear();
        }
        return answer;
    }

    public static void main(String[] args) {
        /*
            "[](){}" 3
            "}]()[{" 2
            "[)(]"	 0
            "}}}"	 0
         */
        String parameter = "\"[(])\"";
        System.out.println("===START===");
        System.out.println(solution(parameter));
        System.out.println("===END===");
    }
}
