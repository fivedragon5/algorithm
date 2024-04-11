package code.programmers.normal;

import java.util.ArrayList;
import java.util.List;

class Lesson67257 {

    static Long max = Long.valueOf(0);

    static Long solution(String expression) {
        List<Long> number = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
        int expressionStack = 0;

        for(int i = 0 ; i < expression.length() ; i ++) {
            char expressionChar = expression.charAt(i);
            if(expressionChar == '*' || expressionChar == '-' || expressionChar == '+') {
                operator.add(expressionChar);
                number.add(Long.parseLong(expression.substring(expressionStack, i)));
                expressionStack = i+1;
            }
        }
        number.add(Long.parseLong(expression.substring(expressionStack, expression.length())));

        List<Character> currentOperator = new ArrayList<>();

        currentOperator.add('*');
        currentOperator.add('+');
        currentOperator.add('-');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        currentOperator.clear();
        currentOperator.add('*');
        currentOperator.add('-');
        currentOperator.add('+');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        currentOperator.clear();
        currentOperator.add('-');
        currentOperator.add('+');
        currentOperator.add('*');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        currentOperator.clear();
        currentOperator.add('-');
        currentOperator.add('*');
        currentOperator.add('+');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        currentOperator.clear();
        currentOperator.add('+');
        currentOperator.add('-');
        currentOperator.add('*');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        currentOperator.clear();
        currentOperator.add('+');
        currentOperator.add('*');
        currentOperator.add('-');
        gop(new ArrayList(number), new ArrayList(operator), currentOperator);

        return max;
    }

    static void gop(List<Long> oriNumber, List<Character> oriOperator, List<Character> currentOperator) {

        List<Long> numberList = oriNumber;
        List<Character> operatorList = oriOperator;

//        System.out.println(numberList.toString());
//        System.out.println(operatorList.toString());
//        System.out.println("current : " + currentOperator.get(0));

        for (int i = 0 ; i < oriOperator.size() ; i++) {
            if (oriOperator.get(i) == '*' && currentOperator.get(0) == '*') {
                long gop = oriNumber.get(i) * oriNumber.get(i+1);
                numberList.remove(i+1);
                numberList.set(i, gop);
                operatorList.remove(i);
                i--;
            }
            else if (oriOperator.get(i) == '+' && currentOperator.get(0) == '+') {
                long gop = oriNumber.get(i) + oriNumber.get(i+1);
                numberList.remove(i+1);
                numberList.set(i, gop);
                operatorList.remove(i);
                i--;
            }
            else if (oriOperator.get(i) == '-' && currentOperator.get(0) == '-') {
                long gop = oriNumber.get(i) - oriNumber.get(i+1);
                numberList.remove(i+1);
                numberList.set(i, gop);
                operatorList.remove(i);
                i--;
            }
        }
        currentOperator.remove(0);
        if (currentOperator.isEmpty()){
            //System.out.println(Math.abs(numberList.get(0)) + " end");
            if (Math.abs(numberList.get(0)) > max) {
                max = Math.abs(numberList.get(0));
            }
        }
        else {
            gop(numberList, operatorList, currentOperator);
        }
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";

        System.out.println("===START===");
        System.out.println(solution(expression));
        System.out.println("===END===");
    }
}
